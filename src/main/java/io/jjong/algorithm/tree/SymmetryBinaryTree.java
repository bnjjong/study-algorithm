package io.jjong.algorithm.tree;

/**
 * create on 2022/12/07. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class SymmetryBinaryTree {

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    // null 이면 어쟀든 대칭이라고 판단?
    return tree == null || checkSymmetric(tree.left, tree.right);
  }

  private static boolean checkSymmetric(BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2) {
    // 둘다 null 이면 대칭!
    if (tree1 == null && tree2 == null) {
      return true;
    } else if (tree1 != null && tree2 != null) {
      return tree1.data == tree2.data
          && checkSymmetric(tree1.left, tree2.right)
          && checkSymmetric(tree1.right, tree2.left);
    }
    return false;
  }

  public static void main(String[] args) {
    BinaryTreeNode<Integer> tree1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
    tree1.left = node1;
    tree1.right = node2;

    BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);

    BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(4);
    node1.left = node3;
    node2.right = node5;

    // 둘다 null 일 경우 대칭
    node2.left = null;
    node1.right = null;




    boolean symmetric = isSymmetric(tree1);
    System.out.println("result : " + symmetric);
  }
}
