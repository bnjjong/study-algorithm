package io.jjong.algorithm.day1.runner

import io.jjong.algorithm.day1.addTwoNumbers
import io.jjong.algorithm.day1.toList
import io.jjong.algorithm.day1.toListNode
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Add Two Numbers (LC #2) stdin/stdout 러너.
 *
 * 입력 (2줄): 각 줄은 한 자릿수씩 거꾸로 저장된 수. 공백으로 구분.
 *   2 4 3
 *   5 6 4
 * 출력 (1줄): 합을 같은 형식으로. 공백 구분.
 *   7 0 8
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val l1 = br.readLine().parseInts().toListNode()
    val l2 = br.readLine().parseInts().toListNode()

    val answer = addTwoNumbers(l1, l2).toList()

    println(answer.joinToString(" "))
}

private fun String?.parseInts(): List<Int> =
    this?.trim()?.takeIf { it.isNotEmpty() }
        ?.split(" ")
        ?.map { it.toInt() }
        ?: emptyList()
