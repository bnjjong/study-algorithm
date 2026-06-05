package io.jjong.leetcode.p0242

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionTest : StringSpec({

    val solution = Solution()

    "정상 anagram이면 true" { solution.isAnagram("anagram", "nagaram") shouldBe true }
    "글자 구성이 다르면 false" { solution.isAnagram("rat", "car") shouldBe false }
    "길이가 다르면 false" { solution.isAnagram("a", "ab") shouldBe false }
    "같은 문자열이면 true" { solution.isAnagram("abc", "abc") shouldBe true }
    "한 글자씩 다르면 false" { solution.isAnagram("aacc", "ccac") shouldBe false }
    "빈 문자열은 안 들어오지만 한 글자끼리 같으면 true" { solution.isAnagram("a", "a") shouldBe true }

    "반복 문자가 많은 경우" {
        solution.isAnagram("aaabbb", "ababab") shouldBe true
        solution.isAnagram("aaabbb", "aaaabb") shouldBe false
    }
})
