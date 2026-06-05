# Kotlin Char 주요 함수 정리

`Char`는 단일 문자를 나타내는 타입입니다. 주로 문자열 순회나 알고리즘 문제에서 문자의 종류를 판별하고 변환할 때 사용됩니다.

## 1. 문자 판별 (Boolean 반환)
문자의 속성을 확인하는 가장 자주 쓰이는 함수들입니다.

| 함수 | 입력 | 출력 | 설명 | 예시 |
| :--- | :--- | :--- | :--- | :--- |
| `isLetter()` | (없음) | `Boolean` | 알파벳인지 확인 | `'a'.isLetter()` → `true` |
| `isDigit()` | (없음) | `Boolean` | 숫자인지 확인 | `'1'.isDigit()` → `true` |
| `isLetterOrDigit()` | (없음) | `Boolean` | 알파벳 혹은 숫자인지 확인 | `'!'.isLetterOrDigit()` → `false` |
| `isLowerCase()` | (없음) | `Boolean` | 소문자인지 확인 | `'a'.isLowerCase()` → `true` |
| `isUpperCase()` | (없음) | `Boolean` | 대문자인지 확인 | `'A'.isUpperCase()` → `true` |
| `isWhitespace()` | (없음) | `Boolean` | 공백 문자(스페이스, 탭, 줄바꿈 등)인지 확인 | `' '.isWhitespace()` → `true` |

## 2. 문자 변환
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `lowercaseChar()` | (없음) | `Char` | 소문자로 변환 | `toLowerCase()` 대체 |
| `uppercaseChar()` | (없음) | `Char` | 대문자로 변환 | `toUpperCase()` 대체 |
| `digitToInt()` | (없음) | `Int` | 숫자를 나타내는 문자를 정수로 변환 | `'5'.digitToInt()` → `5`, 숫자가 아니면 예외 |
| `digitToInt(radix)` | `Int` | `Int` | 지정된 진법으로 변환 | `'F'.digitToInt(16)` → `15` |
| `toInt()` | (없음) | `Int` | 문자의 유니코드(ASCII) 값 반환 | `'A'.toInt()` → `65` (Deprecated 주의) |
| `code` | (프로퍼티) | `Int` | 문자의 유니코드 값 | `'A'.code` → `65` (추천) |

## 3. 비교 및 기타
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `equals(other, ignoreCase = true)` | `Char?`, `Boolean` | `Boolean` | 대소문자 무시 비교 가능 | |
| `plus(n)` / `minus(n)` | `Int` | `Char` | 특정 수만큼 떨어진 문자 반환 | `'a' + 1` → `'b'` |
| `compareTo(other)` | `Char` | `Int` | 두 문자 비교 | 양수/0/음수 반환 |
| `category` | (프로퍼티) | `CharCategory` | 유니코드 카테고리 정보 | |

---

## 💡 알고리즘 활용 팁

### 1. 숫자로 변환하기
문자 `'5'`를 숫자 `5`로 바꾸고 싶을 때:
```kotlin
val c = '5'
val num = c.digitToInt() // 안전하고 직관적임
```

### 2. 아스키 코드 차이 구하기
알파벳 순서를 계산할 때:
```kotlin
val diff = 'c'.code - 'a'.code // 2
```

### 3. 대소문자 무시 비교
```kotlin
if (c1.lowercaseChar() == c2.lowercaseChar()) { ... }
```
