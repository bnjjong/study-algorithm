package io.jjong.algorithm.linkedlist

/**
 * [응용 복습 #3] Reorder List (LeetCode 143) — 세 기술 합성
 *
 * L0→L1→…→Ln-1→Ln 을  L0→Ln→L1→Ln-1→L2→Ln-2→… 로 **제자리** 재배열한다.
 * (값을 바꾸지 말고 '노드'를 옮길 것.)
 *   [1,2,3,4]    → [1,4,2,3]
 *   [1,2,3,4,5]  → [1,5,2,4,3]
 *
 * 풀이 = 세 단계 조립:
 *   ① 중간 찾기   — slow(1칸)/fast(2칸). fast가 끝에 닿으면 slow는 앞 절반의 마지막.
 *   ② 후반 뒤집기 — 뒤 절반을 reverse.
 *   ③ 교차 병합   — 앞/뒤를 한 노드씩 번갈아 끼운다.
 */
fun reorderList(head: SinglyLinkedListNode?) {
    if (head?.next == null) return // 노드 0개·1개면 그대로

    // ① 중간 찾기: fast가 끝나면 slow = 앞 절반의 마지막 노드
    var slow = head
    var fast: SinglyLinkedListNode? = head
    while (fast?.next != null && fast.next?.next != null) {
        slow = slow!!.next
        fast = fast.next?.next
    }

    // 앞/뒤로 끊는다.  [1,2,3,4] → 앞 1→2, 뒤 3→4
    var second = slow!!.next //1,2,null
    slow.next = null

    // ② 뒤 절반 reverse.  3→4  ⇒  4→3
    var prev: SinglyLinkedListNode? = null
    while (second != null) {
        val next = second.next // 4
        second.next = prev // 3 -> null
        prev = second //prev = 3
        second = next // secode = 4
    }
    // prev = 뒤 절반의 새 head(역순)

    // ③ 앞(first)·뒤(back)를 번갈아 끼운다.  1→2 + 4→3  ⇒  1→4→2→3
    var first: SinglyLinkedListNode? = head
    var back = prev
    while (back != null) {
        val fNext = first!!.next
        val bNext = back.next
        first.next = back
        back.next = fNext
        first = fNext
        back = bNext
    }
}
