package io.jjong.algorithm.day3

/**
 * 이진 트리 노드 (LeetCode 표준).
 *
 * [value]는 노드 값, [left]/[right]는 좌·우 자식이며 없으면 null이다.
 * day1의 [io.jjong.algorithm.day1.ListNode]가 `next` 하나였다면, 트리는 갈래가 둘이라
 * 자연스럽게 재귀(좌·우 서브트리)가 된다.
 */
class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
)

/**
 * LeetCode식 level-order(BFS 순서) 배열을 이진 트리로 변환한다. 테스트 입력을 손쉽게 만들기 위한 헬퍼.
 *
 * 배열은 위→아래, 좌→우 순서이며 `null`은 "자식 없음"을 뜻한다.
 *
 * - Input  : 수신 객체 `List<Int?>` (예: [3, 9, 20, null, null, 15, 7])
 * - Output : 루트 `TreeNode?` (빈 목록이거나 첫 원소가 null이면 null)
 */
fun List<Int?>.toTree(): TreeNode? {
    if (isEmpty() || this[0] == null) return null
    val root = TreeNode(this[0]!!)
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)
    var i = 1
    while (queue.isNotEmpty() && i < size) {
        val node = queue.removeFirst()
        if (i < size) { this[i]?.let { node.left = TreeNode(it); queue.add(node.left!!) }; i++ }
        if (i < size) { this[i]?.let { node.right = TreeNode(it); queue.add(node.right!!) }; i++ }
    }
    return root
}
