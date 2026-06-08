package io.jjong.algorithm.day1

/**
 * LeetCode #152 — Maximum Product Subarray (Medium)
 *
 * 정수 배열에서 곱이 가장 큰 **연속 부분 배열**의 곱을 반환한다.
 * 예: [2, 3, -2, 4] → 6.
 *
 * 핵심: 곱셈은 음수 두 개가 만나면 양수가 되므로 매 위치에서 끝나는
 * **최대 곱(curMax)과 최소 곱(curMin)을 동시에** 추적해야 한다.
 * curMax를 먼저 갱신하면 curMin 계산이 망가지므로 tempMax에 저장 후 둘 다 갱신.
 * 복잡도 목표: 시간 O(N), 공간 O(1).
 */
fun maxProduct(nums: IntArray): Int {
    var maxN = nums[0]
    var minN = nums[0]
    var maxSoFar = nums[0]
//    print("input: ")
//    nums.forEach {
//        print("$it,")
//    }
//    println()
    nums.indices.drop(1).forEach { i ->
        val n = nums[i]
        // 최고값은 3개중 하나.
        // 1. 현재값,
        // 2. max * 현재 값
        // 3. min * 현재값 (마이너스 끼리 곱일 경우)
        val tempMax = maxOf(n, maxOf(n*maxN, n*minN))
        minN = minOf(n, minOf(n*maxN, n*minN))
        maxN = tempMax
//        println("max: $maxN, min: $minN, maxF: $maxSoFar")

        maxSoFar = maxOf(maxSoFar, maxN)
    }
    return maxSoFar
}
