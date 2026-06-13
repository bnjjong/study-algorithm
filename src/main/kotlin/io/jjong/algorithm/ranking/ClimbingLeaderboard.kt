package io.jjong.algorithm.ranking

/**
 * HackerRank — Climbing the Leaderboard (Medium)
 *
 * 내림차순 점수판 [ranked]에 새 점수가 들어올 때마다 그 플레이어의 **순위**를 구한다.
 * 순위는 dense ranking — 동점이면 같은 순위이고, 점수 사이 간격이 순위를 건너뛰지 않는다.
 *
 * 핵심: 중복을 제거해 고유 점수만 남기면, 플레이어 점수 [p]의 순위는
 * "p보다 높은 고유 점수의 개수 + 1"이다. 점수판이 내림차순이라, 오름차순 lowerBound의
 * 부등호(`<`)를 뒤집은(`>`) 이진 탐색으로 그 개수를 O(log N)에 찾는다.
 * 복잡도: 전처리 O(N), 쿼리당 O(log N) → 전체 O(N + M log N).
 */
fun climbingLeaderboard(ranked: IntArray, player: IntArray): IntArray {
    val uniqueScores = ranked.distinct() // 내림차순 유지 + 중복 제거 (dense ranking의 핵심)
    return IntArray(player.size) { i -> denseRankOf(uniqueScores, player[i]) }
}

/**
 * 내림차순 고유 점수 [descendingScores]에서 [score]의 dense 순위(1-based)를 이진 탐색으로 구한다.
 * "[score]보다 높은 점수의 개수"가 0-based 위치이고, 거기에 +1 한 값이 순위다.
 */
private fun denseRankOf(descendingScores: List<Int>, score: Int): Int {
    var lo = 0
    var hi = descendingScores.size
    while (lo < hi) {
        val mid = (lo + hi) ushr 1
        // 내림차순이므로: 이 점수가 score보다 높으면(초과) 순위가 더 뒤 → 오른쪽으로
        if (descendingScores[mid] > score) lo = mid + 1
        else hi = mid
    }
    return lo + 1
}

/**
 * Climbing the Leaderboard — 투 포인터 풀이 (O(N + M)).
 *
 * [climbingLeaderboard]는 player마다 독립적으로 이진 탐색해 O(M log N)이다. 하지만 이 문제는
 * **[player]가 오름차순으로 주어진다**는 전제가 있어, 포인터를 한 방향으로만 굴리면 점수판을
 * 되짚어 올라갈 필요가 없어진다 → 전체 O(N + M).
 *
 * ⚠️ 전제: [player]는 오름차순이어야 한다(HackerRank 보장). 아니면 결과가 틀린다.
 *
 * 아이디어:
 *   - 점수판을 distinct()해 내림차순 고유 점수를 얻는다(이진 탐색 버전과 동일).
 *   - 포인터를 점수판 **끝(가장 낮은 점수)**에 두고, player를 **낮은 것부터** 처리한다.
 *   - 각 player에 대해 "포인터가 가리키는 점수가 player 이하인 동안" 포인터를 **위(앞)로** 옮긴다.
 *   - 멈춘 위치로부터 dense 순위를 계산한다("초과 점수 개수 + 1"과 같아지도록).
 *   - player가 커질수록 포인터는 위로만 가므로 되돌아오지 않는다 → 두 포인터가 각자 한 번씩만 훑음.
 */
fun climbingLeaderboardTwoPointer(ranked: IntArray, player: IntArray): IntArray {
    val distincted = ranked.distinct()
    val result = IntArray(player.size)
    var idx = distincted.lastIndex // 마지막부터 가져옴.
    for (i in player.indices) {
        // 100,100,50,30,10 -> 100,50,30,10
        // 20, 50, 60, 100
        // idx = 3
        // 20 >= 10 -> idx = 2
        // 30 >= 20
        // result[0] = idx+2 = 4
        // i -> 50, idx = 2
        // 50 >= 30 = idx = 1
        // 50 >= 50 = idx = 0
        // 50 >= 100 = result[1] = 2
        // i = 60, idx = 0
        // 60 >= 100
        // result[2] = 2
        // i -> 100, idx = 0
        // 100 >= 100 -> idx -1
        // result[4] = -1+2 = 1
        while(idx >= 0 && player[i] >= distincted[idx]) idx--
        result[i] = idx + 2 // index 가 0부터 시작하므로 +1, distincted[idx] 는 나보다 앞의 점수임. 그래서 +1 해야됨.
    }
    return result

}
