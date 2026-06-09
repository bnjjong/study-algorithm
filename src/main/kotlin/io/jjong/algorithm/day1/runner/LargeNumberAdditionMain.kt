package io.jjong.algorithm.day1.runner

import io.jjong.algorithm.day1.addLargeNumbers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger

/**
 * Java BigInteger (HackerRank) stdin/stdout 러너.
 * https://www.hackerrank.com/challenges/java-biginteger/problem
 *
 * 입력 (2줄): a, b  (각각 비음수 정수, 매우 큰 수일 수 있음)
 * 출력 (2줄): a + b,  그리고 a * b
 *
 * 합은 우리가 직접 구현한 addLargeNumbers로 출력하고, 곱은 BigInteger로 처리한다.
 * (곱셈까지 자리별로 직접 구현하는 것은 도전 과제로 남겨둔다.)
 *
 * 예) 입력             출력
 *   1234              1358
 *    124               153016
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val a = br.readLine().trim()
    val b = br.readLine().trim()
    println(addLargeNumbers(a, b))          // 직접 구현한 덧셈 (Add Two Numbers 복습)
    println(BigInteger(a) * BigInteger(b))  // 곱은 BigInteger로
}
