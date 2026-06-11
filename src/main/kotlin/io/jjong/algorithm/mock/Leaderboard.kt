package io.jjong.algorithm.mock

import java.util.TreeMap

/**
 * [D2 모의 · 문제 1] 실시간 리더보드 (Design A Leaderboard)
 *
 * 게임/커머스의 실시간 랭킹을 관리하는 자료구조. 플레이어별 점수를 누적하고,
 * 언제든 "상위 K명의 점수 합"을 질의할 수 있어야 한다.
 *
 * 연산
 * - [addScore] : 플레이어 [playerId]의 점수에 [score]를 더한다(누적). 없으면 새로 등록.
 * - [top]      : 점수 상위 [k]명의 점수 합을 반환한다.
 * - [reset]    : 플레이어 [playerId]의 점수를 0으로 되돌린다(이미 등록된 플레이어만 호출).
 *
 * 예시
 *   addScore(1,73); addScore(2,56); addScore(3,39); addScore(4,51); addScore(5,4)
 *   top(1)  → 73                 // 1등(73)
 *   addScore(5,21)               // 5번 → 4+21 = 25
 *   top(3)  → 73 + 56 + 51 = 180 // 상위 3명 점수 합
 *
 * 무신사 Search&Rec의 "실시간 인기/랭킹" 시나리오.
 * 핵심: 쓰기([addScore])와 읽기([top]) 중 무엇이 잦은지에 따라 전략이 갈린다.
 */
class Leaderboard {
    private val scoreMap = HashMap<Int, Int>()

    fun addScore(playerId: Int, score: Int) {
        scoreMap.merge(playerId, score, {o, n ->
            o + n
        })
    }

    fun top(k: Int): Int {
        return scoreMap.entries
            .sortedByDescending { it.value}
            .take(k)
            .sumOf { it.value }
    }

    fun reset(playerId: Int) {
        scoreMap[playerId] = 0
    }
}

/**
 * [심화] TreeMap 버전 — `top`을 자주 호출할 때 유리한 "읽기 최적화" 설계.
 *
 * 단순 버전은 매 [top]마다 전체 정렬(O(n log n))을 다시 한다. 대신 여기서는
 * **점수를 항상 정렬된 상태로 유지**해, 정렬 없이 상위부터 훑어 합산한다.
 * 같은 점수를 여러 명이 가질 수 있으므로 `점수 → 인원수`로 묶는 게 핵심.
 *
 * - [playerScore] : 플레이어의 현재 점수. 갱신 시 *옛 점수 버킷*에서 빼고 *새 버킷*에 넣기 위해 필요.
 * - [scoreCount]  : 점수별 인원수. `TreeMap`이라 키(점수)가 정렬돼 있어 상위부터 순회하기 쉽다.
 *
 * 복잡도: [addScore]/[reset] O(log D), [top] O(min(k, D)).  (D = 서로 다른 점수 종류 수)
 * 트레이드오프: addScore가 O(1) → O(log D)로 느려지고 두 구조를 동기화해야 하지만,
 *   top이 정렬 비용 없이 빨라진다. → 읽기(top)가 쓰기(addScore)보다 잦을 때 채택.
 */
class LeaderboardTreeMap {
    private val playerScore = HashMap<Int, Int>()
    private val scoreCount = TreeMap<Int, Int>()

    /** 플레이어 점수를 누적하고, 옛 점수 버킷에서 새 점수 버킷으로 인원을 옮긴다. */
    fun addScore(playerId: Int, score: Int) {
        val old = playerScore[playerId]
        val new = (old ?: 0) + score
        playerScore[playerId] = new
        if (old != null) decrement(old) // 처음 등록(old == null)이면 옛 버킷이 없다
        increment(new)
    }

    /** 점수 높은 버킷부터 내려오며 k명을 채울 때까지 합산한다. */
    fun top(k: Int): Int {
        var remain = k
        var sum = 0
        for ((score, count) in scoreCount.descendingMap()) { // 큰 점수부터
            if (remain == 0) break
            val taken = minOf(remain, count) // 같은 점수 인원은 한 번에 처리
            sum += score * taken
            remain -= taken
        }
        return sum
    }

    /** 점수를 0으로 되돌린다. reset된 플레이어는 0 버킷으로 가며, top 합산엔 0이라 영향이 없다. */
    fun reset(playerId: Int) {
        val old = playerScore[playerId] ?: return
        decrement(old)
        playerScore[playerId] = 0
        increment(0)
    }

    private fun increment(score: Int) {
        scoreCount.merge(score, 1, Int::plus)
    }

    private fun decrement(score: Int) {
        val count = scoreCount[score] ?: return
        if (count <= 1) scoreCount.remove(score) else scoreCount[score] = count - 1
    }
}
