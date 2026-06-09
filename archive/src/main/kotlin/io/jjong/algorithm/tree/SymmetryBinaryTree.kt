package io.jjong.algorithm.tree

/**
 * 이진 트리가 좌우 대칭인지 검사한다. 빈 트리는 대칭으로 본다.
 * 좌측 서브트리와 우측 서브트리를 거울처럼(left↔right) 재귀 비교한다.
 */
object SymmetryBinaryTree {
    fun isSymmetric(tree: BinaryTreeNode<Int>?): Boolean {
        return tree == null || checkSymmetric(tree.left, tree.right)
    }

    private fun checkSymmetric(tree1: BinaryTreeNode<Int>?, tree2: BinaryTreeNode<Int>?): Boolean {
        if (tree1 == null && tree2 == null) {
            return true
        } else if (tree1 != null && tree2 != null) {
            return tree1.data == tree2.data &&
                checkSymmetric(tree1.left, tree2.right) &&
                checkSymmetric(tree1.right, tree2.left)
        }
        return false
    }
}

fun main() {
    val tree1 = BinaryTreeNode(1)
    val node1 = BinaryTreeNode(2)
    val node2 = BinaryTreeNode(2)
    tree1.left = node1
    tree1.right = node2

    val node3 = BinaryTreeNode(3)
    val node5 = BinaryTreeNode(3)
    node1.left = node3
    node2.right = node5

    // 둘 다 null이면 대칭
    node2.left = null
    node1.right = null

    val symmetric = SymmetryBinaryTree.isSymmetric(tree1)
    println("result : $symmetric")
}
