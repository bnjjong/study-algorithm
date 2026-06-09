# 참고 풀이 (Day 1)

> ⚠️ 먼저 직접 풀어보고, 막혔을 때만 펼쳐보세요.

## 1. Add Two Numbers (LC #2)
```kotlin
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var cur = dummy
    var p1 = l1
    var p2 = l2
    var carry = 0
    while (p1 != null || p2 != null || carry != 0) {
        var sum = carry
        if (p1 != null) { sum += p1.value; p1 = p1.next }
        if (p2 != null) { sum += p2.value; p2 = p2.next }
        carry = sum / 10
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
    }
    return dummy.next
}
```
핵심: Dummy Head로 첫 노드 분기 제거, `carry != 0`을 루프 조건에 넣어 마지막 올림수까지 처리.

## 2. Reverse Linked List (LC #206)
```kotlin
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
        val next = cur.next   // 1) 미리 저장
        cur.next = prev       // 2) 방향 뒤집기
        prev = cur            // 3) prev 전진
        cur = next            // 4) cur 전진
    }
    return prev
}
```
핵심: `next`를 먼저 저장해야 주소를 잃지 않는다. 마지막 `prev`가 새 head.

## 3. Linked List Cycle II (LC #142)
```kotlin
fun detectCycle(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (slow === fast) {        // 만남 = 사이클 존재
            var p = head
            while (p !== slow) {     // head와 만난 지점을 같이 1칸씩
                p = p?.next
                slow = slow?.next
            }
            return p
        }
    }
    return null
}
```
핵심: `L = nC - x` → head에서 출발한 포인터와 만난 지점 포인터가 사이클 시작점에서 만난다.
참조 비교는 `===`(동일 인스턴스)를 쓴다.

## 4. Maximum Product Subarray (LC #152)
```kotlin
fun maxProduct(nums: IntArray): Int {
    var maxSoFar = nums[0]
    var curMax = nums[0]
    var curMin = nums[0]
    for (i in 1 until nums.size) {
        val n = nums[i]
        val tempMax = maxOf(n, n * curMax, n * curMin)
        curMin = minOf(n, n * curMax, n * curMin)
        curMax = tempMax
        maxSoFar = maxOf(maxSoFar, curMax)
    }
    return maxSoFar
}
```
핵심: 음수 곱으로 min/max 역할이 뒤집히므로 둘 다 추적. `curMax`를 먼저 바꾸면
`curMin` 계산이 오염되므로 `tempMax` 임시변수 필수.

## 5. Merge Two Sorted Lists (LC #21) — Add Two Numbers 형제 문제
```kotlin
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    val dummy = ListNode(0)
    var cur = dummy
    var p1 = l1
    var p2 = l2
    while (p1 != null && p2 != null) {
        if (p1.value <= p2.value) {   // 작은 쪽 노드를 그대로 잇는다 (새 노드 X)
            cur.next = p1
            p1 = p1.next
        } else {
            cur.next = p2
            p2 = p2.next
        }
        cur = cur.next!!
    }
    cur.next = p1 ?: p2               // 남은 쪽을 통째로 붙이면 끝
    return dummy.next
}
```
핵심: Dummy Head로 시작, 두 포인터에서 **작은 값 노드를 splice**(이어붙이기). 한쪽이 끝나면
나머지를 통째로 `cur.next`에 연결. **새 노드를 만들지 않으므로 공간 O(1)** — Add Two Numbers와
달리 결과 노드를 새로 만들 필요가 없다는 점이 포인트.
