package io.jjong.algorithm.word;

/**
 * create on 2022/12/03. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class PalindromeWord {

  public static boolean isPalindrome(String s) {
    // i 는 처음부터, j는 뒤에서 부터
    int i=0, j=s.length()-1;
    while (i < j) {
      while (!Character.isLetterOrDigit(s.charAt(i)) && i < j ) {
        ++i;
        System.out.println("i = "+ i);
      }
      while (!Character.isLetterOrDigit(s.charAt(j)) && i < j ) {
        --j;
        System.out.println("j = "+ j);
      }
      System.out.println("start comparing word,  i : " + i + ", j : " + j);
      if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args){
    String word = "!! ablevelba   !!";
    boolean palindrome = isPalindrome(word);
    System.out.println("is palindrome : " + palindrome);
  }

}
