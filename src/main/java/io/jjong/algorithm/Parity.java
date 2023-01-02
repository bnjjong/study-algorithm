package io.jjong.algorithm;

/**
 * create on 2022/12/09. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class Parity {

  public static void main(String[] args) {
    long x = 220;
    short parity = parity(x);
    System.out.println("parity : " + parity);

//    int x = 27;   // -64;
//    System.out.println(Integer.toBinaryString(x));
//    int n = 2;
//    System.out.println("x >> n = " + (x >> n)); // 나눗셈, (x / 2^n)
//    System.out.println("x << n = " + (x << n)); // 곱셈,  (x * 2^n)
//    System.out.println("x >>> n = " + (x >>> n));
//
//    System.out.println("x >> 34 = " + (x >> 2));    // x / 2^(n % 32) 0001 1111
//    System.out.println(Integer.toBinaryString(x >>> 2));
//    System.out.println("x << 34 = " + (x << 3));
//    System.out.println("x >>> 34 = " + (x >>> 3));
  }

  /**
   * 에러를 검출하기 위해 추가하는 비트
   * @param x
   * @return
   */
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= (x & 1); // x가 1인지 체크 한뒤 xor 연산 수행.
      System.out.println("x & 1 : "+ (x & 1)+", result : " + result);
      x >>>= 1; // 나누기 연산 수행 -> x / 2^1
      System.out.println("x : " + x);
    }
    return result;
  }

}
