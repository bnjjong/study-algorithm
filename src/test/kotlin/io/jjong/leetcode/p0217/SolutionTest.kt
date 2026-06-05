package io.jjong.leetcode.p0217

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `중복이 있으면 true`() {
        assertTrue(solution.containsDuplicate(intArrayOf(1, 2, 3, 1)))
    }

    @Test
    fun `중복이 없으면 false`() {
        assertFalse(solution.containsDuplicate(intArrayOf(1, 2, 3, 4)))
    }

    @Test
    fun `여러 중복이 섞인 경우 true`() {
        assertTrue(solution.containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))
    }

    @Test
    fun `원소가 하나면 false`() {
        assertFalse(solution.containsDuplicate(intArrayOf(7)))
    }

    @Test
    fun `음수와 양수 섞여도 동작`() {
        assertTrue(solution.containsDuplicate(intArrayOf(-1, 0, 1, -1)))
    }
}
