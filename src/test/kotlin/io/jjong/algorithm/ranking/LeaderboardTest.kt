package io.jjong.algorithm.ranking

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

    // ── TreeMap 버전 (읽기 최적화) ──────────────────────
    @Test
    fun `TreeMap — 예시 시나리오`() {
        val board = LeaderboardTreeMap()
        board.addScore(1, 73)
        board.addScore(2, 56)
        board.addScore(3, 39)
        board.addScore(4, 51)
        board.addScore(5, 4)
        assertEquals(73, board.top(1))
        board.addScore(5, 21)
        assertEquals(180, board.top(3))
    }

    @Test
    fun `TreeMap — reset 은 0 버킷으로 보내 top 에 영향 없다`() {
        val board = LeaderboardTreeMap()
        board.addScore(1, 50)
        board.addScore(2, 30)
        board.reset(1)
        assertEquals(30, board.top(1)) // 1번이 0 → 2번(30)이 1등
        assertEquals(30, board.top(5)) // k가 인원보다 커도 0은 합에 무영향
    }

    @Test
    fun `TreeMap — 단순 버전과 top 결과가 항상 같다`() {
        val simple = Leaderboard()
        val tree = LeaderboardTreeMap()

        // 동일한 연산 시퀀스를 두 구현에 똑같이 적용 (누적·재등록 섞기)
        val adds = listOf(1 to 73, 2 to 56, 3 to 39, 4 to 51, 5 to 4, 5 to 21, 2 to 10, 1 to 5, 3 to 39)
        for ((id, s) in adds) {
            simple.addScore(id, s)
            tree.addScore(id, s)
        }
        for (k in 1..6) {
            assertEquals(simple.top(k), tree.top(k), "top($k) 불일치")
        }

        // reset 후에도 동일해야 한다
        simple.reset(2)
        tree.reset(2)
        for (k in 1..6) {
            assertEquals(simple.top(k), tree.top(k), "reset 후 top($k) 불일치")
        }
    }
}
