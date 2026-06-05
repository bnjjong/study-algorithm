package io.jjong.leetcode.p0242

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SolutionTest {

    private val solution = Solution()

    @Test
    fun `정상 anagram이면 true`() {
        assertTrue(solution.isAnagram("anagram", "nagaram"))
    }

    @Test
    fun `글자 구성이 다르면 false`() {
        assertFalse(solution.isAnagram("rat", "car"))
    }

    @Test
    fun `길이가 다르면 false`() {
        assertFalse(solution.isAnagram("a", "ab"))
    }

    @Test
    fun `같은 문자열이면 true`() {
        assertTrue(solution.isAnagram("abc", "abc"))
    }

    @Test
    fun `한 글자씩 다르면 false`() {
        assertFalse(solution.isAnagram("aacc", "ccac"))
    }

    @Test
    fun `빈 문자열은 안 들어오지만 한 글자끼리 같으면 true`() {
        assertTrue(solution.isAnagram("a", "a"))
    }

    @Test
    fun `반복 문자가 많은 경우`() {
        assertTrue(solution.isAnagram("aaabbb", "ababab"))
        assertFalse(solution.isAnagram("aaabbb", "aaaabb"))
    }
}
