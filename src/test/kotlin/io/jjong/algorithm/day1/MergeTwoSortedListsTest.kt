package io.jjong.algorithm.day1

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MergeTwoSortedListsTest {

    @Test
    fun `기본 예제`() {
        val l1 = listOf(1, 2, 4).toListNode()
        val l2 = listOf(1, 3, 4).toListNode()
        assertEquals(listOf(1, 1, 2, 3, 4, 4), mergeTwoLists(l1, l2).toList())
    }

    @Test
    fun `한쪽이 비어있으면 다른 쪽 그대로`() {
        val l2 = listOf(0).toListNode()
        assertEquals(listOf(0), mergeTwoLists(null, l2).toList())
    }

    @Test
    fun `둘 다 비어있으면 null`() {
        assertNull(mergeTwoLists(null, null))
    }

    @Test
    fun `길이가 크게 다른 경우`() {
        val l1 = listOf(1, 2, 3, 4, 5).toListNode()
        val l2 = listOf(6).toListNode()
        assertEquals(listOf(1, 2, 3, 4, 5, 6), mergeTwoLists(l1, l2).toList())
    }

    @Test
    fun `한쪽이 전부 더 작은 경우`() {
        val l1 = listOf(1, 2, 3).toListNode()
        val l2 = listOf(4, 5, 6).toListNode()
        assertEquals(listOf(1, 2, 3, 4, 5, 6), mergeTwoLists(l1, l2).toList())
    }

    @Test
    fun `음수와 중복 값`() {
        val l1 = listOf(-3, -1, 2).toListNode()
        val l2 = listOf(-2, -1, 5).toListNode()
        assertEquals(listOf(-3, -2, -1, -1, 2, 5), mergeTwoLists(l1, l2).toList())
    }
}
