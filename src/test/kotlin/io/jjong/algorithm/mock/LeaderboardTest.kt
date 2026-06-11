package io.jjong.algorithm.mock

import kotlin.test.Test
import kotlin.test.assertEquals

class LeaderboardTest {

    @Test
    fun `예시 시나리오`() {
        val board = Leaderboard()
        board.addScore(1, 73)
        board.addScore(2, 56)
        board.addScore(3, 39)
        board.addScore(4, 51)
        board.addScore(5, 4)
        assertEquals(73, board.top(1))
        board.addScore(5, 21)            // 5번 → 25
        assertEquals(180, board.top(3))  // 73 + 56 + 51
    }

    @Test
    fun `addScore 는 누적된다`() {
        val board = Leaderboard()
        board.addScore(1, 10)
        board.addScore(1, 15)
        assertEquals(25, board.top(1))
    }

    @Test
    fun `reset 후 점수는 0`() {
        val board = Leaderboard()
        board.addScore(1, 50)
        board.addScore(2, 30)
        board.reset(1)
        assertEquals(30, board.top(1)) // 1번이 0 → 2번(30)이 1등
    }

    @Test
    fun `top k 가 전체 인원수면 모든 점수 합`() {
        val board = Leaderboard()
        board.addScore(1, 5)
        board.addScore(2, 10)
        board.addScore(3, 15)
        assertEquals(30, board.top(3))
    }

    @Test
    fun `reset 후 다시 점수를 쌓을 수 있다`() {
        val board = Leaderboard()
        board.addScore(1, 100)
        board.reset(1)
        board.addScore(1, 7)
        assertEquals(7, board.top(1))
    }
}
