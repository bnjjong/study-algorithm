package io.jjong.algorithm.ranking.runner

import io.jjong.algorithm.ranking.climbingLeaderboard
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Climbing the Leaderboard (HackerRank) stdin/stdout 러너.
 *
 * 입력 (4줄):
 *   n              ranked 점수 개수
 *   r1 r2 ... rn   내림차순 점수판
 *   m              player 점수 개수
 *   p1 p2 ... pm   각 플레이어 점수
 * 출력 (m줄): 각 플레이어의 순위.
 *
 * 예) 입력
 *   7
 *   100 100 50 40 40 20 10
 *   4
 *   5 25 50 120
 *   출력
 *   6
 *   4
 *   2
 *   1
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    br.readLine() // n — 다음 줄을 통째로 파싱하므로 개수 값은 쓰지 않고 소비만 한다
    val ranked = br.readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    br.readLine() // m — 동일하게 소비
    val player = br.readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    println(climbingLeaderboard(ranked, player).joinToString("\n"))
}
