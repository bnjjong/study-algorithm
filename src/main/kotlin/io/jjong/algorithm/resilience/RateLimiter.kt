package io.jjong.algorithm.resilience

/**
 * Rate Limiter — 일정 시간 동안 허용되는 요청 수를 제한해 서버를 보호하는 장치.
 *
 * 모든 구현은 요청이 들어올 때 [allow]를 호출해 **통과(true) / 차단(false)** 만 판단한다.
 * 시간을 인자 [now]로 받는 이유: 실제 시스템 시각(`System.currentTimeMillis()`)에 의존하면
 * 테스트가 "진짜 시간을 기다려야" 하므로, 시각을 주입해 결정적(deterministic) 테스트를 가능케 한다.
 * (면접에서도 "시간을 어떻게 다룰지"는 좋은 토론거리 — 시계를 추상화하면 테스트가 쉬워진다.)
 *
 * 한 사용자(키)당 하나의 리미터가 필요하므로, 실무에서는 보통 `Map<UserId, RateLimiter>`로 관리한다.
 * 멀티스레드 환경이라면 [allow] 내부 상태 변경을 `synchronized`로 감싸거나 원자적 연산으로 보호해야 한다.
 */
interface RateLimiter {
    /** [now](epoch millis) 시점의 요청을 허용하면 true, 한도를 초과해 차단하면 false. */
    fun allow(now: Long): Boolean
}

/**
 * Fixed Window(고정 윈도우) — 가장 단순한 방식. 면접의 출발점으로 적합하다.
 *
 * 벽시계에 **정렬된** [windowMillis] 길이의 창(예: 1초 제한이면 [0,1000), [1000,2000)…)을 두고,
 * 그 안의 요청을 [maxRequests]개까지만 허용한다. 창이 바뀌면 카운터를 0으로 리셋한다.
 * 어떤 시각 [now]가 속한 창의 시작은 `now - (now % windowMillis)`로 구한다.
 *
 * ⚠️ 약점(면접 단골 지적): 창 **경계**에서 한도가 샌다. 예를 들어 1초/100개 제한일 때
 * 0.999초에 100개 + 1.000초에 100개가 들어오면, 약 0.001초 사이에 200개가 통과한다.
 * (두 요청이 서로 다른 창에 속해 각각 한도를 꽉 채우기 때문.)
 * 이 약점을 인지하고 [SlidingWindowLogRateLimiter]로 개선하는 흐름을 보여주는 게 핵심.
 */
class FixedWindowRateLimiter(
    private val maxRequests: Int,
    private val windowMillis: Long,
) : RateLimiter {
    private var windowStart = Long.MIN_VALUE
    private var count = 0

    override fun allow(now: Long): Boolean {
        // now가 속한 (정렬된) 창의 시작 시각. now >= 0(epoch millis) 가정.
        val currentWindowStart = now - (now % windowMillis)
        if (currentWindowStart != windowStart) {
            windowStart = currentWindowStart
            count = 0
        }
        if (count < maxRequests) {
            count++
            return true
        }
        return false
    }
}

/**
 * Sliding Window Log(슬라이딩 윈도우 로그) — 정확하지만 메모리를 더 쓰는 방식.
 *
 * 요청이 들어온 **시각을 모두 큐에 기록**하고, 매 판단마다 "지금부터 과거 [windowMillis]" 밖으로
 * 밀려난 시각을 앞에서 제거한다. 남은 개수가 [maxRequests] 미만이면 허용한다.
 * Fixed Window의 경계 누수 문제가 없다 — 항상 "직전 [windowMillis] 구간"을 정확히 본다.
 *
 * 트레이드오프: 윈도우 안의 요청 수만큼 타임스탬프를 들고 있어야 하므로 **메모리가 요청량에 비례**한다.
 * 이게 부담되면 [SlidingWindowCounterRateLimiter](카운터 2개로 근사)나 [TokenBucketRateLimiter]를 쓴다.
 */
class SlidingWindowLogRateLimiter(
    private val maxRequests: Int,
    private val windowMillis: Long,
) : RateLimiter {
    // 오래된 것은 앞(first), 최신은 뒤(last). 양끝 추가/삭제가 O(1)인 ArrayDeque가 적합.
    private val timestamps = ArrayDeque<Long>()

    override fun allow(now: Long): Boolean {
        // 윈도우 경계 밖(now - windowMillis 이전)의 기록을 앞에서 모두 비운다.
        val boundary = now - windowMillis
        while (timestamps.isNotEmpty() && timestamps.first() <= boundary) {
            timestamps.removeFirst()
        }
        if (timestamps.size < maxRequests) {
            timestamps.addLast(now)
            return true
        }
        return false
    }
}

/**
 * Token Bucket(토큰 버킷) — 실무 API에서 가장 널리 쓰이는 방식.
 *
 * 버킷에 초당 [refillTokensPerSecond]개씩 토큰이 차오르고(최대 [capacity]까지), 요청은 토큰 1개를 소비한다.
 * 토큰이 없으면 차단한다. 평소에 쌓아둔 토큰 덕에 **순간 버스트를 [capacity]개까지 허용**하면서도
 * 장기적으로는 충전 속도만큼만 통과시켜 **평균 속도를 제한**한다.
 *
 * 충전은 "타이머로 주기적으로"가 아니라, 요청이 올 때마다 **경과 시간만큼 한꺼번에(lazy)** 계산한다.
 * 별도 스레드 없이 O(1)로 동작하는 게 장점.
 */
class TokenBucketRateLimiter(
    private val capacity: Double,
    private val refillTokensPerSecond: Double,
) : RateLimiter {
    private var tokens = capacity // 가득 찬 상태로 시작 (초기 버스트 허용)
    private var lastRefillMillis = Long.MIN_VALUE

    override fun allow(now: Long): Boolean {
        refill(now)
        if (tokens >= 1.0) {
            tokens -= 1.0
            return true
        }
        return false
    }

    /** [now]까지 흐른 시간만큼 토큰을 채운다(상한은 [capacity]). 첫 호출은 기준 시각만 잡고 끝낸다. */
    private fun refill(now: Long) {
        if (lastRefillMillis == Long.MIN_VALUE) {
            lastRefillMillis = now
            return
        }
        val elapsedMillis = now - lastRefillMillis
        if (elapsedMillis <= 0) return // 시간 역행/동일 시각이면 충전하지 않음
        val refilled = elapsedMillis / 1000.0 * refillTokensPerSecond
        tokens = minOf(capacity, tokens + refilled)
        lastRefillMillis = now
    }
}
