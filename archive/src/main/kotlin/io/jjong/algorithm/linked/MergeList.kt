package io.jjong.algorithm.linked

/**
 * 정렬된 두 연결 리스트를 하나의 정렬된 리스트로 병합한다. Dummy Head 패턴 사용.
 */
object MergeList {
    fun mergeListNode(node1: Node<Int>?, node2: Node<Int>?): Node<Int>? {
        val dummyHead = Node<Int>(0, null)
        var current = dummyHead
        var p1 = node1
        var p2 = node2

        while (p1 != null && p2 != null) {
            if (p1.data!! <= p2.data!!) {
                current.next = p1
                p1 = p1.next
            } else {
                current.next = p2
                p2 = p2.next
            }
            current = current.next!!
        }
        current.next = p1 ?: p2
        return dummyHead.next
    }
}

fun main() {
    val node1 = Node(1, Node(2, Node(5, Node(9))))
    val node2 = Node(3, Node(4, Node(7, Node(8))))
    val mergeNode = MergeList.mergeListNode(node1, node2)
    println(mergeNode)
}
