package io.jjong.algorithm.search

import kotlin.test.Test
import kotlin.test.assertEquals

class SearchSuggestionsSystemTest {

    @Test
    fun `예제1 - mouse`() {
        val products = arrayOf("mobile", "mouse", "moneypot", "monitor", "mousepad")
        val expected = listOf(
            listOf("mobile", "moneypot", "monitor"), // "m"
            listOf("mobile", "moneypot", "monitor"), // "mo"
            listOf("mouse", "mousepad"),             // "mou"
            listOf("mouse", "mousepad"),             // "mous"
            listOf("mouse", "mousepad"),             // "mouse"
        )
        assertEquals(expected, suggestedProducts(products, "mouse"))
    }

    @Test
    fun `예제2 - 단일 상품은 매 글자마다 그대로`() {
        assertEquals(
            List(6) { listOf("havana") },
            suggestedProducts(arrayOf("havana"), "havana"),
        )
    }

    @Test
    fun `매칭이 끊기면 그 이후 글자는 빈 리스트`() {
        val products = arrayOf("apple", "apricot")
        val result = suggestedProducts(products, "az")
        assertEquals(listOf("apple", "apricot"), result[0]) // "a"
        assertEquals(emptyList(), result[1])                // "az" — 매칭 없음
    }

    @Test
    fun `최대 3개까지만 사전순으로`() {
        val products = arrayOf("car", "carbon", "card", "care", "cargo")
        // "car" 접두사 5개 중 사전순 상위 3개
        assertEquals(
            listOf("car", "carbon", "card"),
            suggestedProducts(products, "car")[2], // "car"
        )
    }
}
