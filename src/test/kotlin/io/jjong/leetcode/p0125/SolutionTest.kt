package io.jjong.leetcode.p0125

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionTest : StringSpec({

    val solution = Solution()

    "긴 문장 palindrome" { solution.isPalindrome("A man, a plan, a canal: Panama") shouldBe true }
    "palindrome 아님" { solution.isPalindrome("race a car") shouldBe false }
    "공백만 있으면 빈 문자열로 간주되어 true" { solution.isPalindrome(" ") shouldBe true }

    "숫자가 섞인 palindrome" {
        solution.isPalindrome("0P") shouldBe false // '0'과 'p'는 다름
        solution.isPalindrome("12321") shouldBe true
        solution.isPalindrome("1a2b2a1") shouldBe true
    }

    "한 글자는 항상 true" {
        solution.isPalindrome("a") shouldBe true
        solution.isPalindrome(",") shouldBe true // 정제하면 빈 문자열
    }

    "대소문자 섞임" {
        solution.isPalindrome("Aa") shouldBe true
        solution.isPalindrome("AbBa") shouldBe true
    }

    "특수문자 다수" { solution.isPalindrome(".,!@#") shouldBe true } // 정제하면 빈 문자열
})
