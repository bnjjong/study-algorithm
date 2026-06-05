package io.jjong.algorithm.linked

/**
 * 사이클이 없는 두 연결 리스트가 공유(교차)하는 첫 노드를 찾는다.
 * 긴 리스트를 길이 차이만큼 먼저 전진시킨 뒤 함께 이동하며 같은 노드를 만나면 그곳이 교차점.
 */
object OverlappingChecker {
    fun overlappingNoCycleLists(l1: Node<Int>?, l2: Node<Int>?): Node<Int>? {
        var a = l1
        var b = l2
        val l1Length = Node.length(l1)
        val l2Length = Node.length(l2)

        // 두 리스트 길이가 같아지도록 더 긴 리스트를 먼저 전진시킨다.
        if (l1Length > l2Length) {
            a = advanceList(l1Length - l2Length, a)
        } else {
            b = advanceList(l2Length - l1Length, b)
        }
        // 같은 인스턴스(===)를 만나면 교차점이다.
        while (a != null && b != null && a !== b) {
            a = a.next
            b = b.next
        }
        return a
    }

    private fun advanceList(k: Int, node: Node<Int>?): Node<Int>? {
        var n = node
        var count = k
        while (count-- > 0) {
            n = n?.next
        }
        return n
    }
}

fun main() {
    val sharedNode = Node(10)
    val node1 = Node(1, Node(2, Node(5, Node(9, sharedNode))))
    sharedNode.next = Node(11, Node(13, Node(15, Node(20))))
    val node2 = Node(1, Node(3, sharedNode))

    val result = OverlappingChecker.overlappingNoCycleLists(node1, node2)
    println(result)
}
