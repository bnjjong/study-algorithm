package io.jjong.algorithm.resilience

import kotlin.collections.mutableListOf
import kotlin.math.max

/**
 * [응용 복습 #2] 키별(per-user) Sliding Window Rate Limiter
 *
 * 기존 [SlidingWindowLogRateLimiter]는 "전체"에 하나의 한도였다.
 * 실무에선 **사용자(API 키)마다 따로** 제한해야 한다 — A가 폭주해도 B는 멀쩡해야 한다.
 *
 * 규칙: 같은 [key]의 "직전 [windowMillis] 구간" 요청 수가 [maxRequests] 미만이면 허용(true), 아니면 차단(false).
 *   - allow("userA", now) 와 allow("userB", now)는 서로 독립.
 *   - 처음 보는 키도 당연히 바로 허용된다(빈 윈도우에서 시작).
 *
 * 힌트: 단일 윈도우(ArrayDeque<Long>) 하나를 → 키별로 갖는다. `Map<String, ArrayDeque<Long>>`.
 *       기존 SlidingWindowLog 로직을 "그 키의 큐"에 그대로 적용하면 된다.
 *
 * 💬 면접 심화(테스트엔 없음): 오래 안 쓴 키의 빈 큐가 Map에 계속 쌓이면 메모리 누수.
 *    → 큐가 비면 키 제거, 또는 TTL/주기적 청소 같은 전략을 말할 수 있으면 가산점.
 */
class KeyedSlidingWindowRateLimiter(
    private val maxRequests: Int,
    private val windowMillis: Long,
) {
    private val bucket = HashMap<String, ArrayDeque<Long>>()

    fun allow(key: String, now: Long): Boolean {
        val logs = bucket.getOrPut(key) { ArrayDeque() }
        evictExpired(logs, now)

        if (logs.size >= maxRequests) return false
        logs.addLast(now)
        return true
    }

    /** 비활성 키 정리. 주기적으로 호출하면 메모리 누수 방지. */
    fun cleanup(now: Long) {
        val threshold = now - windowMillis
        bucket.entries.removeAll { (_, logs) ->
            while (logs.isNotEmpty() && logs.first() < threshold) logs.removeFirst()
            logs.isEmpty()
        }
    }

    private fun evictExpired(logs: ArrayDeque<Long>, now: Long) {
        val threshold = now - windowMillis
        while (logs.isNotEmpty() && logs.first() < threshold) {
            logs.removeFirst()
        }
    }
}
