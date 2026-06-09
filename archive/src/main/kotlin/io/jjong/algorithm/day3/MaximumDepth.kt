package io.jjong.algorithm.day3

import kotlin.math.max

/**
 * LeetCode #104 — Maximum Depth of Binary Tree (Easy)
 *
 * 루트에서 가장 먼 리프까지의 **노드 수**(= 트리 높이)를 반환한다. 빈 트리는 0.
 *
 * - Input  : [root] — 트리 루트 (null 가능)
 * - Output : 최대 깊이 (Int). 빈 트리면 0, 루트만 있으면 1.
 * - 예시   : [3, 9, 20, null, null, 15, 7] → 3   (3 → 20 → 15)
 *
 * 복습 포인트(Day 3 재귀 3단):
 *   ① 기저   : root == null 이면 0
 *   ② 분할   : 왼쪽/오른쪽 깊이를 각각 재귀로 구한다
 *   ③ 결합   : 1 + max(왼쪽, 오른쪽)
 *   강의 자료의 count()(합으로 결합)에서 "결합만 max로" 바꾸면 된다.
 */
fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return 1+ maxOf(maxDepth(root.left), maxDepth(root.right))
}
