package io.jjong.algorithm.dp.runner

import io.jjong.algorithm.dp.maxProduct
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Maximum Product Subarray (LC #152) stdin/stdout 러너.
 *
 * 입력 (1줄): 공백으로 구분된 정수들.
 *   2 3 -2 4
 * 출력 (1줄): 연속 부분 배열의 최대 곱.
 *   6
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nums = br.readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    println(maxProduct(nums))
}
