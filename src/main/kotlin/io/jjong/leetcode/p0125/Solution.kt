package io.jjong.leetcode.p0125

/**
 * LeetCode #125 - Valid Palindrome (Easy)
 * https://leetcode.com/problems/valid-palindrome/
 *
 * 대소문자를 무시하고 영숫자만 고려했을 때 [s]가 palindrome인지 판별합니다.
 * 1. 양 끝에서 시작하는 두 개의 포인터를 사용 (Two Pointers)
 * 2. 영숫자가 아닌 문자는 건너뜀
 * 3. 대소문자를 무시하고 비교
 * 4. 두 포인터가 만날 때까지 모두 같으면 palindrome
 */
class Solution {
    fun isPalindrome(s: String): Boolean {
        // 왼쪽 끝에서 시작하는 포인터
        var left = 0
        // 오른쪽 끝에서 시작하는 포인터
        var right = s.lastIndex

        // 두 포인터가 서로 교차할 때까지 반복
        while (left < right) {
            when {
                // 왼쪽 문자가 영문자나 숫자가 아니면 오른쪽으로 한 칸 이동 (건너뛰기)
                !s[left].isLetterOrDigit() -> left++
                // 오른쪽 문자가 영문자나 숫자가 아니면 왼쪽으로 한 칸 이동 (건너뛰기)
                !s[right].isLetterOrDigit() -> right--
                else -> {
                    // 양쪽 다 영문자나 숫자일 때, 소문자로 변환하여 비교
                    if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
                        // 서로 다르면 팰린드롬이 아니므로 false 반환
                        return false
                    }
                    // 같으면 다음 비교를 위해 안쪽으로 이동
                    left++
                    right--
                }
            }
        }
        // 모든 문자를 성공적으로 비교했다면 true 반환
        return true
    }
}
