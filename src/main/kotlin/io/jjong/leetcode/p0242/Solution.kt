package io.jjong.leetcode.p0242

/**
 * LeetCode #242 - Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * [t]가 [s]의 anagram(글자 순서만 바꾼 것)인지 판별합니다.
 */
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val tArray = t.toCharArray()
        val count = HashMap<Char, Int>()
        for (sChar in s.toCharArray()) {
            count[sChar] = count.getOrDefault(sChar, 0) + 1
        }
        for (tChar in tArray) {
            count[tChar] = count.getOrDefault(tChar, 0) - 1
        }


        return count.all { it.value == 0 }
    }
}
