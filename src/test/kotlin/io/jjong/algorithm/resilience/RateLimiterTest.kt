package io.jjong.algorithm.resilience

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RateLimiterTest {

    // --- Fixed Window ---

    @Test
    fun `Fixed Window - 윈도우 안에서는 한도까지 허용하고 초과분은 차단한다`() {
        val limiter = FixedWindowRateLimiter(maxRequests = 3, windowMillis = 1000)
        assertTrue(limiter.allow(0))   // 1
        assertTrue(limiter.allow(100)) // 2
        assertTrue(limiter.allow(200)) // 3
        assertFalse(limiter.allow(300)) // 4 → 한도 초과
    }

    @Test
    fun `Fixed Window - 윈도우가 지나면 카운터가 리셋된다`() {
        val limiter = FixedWindowRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(500))
        assertFalse(limiter.allow(900))  // 같은 창, 초과
        assertTrue(limiter.allow(1000))  // 새 창 → 다시 허용
    }

    @Test
    fun `Fixed Window - 경계에서 한도가 새는 약점을 드러낸다`() {
        // 1초에 2개 제한인데, 창 경계를 끼고 0.001초 사이에 4개가 통과한다.
        val limiter = FixedWindowRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(limiter.allow(999))   // 첫 창 끝자락
        assertTrue(limiter.allow(999))
        assertTrue(limiter.allow(1000))  // 새 창 시작 → 또 2개 허용
        assertTrue(limiter.allow(1000))  // 약 0.001초 동안 총 4개 통과 (의도된 약점)
    }

    // --- Sliding Window Log ---

    @Test
    fun `Sliding Window - 직전 윈도우 구간의 요청 수로 정확히 판단한다`() {
        val limiter = SlidingWindowLogRateLimiter(maxRequests = 3, windowMillis = 1000)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(100))
        assertTrue(limiter.allow(200))
        assertFalse(limiter.allow(300)) // [0,300] 안에 이미 3개
    }

    @Test
    fun `Sliding Window - 오래된 요청이 윈도우를 벗어나면 다시 허용된다`() {
        val limiter = SlidingWindowLogRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(500))
        assertFalse(limiter.allow(800))  // [0,800]에 2개 → 차단
        // t=1001: t=0 기록이 윈도우(직전 1000ms) 밖으로 밀려남 → 한 칸 빔
        assertTrue(limiter.allow(1001))
    }

    @Test
    fun `Sliding Window - Fixed Window가 새던 경계 버스트를 막는다`() {
        val limiter = SlidingWindowLogRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(limiter.allow(999))
        assertTrue(limiter.allow(999))
        // t=1000: [1, 1000] 안에 999의 두 요청이 그대로 살아있음 → 차단 (Fixed Window와 대비)
        assertFalse(limiter.allow(1000))
    }

    // --- Token Bucket ---

    @Test
    fun `Token Bucket - 가득 찬 버킷은 용량만큼 순간 버스트를 허용한다`() {
        val limiter = TokenBucketRateLimiter(capacity = 3.0, refillTokensPerSecond = 1.0)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(0))
        assertFalse(limiter.allow(0)) // 토큰 소진
    }

    @Test
    fun `Token Bucket - 시간이 지나면 충전 속도만큼 토큰이 회복된다`() {
        val limiter = TokenBucketRateLimiter(capacity = 2.0, refillTokensPerSecond = 1.0)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(0))
        assertFalse(limiter.allow(0))    // 소진
        assertTrue(limiter.allow(1000))  // 1초 경과 → 토큰 1개 충전 → 허용
        assertFalse(limiter.allow(1000)) // 다시 0개
    }

    @Test
    fun `Token Bucket - 충전량은 용량을 넘지 않는다`() {
        val limiter = TokenBucketRateLimiter(capacity = 2.0, refillTokensPerSecond = 1.0)
        assertTrue(limiter.allow(0))
        assertTrue(limiter.allow(0)) // 소진
        // 100초가 지나도 토큰은 capacity(2)까지만 → 버스트는 2개로 제한
        assertTrue(limiter.allow(100_000))
        assertTrue(limiter.allow(100_000))
        assertFalse(limiter.allow(100_000))
    }
}
