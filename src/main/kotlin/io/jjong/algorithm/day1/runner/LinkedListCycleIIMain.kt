package io.jjong.algorithm.day1.runner

import io.jjong.algorithm.day1.ListNode
import io.jjong.algorithm.day1.detectCycle
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Linked List Cycle II (LC #142) stdin/stdout 러너.
 *
 * 사이클은 표준입력으로 표현하기 까다로워 LeetCode 관례를 따른다.
 * 입력 (2줄):
 *   1줄 — 공백으로 구분된 노드 값들
 *   2줄 — pos: 마지막 노드가 연결되는 노드의 0-based 인덱스 (-1이면 사이클 없음)
 * 예) 1 2 3 4 5  /  2   → 인덱스 2에서 시작하는 사이클
 * 출력 (1줄): 사이클 시작 노드의 인덱스, 없으면 -1.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val values = br.readLine()?.trim()?.takeIf { it.isNotEmpty() }
        ?.split(" ")?.map { it.toInt() } ?: emptyList()
    val pos = br.readLine()?.trim()?.toIntOrNull() ?: -1

    val nodes = values.map { ListNode(it) }
    for (i in 0 until nodes.size - 1) nodes[i].next = nodes[i + 1]
    if (pos in nodes.indices) nodes.last().next = nodes[pos]

    val cycleNode = detectCycle(nodes.firstOrNull())
    val index = if (cycleNode == null) -1 else nodes.indexOfFirst { it === cycleNode }

    println(index)
}
