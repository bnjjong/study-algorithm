package io.jjong.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class SdokuValidator {

  public static void main(String[] args){
    //
    List<Integer> row1 = List.of(1,2,3,4,0,0,0,0,0);
    List<Integer> row2 = List.of(2,1,3,4,0,0,0,0,0);
    List<Integer> row3 = List.of(0,2,3,4,0,0,0,0,0);
    List<Integer> row4 = List.of(0,2,3,4,0,0,0,0,0);
    List<Integer> row5 = List.of(3,2,0,4,0,0,0,0,0);
    List<Integer> row6 = List.of(0,2,3,4,0,0,0,0,0);
    List<Integer> row7 = List.of(0,2,3,4,0,0,0,0,0);
    List<Integer> row8 = List.of(0,2,3,4,0,0,0,0,0);
    List<Integer> row9 = List.of(0,2,3,4,0,0,0,0,0);
    List<List<Integer>> partialAssignment = new ArrayList<>();
    partialAssignment.add(row1);
    partialAssignment.add(row2);
    partialAssignment.add(row3);
    partialAssignment.add(row4);
    partialAssignment.add(row5);
    partialAssignment.add(row6);
    partialAssignment.add(row7);
    partialAssignment.add(row8);
    partialAssignment.add(row9);
    boolean valid = isValid(partialAssignment);
    System.out.println("is valid : " + valid);
  }

  public static boolean isValid(List<List<Integer>> partialAssignment) {
    // 행 제한
    System.out.println("Start row validation");
    for (int i=0; i<partialAssignment.size(); ++i) {
      if (hasDuplicate(partialAssignment, i, i+1, 0,partialAssignment.size())) {
        return false;
      }
    }
    System.out.println("Completed row validation.");

    // 열 제한
    System.out.println("Start column validation");
    for (int i=0; i<partialAssignment.size(); ++i) {
      if(hasDuplicate(partialAssignment, 0, partialAssignment.size(), i, i+1)) {
        return false;
      }
    }
    System.out.println("Completed column validation.");

    // 격자판 제한 사항
    int regionSize = (int) Math.sqrt(partialAssignment.size());
    for(int i=0; i<regionSize; ++i) {
      for (int j=0; j<regionSize; ++j) {
        if (hasDuplicate(partialAssignment, regionSize * i,
            regionSize * (i+1),
            regionSize * j,
            regionSize * (j+1))
        ) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> partialAssignment, int startRow, int endRow, int startCol, int endCol) {
    Set<Integer> numbers = new HashSet<>();

    for (int i=startRow; i <endRow; ++i) {
      System.out.println("start row : " + i);
      for (int j=startCol; j<endCol; ++j) {
        Integer value = partialAssignment.get(i).get(j);
        System.out.print(value+", ");
        if (value != 0 && numbers.contains(value)) {
          return true;
        }
        numbers.add(value);
      }
      System.out.println();
    }
    return false;
  }

}
