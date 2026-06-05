package io.jjong.leetcode.p0001

import kotlin.test.Test
import kotlin.test.assertTrue

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `example 1`() {
        val result = solution.twoSum(intArrayOf(2, 7, 11, 15), 9)
        assertEqualsAsPair(intArrayOf(0, 1), result)
    }

    @Test
    fun `example 2`() {
        val result = solution.twoSum(intArrayOf(3, 2, 4), 6)
        assertEqualsAsPair(intArrayOf(1, 2), result)
    }

    @Test
    fun `example 3 - same value twice`() {
        val result = solution.twoSum(intArrayOf(3, 3), 6)
        assertEqualsAsPair(intArrayOf(0, 1), result)
    }

    private fun assertEqualsAsPair(expected: IntArray, actual: IntArray) {
        assertTrue(
            actual.toSortedSet() == expected.toSortedSet(),
            "expected ${expected.toList()}, got ${actual.toList()}",
        )
    }
}
