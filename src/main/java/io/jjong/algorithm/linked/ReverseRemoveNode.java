package io.jjong.algorithm.linked;

/**
 * create on 2022/12/06. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class ReverseRemoveNode {

  public static void main(String[] args) {
    Node<Integer> node1 = new Node<>(1,
        new Node<>(2,
            new Node<>(5,
                new Node<>(9,
                    new Node<>(10)))));

    Node<Integer> result = remove(node1, 3);
    System.out.println(result);
  }


  public static Node<Integer> remove(Node<Integer> node, int removeIdx) {
    Node<Integer> dummy = new Node<>(0, node);
    Node<Integer> first = dummy.next;
    Node<Integer> second = dummy;
    // 두번째 반복자 보다 removeIdx 만큼 앞서게 배치 한다.
    // 5 - 3 = 9, 10
    while (removeIdx-- > 0) {
      first = first.next;
    }

    // 첫번째 반복자 끝에서 K+1번째 노드를 가르키게 된다.
    // 총 2회 반복하므로
    // second 는 5,9,10
    while (first != null) {
      second = second.next;
      first = first.next;
    }
    // 삭제 로직
    // 5를 지우고 9로 덮어 쓴다.
    second.next = second.next.next;

    return dummy.next;
  }
}
