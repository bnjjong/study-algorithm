package io.jjong.algorithm.search

import kotlin.test.Test
import kotlin.test.assertEquals

class SearchAutocompleteTest {

    @Test
    fun `접두사로 시작하는 검색어를 인기순으로 추천한다`() {
        val ac = SearchAutocomplete()
        ac.input("nike"); ac.input("nike")   // nike 빈도 2
        ac.input("nike air")                 // nike air 빈도 1
        ac.input("new balance")

        assertEquals(listOf("nike", "nike air"), ac.suggest("ni", 2))
    }

    @Test
    fun `매칭되는 접두사가 없으면 빈 리스트를 반환한다`() {
        val ac = SearchAutocomplete()
        ac.input("nike")
        assertEquals(emptyList<String>(), ac.suggest("zzz", 5))
    }

    @Test
    fun `최대 k개까지만 반환한다`() {
        val ac = SearchAutocomplete()
        ac.input("nike"); ac.input("nike")
        ac.input("nike air")
        ac.input("nike zoom")
        // 'ni' 매칭은 3개지만 k=1 → 가장 인기 있는 하나만
        assertEquals(listOf("nike"), ac.suggest("ni", 1))
    }

    @Test
    fun `빈도가 같으면 사전순으로 정렬한다`() {
        val ac = SearchAutocomplete()
        ac.input("nike zoom")    // 모두 빈도 1
        ac.input("nike air")
        ac.input("nike cortez")
        assertEquals(
            listOf("nike air", "nike cortez", "nike zoom"),
            ac.suggest("nike", 5),
        )
    }

    @Test
    fun `같은 검색어를 반복 입력하면 빈도가 누적된다`() {
        val ac = SearchAutocomplete()
        ac.input("adidas")
        ac.input("nike"); ac.input("nike"); ac.input("nike")  // nike 3 > adidas 1
        // prefix가 빈 문자열이면 전체가 매칭(startsWith("") == true)
        assertEquals(listOf("nike", "adidas"), ac.suggest("", 5))
    }
}
