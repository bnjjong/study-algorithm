package io.jjong.leetcode.p0217

/**
 * LeetCode #217 - Contains Duplicate (Easy)
 * https://leetcode.com/problems/contains-duplicate/
 *
 * [nums]에 동일한 값이 두 번 이상 나타나면 true를 반환합니다.
 */
class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        val seen = HashSet<Int>()
        for (c in nums) {
            if (c in seen) {
                return true
            } else {
                seen.add(c)
            }
        }
        return false
    }
}
