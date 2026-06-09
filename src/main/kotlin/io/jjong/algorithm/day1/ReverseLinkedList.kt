package io.jjong.algorithm.day1

/**
 * LeetCode #206 — Reverse Linked List (Easy)
 *
 * 연결 리스트를 뒤집어서 새 head를 반환한다. 1→2→3→4→5 가 들어오면 5→4→3→2→1.
 *
 * - Input  : [head] — 리스트의 머리 (null 가능)
 * - Output : 뒤집힌 리스트의 새 머리 (입력이 null이면 null)
 *
 * 핵심 패턴: 3-pointer (prev / cur / next).
 * 반드시 next를 먼저 저장한 뒤 cur.next = prev 로 방향을 뒤집어야 다음 노드 주소를 잃지 않는다.
 * 복잡도 목표: 시간 O(N), 공간 O(1).
 */
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
        // 이 루프에서 처리 해야될것 을 정리
        // 초기값 prev = null, cur:1, next = 2
        // null, 1->2 // null<-1<-2 이렇게 변경
//        println("prev: ${prev?.value}, cur: ${cur?.value}, next: ${next?.value}" )
        val next = cur.next

        // null <- 1
        cur.next = prev

        // 다음 초기값 prev1, cur:2, next3
        prev = cur
        cur = next
    }
    return prev
}
