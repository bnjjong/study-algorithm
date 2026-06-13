package io.jjong.algorithm.resilience

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KeyedRateLimiterTest {

    @Test
    fun `서로 다른 사용자는 독립적으로 제한된다`() {
        val rl = KeyedSlidingWindowRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(rl.allow("userA", 0))
        assertTrue(rl.allow("userA", 100))
        assertFalse(rl.allow("userA", 200))  // A 한도 초과
        assertTrue(rl.allow("userB", 200))   // B는 A와 무관하게 멀쩡
        assertTrue(rl.allow("userB", 300))
        assertFalse(rl.allow("userB", 400))  // B도 자기 한도
    }

    @Test
    fun `같은 사용자는 윈도우가 지나면 다시 허용된다`() {
        val rl = KeyedSlidingWindowRateLimiter(maxRequests = 2, windowMillis = 1000)
        assertTrue(rl.allow("u", 0))
        assertTrue(rl.allow("u", 500))
        assertFalse(rl.allow("u", 800))      // [0,800]에 2개 → 차단
        assertTrue(rl.allow("u", 1001))      // t=0 기록이 윈도우 밖 → 한 칸 빔
    }

    @Test
    fun `처음 보는 키도 바로 허용된다`() {
        val rl = KeyedSlidingWindowRateLimiter(maxRequests = 1, windowMillis = 1000)
        assertTrue(rl.allow("new-key", 0))
        assertFalse(rl.allow("new-key", 100))
    }

    @Test
    fun `한 키의 폭주가 다른 키에 영향 없다`() {
        val rl = KeyedSlidingWindowRateLimiter(maxRequests = 1, windowMillis = 1000)
        assertTrue(rl.allow("heavy", 0))
        assertFalse(rl.allow("heavy", 1))
        assertFalse(rl.allow("heavy", 2))
        assertTrue(rl.allow("light", 2))     // light는 자기 한도 멀쩡
    }
}
