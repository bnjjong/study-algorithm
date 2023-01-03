package io.jjong.algorithm.word;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * create on 2023/01/03. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 *
 * @author Jongsang Han(henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class NoDuplicationFirstWordFinder {

  public static final int EXTENDED_ASCII_CODES = 256;

  public static char firstRepeatedCharacter(String str) {
    int[] flags = new int[EXTENDED_ASCII_CODES];

    // flags 초기화
    for (int i=0; i <flags.length; i++) {
      flags[i] = -1;
    }

    for (int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);
      if (flags[ch] == -1) {
        flags[ch] = i; // index 저장
      } else {
        flags[ch] = -2;
      }
    }

    int position = Integer.MAX_VALUE;

    for (int i=0; i < EXTENDED_ASCII_CODES; i++) {
      if (flags[i] >=0 ) {
        position = Math.min(position, flags[i]);
      }
    }
    return position == Integer.MAX_VALUE ? Character.MIN_VALUE : str.charAt(position);
  }

  public static char firstRepeatedCharacter2(String str) {
    Map<Character, Integer> chars = new LinkedHashMap<>();

    for (int i=0; i<str.length(); i++) {
      char ch = str.charAt(i);

      chars.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
    }

    System.out.println(chars);

    for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey(); // 순서가 보장되므로 가장 먼저 찾은 key값을 리턴 한다.
      }
    }

    return Character.MIN_VALUE;
  }

  public static String firstRepeatedCharacter3(String str) {
    Map<Integer, Long> chs = str.codePoints()
        .mapToObj(cp -> cp)
        .collect(Collectors.groupingBy(
            Function.identity(), // 인자로 넘어온 값을 그대로 반환
            LinkedHashMap::new,
            Collectors.counting())
        );

    System.out.println(chs);

    Integer cp = chs.entrySet().stream()
        .filter(e -> e.getValue() == 1L)
        .findFirst()
        .map(Entry::getKey)
        .orElse(Integer.valueOf(Character.MIN_VALUE));

    return String.valueOf(Character.toChars(cp));
  }


  public static void main(String[] args) {
    String word = "my name is jongsang han. my";

    System.out.println(firstRepeatedCharacter(word));
    System.out.println(firstRepeatedCharacter2(word));
    System.out.println(firstRepeatedCharacter3(word));
  }

}
