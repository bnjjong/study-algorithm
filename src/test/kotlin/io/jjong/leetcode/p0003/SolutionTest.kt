package io.jjong.leetcode.p0003

import kotlin.test.Test
import kotlin.test.assertEquals

class SolutionTest {

    private val solution = Solution()

    // ---- 기본 예시 ----

    @Test
    fun `문제 예시 1 - abcabcbb`() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"))
    }

    @Test
    fun `문제 예시 2 - 같은 문자만`() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"))
    }

    @Test
    fun `문제 예시 3 - pwwkew`() {
        // "wke"가 답. "pwke"는 substring 아님(연속 X)
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"))
    }

    // ---- 길이 경계값 ----

    @Test
    fun `빈 문자열은 0`() {
        assertEquals(0, solution.lengthOfLongestSubstring(""))
    }

    @Test
    fun `한 글자면 1`() {
        assertEquals(1, solution.lengthOfLongestSubstring("a"))
    }

    @Test
    fun `두 글자 모두 다르면 2`() {
        assertEquals(2, solution.lengthOfLongestSubstring("ab"))
    }

    @Test
    fun `두 글자 같으면 1`() {
        assertEquals(1, solution.lengthOfLongestSubstring("aa"))
    }

    // ---- 정답 위치 다양화 ----

    @Test
    fun `정답이 처음에 위치`() {
        // "abcd" 처음 4개가 답
        assertEquals(4, solution.lengthOfLongestSubstring("abcdaaaa"))
    }

    @Test
    fun `정답이 중간에 위치`() {
        // "abcde"가 중간에 등장
        assertEquals(5, solution.lengthOfLongestSubstring("aaabcdeaaa"))
    }

    @Test
    fun `정답이 끝에 위치`() {
        // 마지막 "abcdef"가 답
        assertEquals(6, solution.lengthOfLongestSubstring("aaaabcdef"))
    }

    // ---- 단조 / 전부 유니크 / 전부 동일 ----

    @Test
    fun `모든 문자가 다 다르면 길이 자체가 답`() {
        assertEquals(6, solution.lengthOfLongestSubstring("abcdef"))
    }

    @Test
    fun `모두 같은 문자가 매우 길어도 1`() {
        assertEquals(1, solution.lengthOfLongestSubstring("aaaaaaaaaaaaaaaaaa"))
    }

    // ---- 함정 케이스 (left 후퇴 방지 검증) ----

    @Test
    fun `중복이 윈도우 밖에 있으면 무시해야 함`() {
        // s = "abba"
        // right=0 'a': window="a", left=0, max=1
        // right=1 'b': window="ab", left=0, max=2
        // right=2 'b': 중복! left=2 (이미 봤던 b 인덱스+1)
        //              window="b", max=2
        // right=3 'a': 'a'를 0에서 봤지만 left=2라 무시해야 함!
        //              만약 left = seen['a']+1 = 1 로 후퇴시키면 잘못됨
        //              window="ba", max=2
        assertEquals(2, solution.lengthOfLongestSubstring("abba"))
    }

    @Test
    fun `tmmzuxt - 끝에 함정`() {
        // 'z', 'u', 'x', 't' → "mzuxt" 길이 5
        assertEquals(5, solution.lengthOfLongestSubstring("tmmzuxt"))
    }

    // ---- 다양한 문자 종류 ----

    @Test
    fun `숫자 포함`() {
        assertEquals(5, solution.lengthOfLongestSubstring("a1b2c"))
    }

    @Test
    fun `공백 포함`() {
        // " " 도 하나의 문자
        assertEquals(3, solution.lengthOfLongestSubstring("a b"))
    }

    @Test
    fun `특수문자 포함`() {
        assertEquals(4, solution.lengthOfLongestSubstring("a!@#"))
    }

    @Test
    fun `대소문자는 다른 문자로 취급`() {
        // a, A 는 서로 다름
        assertEquals(2, solution.lengthOfLongestSubstring("aA"))
        assertEquals(4, solution.lengthOfLongestSubstring("aAbB"))
    }

    @Test
    fun `같은 문자 사이 거리가 다양한 경우`() {
        // "dvdf" → "vdf" 가 답 (d 두 번 등장)
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf"))
    }

    @Test
    fun `긴 반복 패턴`() {
        // "abcabcabc" → 답은 3 (abc 반복)
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcabc"))
    }
}
