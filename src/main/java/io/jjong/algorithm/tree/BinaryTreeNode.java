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
public class BinaryTreeNode<T> {
  public T data;
  public BinaryTreeNode<T> left, right;

  public BinaryTreeNode(T data) {
    this.data = data;
  }



}
