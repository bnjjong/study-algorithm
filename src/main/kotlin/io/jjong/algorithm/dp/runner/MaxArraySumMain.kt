package io.jjong.algorithm.dp.runner

import io.jjong.algorithm.dp.maxArraySum
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Max Array Sum (HackerRank) stdin/stdout 러너.
 * https://www.hackerrank.com/challenges/max-array-sum/problem
 *
 * 입력 (2줄):
 *   n              원소 개수
 *   a1 a2 ... an   정수 배열
 * 출력 (1줄): 비인접 부분집합의 최대 합.
 *
 * 예) 입력
 *   5
 *   3 7 4 6 5
 *   출력
 *   13
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    br.readLine() // n — 다음 줄을 통째로 파싱하므로 개수 값은 쓰지 않고 소비만 한다
    val arr = br.readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    println(maxArraySum(arr))
}
