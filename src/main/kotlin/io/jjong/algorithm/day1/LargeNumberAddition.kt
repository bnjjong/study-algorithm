package io.jjong.algorithm.day1

/**
 * HackerRank — Java BigInteger (Easy)
 * https://www.hackerrank.com/challenges/java-biginteger/problem
 *
 * **Add Two Numbers의 문자열 버전.** 두 개의 비음수 정수를 *문자열*로 받아 합을 구한다.
 * 연결 리스트 대신 문자열이라는 점만 다를 뿐 핵심은 같다 — 끝(일의 자리)에서부터
 * 한 자리씩 더하며 자리올림(carry)을 다음 자리로 넘긴다.
 *
 * - Input  : [a], [b] — 비음수 정수 문자열 (Long 범위를 넘을 수 있음, 선행 0 없음)
 * - Output : a + b 를 선행 0 없는 정수 문자열로
 * - 예시   : "342" + "465" → "807",  "99" + "1" → "100"
 *
 * 복습 포인트(day1, Add Two Numbers):
 *   ① 두 문자열의 *끝*에서부터 인덱스를 줄여가며 같은 자리끼리 더한다(짧은 쪽은 0으로 취급).
 *   ② sum = da + db + carry → 이번 자리 = sum % 10, 다음 carry = sum / 10.
 *   ③ 두 인덱스가 모두 끝났어도 carry가 남아 있으면 한 자리를 더 붙인다 ("99"+"1" → "100").
 *   ④ 자리를 뒤에서부터 만들었으니 마지막에 뒤집는다 (StringBuilder.reverse 등).
 *   ⚠ java.math.BigInteger를 쓰면 한 줄이지만, 복습이 목적이므로 직접 구현해 볼 것.
 */
fun addLargeNumbers(a: String, b: String): String {
    TODO("끝에서부터 자리별로 더하며 carry를 굴린다 — Add Two Numbers를 문자열로 옮긴 셈. 위 ①~④ 참고")
}
