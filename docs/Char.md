# Kotlin Char 주요 함수 정리

`Char`는 단일 문자를 나타내는 타입입니다. 문자열 순회·판별·변환에서 핵심 역할을 합니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 문자 판별 (Boolean 반환) ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `isDigit()` | (없음) | `Boolean` | 숫자 문자인지 확인 | ⭐ `'5'.isDigit()` → `true` |
| `isLetter()` | (없음) | `Boolean` | 알파벳 문자인지 확인 | `'a'.isLetter()` → `true` |
| `isLetterOrDigit()` | (없음) | `Boolean` | 알파벳 또는 숫자인지 확인 | `'!'.isLetterOrDigit()` → `false` |
| `isLowerCase()` | (없음) | `Boolean` | 소문자인지 확인 | `'a'.isLowerCase()` → `true` |
| `isUpperCase()` | (없음) | `Boolean` | 대문자인지 확인 | `'A'.isUpperCase()` → `true` |
| `isWhitespace()` | (없음) | `Boolean` | 공백·탭·줄바꿈인지 확인 | `' '.isWhitespace()` → `true` |
| `isAlphabetic()` | (없음) | `Boolean` | 유니코드 알파벳인지 확인 | Java `Character.isAlphabetic()` 위임 |

## 2. 문자 변환 ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `uppercaseChar()` | (없음) | `Char` | 대문자로 변환 | ⭐ `toUpperCase()` 대체 |
| `lowercaseChar()` | (없음) | `Char` | 소문자로 변환 | ⭐ `toLowerCase()` 대체 |
| `digitToInt()` | (없음) | `Int` | 숫자 문자 → 정수 | ⭐ `'7'.digitToInt()` → `7`, 비숫자면 예외 |
| `digitToInt(radix)` | `Int` | `Int` | 지정 진법으로 변환 | `'F'.digitToInt(16)` → `15` |
| `digitToIntOrNull()` | (없음) | `Int?` | 숫자 문자 → 정수, 실패 시 null | 안전한 변환 |
| `digitToIntOrNull(radix)` | `Int` | `Int?` | 지정 진법, 실패 시 null | |
| `code` | (프로퍼티) | `Int` | 유니코드(ASCII) 코드값 | ⭐ `'A'.code` → `65` |
| `Int.toChar()` / `Char(code)` | `Int` | `Char` | 코드값 → 문자 | ⭐ `65.toChar()` → `'A'` / `Char(65)` → `'A'` |

## 3. 산술 연산 ⭐⭐
| 연산 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `'a' + n` | `Int` | `Char` | n번째 뒤 문자 반환 | ⭐ `'a' + 2` → `'c'` |
| `'z' - n` | `Int` | `Char` | n번째 앞 문자 반환 | `'z' - 1` → `'y'` |
| `c2 - c1` | `Char` | `Int` | 두 문자 간 거리(정수) | ⭐ `'z' - 'a'` → `25` |
| `c - '0'` | `Char` | `Int` | 숫자 문자 → 정수 (아스키 차이) | ⭐ `'5' - '0'` → `5` |
| `c - 'a'` | `Char` | `Int` | 소문자 알파벳 → 0-based 인덱스 | ⭐ `'c' - 'a'` → `2` |
| `c - 'A'` | `Char` | `Int` | 대문자 알파벳 → 0-based 인덱스 | `'C' - 'A'` → `2` |

## 4. 범위 검사 ⭐
| 방식 | 예시 | 설명 |
| :--- | :--- | :--- |
| `c in 'a'..'z'` | `'g' in 'a'..'z'` → `true` | 소문자 범위 확인 |
| `c in 'A'..'Z'` | `'G' in 'A'..'Z'` → `true` | 대문자 범위 확인 |
| `c in '0'..'9'` | `'5' in '0'..'9'` → `true` | 숫자 문자 범위 확인 |
| `c !in 'a'..'z'` | `'A' !in 'a'..'z'` → `true` | 범위 밖 확인 |
| `'a'..'z'` (ClosedRange) | `for (c in 'a'..'z')` | 알파벳 순회 |

## 5. 비교 및 기타
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `equals(other, ignoreCase)` | `Char?`, `Boolean` | `Boolean` | 대소문자 무시 비교 | `'a'.equals('A', true)` → `true` |
| `compareTo(other)` | `Char` | `Int` | 두 문자 대소 비교 | 양수/0/음수 반환 |
| `toString()` | (없음) | `String` | 문자 → 문자열 | `'a'.toString()` → `"a"` |
| `category` | (프로퍼티) | `CharCategory` | 유니코드 카테고리 | `CharCategory.LOWERCASE_LETTER` 등 |

---

## 6. 실전 사용법 (코드)

### 빈도 배열 — `IntArray(26)` + `c - 'a'` 인덱싱 ⭐⭐
```kotlin
// 소문자 알파벳 빈도 카운트 (코테 단골)
val freq = IntArray(26)
for (c in str) {
    freq[c - 'a']++
}
// 특정 문자의 빈도: freq['z' - 'a']

// 대소문자 혼합이면 먼저 정규화
for (c in str) {
    freq[c.lowercaseChar() - 'a']++
}
```

### 숫자 문자 ↔ 정수 변환 ⭐⭐
```kotlin
val c = '5'

// 방법 A: digitToInt() — 가장 명시적, 권장
val n1 = c.digitToInt()          // 5

// 방법 B: 아스키 차이 — 빠르고 간결
val n2 = c - '0'                 // 5

// 방법 C: 안전 변환 (null 처리 필요)
val n3 = c.digitToIntOrNull()    // 5 또는 null

// 정수 → 숫자 문자
val ch = '0' + 5                 // '5'
// 또는:
val ch2 = (5 + '0'.code).toChar() // '5'
```

### 아스키 기반 변환 — `Char.code` / `Int.toChar()` ⭐
```kotlin
val c = 'A'
val code = c.code          // 65

// 코드 → 문자 (두 방법 동일)
val back1 = code.toChar()  // 'A'
val back2 = Char(code)     // 'A'

// ROT13 예시
fun rot13(c: Char): Char = when {
    c in 'a'..'z' -> 'a' + (c - 'a' + 13) % 26
    c in 'A'..'Z' -> 'A' + (c - 'A' + 13) % 26
    else -> c
}
```

### 대소문자 정규화 ⭐
```kotlin
val s = "HeLLo"

// 문자열 전체 소문자화
val lower = s.lowercase()           // "hello"
val upper = s.uppercase()           // "HELLO"

// 문자 단위 (for 루프 내)
for (c in s) {
    val lc = c.lowercaseChar()      // 각 문자를 소문자로
    val uc = c.uppercaseChar()      // 각 문자를 대문자로
}

// 대소문자 무시 비교
'a'.equals('A', ignoreCase = true)  // true
```

### 문자열 순회 + 조건 필터 ⭐
```kotlin
val str = "Hello, World! 123"

// 알파벳만 추출
val letters = str.filter { it.isLetter() }         // "HelloWorld"

// 숫자 문자만 추출
val digits  = str.filter { it.isDigit() }           // "123"

// 알파벳+숫자만 (특수문자 제거)
val alnum   = str.filter { it.isLetterOrDigit() }   // "HelloWorld123"

// 공백 제거
val noSpace = str.filterNot { it.isWhitespace() }   // "Hello,World!123"

// for 루프로 순회
for (c in str) {
    when {
        c.isDigit()       -> print("숫자: $c")
        c.isUpperCase()   -> print("대문자: $c")
        c.isLowerCase()   -> print("소문자: $c")
        c.isWhitespace()  -> print("공백")
        else              -> print("특수문자: $c")
    }
}
```

### 알파벳 전체 순회 ⭐
```kotlin
// 소문자 a~z 출력
for (c in 'a'..'z') print(c)

// 대문자 A~Z 리스트 생성
val upperList = ('A'..'Z').toList()

// 알파벳 인덱스 ↔ 문자 변환
val idx = 'e' - 'a'        // 4  (0-based)
val ch  = 'a' + idx        // 'e'
```

### 팰린드롬 검사 패턴
```kotlin
fun isPalindrome(s: String): Boolean {
    val cleaned = s.filter { it.isLetterOrDigit() }.lowercase()
    return cleaned == cleaned.reversed()
}
```

### 문자 빈도 Map 만들기
```kotlin
val str = "banana"
val freqMap = str.groupingBy { it }.eachCount()  // {b=1, a=3, n=2}
// 또는
val freq = mutableMapOf<Char, Int>()
for (c in str) freq[c] = (freq[c] ?: 0) + 1
```

---

## 7. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 숫자 문자 → 정수 | `c.digitToInt()` 또는 `c - '0'` |
| 정수 → 숫자 문자 | `'0' + n` 또는 `(n + '0'.code).toChar()` |
| 문자 → ASCII 코드 | `c.code` |
| ASCII 코드 → 문자 | `code.toChar()` / `Char(code)` |
| 소문자 인덱스 (0~25) | `c - 'a'` |
| 대문자 인덱스 (0~25) | `c - 'A'` |
| 빈도 배열 (소문자) | `IntArray(26)` + `freq[c - 'a']++` |
| 소문자로 변환 | `c.lowercaseChar()` |
| 대문자로 변환 | `c.uppercaseChar()` |
| 소문자 범위 확인 | `c in 'a'..'z'` |
| 숫자 문자 확인 | `c.isDigit()` 또는 `c in '0'..'9'` |
| 알파벳 확인 | `c.isLetter()` |
| 공백 확인 | `c.isWhitespace()` |
| 두 문자 거리 | `c2 - c1` (Int) |
| n번째 뒤 문자 | `c + n` |

> ⚠️ `'a' + n`의 결과는 `Char`이지만, `c - '0'`이나 `c - 'a'`처럼 **Char끼리 빼면 `Int`** 반환. 타입 혼동 주의.
>
> ⚠️ `digitToInt()`는 숫자 문자가 아닐 때 `IllegalArgumentException` 발생 — 입력 검증이 필요하면 `digitToIntOrNull()` 사용.
>
> 🎯 **코테 빈도 1위 패턴**: `IntArray(26)` + `c - 'a'` 인덱싱으로 알파벳 빈도를 O(1)에 누적. 대소문자 혼합이면 `c.lowercaseChar() - 'a'`로 정규화 먼저.
