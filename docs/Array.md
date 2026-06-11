# Kotlin Array 주요 함수 정리

`Array`는 크기가 고정된 인덱스 접근(`O(1)`) 자료구조입니다. 기본 타입 전용 배열(`IntArray` 등)은 박싱 오버헤드 없이 성능상 유리합니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `arrayOf(vararg elements)` ⭐ | `T...` | `Array<T>` | 값 지정 배열 생성 | `arrayOf(1, 2, 3)` |
| `intArrayOf(...)` ⭐ | `Int...` | `IntArray` | 기본 타입 배열 생성 | 박싱 없음 |
| `IntArray(n)` | `Int` | `IntArray` | 0으로 초기화 | `LongArray`, `BooleanArray` 등 동일 |
| `IntArray(n) { i -> ... }` ⭐ | `Int`, `(Int) -> Int` | `IntArray` | 람다로 초기화 | `IntArray(5) { it * 2 }` |
| `Array(n) { ... }` ⭐ | `Int`, `(Int) -> T` | `Array<T>` | 제네릭 배열 생성 | `Array(5) { "" }` |
| `arrayOfNulls<T>(n)` | `Int` | `Array<T?>` | null로 초기화 | nullable 배열 |
| `emptyArray<T>()` | (없음) | `Array<T>` | 빈 배열 생성 | |
| `size` | (프로퍼티) | `Int` | 배열 크기 | 고정, 변경 불가 |
| `indices` ⭐ | (프로퍼티) | `IntRange` | 인덱스 범위 `0..size-1` | `for (i in arr.indices)` |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 인덱스 | `size - 1` |
| `isEmpty()` / `isNotEmpty()` | (없음) | `Boolean` | 비었는지 여부 | |

## 2. 요소 접근 및 수정
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(index)` / `[index]` ⭐ | `Int` | `T` | 인덱스로 접근 | 범위 벗어나면 예외 |
| `set(index, value)` / `[index] = v` | `Int`, `T` | `Unit` | 인덱스 요소 수정 | |
| `first()` / `last()` ⭐ | (없음) | `T` | 첫 / 마지막 요소 | 빈 배열이면 예외 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 빈 배열이면 null | 안전한 접근 |
| `getOrNull(index)` | `Int` | `T?` | 범위 벗어나면 null | |
| `getOrElse(index) { default }` | `Int`, `(Int) -> T` | `T` | 범위 벗어나면 기본값 | |
| `fill(element)` | `T` | `Unit` | 전체를 한 값으로 채움 | `fill(element, from, to)` 부분 채움 가능 |

## 3. 정렬 (Sorting) ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `sort()` ⭐ | (없음) | `Unit` | 오름차순 in-place 정렬 | **원본 변경** |
| `sortDescending()` ⭐ | (없음) | `Unit` | 내림차순 in-place 정렬 | **원본 변경** |
| `sorted()` ⭐ | (없음) | `List<T>` | 오름차순 새 리스트 반환 | 원본 유지 |
| `sortedDescending()` | (없음) | `List<T>` | 내림차순 새 리스트 반환 | 원본 유지 |
| `sortedBy { ... }` ⭐ | `(T) -> R` | `List<T>` | 키 기준 오름차순 리스트 | |
| `sortedByDescending { ... }` | `(T) -> R` | `List<T>` | 키 기준 내림차순 리스트 | |
| `sortedWith(comparator)` ⭐ | `Comparator<T>` | `List<T>` | 커스텀 비교자로 새 리스트 | 다중 기준 정렬 |
| `sortWith(comparator)` | `Comparator<T>` | `Unit` | 커스텀 비교자 in-place | **원본 변경** |
| `reverse()` | (없음) | `Unit` | 배열 순서 뒤집기 in-place | **원본 변경** |
| `reversed()` | (없음) | `List<T>` | 뒤집힌 새 리스트 반환 | 원본 유지 |
| `reversedArray()` | (없음) | `Array<T>` | 뒤집힌 새 배열 반환 | 원본 유지 |
| `sortedArray()` | (없음) | `Array<T>` | 오름차순 새 배열 반환 | Array 유지 필요할 때 |

## 4. 탐색 및 검색 (Searching)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `indexOf(element)` ⭐ | `T` | `Int` | 첫 번째 인덱스 | 없으면 -1, `O(n)` |
| `lastIndexOf(element)` | `T` | `Int` | 마지막 인덱스 | 없으면 -1 |
| `contains(element)` ⭐ | `T` | `Boolean` | 포함 여부 | `element in array`, `O(n)` |
| `indexOfFirst { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 첫 인덱스 | 없으면 -1 |
| `indexOfLast { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 마지막 인덱스 | 없으면 -1 |
| `find { ... }` ⭐ | `(T) -> Boolean` | `T?` | 조건 만족 첫 요소 | 없으면 null |
| `firstOrNull { ... }` | `(T) -> Boolean` | `T?` | `find`와 동일 | |
| `binarySearch(element)` ⭐ | `T` | `Int` | 이진 탐색 | ⚠️ 정렬된 배열 필수, `O(log n)` |
| `any { ... }` | `(T) -> Boolean` | `Boolean` | 하나라도 조건 만족? | |
| `all { ... }` | `(T) -> Boolean` | `Boolean` | 모두 조건 만족? | |
| `none { ... }` | `(T) -> Boolean` | `Boolean` | 아무것도 만족 안 함? | |
| `count { ... }` ⭐ | `(T) -> Boolean` | `Int` | 조건 만족 개수 | |

## 5. 변환 (Transformation)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `map { ... }` ⭐ | `(T) -> R` | `List<R>` | 각 요소 변환 | |
| `mapIndexed { i, x -> ... }` | `(Int, T) -> R` | `List<R>` | 인덱스와 함께 변환 | |
| `filter { ... }` ⭐ | `(T) -> Boolean` | `List<T>` | 조건 만족 요소만 | |
| `filterNot { ... }` | `(T) -> Boolean` | `List<T>` | 조건 불만족 요소만 | |
| `filterNotNull()` | (없음) | `List<T>` | null 제거 | `Array<T?>` → `List<T>` |
| `flatMap { ... }` | `(T) -> Iterable<R>` | `List<R>` | 변환 후 평탄화 | |
| `flatten()` | (없음) | `List<T>` | 중첩 배열 1차원으로 | `Array<Array<T>>` 전용 |
| `zip(other)` | `Array<R>` | `List<Pair<T, R>>` | 두 배열을 짝지음 | 짧은 쪽 기준 |
| `toList()` ⭐ | (없음) | `List<T>` | 리스트로 변환 | |
| `toMutableList()` ⭐ | (없음) | `MutableList<T>` | 변경 가능 리스트 | |
| `toSet()` | (없음) | `Set<T>` | 중복 제거 집합 | |
| `toIntArray()` | (없음) | `IntArray` | `IntArray`로 변환 | `List<Int>` → `IntArray` |
| `toTypedArray()` | (없음) | `Array<T>` | `IntArray` → `Array<Int>` | |
| `joinToString(sep)` ⭐ | `String` | `String` | 문자열로 연결 | 기본 `", "` |
| `copyOf()` | (없음) | `Array<T>` | 얕은 복사 | `copyOf(newSize)` 크기 변경 가능 |

## 6. 집계 (Aggregation)
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `sum()` ⭐ | `Int`/`Long`/`Double` | 전체 합 | 숫자 배열 전용 |
| `sumOf { ... }` | `Int`/`Long`/`Double` | 변환 후 합 | `sumOf { it.length }` |
| `average()` | `Double` | 평균 | 숫자 배열 전용 |
| `max()` / `min()` | `T` | 최댓값 / 최솟값 | 빈 배열이면 예외 (deprecated) |
| `maxOrNull()` / `minOrNull()` ⭐ | `T?` | 빈 배열이면 null | **권장** |
| `maxByOrNull { ... }` | `T?` | 특정 키가 최대인 요소 | |
| `minByOrNull { ... }` | `T?` | 특정 키가 최소인 요소 | |
| `count { ... }` | `Int` | 조건 만족 개수 | |
| `any { ... }` / `all { ... }` / `none { ... }` | `Boolean` | 조건 검사 | |
| `reduce { acc, x -> ... }` | `T` | 첫 요소부터 누적 | 빈 배열이면 예외 |
| `fold(init) { acc, x -> ... }` ⭐ | `R` | 초깃값부터 누적 | 타입 변환 가능, 빈 배열 안전 |
| `runningFold(init) { ... }` ⭐ | `List<R>` | 누적 중간 과정 전부 기록 | prefix sum 배열 생성 |
| `scan(init) { ... }` | `List<R>` | `runningFold`와 동일 | |

## 7. 슬라이싱 (Slicing)
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `slice(indices)` ⭐ | `List<T>` | IntRange 또는 목록으로 잘라냄 | `arr.slice(1..3)` |
| `sliceArray(indices)` | `Array<T>` | slice와 같으나 배열 반환 | |
| `copyOfRange(from, to)` | `Array<T>` | `[from, to)` 범위 복사 | to 미포함 |
| `take(n)` ⭐ | `List<T>` | 앞에서 n개 | |
| `takeLast(n)` | `List<T>` | 뒤에서 n개 | |
| `takeWhile { ... }` | `List<T>` | 조건 만족하는 동안 앞에서 | 첫 실패 시 중단 |
| `drop(n)` ⭐ | `List<T>` | 앞 n개 제외 | |
| `dropLast(n)` | `List<T>` | 뒤 n개 제외 | |
| `dropWhile { ... }` | `List<T>` | 조건 만족하는 동안 앞에서 제외 | |
| `chunked(size)` | `List<List<T>>` | 고정 크기로 자르기 | |
| `windowed(size, step)` ⭐ | `List<List<T>>` | 슬라이딩 윈도우 | 연속 구간 문제 |

## 8. 순회 (Iteration)
| 방식 | 예시 | 비고 |
| :--- | :--- | :--- |
| 기본 for | `for (x in arr)` | 값만 필요할 때 |
| 인덱스 for | `for (i in arr.indices)` | 인덱스 필요할 때 |
| forEach | `arr.forEach { x -> }` | 람다 스타일 |
| forEachIndexed ⭐ | `arr.forEachIndexed { i, x -> }` | 인덱스+값 동시 |
| withIndex ⭐ | `for ((i, x) in arr.withIndex())` | 구조 분해 |

## 9. 중복 제거 및 그룹화
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `distinct()` | `List<T>` | 중복 제거 (입력 순서 유지) | 내부적으로 LinkedHashSet |
| `distinctBy { ... }` | `List<T>` | 특정 키 기준 중복 제거 | |
| `groupBy { ... }` ⭐ | `Map<K, List<T>>` | 키별로 묶기 | |
| `groupingBy { }.eachCount()` ⭐ | `Map<T, Int>` | 원소별 개수 세기 | 빈도 한 줄 |
| `partition { ... }` ⭐ | `Pair<List, List>` | 조건 만족/불만족 둘로 분리 | |
| `associateWith { ... }` | `Map<T, V>` | 요소를 키로, 값을 람다로 | |

## 10. 2차원 배열 (행렬) ⭐
| 생성 방법 | 설명 |
| :--- | :--- |
| `Array(rows) { IntArray(cols) }` | 0으로 초기화된 행렬 (권장) |
| `Array(rows) { r -> IntArray(cols) { c -> r * c } }` | 좌표로 초기화 |
| `arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))` | 값 직접 지정 |

## 11. 배열 비교 및 출력 ⚠️
| 하고 싶은 것 | 잘못된 방법 | 올바른 방법 |
| :--- | :--- | :--- |
| 내용 비교 | `a == b` (참조 비교) | `a.contentEquals(b)` |
| 내용 출력 | `println(a)` (`[I@1b6d3586`) | `println(a.contentToString())` |
| 2차원 내용 비교 | `a.contentEquals(b)` (얕음) | `a.contentDeepEquals(b)` |
| 2차원 내용 출력 | `a.contentToString()` (얕음) | `a.contentDeepToString()` |

---

## 12. 실전 사용법 (코드)

### 입력 파싱 — 한 줄 정수 배열 ⭐
```kotlin
val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
// 박싱 피하려면 toIntArray(), 제네릭 필요하면 toTypedArray()
```

### 정렬 후 투 포인터 ⭐
```kotlin
// 두 수의 합이 target인 쌍 찾기
val arr = intArrayOf(1, 3, 5, 7, 9)
arr.sort()
var lo = 0; var hi = arr.lastIndex
while (lo < hi) {
    val s = arr[lo] + arr[hi]
    when {
        s == target -> { /* 찾음 */ lo++; hi-- }
        s < target  -> lo++
        else        -> hi--
    }
}
```

### 누적합 (Prefix Sum) ⭐⭐
```kotlin
val nums = intArrayOf(1, 2, 3, 4, 5)

// 방법 A: runningFold (가장 Kotlin스러움)
val prefix = nums.runningFold(0) { acc, x -> acc + x }
// prefix == [0, 1, 3, 6, 10, 15]
// 구간 [l, r] 합: prefix[r + 1] - prefix[l]

// 방법 B: IntArray 직접 구성
val pre = IntArray(nums.size + 1)
for (i in nums.indices) pre[i + 1] = pre[i] + nums[i]
val rangeSum = pre[4] - pre[1]  // 인덱스 1..3 합 = 9
```

### 빈도 배열 (Frequency Array) ⭐
```kotlin
// 값의 범위가 정해진 경우 (예: 알파벳, 0..100)
val freq = IntArray(26)
"hello".forEach { freq[it - 'a']++ }
// 또는 Map 방식:
val freqMap = "hello".groupingBy { it }.eachCount()
```

### 다중 기준 정렬 — 길이 오름차순, 동점이면 사전순 ⭐
```kotlin
val words = arrayOf("bb", "a", "cc", "b")
val sorted = words.sortedWith(compareBy({ it.length }, { it }))  // [a, b, bb, cc]

// 내림차순 + 타이브레이크
words.sortedWith(compareByDescending<String> { it.length }.thenBy { it })
```

### binarySearch — 삽입 위치 계산 ⭐
```kotlin
val arr = intArrayOf(10, 20, 30, 40)  // 정렬 필수
val r = arr.binarySearch(25)
// 못 찾으면 -(삽입될 인덱스) - 1 반환
val insertAt = if (r >= 0) r else -(r + 1)  // → 2
```

### 2차원 배열 생성 및 접근 ⭐
```kotlin
val rows = 3; val cols = 4
val grid = Array(rows) { IntArray(cols) }   // 3×4, 전부 0
grid[1][2] = 7
val h = grid.size        // 행 수
val w = grid[0].size     // 열 수

// ⚠️ 같은 참조 함정
// val bad = Array(3) { row }  // ❌ 모든 행이 같은 배열
val good = Array(3) { IntArray(3) }  // ✅ 각 행이 독립
```

### 배열 복사 및 비교
```kotlin
val a = intArrayOf(1, 2, 3)
val b = a.copyOf()               // 독립적 복사
println(a.contentEquals(b))      // true
println(a.contentToString())     // [1, 2, 3]

val grid2 = Array(2) { intArrayOf(1, 2) }
println(grid2.contentDeepToString())  // [[1, 2], [1, 2]]
```

### 자주 쓰는 변환 패턴
```kotlin
val arr = intArrayOf(1, 2, 3, 4, 5)
arr.joinToString(" ")            // "1 2 3 4 5"  출력 포맷
arr.filter { it % 2 == 0 }      // [2, 4]
arr.map { it * it }              // [1, 4, 9, 16, 25]
arr.fold(0) { acc, x -> acc + x } // 15
arr.take(3)                      // [1, 2, 3]
arr.drop(2)                      // [3, 4, 5]
arr.slice(1..3)                  // [2, 3, 4]
arr.maxOrNull()                  // 5
```

---

## 13. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 한 줄 정수 입력 | `readLine()!!.split(" ").map { it.toInt() }.toIntArray()` |
| 크기 n, 0으로 초기화 | `IntArray(n)` |
| 람다로 초기화 | `IntArray(n) { i -> i * 2 }` |
| 오름차순 정렬 (원본) | `arr.sort()` |
| 내림차순 정렬 (원본) | `arr.sortDescending()` |
| 다중 기준 정렬 | `sortedWith(compareBy({ 1순위 }, { 2순위 }))` |
| 내림차순 + 타이브레이크 | `sortedWith(compareByDescending<T>{...}.thenBy{...})` |
| 배열 뒤집기 (원본) | `arr.reverse()` |
| 이진 탐색 | `arr.binarySearch(x)` (정렬 선행 필수) |
| 삽입 위치 계산 | `val pos = binarySearch(x).let { if (it >= 0) it else -(it+1) }` |
| 누적합 배열 | `arr.runningFold(0) { acc, x -> acc + x }` |
| 구간 [l, r] 합 | `prefix[r + 1] - prefix[l]` |
| 빈도 배열 (알파벳) | `IntArray(26).also { "str".forEach { c -> it[c - 'a']++ } }` |
| 빈도 Map | `list.groupingBy { it }.eachCount()` |
| 두 배열 내용 비교 | `a.contentEquals(b)` |
| 배열 → 문자열 출력 | `arr.contentToString()` |
| 투 포인터 초기화 | `var lo = 0; var hi = arr.lastIndex` |
| 중복 제거 | `arr.distinct()` |
| 조건 분리 | `arr.partition { 조건 }` |
| 슬라이딩 윈도우 | `arr.windowed(size, step)` |
| 2차원 배열 생성 | `Array(r) { IntArray(c) }` |

> ⚠️ `sort()` / `sortDescending()`은 **원본이 바뀜**. 원본을 보존해야 하면 `sorted()` / `sortedDescending()`(결과가 `List`)를 쓸 것.
>
> ⚠️ `binarySearch`는 **정렬된 배열에서만** 올바르게 동작. 못 찾으면 음수 반환 — 삽입 위치는 `-(result) - 1`.
>
> 🎯 **투 포인터 도구**: ① `sort()` → ② `lo = 0; hi = lastIndex` → ③ `while (lo < hi)` 조건에 따라 이동.
>
> 🎯 **누적합 도구**: ① `runningFold(0){acc,x->acc+x}` → ② `prefix[r+1] - prefix[l]`로 구간 합 `O(1)`. 쿼리가 많을수록 빛남.
