package io.jjong.leetcode.p0125

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `긴 문장 palindrome`() {
        assertTrue(solution.isPalindrome("A man, a plan, a canal: Panama"))
    }

    @Test
    fun `palindrome 아님`() {
        assertFalse(solution.isPalindrome("race a car"))
    }

    @Test
    fun `공백만 있으면 빈 문자열로 간주되어 true`() {
        assertTrue(solution.isPalindrome(" "))
    }

    @Test
    fun `숫자가 섞인 palindrome`() {
        assertTrue(solution.isPalindrome("0P") == false)   // '0'과 'p'는 다름
        assertTrue(solution.isPalindrome("12321"))
        assertTrue(solution.isPalindrome("1a2b2a1"))
    }

    @Test
    fun `한 글자는 항상 true`() {
        assertTrue(solution.isPalindrome("a"))
        assertTrue(solution.isPalindrome(","))   // 정제하면 빈 문자열
    }

    @Test
    fun `대소문자 섞임`() {
        assertTrue(solution.isPalindrome("Aa"))
        assertTrue(solution.isPalindrome("AbBa"))
    }

    @Test
    fun `특수문자 다수`() {
        assertTrue(solution.isPalindrome(".,!@#"))   // 정제하면 빈 문자열
    }
}
