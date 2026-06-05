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
    TODO("curMax와 curMin을 동시에 추적하세요 (tempMax 임시변수 주의)")
}
