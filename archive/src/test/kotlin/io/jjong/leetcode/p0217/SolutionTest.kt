package io.jjong.leetcode.p0217

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionTest : StringSpec({

    val solution = Solution()

    "중복이 있으면 true" { solution.containsDuplicate(intArrayOf(1, 2, 3, 1)) shouldBe true }
    "중복이 없으면 false" { solution.containsDuplicate(intArrayOf(1, 2, 3, 4)) shouldBe false }
    "여러 중복이 섞인 경우 true" { solution.containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)) shouldBe true }
    "원소가 하나면 false" { solution.containsDuplicate(intArrayOf(7)) shouldBe false }
    "음수와 양수 섞여도 동작" { solution.containsDuplicate(intArrayOf(-1, 0, 1, -1)) shouldBe true }
})
