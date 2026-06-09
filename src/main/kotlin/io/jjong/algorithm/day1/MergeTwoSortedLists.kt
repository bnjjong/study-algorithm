package io.jjong.algorithm.day1

/**
 * LeetCode #21 — Merge Two Sorted Lists (Easy)
 *
 * 오름차순으로 정렬된 두 연결 리스트를 하나의 정렬된 리스트로 병합한다.
 * **새 노드를 만들지 말고 기존 노드를 이어 붙여(splice)** 만드는 것이 정석.
 * 예: 1→2→4, 1→3→4  →  1→1→2→3→4→4
 *
 * - Input  : [l1], [l2] — 각각 오름차순 정렬된 리스트 (한쪽/양쪽 null 가능)
 * - Output : 두 리스트를 합친 오름차순 리스트 (기존 노드를 재사용해 연결)
 *
 * 핵심 패턴: Dummy Head + 두 포인터 직접 순회. (List 변환 금지!)
 * 복잡도 목표: 시간 O(m + n), 공간 O(1).
 *
 * 엣지케이스: 한쪽이 null, 둘 다 null, 길이가 크게 다를 때, 한쪽이 전부 더 작을 때.
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    var dummy = ListNode(0)
    var cur = dummy
    var p1 = l1
    var p2 = l2
    while(p1 != null && p2 != null) {
        if (p1.value <= p2.value) {
            cur.next = p1
            p1 = p1.next
        } else {
            cur.next = p2
            p2 = p2.next
        }
        cur = cur.next!!
    }
    cur.next = p1 ?: p2
    return dummy.next
}