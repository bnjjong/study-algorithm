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
public class MergeList {

  public static void main(String[] args){

    Node<Integer> node1 = new Node<>(1,
        new Node<>(2,
            new Node<>(5,
                new Node<>(9))));

    Node<Integer> node2 = new Node<>(3,
        new Node<>(4,
            new Node<>(7,
                new Node<>(8))));
    Node<Integer> mergeNode = mergeListNode(node1, node2);
    System.out.println(mergeNode);

  }


  public static Node<Integer> mergeListNode(Node<Integer> node1, Node<Integer> node2) {
    Node<Integer> dummyHead = new Node<>(0, null);
    Node<Integer> current = dummyHead;
    Node<Integer> p1 = node1, p2 = node2;

    while (p1 != null && p2 != null) {
      if (p1.data <= p2.data) {
        current.next = p1;
        p1 = p1.next;
      } else {
        current.next = p2;
        p2 = p2.next;
      }
      current = current.next;
    }
    current.next = p1 != null ? p1 : p2;
    return dummyHead.next;
  }
}
