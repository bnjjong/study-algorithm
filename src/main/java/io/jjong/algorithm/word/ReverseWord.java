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
public class ReverseWord {
  public static void main(String[] args){
    String word = "jonsang is king is power";
    char[] charArray = word.toCharArray();
    reverseWord(charArray);

    System.out.println(charArray);
  }

  public static void reverseWord(char[] input) {
    int wordLength = input.length;

    // 전체를 뒤집는다.
    reverse(input, 0, wordLength-1);
    System.out.print("reverse : ");
    System.out.println(input);

    // 문자열의 각 단어를 뒤집는다.
    int start = 0, end = 0;
    while (start < wordLength) {
      // 문자열 읽을 index 구하기
      // 시작은 공백일 경우
      while (start < end || (start < wordLength && input[start] == ' ')) {
        System.out.print("start : " + (start+1));
        System.out.println(", char : " + input[start]);
        start++;
      }
      // 끝은 공백이 아닐 경우
      while (end < start || (end < wordLength && input[end] != ' ')) {
        System.out.print("end : " + (end + 1));
        System.out.println(", char : " + input[end]);
        end++;
      }
      reverse(input, start, end-1);
    }
  }

  private static void reverse(char[] input, int start, int end) {
    while (start < end) {
      char tmp = input[start];
      input[start++] = input[end];
      input[end--] = tmp;
    }
  }

  private static int find(char[] input, char c, int start) {
    for (int i=start; i<input.length; i++) {
      if (input[i] == c) {
        return i;
      }
    }
    return -1;
  }

}
