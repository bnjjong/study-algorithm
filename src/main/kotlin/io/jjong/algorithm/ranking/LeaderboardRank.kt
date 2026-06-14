package io.jjong.algorithm.ranking

import java.util.TreeMap

/**
 * 1244. Design A Leaderboard — Rank Query                              Medium
 * ---------------------------------------------------------------------------
 * Design a leaderboard that supports cumulative scoring and rank queries.
 *
 * Implement the LeaderboardRank class:
 *   - addScore(playerId, score): Add `score` to the player's total. If the
 *     player is not registered yet, register them with `score`.
 *   - rank(playerId): Return the player's 1-based rank using COMPETITION
 *     ranking — a player's rank is 1 plus the number of players whose total
 *     score is STRICTLY greater. Players with the same score share the same
 *     rank.  (scores 80, 80, 50, 30  ->  ranks 1, 1, 3, 4)
 *
 * Example:
 *   addScore(1, 50)
 *   addScore(2, 80)
 *   addScore(3, 80)
 *   addScore(4, 30)
 *   rank(2) -> 1     // 80 is the highest
 *   rank(3) -> 1     // tie with player 2
 *   rank(1) -> 3     // two players (80, 80) score higher
 *   rank(4) -> 4     // three players score higher
 *   addScore(1, 40)  // player 1 -> 90, now the highest
 *   rank(1) -> 1
 *   rank(2) -> 2
 *
 * Constraints:
 *   - 1 <= playerId <= 10^5
 *   - rank() is only called for a registered playerId
 *   - addScore() and rank() may be called up to 10^5 times in total
 *
 * Follow up: Can rank() run faster than O(n) per call when scores change
 * frequently?
 */
class LeaderboardRank {
    // playerId, total score
    private val userMap = HashMap<Int,Int>() //Long 으로 할지?
    // score, count
    private val scoreTree = TreeMap<Int, Int>()

    fun addScore(playerId: Int, score: Int) {
        // 저장하는 것은 o(1)
        val old = userMap.get(playerId)
        val new = (old?:0) + score
        userMap[playerId] = new
        if (old != null) leaveBucket(old)
        enterBucket(new)
    }

    private fun enterBucket(new: Int) {
        scoreTree.merge(new, 1, Int::plus)
    }

    private fun leaveBucket(old: Int) {
        val count = scoreTree[old] ?: return
        if (count <= 1) {
            scoreTree.remove(old)
        } else {
            scoreTree[old] = count - 1
        }
    }

    fun rank(playerId: Int): Int {
        // 조회 할때는 일단 player에 score 를 가져오고,
        // 순서대로 조회 하면서 해당 rank 값을 확인해본다.
        // 최악의 경우 가장끝에 있는 Tree의 값을 가져와야 한다 그래도 o(n)은 만족할수 있음.
        val score = userMap.get(playerId) ?: -1
        var rank = 1
        for ((s,c) in scoreTree.descendingMap()) {
            if (score == s) {
                break
            }
            // 1등 2명 다음 랭커는 3등이 되어야 함.
            rank += c
        }
        return rank
    }
}
