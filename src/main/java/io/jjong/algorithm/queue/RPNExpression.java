package io.jjong.algorithm.queue;

import java.util.Deque;
import java.util.LinkedList;

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
public class RPNExpression {

  public static void main(String[] args) {
    String expression = "3,4,+,2,*,1,+"; // (3 + 4) * 2 + 1 = 15
    int result = eval(expression);
    System.out.println("result : " + result);
  }

  public static int eval(String expression) {
    Deque<Integer> results = new LinkedList<>();
    String delimiter = ",";
    String[] symbols = expression.split(delimiter);

    for (String s : symbols) {
      if (s.length() == 1 && "+-*/".contains(s)) {
        final int y = results.removeFirst();
        final int x = results.removeFirst();
        switch (s.charAt(0)) {
          case '+':
            results.addFirst(x+y);
            break;
          case '-':
            results.addFirst(x-y);
            break;
          case '*':
            results.addFirst(x*y);
            break;
          case '/':
            results.addFirst(x/y);
            break;
          default:
            throw new IllegalArgumentException("Malformed RPN at :" + s);
        }
      } else {
        // 숫자 타입
        results.addFirst(Integer.parseInt(s));
      }
    }
    return results.removeFirst();
  }

}
