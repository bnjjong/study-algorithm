package io.jjong.algorithm.day2

import kotlin.test.Test
import kotlin.test.assertContentEquals

class SearchRangeTest {

    private val s = SearchRange(true)

    @Test
    fun `중복 target의 첫·끝 위치`() {
        // 8은 index 3,4에 있음
        assertContentEquals(intArrayOf(3, 4), s.searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8))
    }

    @Test
    fun `존재하지 않는 target은 -1 -1`() {
        assertContentEquals(intArrayOf(-1, -1), s.searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6))
    }

    @Test
    fun `빈 배열`() {
        assertContentEquals(intArrayOf(-1, -1), s.searchRange(intArrayOf(), 0))
    }

    @Test
    fun `단일 원소 일치`() {
        assertContentEquals(intArrayOf(0, 0), s.searchRange(intArrayOf(1), 1))
    }

    @Test
    fun `단일 원소 불일치`() {
        assertContentEquals(intArrayOf(-1, -1), s.searchRange(intArrayOf(1), 2))
    }

    @Test
    fun `전부 같은 값이면 0부터 끝까지`() {
        assertContentEquals(intArrayOf(0, 4), s.searchRange(intArrayOf(2, 2, 2, 2, 2), 2))
    }

    @Test
    fun `맨 앞 경계`() {
        assertContentEquals(intArrayOf(0, 1), s.searchRange(intArrayOf(1, 1, 2, 3), 1))
    }

    @Test
    fun `맨 뒤 경계`() {
        assertContentEquals(intArrayOf(3, 3), s.searchRange(intArrayOf(1, 1, 2, 3), 3))
    }
}
