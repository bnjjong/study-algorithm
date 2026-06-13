package io.jjong.algorithm.linkedlist

/**
 * LeetCode #142 — Linked List Cycle II (Medium)
 *
 * 사이클이 있는지 판별하고, 있다면 사이클이 **시작되는 노드**를 반환한다.
 * 사이클이 없으면 null.
 *
 * - Input  : [head] — 사이클이 있을 수 있는 리스트의 머리 (null 가능)
 * - Output : 사이클이 시작되는 노드, 사이클이 없으면 null
 * - 예시   : 3→2→0→-4 에서 -4가 노드(2)로 연결되면 → 노드(값 2) 반환
 *
 * 핵심 패턴: Floyd 토끼와 거북이.
 * 1단계: slow(1칸)/fast(2칸)로 만나는 지점을 찾아 사이클 존재 확인.
 * 2단계: 한 포인터를 head로 보낸 뒤 둘을 같이 1칸씩 → 사이클 시작점에서 만난다 (L = nC - x).
 * 복잡도 목표: 시간 O(N), 공간 O(1).
 */
fun detectCycle(head: ListNode?): ListNode? {
    var slow = head // 1
    var fast = head // 1
    while(fast?.next != null) {
        // 언제 만나는지?
        slow = slow?.next
        fast = fast?.next?.next
        if (slow === fast) break
    }

    if (fast?.next == null) return null
    slow = head
    while(slow !== fast) {
        slow = slow?.next
        fast = fast?.next
    }

    return slow
}
