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
public class OverlappingChecker {
  public static void main(String[] args){
    Node<Integer> sharedNode = new Node<>(10);
    Node<Integer> node1 = new Node<>(1,
        new Node<>(2,
            new Node<>(5,
                new Node<>(9, sharedNode))));

    sharedNode.next = new Node<>(11,
        new Node<>(13,
            new Node<>(15,
                new Node<>(20))));

    Node<Integer> node2 = new Node<>(1,
        new Node<>(3, sharedNode));

    Node<Integer> result = overlappingNoCycleLists(node1, node2);
    System.out.println(result);


  }

//  public static
  public static Node<Integer> overlappingNoCycleLists(
      Node<Integer> l1,
      Node<Integer> l2
      ) {
    int l1Length = Node.length(l1);
    int l2Length = Node.length(l2);

    // 두 리스트 길이가 같아지도록 길이가 긴 리스트를 먼저 나아간다.
    if (l1Length > l2Length) {
      l1 = advanceList(l1Length - l2Length, l1);
    } else {
      l2 = advanceList(l2Length - l1Length, l2);
    }
    while (l1 != null && l2 != null && l1 != l2) {
      l1 = l1.next;
      l2 = l2.next;
    }
    return l1;
  }

  private static Node<Integer> advanceList(int k, Node<Integer> node) {
    while (k-- > 0) {
      node = node.next;
    }
    return node;
  }

}
