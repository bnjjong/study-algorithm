package io.jjong.algorithm.cache

import kotlin.test.Test
import kotlin.test.assertEquals

class LFUCacheTest {

    @Test
    fun `LeetCode 460 표준 시퀀스`() {
        val cache = LFUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))   // 1의 빈도 ↑ (f2), 2는 f1
        cache.put(3, 3)                 // 가득 참 → 최소 빈도(2) 제거
        assertEquals(-1, cache.get(2))  // 2는 제거됨
        assertEquals(3, cache.get(3))   // 3의 빈도 ↑
        cache.put(4, 4)                 // 가득 참 → f2 동률(1,3) 중 더 오래된 1 제거
        assertEquals(-1, cache.get(1))  // 1은 제거됨
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun `빈도가 같으면 더 오래된 키(LRU)를 제거`() {
        val cache = LFUCache(2)
        cache.put(1, 1)                 // f1
        cache.put(2, 2)                 // f1
        cache.put(3, 3)                 // 동률 f1 → 먼저 넣은 1 제거
        assertEquals(-1, cache.get(1))
        assertEquals(2, cache.get(2))
        assertEquals(3, cache.get(3))
    }

    @Test
    fun `기존 키 갱신은 값을 바꾸고 빈도를 올린다`() {
        val cache = LFUCache(2)
        cache.put(1, 1)                 // f1
        cache.put(2, 2)                 // f1
        cache.put(1, 10)                // 1 갱신 → 값 10, f2
        cache.put(3, 3)                 // 최소 빈도(2) 제거
        assertEquals(-1, cache.get(2))
        assertEquals(10, cache.get(1))  // 갱신된 값
        assertEquals(3, cache.get(3))
    }

    @Test
    fun `용량 0이면 저장하지 않는다`() {
        val cache = LFUCache(0)
        cache.put(1, 1)
        assertEquals(-1, cache.get(1))
    }
}
