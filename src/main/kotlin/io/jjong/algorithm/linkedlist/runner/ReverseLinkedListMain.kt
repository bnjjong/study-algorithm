package io.jjong.algorithm.linkedlist.runner

import io.jjong.algorithm.linkedlist.reverseList
import io.jjong.algorithm.linkedlist.toList
import io.jjong.algorithm.linkedlist.toListNode
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Reverse Linked List (LC #206) stdin/stdout 러너.
 *
 * 입력 (1줄): 공백으로 구분된 정수들. 빈 줄이면 빈 리스트.
 *   1 2 3 4 5
 * 출력 (1줄): 뒤집힌 결과.
 *   5 4 3 2 1
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val nums = br.readLine()?.trim()?.takeIf { it.isNotEmpty() }
        ?.split(" ")?.map { it.toInt() } ?: emptyList()

    val answer = reverseList(nums.toListNode()).toList()

    println(answer.joinToString(" "))
}
