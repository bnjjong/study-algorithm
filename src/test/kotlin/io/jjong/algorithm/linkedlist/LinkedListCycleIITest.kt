package io.jjong.algorithm.linkedlist

import kotlin.test.Test
import kotlin.test.assertNull
import kotlin.test.assertSame

class LinkedListCycleIITest {

    /**
     * [values]로 리스트를 만든 뒤, [pos]가 0 이상이면 마지막 노드의 next를
     * pos번째(0-based) 노드로 연결해 사이클을 만든다. pos가 -1이면 사이클 없음.
     * 반환값은 (head, 사이클 시작 노드 또는 null).
     */
    private fun build(values: List<Int>, pos: Int): Pair<ListNode?, ListNode?> {
        val nodes = values.map { ListNode(it) }
        for (i in 0 until nodes.size - 1) nodes[i].next = nodes[i + 1]
        val cycleStart = if (pos >= 0) {
            nodes.last().next = nodes[pos]
            nodes[pos]
        } else null
        return nodes.firstOrNull() to cycleStart
    }

    @Test
    fun `1사이클이 없으면 null`() {
        val (head, _) = build(listOf(1, 2, 3, 4), pos = -1)
        assertNull(detectCycle(head))
    }

    @Test
    fun `2사이클 시작점을 정확히 반환한다`() {
        val (head, start) = build(listOf(1, 2, 3, 4, 5), pos = 2) // 3번 노드에서 시작
        assertSame(start, detectCycle(head))
    }

    @Test
    fun `3head 자신이 사이클 시작점`() {
        val (head, start) = build(listOf(1, 2), pos = 0)
        assertSame(start, detectCycle(head))
    }

    @Test
    fun `4단일 노드 자기 자신을 가리키는 사이클`() {
        val (head, start) = build(listOf(1), pos = 0)
        assertSame(start, detectCycle(head))
    }

    @Test
    fun `5단일 노드 사이클 없음`() {
        val (head, _) = build(listOf(1), pos = -1)
        assertNull(detectCycle(head))
    }

    @Test
    fun `6빈 리스트는 null`() {
        assertNull(detectCycle(null))
    }
}
