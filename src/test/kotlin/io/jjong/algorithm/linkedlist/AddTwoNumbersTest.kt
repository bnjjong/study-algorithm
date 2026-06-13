package io.jjong.algorithm.linkedlist

import kotlin.test.Test
import kotlin.test.assertEquals

class AddTwoNumbersTest {

    @Test
    fun `342 더하기 465 는 807`() {
        val l1 = listOf(2, 4, 3).toListNode() // 342
        val l2 = listOf(5, 6, 4).toListNode() // 465
        assertEquals(listOf(7, 0, 8), addTwoNumbers(l1, l2).toList()) // 807
    }

    @Test
    fun `0 더하기 0 은 0`() {
        val l1 = listOf(0).toListNode()
        val l2 = listOf(0).toListNode()
        assertEquals(listOf(0), addTwoNumbers(l1, l2).toList())
    }

    @Test
    fun `길이가 다른 경우 짧은 쪽을 0으로 취급`() {
        val l1 = listOf(3, 2, 1).toListNode() // 123
        val l2 = listOf(5).toListNode()       // 5
        assertEquals(listOf(8, 2, 1), addTwoNumbers(l1, l2).toList()) // 128
    }

    @Test
    fun `마지막 자리에서 carry 가 남아 자릿수가 늘어나는 경우`() {
        val l1 = listOf(9, 9).toListNode() // 99
        val l2 = listOf(1).toListNode()    // 1
        assertEquals(listOf(0, 0, 1), addTwoNumbers(l1, l2).toList()) // 100
    }

    @Test
    fun `연속된 carry 전파`() {
        val l1 = listOf(9, 9, 9, 9, 9, 9, 9).toListNode()
        val l2 = listOf(9, 9, 9, 9).toListNode()
        assertEquals(listOf(8, 9, 9, 9, 0, 0, 0, 1), addTwoNumbers(l1, l2).toList())
    }
}
