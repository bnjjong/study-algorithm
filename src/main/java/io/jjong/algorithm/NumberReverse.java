package io.jjong.algorithm;

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
public class NumberReverse {
  public static final int CIPHER = 10;

  public static void main(String[] args){
    int input = 23131237;
    long output = reverse(input);
    System.out.println(output);
  }

  public static long reverse(int x) {
    long result = 0;
    long xRemaining = Math.abs(x);
    while (xRemaining != 0) {
      result = (result * CIPHER) + (xRemaining % 10); //  자릿 수를 올리기 위해 10을 곱해준다.
      xRemaining /= CIPHER;

      System.out.println("resut = " + result);
      System.out.println("remain = " + xRemaining);
    }
    return x < 0 ? -result : result;
  }

}
