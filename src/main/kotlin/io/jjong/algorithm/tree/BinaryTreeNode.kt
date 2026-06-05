package io.jjong.algorithm.tree

/**
 * 이진 트리 노드. [data]는 값, [left]/[right]는 좌/우 자식(없으면 null).
 */
class BinaryTreeNode<T>(
    var data: T?,
    var left: BinaryTreeNode<T>? = null,
    var right: BinaryTreeNode<T>? = null,
)
