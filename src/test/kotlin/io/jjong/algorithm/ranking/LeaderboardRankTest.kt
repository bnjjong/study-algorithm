package io.jjong.algorithm.ranking

import kotlin.test.Test
import kotlin.test.assertEquals

class LeaderboardRankTest {

    @Test
    fun `경쟁 랭킹 - 동점은 같은 등수`() {
        val lb = LeaderboardRank()
        lb.addScore(1, 50)
        lb.addScore(2, 80)
        lb.addScore(3, 80)
        lb.addScore(4, 30)

        assertEquals(1, lb.rank(2)) // 80, 최고점
        assertEquals(1, lb.rank(3)) // 80, 2번과 동점
        assertEquals(3, lb.rank(1)) // 50, 위에 80이 둘
        assertEquals(4, lb.rank(4)) // 30, 위에 셋
    }

    @Test
    fun `점수 누적으로 순위가 바뀐다`() {
        val lb = LeaderboardRank()
        lb.addScore(1, 50)
        lb.addScore(2, 80)
        lb.addScore(3, 80)
        lb.addScore(4, 30)
        lb.addScore(1, 40) // 1번 -> 90, 최고점

        assertEquals(1, lb.rank(1)) // 90
        assertEquals(2, lb.rank(2)) // 80, 위에 90 하나
        assertEquals(2, lb.rank(3)) // 80, 2번과 동점
        assertEquals(4, lb.rank(4)) // 30, 위에 셋
    }

    @Test
    fun `단일 플레이어는 1등`() {
        val lb = LeaderboardRank()
        lb.addScore(7, 10)
        assertEquals(1, lb.rank(7))
    }

    @Test
    fun `모두 동점이면 모두 1등`() {
        val lb = LeaderboardRank()
        lb.addScore(1, 100)
        lb.addScore(2, 100)
        lb.addScore(3, 100)
        assertEquals(1, lb.rank(1))
        assertEquals(1, lb.rank(2))
        assertEquals(1, lb.rank(3))
    }
}
