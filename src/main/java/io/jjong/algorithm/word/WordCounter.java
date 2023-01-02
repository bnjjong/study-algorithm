package io.jjong.algorithm.word;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create on 2023/01/02. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class WordCounter {

  public static void main(String[] args) {
    String str = "aaabbbccceeefff12345";

    System.out.println(countDuplicateCharacters(str));
    System.out.println(countDuplicateCharacters2(str));
  }

  public static Map<Character, Integer> countDuplicateCharacters(String str) {
    // 맵을 이용하여 문자를 키, 빈도수를 값으로 저장 한다.
    Map<Character, Integer> result = new HashMap<>();

    for (char c : str.toCharArray()) {
      result.compute(c, (k, v) -> v == null ? 1 : ++v);
    }

    return result;
  }

  public static Map<Character, Long> countDuplicateCharacters2(String str) {
    return str.chars()
        .mapToObj(c -> (char) c) // IntStream -> 문자 스트림으로 변경
        .collect(Collectors.groupingBy( // 문자를 분류 하고
            c -> c, Collectors.counting()) // 카운팅
        );
  }

}
