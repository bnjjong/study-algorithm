package io.jjong.algorithm.linkedlist

/**
 * HackerRank 연결 리스트 문제의 표준 노드 — 실제 시험 화면과 동일한 형태.
 *
 * HackerRank의 Kotlin 스텁은 [data]/[next]를 쓰는 `SinglyLinkedListNode`를 제공한다.
 * (우리 [ListNode]와 구조는 같고 값 필드 이름만 value → data 로 다르다.)
 * 실전과 똑같은 타입으로 연습하려고 그대로 옮겨 둔다.
 */
class SinglyLinkedListNode(var data: Int) {
    var next: SinglyLinkedListNode? = null
}

/** 정수 목록을 [SinglyLinkedListNode] 리스트로 만든다(테스트 입력용). 빈 목록이면 null. */
fun List<Int>.toSinglyLinkedList(): SinglyLinkedListNode? {
    val dummy = SinglyLinkedListNode(0)
    var cur = dummy
    for (v in this) {
        cur.next = SinglyLinkedListNode(v)
        cur = cur.next!!
    }
    return dummy.next
}

/** [SinglyLinkedListNode] 리스트를 정수 목록으로 펼친다(결과 검증용). */
fun SinglyLinkedListNode?.toList(): List<Int> {
    val out = mutableListOf<Int>()
    var cur = this
    while (cur != null) {
        out.add(cur.data)
        cur = cur.next
    }
    return out
}
