# 3. Longest Substring Without Repeating Characters

- 난이도: **Medium** ⭐ 빈출
- 링크: https://leetcode.com/problems/longest-substring-without-repeating-characters/
- 주제: 문자열, 슬라이딩 윈도우, 해시

## 문제

문자열 `s`가 주어집니다.
**중복 문자 없이** 만들 수 있는 **가장 긴 부분 문자열(substring)** 의 길이를 반환하세요.

> **substring** = **연속된** 문자들. (예: "abc"의 substring은 "a", "b", "c", "ab", "bc", "abc"이지만 "ac"는 아님)

## 예시

```
입력: s = "abcabcbb"
출력: 3
설명: "abc" (길이 3)

입력: s = "bbbbb"
출력: 1
설명: "b" (모두 같은 문자라 1이 최선)

입력: s = "pwwkew"
출력: 3
설명: "wke" (길이 3). "pwke"는 substring이 아님(연속 X)
```

## 제약 조건

- `0 <= s.length <= 5 * 10^4`
- `s`는 영문자, 숫자, 기호, 공백 등 모두 가능

## 함수 시그니처

```kotlin
class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        // 여기에 작성
    }
}
```

## 생각 거리

### Level 1 — 무식하게 (O(N²) 또는 O(N³))
모든 부분 문자열을 만들어보고 중복 없는 것 중 가장 긴 길이를 구함. 길이 N이 5만이라 **시간 초과 위험**.

### Level 2 — 슬라이딩 윈도우 (O(N)) ⭐ 면접 기대 정답

**슬라이딩 윈도우**란?
> 두 포인터 `left`, `right`를 사용해 **연속된 구간(window)** 을 만들고, 조건이 깨지면 윈도우의 왼쪽을 좁히는 패턴.

이번 문제의 윈도우 정의:
> **"현재 윈도우 안에는 중복 문자가 없다"** 를 항상 유지

흐름:
1. `right`를 한 칸씩 오른쪽으로 늘림 (윈도우 확장)
2. 새로 들어온 문자가 **이미 윈도우 안에 있으면**, `left`를 오른쪽으로 옮겨서 중복을 윈도우에서 빼냄
3. 매번 `right - left + 1`을 최대 길이 후보로 갱신

```kotlin
val seen = HashMap<Char, Int>()   // char -> 마지막에 본 인덱스
var left = 0
var maxLen = 0

for (right in s.indices) {
    val c = s[right]
    // 만약 c가 이미 윈도우 안에 있다면, left를 그 다음 인덱스로 점프
    // (윈도우 밖에 있던 거면 무시)

    seen[c] = right
    maxLen = maxOf(maxLen, right - left + 1)
}
return maxLen
```

### 윈도우 시각화 (`"abcabcbb"` 예시)

```
right=0  s[0]='a'  window="a"      → maxLen=1
right=1  s[1]='b'  window="ab"     → maxLen=2
right=2  s[2]='c'  window="abc"    → maxLen=3
right=3  s[3]='a'  중복! 'a'가 0에 있음
                   left를 1로 점프  window="bca"  → maxLen=3
right=4  s[4]='b'  중복! 'b'가 1에 있음
                   left를 2로 점프  window="cab"  → maxLen=3
right=5  s[5]='c'  중복! 'c'가 2에 있음
                   left를 3으로 점프 window="abc"  → maxLen=3
right=6  s[6]='b'  중복! 'b'가 4에 있음
                   left를 5로 점프  window="cb"   → maxLen=3
right=7  s[7]='b'  중복! 'b'가 6에 있음
                   left를 7로 점프  window="b"    → maxLen=3
```

### 핵심 함정 ⚠️

**"이미 본 적 있는 문자"** 와 **"현재 윈도우 안에 있는 문자"** 는 다릅니다.

`seen[c]`가 있더라도 그 인덱스가 **현재 left보다 작으면** 윈도우 밖에 있는 거라 무시해야 합니다. 그래서 left 갱신은:
```kotlin
left = maxOf(left, seen[c]!! + 1)
```
이렇게 해야 안전. (left를 절대 뒤로 후퇴시키지 않음)

### 왜 슬라이딩 윈도우가 중요한가
**"연속 구간에서 어떤 조건을 만족하는 최장/최단 부분 찾기"** — 코테 단골.
이 패턴이 응용되는 문제들:
- #76 Minimum Window Substring
- #424 Longest Repeating Character Replacement
- #438 Find All Anagrams in a String
- #209 Minimum Size Subarray Sum

## 실무에서 어디에 쓰이는가

실무 직접 응용 사례는 떠오르지 않음 — 알고리즘 패턴 학습용.
다만 "조건을 만족하는 연속 구간을 한 번 훑기로 찾는다"는 슬라이딩 윈도우 발상은 시계열 데이터 분석(연속된 시간 윈도우 통계)에서 자주 보입니다.
