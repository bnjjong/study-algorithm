# 242. Valid Anagram

- 난이도: **Easy**
- 링크: https://leetcode.com/problems/valid-anagram/
- 주제: 문자열, 해시맵 (카운팅)

## 문제

두 문자열 `s`와 `t`가 주어집니다.
`t`가 `s`의 **anagram**(글자 순서만 바꾼 것)이면 `true`, 아니면 `false`를 반환하세요.

> **anagram**: 같은 글자들을 같은 개수만큼 사용해 순서만 다르게 배열한 문자열.
> 예: "listen" ↔ "silent", "anagram" ↔ "nagaram"

## 예시

```
입력: s = "anagram", t = "nagaram"
출력: true

입력: s = "rat", t = "car"
출력: false   // 'r','a','t' vs 'c','a','r' — 글자 구성이 다름

입력: s = "a", t = "ab"
출력: false   // 길이부터 다름
```

## 제약 조건

- `1 <= s.length, t.length <= 5 * 10^4`
- `s`, `t`는 **소문자 영문자**만 포함

## 함수 시그니처

```kotlin
class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        // 여기에 작성
    }
}
```

## 생각 거리

1. **0초만에 false로 거를 수 있는 조건**은 무엇일까? (가장 먼저 체크할 것)
2. anagram 판별 = "두 문자열의 **글자 구성이 같은가**" → 글자별로 **몇 번 나왔는지** 비교하면 됨. 어떻게 셀까?
3. 가장 단순하게 떠오르는 방법은 두 문자열을 **정렬해서 비교**. 시간복잡도는?
4. 더 빠른 방법은? (힌트: 카운트하는 자료구조 — 위에서 봤던 표 다시 보기)
5. 제약 조건이 **소문자 영문자뿐**이라는 점은 어떤 최적화를 가능하게 할까? (힌트: 26)
