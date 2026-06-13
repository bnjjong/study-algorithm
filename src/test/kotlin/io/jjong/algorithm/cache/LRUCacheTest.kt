package io.jjong.algorithm.cache

import kotlin.test.Test
import kotlin.test.assertEquals

class LRUCacheTest {

    @Test
    fun `LeetCode 146 표준 시퀀스`() {
        val cache = LRUCache(2)
        cache.put(1, 1)                 // {1}
        cache.put(2, 2)                 // {1, 2}
        assertEquals(1, cache.get(1))   // 1 사용 → 2가 가장 오래됨
        cache.put(3, 3)                 // 용량 초과 → key 2 제거 {1, 3}
        assertEquals(-1, cache.get(2))  // 2는 쫓겨남
        cache.put(4, 4)                 // 용량 초과 → key 1 제거 {3, 4}
        assertEquals(-1, cache.get(1))  // 1은 쫓겨남
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun `기존 키 갱신은 용량을 늘리지 않는다`() {
        val cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        cache.put(1, 10)                // 갱신(추가 아님) → 1이 값 10, 최근으로
        assertEquals(10, cache.get(1))
        assertEquals(2, cache.get(2))   // 2는 여전히 살아있어야 한다
    }

    @Test
    fun `get으로 갱신된 최근성이 제거 대상을 바꾼다`() {
        val cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))   // 1을 최근으로 → 이제 2가 LRU
        cache.put(3, 3)                 // 2 제거 (1이 아니라!)
        assertEquals(-1, cache.get(2))
        assertEquals(1, cache.get(1))   // 1은 생존
        assertEquals(3, cache.get(3))
    }

    @Test
    fun `용량 1 캐시`() {
        val cache = LRUCache(1)
        cache.put(1, 1)
        assertEquals(1, cache.get(1))
        cache.put(2, 2)                 // 1 제거
        assertEquals(-1, cache.get(1))
        assertEquals(2, cache.get(2))
    }
}
