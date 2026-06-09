package io.jjong.leetcode.p0001

/**
 * LeetCode #1 - Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * 합이 [target]이 되는 두 원소의 인덱스를 반환합니다.
 */
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        println("=== twoSum 시작 | nums=${nums.toList()}, target=$target ===")

        // seen: "이미 본 값" -> "그 값이 있던 인덱스"
        val seen = HashMap<Int, Int>()

        for (i in nums.indices) {
            val current = nums[i]
            val need = target - current

            println("\n[i=$i] current=$current  →  need=$need (= target $target - current $current)")
            println("       지금까지 seen = $seen")

            if (need in seen) {
                val pairIndex = seen[need]!!
                println("       ✅ seen에 need($need) 있음! 인덱스=$pairIndex → 정답 [$pairIndex, $i]")
                return intArrayOf(pairIndex, i)
            } else {
                println("       ❌ seen에 need($need) 없음 → current($current)를 인덱스 $i 와 함께 기록")
                seen[current] = i
            }
        }

        println("정답을 찾지 못함 (문제 가정상 도달하지 않음)")
        return intArrayOf()
    }
}
