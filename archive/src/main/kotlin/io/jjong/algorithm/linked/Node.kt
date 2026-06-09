package io.jjong.algorithm.linked

/**
 * 단일 연결 리스트 노드. [data]는 값, [next]는 다음 노드(없으면 null).
 *
 * 연결 리스트 알고리즘에서 공통으로 쓰는 노드 타입이며, 탐색/삽입/삭제/길이 같은
 * 정적 헬퍼는 [Node.Companion]에 둔다.
 */
class Node<T>(
    var data: T?,
    var next: Node<T>? = null,
) {
    override fun toString(): String = "ListNode{data=$data, next=$next}"

    companion object {
        /** [key]와 같은 값을 가진 첫 노드를 [node]부터 탐색해 반환한다. 없으면 null. */
        fun search(node: Node<Int>?, key: Int): Node<Int>? {
            var cur = node
            while (cur != null && cur.data != key) {
                cur = cur.next
            }
            return cur
        }

        /** [node] 바로 뒤에 [newNode]를 삽입한다. */
        fun insertAfter(node: Node<Int>, newNode: Node<Int>) {
            newNode.next = node.next
            node.next = newNode
        }

        /** [node]의 다음 노드를 삭제한다. 테일이 아니라고 가정한다. */
        fun deleteList(node: Node<Int>) {
            node.next = node.next?.next
        }

        /** [node]부터 끝까지의 노드 개수를 반환한다. */
        fun length(node: Node<*>?): Int {
            var cur = node
            var length = 0
            while (cur != null) {
                ++length
                cur = cur.next
            }
            return length
        }
    }
}
