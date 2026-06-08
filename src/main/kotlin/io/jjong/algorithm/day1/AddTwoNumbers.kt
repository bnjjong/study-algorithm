package io.jjong.algorithm.day1

/**
 * LeetCode #2 — Add Two Numbers (Medium)
 *
 * 두 개의 연결 리스트가 각각 한 자릿수씩 **거꾸로** 저장한 음이 아닌 정수를 나타낸다.
 * 예: 342 는 [2, 4, 3]. 두 수의 합을 같은 형식의 연결 리스트로 반환한다.
 *
 * 핵심 패턴: Dummy Head + carry(올림수).
 * 복잡도 목표: 시간 O(max(m, n)), 공간 O(max(m, n)).
 *
 * 엣지케이스: 길이가 다를 때, 마지막에 carry가 남을 때([9,9]+[1]=[0,0,1]).
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    // 변수 선언
    val dummy = ListNode(0)
    var cur = dummy
    var p1 = l1
    var p2 = l2
    var carry = 0
    // 루프가 돌아가야 하는 조건들을 잘 고민해야됨.
    while (p1 != null || p2 != null || carry != 0) {
        val sum = (p1?.value ?: 0) + (p2?.value ?: 0) + carry
        carry = sum / 10
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        p1 = p1?.next
        p2 = p2?.next
    }
    return dummy.next
}
