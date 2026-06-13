package io.jjong.algorithm.linkedlist

import kotlin.test.Test
import kotlin.test.assertEquals

class AddTwoNumbersIITest {

    /** 정수 목록 두 개를 정방향 연결 리스트로 만들어 더한 뒤, 결과를 목록으로 펼친다. */
    private fun add(a: List<Int>, b: List<Int>): List<Int> =
        addTwoNumbersII(a.toSinglyLinkedList(), b.toSinglyLinkedList()).toList()

    @Test
    fun `7243 더하기 564 는 7807`() {
        assertEquals(listOf(7, 8, 0, 7), add(listOf(7, 2, 4, 3), listOf(5, 6, 4)))
    }

    @Test
    fun `0 더하기 0 은 0`() {
        assertEquals(listOf(0), add(listOf(0), listOf(0)))
    }

    @Test
    fun `5 더하기 5 는 10`() {
        assertEquals(listOf(1, 0), add(listOf(5), listOf(5)))
    }

    @Test
    fun `자릿수가 늘어나는 받아올림 99 + 1`() {
        assertEquals(listOf(1, 0, 0), add(listOf(9, 9), listOf(1)))
    }

    @Test
    fun `연쇄 받아올림 999 + 1`() {
        assertEquals(listOf(1, 0, 0, 0), add(listOf(9, 9, 9), listOf(1)))
    }
}
