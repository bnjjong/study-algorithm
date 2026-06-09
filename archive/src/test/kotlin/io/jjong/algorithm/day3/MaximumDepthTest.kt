package io.jjong.algorithm.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class MaximumDepthTest {

    @Test
    fun `표준 예제`() {
        // [3,9,20,null,null,15,7] → 3 (3 → 20 → 15)
        assertEquals(3, maxDepth(listOf(3, 9, 20, null, null, 15, 7).toTree()))
    }

    @Test
    fun `빈 트리는 0`() {
        assertEquals(0, maxDepth(null))
    }

    @Test
    fun `루트만 있으면 1`() {
        assertEquals(1, maxDepth(listOf(1).toTree()))
    }

    @Test
    fun `오른쪽으로만 치우친 사슬`() {
        // 1 → (오른쪽) 2 → (오른쪽) 3
        assertEquals(3, maxDepth(listOf(1, null, 2, null, 3).toTree()))
    }

    @Test
    fun `왼쪽으로 더 깊은 트리`() {
        // 1 → 2 → 3 → 4 (왼쪽 사슬), 깊이 4
        assertEquals(4, maxDepth(listOf(1, 2, null, 3, null, 4).toTree()))
    }
}
