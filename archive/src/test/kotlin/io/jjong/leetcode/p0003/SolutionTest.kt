package io.jjong.leetcode.p0003

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionTest : StringSpec({

    val solution = Solution()

    // ---- 기본 예시 ----
    "문제 예시 1 - abcabcbb" { solution.lengthOfLongestSubstring("abcabcbb") shouldBe 3 }
    "문제 예시 2 - 같은 문자만" { solution.lengthOfLongestSubstring("bbbbb") shouldBe 1 }
    // "wke"가 답. "pwke"는 연속이 아니라 substring 아님
    "문제 예시 3 - pwwkew" { solution.lengthOfLongestSubstring("pwwkew") shouldBe 3 }

    // ---- 길이 경계값 ----
    "빈 문자열은 0" { solution.lengthOfLongestSubstring("") shouldBe 0 }
    "한 글자면 1" { solution.lengthOfLongestSubstring("a") shouldBe 1 }
    "두 글자 모두 다르면 2" { solution.lengthOfLongestSubstring("ab") shouldBe 2 }
    "두 글자 같으면 1" { solution.lengthOfLongestSubstring("aa") shouldBe 1 }

    // ---- 정답 위치 다양화 ----
    "정답이 처음에 위치" { solution.lengthOfLongestSubstring("abcdaaaa") shouldBe 4 }
    "정답이 중간에 위치" { solution.lengthOfLongestSubstring("aaabcdeaaa") shouldBe 5 }
    "정답이 끝에 위치" { solution.lengthOfLongestSubstring("aaaabcdef") shouldBe 6 }

    // ---- 단조 / 전부 유니크 / 전부 동일 ----
    "모든 문자가 다 다르면 길이 자체가 답" { solution.lengthOfLongestSubstring("abcdef") shouldBe 6 }
    "모두 같은 문자가 매우 길어도 1" { solution.lengthOfLongestSubstring("aaaaaaaaaaaaaaaaaa") shouldBe 1 }

    // ---- 함정 케이스 (left 후퇴 방지 검증) ----
    // "abba": right=3 'a'를 0에서 봤지만 left=2라 무시해야 함. left가 후퇴하면 오답.
    "중복이 윈도우 밖에 있으면 무시해야 함" { solution.lengthOfLongestSubstring("abba") shouldBe 2 }
    // "mzuxt" 길이 5
    "tmmzuxt - 끝에 함정" { solution.lengthOfLongestSubstring("tmmzuxt") shouldBe 5 }

    // ---- 다양한 문자 종류 ----
    "숫자 포함" { solution.lengthOfLongestSubstring("a1b2c") shouldBe 5 }
    "공백 포함" { solution.lengthOfLongestSubstring("a b") shouldBe 3 }
    "특수문자 포함" { solution.lengthOfLongestSubstring("a!@#") shouldBe 4 }

    "대소문자는 다른 문자로 취급" {
        solution.lengthOfLongestSubstring("aA") shouldBe 2
        solution.lengthOfLongestSubstring("aAbB") shouldBe 4
    }

    // "dvdf" → "vdf"가 답
    "같은 문자 사이 거리가 다양한 경우" { solution.lengthOfLongestSubstring("dvdf") shouldBe 3 }
    // "abcabcabc" → "abc" 반복이라 3
    "긴 반복 패턴" { solution.lengthOfLongestSubstring("abcabcabc") shouldBe 3 }
})
