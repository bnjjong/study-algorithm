package io.jjong.algorithm.linked;

/**
 * create on 2022/12/04. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */

public class Node<T> {
  public T data;
  public Node<T> next;

  public Node(T data, Node<T> next) {
    this.data = data;
    this.next = next;
  }

  public Node(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "ListNode{" +
        "data=" + data +
        ", next=" + next +
        '}';
  }

  public static Node<Integer> search(Node<Integer> node, int key) {
    while (node != null && node.data != key) {
      node = node.next;
    }
    return node;
  }

  public static void insertAfter(Node<Integer> node, Node<Integer> newNode) {
    // 다음 노드에 newNode 삽입.
    newNode.next = node.next;
    node.next = newNode;
  }

  public static void deleteList(Node<Integer> node) {
    // 다음 노드를 삭제 함.
    // 테일이 아니라고 가정 함.
    node.next = node.next.next;
  }

  public static int length(Node node) {
    int length = 0;
    while (node != null) {
      ++length;
      node = node.next;
    }
    return length;
  }
}
