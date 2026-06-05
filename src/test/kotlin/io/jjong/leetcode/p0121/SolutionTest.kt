package io.jjong.leetcode.p0121

import kotlin.test.Test
import kotlin.test.assertEquals

class SolutionTest {

    private val solution = Solution()

    // ---- 기본 예시 ----

    @Test
    fun `문제 예시 1 - 일반적인 케이스`() {
        // 1에 사서 6에 팔면 5
        assertEquals(5, solution.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
    }

    @Test
    fun `문제 예시 1 - 응용 케이스`() {
        // 1에 사서 6에 팔면 5
        assertEquals(5, solution.maxProfit(intArrayOf(7, 3, 4, 5, 1, 6)))
    }

    @Test
    fun `문제 예시 2 - 계속 떨어지면 0`() {
        assertEquals(0, solution.maxProfit(intArrayOf(7, 6, 4, 3, 1)))
    }

    // ---- 길이 경계값 ----

    @Test
    fun `원소 1개면 거래 불가능 0`() {
        assertEquals(0, solution.maxProfit(intArrayOf(5)))
    }

    @Test
    fun `원소 2개 - 오르면 차이만큼 이익`() {
        assertEquals(4, solution.maxProfit(intArrayOf(1, 5)))
    }

    @Test
    fun `원소 2개 - 내리면 0`() {
        assertEquals(0, solution.maxProfit(intArrayOf(5, 1)))
    }

    // ---- 단조 케이스 ----

    @Test
    fun `계속 오르기만 하면 처음에 사서 마지막에 판 차이`() {
        assertEquals(4, solution.maxProfit(intArrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `모두 같은 값이면 0`() {
        assertEquals(0, solution.maxProfit(intArrayOf(3, 3, 3, 3)))
    }

    // ---- 정답 위치 다양화 ----

    @Test
    fun `최저가가 중간에 있고 그 뒤 최고가가 등장`() {
        // [3, 2, 6, 5, 0, 3, 100]: 0에 사서 100에 팔면 100
        assertEquals(100, solution.maxProfit(intArrayOf(3, 2, 6, 5, 0, 3, 100)))
    }

    @Test
    fun `최저가가 중간 - V자 패턴`() {
        // [3, 2, 1, 2, 3, 4]: 1에 사서 4에 팔면 3
        assertEquals(3, solution.maxProfit(intArrayOf(3, 2, 1, 2, 3, 4)))
    }

    @Test
    fun `최저가가 처음 - 최고가가 마지막`() {
        // [1, 2, 3, 4, 5, 6, 7]: 1 → 7
        assertEquals(6, solution.maxProfit(intArrayOf(1, 2, 3, 4, 5, 6, 7)))
    }

    @Test
    fun `최저가가 처음이지만 그 뒤 큰 변동`() {
        // [2, 4, 1, 5]: 1에 사서 5에 팔면 4 (최저가가 인덱스 0이 아닌 2)
        assertEquals(4, solution.maxProfit(intArrayOf(2, 4, 1, 5)))
    }

    @Test
    fun `여러 번의 골짜기와 봉우리`() {
        // [3, 1, 4, 1, 5, 9, 2, 6]: 1에 사서 9에 팔면 8
        assertEquals(8, solution.maxProfit(intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)))
    }

    @Test
    fun `최고 이익 페어가 인접하지 않은 경우`() {
        // [10, 1, 100, 50, 200]: 1에 사서 200에 팔면 199
        assertEquals(199, solution.maxProfit(intArrayOf(10, 1, 100, 50, 200)))
    }

    @Test
    fun `최저가 이후로 새로운 최저가가 등장하지만 이익은 안 늘어남`() {
        // [5, 1, 3, 0, 1]: 1에 사서 3에 팔면 2 (마지막 0,1 페어는 1)
        assertEquals(2, solution.maxProfit(intArrayOf(5, 1, 3, 0, 1)))
    }

    @Test
    fun `최저가 이후 새로운 최저가에서 더 큰 이익이 나오는 경우`() {
        // [4, 5, 1, 10]: 1에 사서 10에 팔면 9
        assertEquals(9, solution.maxProfit(intArrayOf(4, 5, 1, 10)))
    }

    // ---- 0과 큰 값 ----

    @Test
    fun `0에서 사서 큰 값에 팔기`() {
        assertEquals(10000, solution.maxProfit(intArrayOf(0, 10000)))
    }

    @Test
    fun `모두 0이면 0`() {
        assertEquals(0, solution.maxProfit(intArrayOf(0, 0, 0, 0)))
    }

    @Test
    fun `최고가가 처음에 있고 이후 떨어지기만`() {
        assertEquals(0, solution.maxProfit(intArrayOf(10000, 5, 3, 1, 0)))
    }

    // ---- 중복 값 패턴 ----

    @Test
    fun `같은 가격 반복 후 상승`() {
        // [1, 1, 1, 1, 5]: 1에 사서 5에 팔면 4
        assertEquals(4, solution.maxProfit(intArrayOf(1, 1, 1, 1, 5)))
    }

    @Test
    fun `상승 후 같은 가격 반복`() {
        // [1, 5, 5, 5, 5]: 1 → 5 = 4
        assertEquals(4, solution.maxProfit(intArrayOf(1, 5, 5, 5, 5)))
    }
}
