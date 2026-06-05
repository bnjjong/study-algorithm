### Kotlin String 관련 주요 함수 정리

Kotlin에서 문자열(`String`)을 다룰 때 실무와 알고리즘 문제 풀이(LeetCode 등)에서 가장 자주 사용되는 주요 함수들을 카테고리별로 정리해 드립니다.

#### 1. 변환 및 생성
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `lowercase()` / `uppercase()` | (없음) | `String` | 대/소문자로 변환 | `toLowerCase()`는 Deprecated 됨 |
| `reversed()` | (없음) | `String` | 문자열을 뒤집음 | `"abc"` → `"cba"` |
| `toCharArray()` | (없음) | `CharArray` | `CharArray`로 변환 | 문자 수정이 필요할 때 유용 |
| `split(delimiters)` | `vararg String` | `List<String>` | 구분자를 기준으로 리스트 분리 | 정규표현식 사용 가능 |
| `replace(old, new)` | `String`/`Char`, `String`/`Char` | `String` | 특정 문자(열)를 치환 | |
| `trim()` | (없음) | `String` | 앞뒤 공백 제거 | `trimIndent()` 등 변형 존재 |
| `padStart(length, padChar)` | `Int`, `Char` | `String` | 앞을 채워 길이 맞춤 | |
| `padEnd(length, padChar)` | `Int`, `Char` | `String` | 뒤를 채워 길이 맞춤 | |

#### 2. 검사 및 조건
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `isNullOrEmpty()` | (없음) | `Boolean` | `null`이거나 빈 문자열(`""`)인지 확인 | 안전한 검사 시 필수 |
| `isNullOrBlank()` | (없음) | `Boolean` | `null`, 빈 문자열, 혹은 공백만 있는지 확인 | `"  "`인 경우 true |
| `isEmpty()` / `isBlank()` | (없음) | `Boolean` | 빈/공백 문자열 여부 | non-null 대상 |
| `contains(other)` | `CharSequence`/`Char` | `Boolean` | 특정 문자열 포함 여부 | |
| `startsWith(prefix)` | `String`/`Char` | `Boolean` | 특정 접두사 확인 | |
| `endsWith(suffix)` | `String`/`Char` | `Boolean` | 특정 접미사 확인 | |
| `equals(other, ignoreCase = true)` | `String?`, `Boolean` | `Boolean` | 대소문자 무시 비교 가능 | `s1 == s2`는 대소문자 구분함 |

#### 3. 부분 추출 및 인덱스
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `substring(range)` | `IntRange` 또는 `Int, Int` | `String` | 지정한 범위의 부분 문자열 추출 | `s.substring(0..2)` |
| `first()` / `last()` | (없음) | `Char` | 첫 번째 / 마지막 문자 반환 | 빈 문자열인 경우 예외 발생 |
| `getOrNull(index)` | `Int` | `Char?` | 해당 인덱스 문자 반환 (안전) | 범위를 벗어나면 `null` |
| `indexOf(char)` | `Char`/`String` | `Int` | 특정 문자의 첫 위치 반환 | 없으면 `-1` |
| `lastIndexOf(char)` | `Char`/`String` | `Int` | 특정 문자의 마지막 위치 반환 | 없으면 `-1` |
| `length` | (프로퍼티) | `Int` | 문자열의 길이 | |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 인덱스 | `length - 1` |

#### 4. Char 관련 (문자 단위 판별)
문자열 순회 시 각 문자(`Char`)에 대해 자주 쓰이는 함수들입니다.

| 함수 | 입력 | 출력 | 설명 | 예시 |
| :--- | :--- | :--- | :--- | :--- |
| `isLetterOrDigit()` | (없음) | `Boolean` | 알파벳 혹은 숫자인지 확인 | `'a'`, `'1'` → `true` |
| `isLetter()` | (없음) | `Boolean` | 알파벳인지 확인 | |
| `isDigit()` | (없음) | `Boolean` | 숫자인지 확인 | |
| `isLowerCase()` / `isUpperCase()` | (없음) | `Boolean` | 대/소문자 여부 확인 | |

#### 5. 변환 (숫자/리스트 등)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `toInt()` / `toLong()` | (없음) | `Int` / `Long` | 숫자 문자열 → 정수 | 실패 시 예외 |
| `toIntOrNull()` | (없음) | `Int?` | 변환 실패 시 null | 안전 |
| `toList()` | (없음) | `List<Char>` | 문자 리스트로 변환 | |
| `chunked(size)` | `Int` | `List<String>` | 지정 길이로 잘라 리스트화 | |

---

#### 💡 팁: LeetCode #125 (Valid Palindrome) 활용 예시
위 함수들을 조합하면 문제를 매우 간결하게 풀 수 있습니다.

```kotlin
// 방법 1: 함수 체이닝 (가독성 중심)
fun isPalindrome(s: String): Boolean {
    val cleaned = s.filter { it.isLetterOrDigit() }.lowercase()
    return cleaned == cleaned.reversed()
}

// 방법 2: 투 포인터에서 활용
if (!s[left].isLetterOrDigit()) {
    left++
} else if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
    return false
}
```
