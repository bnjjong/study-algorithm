package io.jjong.algorithm.day1

/**
 * HackerRank — Max Array Sum (Medium)
 * https://www.hackerrank.com/challenges/max-array-sum/problem
 *
 * 배열에서 **인접하지 않은** 원소들의 부분집합 중 최대 합을 구한다.
 * [arr]의 i번째를 고르면 i-1·i+1번째는 고를 수 없다. 음수가 섞일 수 있다.
 * 예: [3, 7, 4, 6, 5] → 13 (7+6),  [2, 1, 5, 8, 4] → 11 (2+5+4)
 *
 * - Input  : [arr] — 정수 배열 (음수 포함 가능, 최소 1개 원소)
 * - Output : 비인접 부분집합의 최대 합 (Int)
 * - [trace]가 true면 각 칸에서 take·skip이 갱신되는 과정을 표준출력으로 찍는다 (학습/디버깅용).
 *
 * 복습 포인트(day1 DP):
 *   Maximum Product Subarray가 max·min 두 값을 추적했듯, 여기선 "직전 원소를 포함/미포함"
 *   두 상태로 점화식을 세운다 — 현재를 쓰면 i-2까지의 최선 + arr[i], 안 쓰면 i-1까지의 최선.
 *   (Max Product는 '연속'이 제약, 이 문제는 '인접 금지'가 제약 — 정반대라 비교 학습에 좋다.)
 */
fun maxArraySum(arr: IntArray, trace: Boolean = false): Int {
    var take = arr[0] // 직전 원소를 '포함'했을 때의 최대합
    var skip = 0      // 직전 원소를 '건너뛰었을' 때의 최대합 (아무것도 안 고르면 0)
    if (trace) {
        println("=== maxArraySum(${arr.toList()}) ===")
        println("초기   : take=$take  (idx0을 '포함'),  skip=0  (아무것도 안 고름)")
        println("─".repeat(64))
    }
    arr.indices.drop(1).forEach { i ->
        val newTake = skip + arr[i]     // 지금 고르려면 직전은 '건너뛴' 상태여야 한다
        val newSkip = maxOf(take, skip) // 지금 건너뛰면 직전까지의 최선을 그대로 물려받는다
        if (trace) {
            println("i=$i  arr[i]=${arr[i]}")
            println("  포함 newTake = skip($skip) + ${arr[i]} = $newTake".padEnd(40) + "← 직전(idx${i - 1})을 건너뛴 뒤 idx$i 선택")
            println("  제외 newSkip = max(take $take, skip $skip) = $newSkip".padEnd(40) + "← idx$i 안 쓰고 직전까지의 최선 유지")
            println("  ⇒ take=$newTake, skip=$newSkip")
        }
        take = newTake
        skip = newSkip
    }
    if (trace) {
        println("─".repeat(64))
        println("정답 = max(take $take, skip $skip) = ${maxOf(take, skip)}")
        println()
    }
    return maxOf(take, skip)
}
