# Kotlin String 주요 함수 정리

`String`은 불변(immutable) 문자 시퀀스입니다. 인덱스 접근·슬라이싱·변환·빌더까지 폭넓게 활용됩니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 접근 및 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `length` | (프로퍼티) | `Int` | 문자열 길이 | ⭐ |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 인덱스 | `length - 1` |
| `indices` | (프로퍼티) | `IntRange` | `0..lastIndex` 범위 | ⭐ for 루프에서 자주 |
| `get(index)` / `[index]` | `Int` | `Char` | 인덱스 위치 문자 | 범위 벗어나면 예외 |
| `getOrNull(index)` | `Int` | `Char?` | 안전한 인덱스 접근 | 범위 벗어나면 null |
| `getOrElse(index) { }` | `Int`, `(Int) -> Char` | `Char` | 없으면 람다 결과 | |
| `first()` | (없음) | `Char` | 첫 번째 문자 | 빈 문자열이면 예외 |
| `first { }` | `(Char) -> Boolean` | `Char` | 조건 맞는 첫 문자 | |
| `last()` | (없음) | `Char` | 마지막 문자 | 빈 문자열이면 예외 |
| `last { }` | `(Char) -> Boolean` | `Char` | 조건 맞는 마지막 문자 | |
| `single()` | (없음) | `Char` | 길이가 1일 때 그 문자 | 2개 이상이면 예외 |

## 2. 부분 문자열
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `substring(startIndex)` | `Int` | `String` | startIndex부터 끝까지 | ⭐ |
| `substring(startIndex, endIndex)` | `Int, Int` | `String` | `[start, end)` 반열린 구간 | ⭐ |
| `substring(range)` | `IntRange` | `String` | `0..2` 닫힌 구간 | `s.substring(0..2)` |
| `subSequence(start, end)` | `Int, Int` | `CharSequence` | 반열린 구간, 복사 없음 | 무거운 문자열 슬라이딩에 유리 |
| `take(n)` | `Int` | `String` | 앞 n글자 | ⭐ `n > length`여도 안전 |
| `takeLast(n)` | `Int` | `String` | 뒤 n글자 | |
| `takeWhile { }` | `(Char) -> Boolean` | `String` | 조건이 참인 동안 앞에서 취함 | |
| `drop(n)` | `Int` | `String` | 앞 n글자 제거 후 나머지 | ⭐ |
| `dropLast(n)` | `Int` | `String` | 뒤 n글자 제거 | |
| `dropWhile { }` | `(Char) -> Boolean` | `String` | 조건이 참인 동안 앞에서 제거 | |
| `slice(range)` | `IntRange` / `Iterable<Int>` | `String` | 인덱스 집합으로 슬라이싱 | `s.slice(listOf(0,2,4))` |

## 3. 분리 및 결합 ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `split(vararg delimiters)` | `String...` | `List<String>` | 구분자로 분리 | ⭐ 빈 토큰 포함 가능 |
| `split(regex)` | `Regex` | `List<String>` | 정규식으로 분리 | `"\\s+".toRegex()` |
| `split(..., limit = n)` | 구분자, `Int` | `List<String>` | 최대 n조각으로 분리 | |
| `splitToSequence(...)` | `String...` | `Sequence<String>` | 지연 평가 분리 | 대용량 파싱에 유리 |
| `lines()` | (없음) | `List<String>` | 줄 단위로 분리 (`\n`) | ⭐ 멀티라인 입력 |
| `joinToString(sep)` | `CharSequence` | `String` | 구분자로 결합 | ⭐ |
| `joinToString(sep, prefix, postfix)` | 구분자·접두·접미 | `String` | 감싸기 포함 결합 | `"[1, 2, 3]"` |
| `chunked(size)` | `Int` | `List<String>` | 고정 길이로 잘라 리스트화 | ⭐ 블록 처리 |
| `chunked(size) { }` | `Int`, `(String) -> R` | `List<R>` | 잘라서 바로 변환 | |
| `windowed(size, step)` | `Int, Int` | `List<String>` | 슬라이딩 윈도우 | |

## 4. 변환
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `lowercase()` | (없음) | `String` | 소문자로 변환 | ⭐ `toLowerCase()`는 Deprecated |
| `uppercase()` | (없음) | `String` | 대문자로 변환 | |
| `trim()` | (없음) | `String` | 앞뒤 공백 제거 | ⭐ |
| `trimStart()` / `trimEnd()` | (없음) | `String` | 앞 또는 뒤 공백만 제거 | |
| `trim { }` | `(Char) -> Boolean` | `String` | 조건 문자 앞뒤 제거 | |
| `reversed()` | (없음) | `String` | 문자열 뒤집기 | ⭐ 팰린드롬 검사 |
| `toCharArray()` | (없음) | `CharArray` | `CharArray`로 변환 | ⭐ 정렬·수정 필요 시 |
| `toList()` | (없음) | `List<Char>` | 문자 리스트로 변환 | |
| `toSet()` | (없음) | `Set<Char>` | 고유 문자 집합 | |
| `toSortedSet()` | (없음) | `SortedSet<Char>` | 정렬된 고유 문자 집합 | |
| `toInt()` | (없음) | `Int` | 숫자 문자열 → Int | 실패 시 예외 |
| `toIntOrNull()` | (없음) | `Int?` | 실패 시 null | ⭐ 안전 변환 |
| `toLong()` / `toLongOrNull()` | (없음) | `Long` / `Long?` | Long 변환 | 큰 수 문제 |
| `toDouble()` / `toDoubleOrNull()` | (없음) | `Double` / `Double?` | Double 변환 | |
| `replace(old, new)` | `String, String` | `String` | 문자열 치환 | ⭐ |
| `replace(char, char)` | `Char, Char` | `String` | 단일 문자 치환 | |
| `replace(regex, replacement)` | `Regex, String` | `String` | 정규식 치환 | |
| `replaceFirst(old, new)` | `String, String` | `String` | 첫 번째 일치만 치환 | |
| `filter { }` | `(Char) -> Boolean` | `String` | 조건 만족 문자만 남김 | ⭐ 문자 제거 |
| `map { }` | `(Char) -> R` | `List<R>` | 각 문자 변환 → List | |
| `flatMap { }` | `(Char) -> Iterable<R>` | `List<R>` | 문자별 펼침 | |

## 5. 탐색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `indexOf(char)` | `Char` | `Int` | 첫 위치, 없으면 -1 | ⭐ |
| `indexOf(string)` | `String` | `Int` | 부분 문자열 첫 위치 | |
| `indexOf(char, fromIndex)` | `Char, Int` | `Int` | 특정 위치부터 탐색 | |
| `lastIndexOf(char)` | `Char` | `Int` | 마지막 위치, 없으면 -1 | |
| `contains(other)` | `CharSequence` | `Boolean` | 부분 문자열 포함 여부 | ⭐ `"bc" in s` |
| `contains(char)` | `Char` | `Boolean` | 단일 문자 포함 여부 | `'a' in s` |
| `contains(regex)` | `Regex` | `Boolean` | 정규식 매칭 포함 여부 | |
| `startsWith(prefix)` | `String` | `Boolean` | 접두사 확인 | ⭐ |
| `endsWith(suffix)` | `String` | `Boolean` | 접미사 확인 | ⭐ |
| `count { }` | `(Char) -> Boolean` | `Int` | 조건 만족 문자 수 | ⭐ |
| `any { }` | `(Char) -> Boolean` | `Boolean` | 하나라도 조건 만족 | |
| `all { }` | `(Char) -> Boolean` | `Boolean` | 모두 조건 만족 | ⭐ |
| `none { }` | `(Char) -> Boolean` | `Boolean` | 모두 조건 불만족 | |
| `find { }` | `(Char) -> Boolean` | `Char?` | 조건 만족 첫 문자 | |
| `findLast { }` | `(Char) -> Boolean` | `Char?` | 조건 만족 마지막 문자 | |

## 6. 검사
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `isEmpty()` | (없음) | `Boolean` | 길이가 0이면 true | |
| `isNotEmpty()` | (없음) | `Boolean` | 길이가 1 이상이면 true | |
| `isBlank()` | (없음) | `Boolean` | 비었거나 공백만 있으면 true | ⭐ |
| `isNotBlank()` | (없음) | `Boolean` | 공백 아닌 문자 있으면 true | |
| `isNullOrEmpty()` | (없음) | `Boolean` | null이거나 빈 문자열 | nullable String에서 사용 |
| `isNullOrBlank()` | (없음) | `Boolean` | null이거나 blank | nullable String에서 사용 |
| `all { it.isDigit() }` | `(Char) -> Boolean` | `Boolean` | 모두 숫자인지 | ⭐ 정수 문자열 검증 |
| `all { it.isLetter() }` | `(Char) -> Boolean` | `Boolean` | 모두 알파벳인지 | |
| `equals(other, ignoreCase)` | `String?, Boolean` | `Boolean` | 대소문자 무시 비교 | `ignoreCase = true` |

## 7. Char 단위 판별
| 함수 | 출력 | 설명 | 예시 |
| :--- | :--- | :--- | :--- |
| `isDigit()` | `Boolean` | 숫자 `0-9` | `'3'.isDigit()` → true |
| `isLetter()` | `Boolean` | 알파벳 | `'a'.isLetter()` → true |
| `isLetterOrDigit()` | `Boolean` | 알파벳 또는 숫자 | ⭐ 팰린드롬 전처리 |
| `isUpperCase()` | `Boolean` | 대문자 | |
| `isLowerCase()` | `Boolean` | 소문자 | |
| `isWhitespace()` | `Boolean` | 공백 문자 | |
| `lowercaseChar()` | `Char` | 소문자로 변환 | ⭐ 투 포인터 비교 시 |
| `uppercaseChar()` | `Char` | 대문자로 변환 | |
| `digitToInt()` | `Int` | `'7'` → `7` | ⭐ 자릿수 처리 |
| `code` | `Int` | 유니코드 코드포인트 | `'a'.code` = 97 |
| `- 'a'` | `Int` | 알파벳 오프셋 | ⭐ `IntArray(26)` 인덱스 |

## 8. 빌드 ⭐
| 방식 | 설명 | 비고 |
| :--- | :--- | :--- |
| `StringBuilder()` | 가변 문자열 빌더 | ⭐ 루프 내 `+` 대신 사용 |
| `sb.append(value)` | 뒤에 추가 | `String`, `Char`, `Int` 등 |
| `sb.insert(index, value)` | 특정 위치에 삽입 | |
| `sb.delete(start, end)` | `[start, end)` 구간 삭제 | |
| `sb.deleteCharAt(index)` | 특정 인덱스 문자 삭제 | ⭐ |
| `sb.replace(start, end, str)` | 구간을 str로 교체 | |
| `sb.reverse()` | 내부 반전 (in-place) | ⭐ |
| `sb.setCharAt(index, char)` | 특정 위치 문자 변경 | |
| `sb.toString()` | `String`으로 변환 | |
| `sb.length` | 현재 길이 | |
| `buildString { append("...") }` | DSL 방식 빌더 | ⭐ 함수형으로 깔끔 |

## 9. 포매팅
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `padStart(length, padChar)` | `Int, Char` | `String` | 왼쪽 채움 | ⭐ `padStart(5, '0')` |
| `padEnd(length, padChar)` | `Int, Char` | `String` | 오른쪽 채움 | |
| `repeat(n)` | `Int` | `String` | n번 반복 | ⭐ `"ab".repeat(3)` = `"ababab"` |
| `String.format(vararg args)` | `Any...` | `String` | printf 스타일 | `"%02d".format(5)` = `"05"` |
| `"$변수"` / `"${expr}"` | 템플릿 | `String` | 문자열 템플릿 | ⭐ |

## 10. 정규식 (Regex)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `"패턴".toRegex()` | (없음) | `Regex` | 정규식 객체 생성 | |
| `Regex("패턴")` | `String` | `Regex` | 정규식 객체 생성 | |
| `regex.matches(input)` | `CharSequence` | `Boolean` | 전체 문자열 매칭 | `^...$` 불필요 |
| `regex.containsMatchIn(input)` | `CharSequence` | `Boolean` | 부분 매칭 포함 여부 | |
| `regex.find(input)` | `CharSequence` | `MatchResult?` | 첫 번째 매칭 | `.value`, `.range` |
| `regex.findAll(input)` | `CharSequence` | `Sequence<MatchResult>` | 모든 매칭 | ⭐ |
| `regex.replace(input, replacement)` | `CharSequence, String` | `String` | 매칭 부분 치환 | |
| `regex.split(input)` | `CharSequence` | `List<String>` | 정규식으로 분리 | |
| `input.matches(regex)` | `Regex` | `Boolean` | String 확장, 전체 매칭 | |

---

## 11. 실전 사용법 (코드)

### 팰린드롬 검사 — reversed() 비교
```kotlin
// 방법 A: reversed() 비교 (한 줄)
fun isPalindrome(s: String) = s == s.reversed()

// 방법 B: 투 포인터 (공간 O(1))
fun isPalindrome(s: String): Boolean {
    var l = 0; var r = s.lastIndex
    while (l < r) {
        if (s[l] != s[r]) return false
        l++; r--
    }
    return true
}

// LeetCode #125 — 영숫자만 남기고 소문자 통일 후 검사
fun isPalindromeAlphanumeric(s: String): Boolean {
    val clean = s.filter { it.isLetterOrDigit() }.lowercase()
    return clean == clean.reversed()
}
```

### 아나그램 검사 — 정렬 또는 빈도 비교 ⭐
```kotlin
// 방법 A: 정렬 비교
fun isAnagram(s: String, t: String) =
    s.length == t.length && s.toCharArray().sorted() == t.toCharArray().sorted()

// 방법 B: IntArray(26) 빈도 비교 (O(n), 알파벳 소문자 전용)
fun isAnagramFast(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val freq = IntArray(26)
    for (c in s) freq[c - 'a']++
    for (c in t) freq[c - 'a']--
    return freq.all { it == 0 }
}
```

### 토큰 파싱 — split·trim·toInt ⭐
```kotlin
// "  3  5  1  " → [3, 5, 1]
val nums = line.trim().split("\\s+".toRegex()).map { it.toInt() }

// "apple,banana, cherry " → ["apple", "banana", "cherry"]
val tokens = input.split(",").map { it.trim() }.filter { it.isNotBlank() }
```

### 문자 빈도 — IntArray(26)
```kotlin
// 알파벳 소문자 빈도 세기
val freq = IntArray(26)
for (c in s) freq[c - 'a']++

// 가장 많이 나온 문자
val maxChar = 'a' + freq.indices.maxByOrNull { freq[it] }!!
```

### 문자 빈도 — Map 방식 (범용)
```kotlin
val freq = s.groupingBy { it }.eachCount()         // Map<Char, Int>
val topChar = freq.maxByOrNull { it.value }?.key
```

### StringBuilder로 문자열 조립 ⭐
```kotlin
// 루프 내 문자열 조립 — + 대신 StringBuilder 필수
val sb = StringBuilder()
for (i in 0 until n) {
    sb.append(arr[i])
    if (i < n - 1) sb.append(", ")
}
val result = sb.toString()

// buildString DSL
val result2 = buildString {
    repeat(n) { i ->
        append(arr[i])
        if (i < n - 1) append(", ")
    }
}
```

### 슬라이딩 윈도우 — 부분 문자열 탐색
```kotlin
// 길이 k인 모든 부분 문자열 순회
for (i in 0..s.length - k) {
    val window = s.substring(i, i + k)
    // ...
}
// chunked / windowed 활용
s.windowed(k).forEach { window -> /* ... */ }
```

### 0 패딩 및 숫자 포매팅
```kotlin
val n = 7
val padded = n.toString().padStart(5, '0')   // "00007"
val formatted = "%05d".format(n)             // "00007"
```

### 대소문자·문자 변환
```kotlin
val s = "Hello World"
s.lowercase()                                // "hello world"
s.filter { it.isLetter() }                  // "HelloWorld"
s.map { it.lowercaseChar() }                // [h, e, l, l, o, ...]
s.replace(" ", "_")                         // "Hello_World"
```

---

## 12. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 팰린드롬 검사 | `s == s.reversed()` |
| 팰린드롬(영숫자) | `s.filter{it.isLetterOrDigit()}.lowercase().let{it==it.reversed()}` |
| 아나그램 검사 | `s.toCharArray().sorted() == t.toCharArray().sorted()` |
| 문자 빈도 세기 | `s.groupingBy{it}.eachCount()` |
| 알파벳 빈도 배열 | `IntArray(26).also{ for(c in s) it[c-'a']++ }` |
| 공백 구분 파싱 | `line.trim().split("\\s+".toRegex()).map{it.toInt()}` |
| 줄 단위 분리 | `input.lines()` |
| 문자 제거 | `s.filter{ it != ' ' }` |
| 앞 n글자 / 뒤 n글자 | `s.take(n)` / `s.takeLast(n)` |
| 특정 문자 개수 | `s.count{ it == 'a' }` |
| n글자씩 자르기 | `s.chunked(n)` |
| 문자열 반복 | `"ab".repeat(n)` |
| 0 패딩 | `n.toString().padStart(5, '0')` |
| 문자열 조립(루프) | `buildString{ ... }` 또는 `StringBuilder` |
| 인덱스+문자 순회 | `s.forEachIndexed{ i, c -> }` |
| Char → Int 오프셋 | `c - 'a'` |
| Char 숫자 → Int | `c.digitToInt()` |
| 정규식 전체 매칭 | `Regex("^[a-z]+$").matches(s)` |
| 정규식 모든 매칭 | `Regex("\\d+").findAll(s).map{it.value}` |

> ⚠️ `String`은 불변이므로 루프 내 `+=` 반복은 O(n²). 반드시 `StringBuilder` 또는 `buildString { }` 사용.
>
> ⚠️ `substring(start, end)`는 `[start, end)` 반열린 구간. `substring(0..2)`는 닫힌 구간 `[0, 2]`로 다름에 주의.
>
> 🎯 **코테 3종 세트**: ① 팰린드롬 → `reversed()` 비교 or 투 포인터 ② 아나그램 → `IntArray(26)` 빈도 ③ 토큰 파싱 → `trim().split().map{toInt()}`. 도구는 다 여기 있어요 — **조립은 직접!**
