package io.jjong.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create on 2022/12/02. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class ArrayNumberCalculator {



  public static void main(String[] args){
    final int plusNum = 10;
    List<Integer> numbers = Arrays.asList(1,9,9,9,6);
    List<Integer> result = plusArray(numbers, plusNum);

    System.out.println("result num : ");
    result.forEach(System.out::println);
  }

  public static List<Integer> plusArray(List<Integer> numbers, int plusNum) {
    if (plusNum > 10) {
      throw new IllegalArgumentException("plus number is not over than 10.");
    }
    int lastIndex = numbers.size() - 1;
    numbers.set(lastIndex, numbers.get(lastIndex) + plusNum); // 마지막 index 수를 더해준다.
    System.out.print("first num : ");
    printArray(numbers);

    // 올림 처리
    for (int i = lastIndex; i > 0 && numbers.get(i) >= 10; --i) {
      numbers.set(i, numbers.get(i) % 10); // 나머지 수 처리
      numbers.set(i-1, numbers.get(i-1) + 1); // 앞자리 올림 처리
      printArray(numbers);
    }
    // 앞자리 올림 처리
    if (numbers.get(0) >= 10) {
      System.out.println("앞 자리 올림.");
      numbers.set(0, 1);
      numbers.add(1,0);
    }
    return numbers;
  }

  public static void printArray(List<Integer> numbers) {
    numbers.forEach(System.out::print);
    System.out.println();
  }
}
