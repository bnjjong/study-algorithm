package io.jjong.algorithm.linkedlist

import java.util.Stack

/**
 * [응용 복습 #1] Add Two Numbers II (LeetCode 445) — HackerRank 스타일 노드 사용
 *
 * 원조(Add Two Numbers)는 자릿수가 *역방향*(최하위 먼저) 저장이라 head부터 더하면 됐다.
 * 이번엔 *정방향*(최상위 먼저) 저장이다. 즉 7→2→4→3 은 7243을 뜻한다.
 *   7243 + 564 = 7807  →  7→8→0→7
 *
 * 비틀기: 받아올림은 "아래 자리 → 위 자리" 방향인데, 정방향 저장이라 head부터 더하면
 *         받아올림 방향과 반대다. 그래서 바로 못 더한다.
 *
 * HackerRank Kotlin 시그니처 느낌:
 *   fun addTwoNumbers(l1: SinglyLinkedListNode?, l2: SinglyLinkedListNode?): SinglyLinkedListNode?
 *
 * 생각할 거리(둘 중 택1):
 *   ① 두 리스트를 reverse → 원조 문제로 환원 → 더한 결과를 다시 reverse  (reverseList 재활용!)
 *   ② 두 리스트를 스택에 push → 꼭대기(=최하위 자리)부터 pop하며 더함
 */
fun addTwoNumbersII(l1: SinglyLinkedListNode?, l2: SinglyLinkedListNode?): SinglyLinkedListNode? {
    // 리버스를 하게 되면 어쨌든 O(n+n) 이됨.
    // 2번째 방식은 반올림 방식이 발생할 경우 좀 복잡해짐
    // 9999 + 1을 하게 되면 캐리가 4번 발생할수 잇음.
    // stack 을 쓰면 lifo, pop 한뒤 처리
    // 코드는 stack 이 편할것 같음.
    val s1 = Stack<Int>()
    var p1 = l1
    while(p1 != null) {s1.add(p1.data); p1 = p1.next}

    val s2 = Stack<Int>()
    var p2 = l2
    while (p2 != null) {s2.add(p2.data); p2 = p2.next}

    var head: SinglyLinkedListNode? = null
    var carry = 0
    while (s1.isNotEmpty() || s2.isNotEmpty() || carry != 0) {
        val sum = (s1.removeLastOrNull() ?: 0) + (s2.removeLastOrNull() ?: 0) + carry
        carry = sum / 10
        head = SinglyLinkedListNode(sum % 10).also { it.next = head }  // prepend
    }
    return head
}
