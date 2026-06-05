package io.jjong.algorithm.day1

/**
 * 단일 연결 리스트의 노드.
 *
 * LeetCode/HackerRank에서 Linked List 문제에 항상 주어지는 표준 노드 구조를
 * Kotlin으로 옮긴 것. [value]는 노드가 담는 정수 데이터, [next]는 다음 노드를
 * 가리키는 포인터이며 마지막 노드에서는 null이다.
 */
class ListNode(
    var value: Int,
    var next: ListNode? = null,
)

/**
 * 정수 목록을 [ListNode] 연결 리스트로 변환한다. 테스트 입력을 손쉽게 만들기 위한 헬퍼.
 *
 * 빈 목록이면 null을 반환한다.
 */
fun List<Int>.toListNode(): ListNode? {
    val dummy = ListNode(0)
    var cur = dummy
    for (v in this) {
        cur.next = ListNode(v)
        cur = cur.next!!
    }
    return dummy.next
}

/**
 * 연결 리스트를 정수 목록으로 펼친다. 결과 검증을 쉽게 하기 위한 헬퍼이며,
 * 사이클이 있는 리스트에 사용하면 무한 루프에 빠지므로 주의한다.
 */
fun ListNode?.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var cur = this
    while (cur != null) {
        result.add(cur.value)
        cur = cur.next
    }
    return result
}
