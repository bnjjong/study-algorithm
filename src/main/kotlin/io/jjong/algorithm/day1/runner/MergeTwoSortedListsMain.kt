package io.jjong.algorithm.day1.runner

import io.jjong.algorithm.day1.mergeTwoLists
import io.jjong.algorithm.day1.toList
import io.jjong.algorithm.day1.toListNode
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Merge Two Sorted Lists (LC #21) stdin/stdout 러너.
 *
 * 입력 (2줄): 각 줄은 오름차순 정렬된 정수들. 공백 구분. 빈 줄이면 빈 리스트.
 *   1 2 4
 *   1 3 4
 * 출력 (1줄): 병합된 정렬 리스트.
 *   1 1 2 3 4 4
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val l1 = br.readLine().parseInts().toListNode()
    val l2 = br.readLine().parseInts().toListNode()

    val answer = mergeTwoLists(l1, l2).toList()

    println(answer.joinToString(" "))
}

private fun String?.parseInts(): List<Int> =
    this?.trim()?.takeIf { it.isNotEmpty() }
        ?.split(" ")
        ?.map { it.toInt() }
        ?: emptyList()
