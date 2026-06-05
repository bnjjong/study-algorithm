package io.jjong.algorithm.linked

/**
 * 끝에서 [removeIdx]번째 노드를 제거한다. 두 포인터(first/second)를 [removeIdx]만큼
 * 벌려두고 first가 끝에 닿으면 second가 삭제 대상 직전을 가리키도록 한다.
 */
object ReverseRemoveNode {
    fun remove(node: Node<Int>?, removeIdx: Int): Node<Int>? {
        val dummy = Node(0, node)
        var first = dummy.next
        var second: Node<Int> = dummy
        var idx = removeIdx

        // 두 번째 반복자보다 removeIdx 만큼 앞서게 배치한다.
        while (idx-- > 0) {
            first = first?.next
        }
        // first가 끝에 닿으면 second는 삭제 대상 직전 노드를 가리킨다.
        while (first != null) {
            second = second.next!!
            first = first.next
        }
        // 삭제 대상을 건너뛰어 다음 노드로 잇는다.
        second.next = second.next?.next
        return dummy.next
    }
}

fun main() {
    val node1 = Node(1, Node(2, Node(5, Node(9, Node(10)))))
    val result = ReverseRemoveNode.remove(node1, 3)
    println(result)
}
