package io.jjong.algorithm.mock

import kotlin.test.Test
import kotlin.test.assertEquals

class TopKSearchKeywordsTest {

    @Test
    fun `예시 — nike 3 adidas 2 puma 1`() {
        val logs = listOf("nike", "adidas", "nike", "puma", "nike", "adidas")
        assertEquals(listOf("nike", "adidas"), topKKeywords(logs, 2))
    }

    @Test
    fun `빈도가 모두 같으면 사전순`() {
        val logs = listOf("banana", "apple", "cherry") // 각 1회
        assertEquals(listOf("apple", "banana"), topKKeywords(logs, 2))
    }

    @Test
    fun `빈도 동점은 사전순으로 타이브레이크`() {
        val logs = listOf("apple", "banana", "apple", "banana", "cherry") // apple2, banana2, cherry1
        assertEquals(listOf("apple", "banana"), topKKeywords(logs, 2))
    }

    @Test
    fun `k가 고유 키워드 수만큼이면 전부 반환`() {
        val logs = listOf("a", "a", "b") // a2, b1
        assertEquals(listOf("a", "b"), topKKeywords(logs, 2))
    }

    @Test
    fun `단일 키워드`() {
        assertEquals(listOf("solo"), topKKeywords(listOf("solo", "solo"), 1))
    }
}
