# 217. Contains Duplicate

- 난이도: **Easy**
- 링크: https://leetcode.com/problems/contains-duplicate/
- 주제: 배열, 해시셋

## 문제

정수 배열 `nums`가 주어집니다.
**어떤 값이 두 번 이상** 등장하면 `true`, 모두 다르면 `false`를 반환하세요.

## 예시

```
입력: nums = [1, 2, 3, 1]
출력: true
설명: 1이 두 번 등장

입력: nums = [1, 2, 3, 4]
출력: false
설명: 모두 다름

입력: nums = [1, 1, 1, 3, 3, 4, 3, 2, 4, 2]
출력: true
```

## 제약 조건

- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

## 함수 시그니처

```kotlin
class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        // 여기에 작성
    }
}
```

## 생각 거리

1. Two Sum에서 배운 **HashMap** 대신 이 문제에는 어떤 자료구조가 더 자연스러울까? (값만 저장하면 되고 인덱스는 필요 없음)
2. "이미 본 적 있는가?"를 한 번 훑으면서 어떻게 판단할까?
3. **Kotlin 한 줄로도** 풀 수 있어요. 어떤 표준 라이브러리가 도움이 될까? (힌트: 중복을 자동으로 제거하는 컬렉션)
