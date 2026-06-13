package io.jjong.algorithm.ranking

import kotlin.test.Test
import kotlin.test.assertContentEquals

/**
 * 투 포인터 버전 검증. player가 **반드시 오름차순**인 입력만 사용한다(투 포인터의 전제).
 * 검증된 이진 탐색 버전([climbingLeaderboard])과 항상 같은 답을 내야 한다.
 */
class ClimbingLeaderboardTwoPointerTest {

    @Test
    fun `HackerRank 샘플 1`() {
        val ranked = intArrayOf(100, 100, 50, 40, 40, 20, 10)
        val player = intArrayOf(5, 25, 50, 120) // 오름차순
        assertContentEquals(intArrayOf(6, 4, 2, 1), climbingLeaderboardTwoPointer(ranked, player))
    }

    @Test
    fun `HackerRank 샘플 2`() {
        val ranked = intArrayOf(100, 90, 90, 80, 75, 60)
        val player = intArrayOf(50, 65, 77, 90, 102) // 오름차순
        assertContentEquals(intArrayOf(6, 5, 4, 2, 1), climbingLeaderboardTwoPointer(ranked, player))
    }

    @Test
    fun `동점과 경계 (player 오름차순)`() {
        // distinct = [100, 50] → 50은 2위, 100·120은 1위
        val ranked = intArrayOf(100, 100, 50)
        assertContentEquals(intArrayOf(2, 2, 1, 1), climbingLeaderboardTwoPointer(ranked, intArrayOf(50, 50, 100, 120)))
    }

    @Test
    fun `이진 탐색 버전과 동일한 답`() {
        // 두 구현이 같은 입력(player 오름차순)에 대해 일치하는지 교차 검증
        val ranked = intArrayOf(982, 782, 426, 282, 246, 193, 178)
        val player = intArrayOf(4, 271, 280, 765, 945) // 오름차순
        assertContentEquals(
            climbingLeaderboard(ranked, player),
            climbingLeaderboardTwoPointer(ranked, player)
        )
    }
}
