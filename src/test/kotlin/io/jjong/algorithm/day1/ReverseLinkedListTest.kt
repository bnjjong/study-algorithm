package io.jjong.algorithm.day1

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ReverseLinkedListTest {

    @Test
    fun `다섯 노드를 뒤집는다`() {
        val head = listOf(1, 2, 3, 4, 5).toListNode()
        assertEquals(listOf(5, 4, 3, 2, 1), reverseList(head).toList())
    }

    @Test
    fun `두 노드를 뒤집는다`() {
        val head = listOf(1, 2).toListNode()
        assertEquals(listOf(2, 1), reverseList(head).toList())
    }

    @Test
    fun `단일 노드는 그대로`() {
        val head = listOf(7).toListNode()
        assertEquals(listOf(7), reverseList(head).toList())
    }

    @Test
    fun `빈 리스트는 null`() {
        assertNull(reverseList(null))
    }
}
