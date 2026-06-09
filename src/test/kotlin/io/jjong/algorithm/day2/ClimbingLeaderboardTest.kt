package io.jjong.algorithm.day2

import kotlin.test.Test
import kotlin.test.assertContentEquals

class ClimbingLeaderboardTest {

    @Test
    fun `HackerRank 샘플 1`() {
        val ranked = intArrayOf(100, 100, 50, 40, 40, 20, 10)
        val player = intArrayOf(5, 25, 50, 120)
        assertContentEquals(intArrayOf(6, 4, 2, 1), climbingLeaderboard(ranked, player))
    }

    @Test
    fun `HackerRank 샘플 2`() {
        val ranked = intArrayOf(100, 90, 90, 80, 75, 60)
        val player = intArrayOf(50, 65, 77, 90, 102)
        assertContentEquals(intArrayOf(6, 5, 4, 2, 1), climbingLeaderboard(ranked, player))
    }

    @Test
    fun `동점이면 같은 순위(dense ranking)`() {
        // distinct = [100, 50] → 50은 2위(위에 고유점수 100 하나), 100은 1위
        val ranked = intArrayOf(100, 100, 50)
        assertContentEquals(intArrayOf(2, 2, 1), climbingLeaderboard(ranked, intArrayOf(50, 50, 100)))
    }

    @Test
    fun `최고점 초과는 1위, 최저점 미만은 꼴찌 다음`() {
        // distinct = [90, 80, 70] → 95는 모두 초과해 1위, 60은 아무것도 못 넘어 4위
        val ranked = intArrayOf(90, 80, 70)
        assertContentEquals(intArrayOf(1, 4), climbingLeaderboard(ranked, intArrayOf(95, 60)))
    }

    @Test
    fun `점수판이 한 종류뿐`() {
        val ranked = intArrayOf(50, 50, 50)
        // distinct = [50] → 50은 1위, 40은 2위, 60은 1위
        assertContentEquals(intArrayOf(1, 2, 1), climbingLeaderboard(ranked, intArrayOf(50, 40, 60)))
    }
}
