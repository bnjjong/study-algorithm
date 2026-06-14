package io.jjong.algorithm.linkedlist

import kotlin.test.Test
import kotlin.test.assertEquals

class ReorderListTest {

    /** 값 목록으로 리스트를 만들어 제자리 재배열한 뒤, 결과를 목록으로 펼친다. */
    private fun reorder(values: List<Int>): List<Int> {
        val head = values.toSinglyLinkedList()
        reorderList(head)
        return head.toList()
    }

    @Test
    fun `짝수 길이 1234 → 1423`() {
        assertEquals(listOf(1, 4, 2, 3), reorder(listOf(1, 2, 3, 4)))
    }

    @Test
    fun `홀수 길이 12345 → 15243`() {
        assertEquals(listOf(1, 5, 2, 4, 3), reorder(listOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `노드 하나는 그대로`() {
        assertEquals(listOf(1), reorder(listOf(1)))
    }

    @Test
    fun `노드 둘은 그대로`() {
        assertEquals(listOf(1, 2), reorder(listOf(1, 2)))
    }

    @Test
    fun `빈 리스트`() {
        assertEquals(emptyList<Int>(), reorder(emptyList()))
    }
}
