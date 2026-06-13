package io.jjong.algorithm.cache

import kotlin.test.Test
import kotlin.test.assertEquals

class CursorPaginatorTest {

    private val data = listOf(10, 20, 30, 40, 50)

    @Test
    fun `처음부터 한 페이지`() {
        val p = CursorPaginator(data)
        assertEquals(listOf(10, 20), p.page(null, 2))   // cursor 없음 → 맨 앞 2개
    }

    @Test
    fun `cursor 이후 페이지`() {
        val p = CursorPaginator(data)
        assertEquals(listOf(30, 40), p.page(20, 2))      // 20 "초과" = 30부터
    }

    @Test
    fun `마지막 페이지는 부족분만`() {
        val p = CursorPaginator(data)
        assertEquals(listOf(50), p.page(40, 2))          // 40 초과 = 50 하나뿐
    }

    @Test
    fun `끝을 넘으면 빈 리스트`() {
        val p = CursorPaginator(data)
        assertEquals(emptyList(), p.page(50, 2))         // 50 초과 = 없음
    }

    @Test
    fun `cursor가 데이터에 없어도 그 초과부터`() {
        val p = CursorPaginator(data)
        assertEquals(listOf(30, 40), p.page(25, 2))      // 25 초과 첫 원소 = 30
    }

    @Test
    fun `정렬되지 않은 입력도 정렬해 처리`() {
        val p = CursorPaginator(listOf(30, 10, 50, 20, 40))
        assertEquals(listOf(10, 20), p.page(null, 2))    // 내부 정렬 후 맨 앞
    }
}
