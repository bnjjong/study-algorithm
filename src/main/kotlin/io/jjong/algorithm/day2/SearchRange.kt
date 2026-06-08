package io.jjong.algorithm.day2

/**
 * LeetCode #34 — Find First and Last Position of Element in Sorted Array (Medium)
 *
 * 오름차순 정렬된 [nums]에서 [target]이 처음/마지막으로 나타나는 인덱스를 `[first, last]`로 반환한다.
 * [target]이 없으면 `[-1, -1]`. lowerBound(`<`)와 upperBound(`<=`)를 조합해 전체 O(log N).
 *   - first = lowerBound(target)        // target "이상"인 첫 위치
 *   - last  = upperBound(target) - 1     // target "초과"인 첫 위치의 바로 앞
 *
 * [trace]를 true로 주면 이진 탐색 각 단계(lo/hi/mid/비교/분기)를 표준출력으로 찍어,
 * 테스트를 돌리며 경계가 어떻게 좁혀지는지 눈으로 따라갈 수 있다.
 * 기본 false라 운영 로직과 기존 테스트에는 영향이 없다.
 */
class SearchRange(private val trace: Boolean = false) {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        val first = lowerBound(nums, target)
        // 존재 판정: 범위 안인지(A) → 그 자리가 target인지(B). A를 먼저 봐 nums[first] OOB를 막는다.
        if (first == nums.size || nums[first] != target) {
            if (trace) {
                val why = if (first == nums.size) "first=$first 가 끝(size=${nums.size}) 너머"
                          else "nums[$first]=${nums[first]} ≠ target($target)"
                println("  ⇒ 존재하지 않음 ($why) ⇒ [-1, -1]\n")
            }
            return intArrayOf(-1, -1)
        }
        val last = upperBound(nums, target) - 1
        if (trace) println("  ⇒ first=$first, last=$last ⇒ [$first, $last]\n")
        return intArrayOf(first, last)
    }

    /** [target] "이상"인 첫 위치(가장 왼쪽 삽입점). 비교는 `<`. */
    private fun lowerBound(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.size
        if (trace) traceHeader("lowerBound", "<", nums, target)
        var step = 0
        while (lo < hi) {
            val mid = (lo + hi) ushr 1
            val goRight = nums[mid] < target
            if (trace) traceStep(++step, lo, hi, mid, nums[mid], "<", target, goRight)
            if (goRight) lo = mid + 1
            else hi = mid
        }
        if (trace) println("  ⇒ return $lo")
        return lo
    }

    /** [target] "초과"인 첫 위치(가장 오른쪽 삽입점). 비교는 `<=`. */
    private fun upperBound(nums: IntArray, target: Int): Int {
        var lo = 0
        var hi = nums.size
        if (trace) traceHeader("upperBound", "<=", nums, target)
        var step = 0
        while (lo < hi) {
            val mid = (lo + hi) ushr 1
            val goRight = nums[mid] <= target
            if (trace) traceStep(++step, lo, hi, mid, nums[mid], "<=", target, goRight)
            if (goRight) lo = mid + 1
            else hi = mid
        }
        if (trace) println("  ⇒ return $lo")
        return lo
    }

    private fun traceHeader(name: String, op: String, nums: IntArray, target: Int) {
        println("[$name] nums=${nums.toList()}, target=$target  (조건: nums[mid] $op target 이면 lo=mid+1, 아니면 hi=mid)")
    }

    private fun traceStep(step: Int, lo: Int, hi: Int, mid: Int, value: Int, op: String, target: Int, goRight: Boolean) {
        val action = if (goRight) "lo=${mid + 1}" else "hi=$mid"
        println("  step$step: [lo=$lo, hi=$hi] mid=$mid nums[$mid]=$value | $value $op $target ? $goRight → $action")
    }
}
