package io.jjong.leetcode.p0001

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class SolutionTest : StringSpec({

    val solution = Solution()

    // 정답 인덱스 쌍은 순서가 달라도 동일하게 취급한다.
    "example 1" {
        solution.twoSum(intArrayOf(2, 7, 11, 15), 9).toList() shouldContainExactlyInAnyOrder listOf(0, 1)
    }

    "example 2" {
        solution.twoSum(intArrayOf(3, 2, 4), 6).toList() shouldContainExactlyInAnyOrder listOf(1, 2)
    }

    "example 3 - same value twice" {
        solution.twoSum(intArrayOf(3, 3), 6).toList() shouldContainExactlyInAnyOrder listOf(0, 1)
    }
})
