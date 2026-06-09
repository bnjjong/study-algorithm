# Kotlin Array 주요 함수 및 특징 정리

Kotlin에서 `Array`는 고정된 크기를 가지며, 내부 요소의 변경이 가능한(Mutable) 자료구조입니다.

> **한 줄 요약**: 크기는 고정, 값은 변경 가능. 인덱스 임의 접근(`O(1)`)이 강점이고, 삽입/삭제로 크기를 바꾸려면 `List`/`MutableList`를 쓰는 게 맞습니다.

## 1. 변수 선언 및 초기화
Kotlin에서는 `val`(읽기 전용 참조) 또는 `var`(변경 가능한 참조) 키워드를 사용하여 배열을 선언합니다.

```kotlin
// 1. 초기값을 직접 지정하여 생성
val numbers = arrayOf(1, 2, 3, 4, 5) // Array<Int>
val strings = arrayOf("A", "B", "C") // Array<String>

// 2. 크기만 지정하고 람다로 초기화 (추천)
val squares = Array(5) { i -> i * i } // [0, 1, 4, 9, 16]

// 3. 특정 타입의 빈 배열 생성
val empty = emptyArray<String>()

// 4. Null을 허용하는 배열
val nulls = arrayOfNulls<Int>(3) // [null, null, null]

// 5. 기본 타입 전용 배열 (박싱 오버헤드 없음)
val intArray = intArrayOf(1, 2, 3)
val doubleArray = DoubleArray(5) { 0.0 }
```

> 💡 `val ranked = readLine()!!.split(" ").map { it.toInt() }.toIntArray()`
> PS(Problem Solving)에서 한 줄 입력을 배열로 받는 가장 흔한 패턴입니다. 박싱을 피하려면 `toIntArray()`, 제네릭이 필요하면 `toTypedArray()`.

## 2. 생성 및 기본 정보
| 함수/프로퍼티 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `arrayOf(vararg elements: T)` | `T...` | `Array<T>` | 특정 요소들을 포함하는 배열 생성 | `arrayOf(1, 2, 3)` |
| `Array(size) { index -> ... }` | `Int`, `(Int) -> T` | `Array<T>` | 크기와 초기화 로직으로 배열 생성 | `Array(5) { it * 2 }` |
| `emptyArray<T>()` | (없음) | `Array<T>` | 빈 배열 생성 | |
| `arrayOfNulls<T>(size)` | `Int` | `Array<T?>` | null로 초기화된 배열 생성 | |
| `size` | (프로퍼티) | `Int` | 배열의 크기 | 고정 크기 |
| `indices` | (프로퍼티) | `IntRange` | 인덱스 범위 반환 (`0..size-1`) | `for (i in arr.indices)` |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 요소의 인덱스 | `size - 1` |

## 3. 요소 접근 및 수정
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(index)` / `[index]` | `Int` | `T` | 해당 인덱스의 요소 접근 | 범위 벗어나면 예외 |
| `set(index, value)` / `[index] = value` | `Int`, `T` | `Unit` | 해당 인덱스의 요소 수정 | |
| `first()` / `last()` | (없음) | `T` | 첫 번째 / 마지막 요소 반환 | 빈 배열이면 예외 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 비었으면 null 반환 | 안전한 접근 |
| `getOrNull(index)` | `Int` | `T?` | 범위를 벗어나면 null 반환 | 안전한 접근 |
| `getOrElse(index) { default }` | `Int`, `(Int) -> T` | `T` | 범위를 벗어나면 기본값 반환 | |
| `fill(element)` | `T` | `Unit` | 모든 요소를 특정 값으로 채움 | 원본 변경 |

## 4. 정렬 (Sorting)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `sort()` | (없음) | `Unit` | 배열 자체를 오름차순 정렬 | **원본 변경** |
| `sortDescending()` | (없음) | `Unit` | 배열 자체를 내림차순 정렬 | **원본 변경** |
| `sortedArray()` | (없음) | `Array<T>` | 정렬된 **새 배열** 반환 | 원본 유지 |
| `sortedArrayDescending()` | (없음) | `Array<T>` | 내림차순 정렬된 새 배열 반환 | 원본 유지 |
| `sortedBy { ... }` | `(T) -> R` | `List<T>` | 특정 키 기준 정렬된 리스트 | 결과는 `List` |
| `sortedByDescending { ... }` | `(T) -> R` | `List<T>` | 특정 키 기준 내림차순 리스트 | 결과는 `List` |
| `sortWith(comparator)` | `Comparator<T>` | `Unit` | 커스텀 비교자로 정렬 | 다중 기준 정렬에 유용 |

```kotlin
// in-place(원본 변경) vs 새 객체 반환 구분이 중요
val a = intArrayOf(3, 1, 2)
a.sort()                 // a == [1, 2, 3]  (원본이 바뀜)

val b = arrayOf(3, 1, 2)
val c = b.sortedArray()  // c == [1, 2, 3], b는 그대로 [3, 1, 2]

// 다중 기준 정렬: 길이 오름차순, 같으면 사전순
val words = arrayOf("bb", "a", "cc", "b")
words.sortWith(compareBy({ it.length }, { it }))  // [a, b, bb, cc]
```

## 5. 검색 및 탐색 (Searching)
PS에서 가장 자주 쓰는 영역입니다. 특히 **정렬된 배열에서는 `binarySearch`** 를 활용하세요.

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `indexOf(element)` | `T` | `Int` | 요소의 첫 인덱스 | 없으면 -1, `O(n)` |
| `lastIndexOf(element)` | `T` | `Int` | 요소의 마지막 인덱스 | 없으면 -1 |
| `contains(element)` | `T` | `Boolean` | 포함 여부 | `element in array`, `O(n)` |
| `indexOfFirst { ... }` | `(T) -> Boolean` | `Int` | 조건을 만족하는 첫 인덱스 | 없으면 -1 |
| `indexOfLast { ... }` | `(T) -> Boolean` | `Int` | 조건을 만족하는 마지막 인덱스 | 없으면 -1 |
| `find { ... }` | `(T) -> Boolean` | `T?` | 조건을 만족하는 첫 요소 | 없으면 null |
| `binarySearch(element)` | `T` | `Int` | **정렬된 배열**에서 이진 탐색 | `O(log n)`, ⚠️아래 설명 |
| `any { ... }` | `(T) -> Boolean` | `Boolean` | 하나라도 조건 만족? | |
| `all { ... }` | `(T) -> Boolean` | `Boolean` | 모두 조건 만족? | |
| `none { ... }` | `(T) -> Boolean` | `Boolean` | 아무도 조건 불만족? | |
| `count { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 요소 개수 | |

### ⭐ binarySearch 의 반환값 규칙
정렬된 배열에서 `O(log n)` 으로 탐색하지만, **못 찾았을 때의 반환값이 핵심**입니다.

- **찾으면**: 해당 요소의 인덱스 (0 이상)
- **못 찾으면**: `-(삽입되어야 할 위치) - 1` (음수)

```kotlin
val arr = intArrayOf(10, 20, 30, 40) // 오름차순 정렬 필수
arr.binarySearch(30)   // → 2  (찾음)
arr.binarySearch(25)   // → -3 ( = -(2) - 1, "인덱스 2에 들어가야 함"을 의미)

// 음수일 때 삽입 위치 복원: insertionPoint = -(result) - 1
val r = arr.binarySearch(25)
val insertionPoint = if (r >= 0) r else -(r + 1)  // → 2
```

> 💡 "정렬된 배열에서 X 이상/이하인 첫 위치 찾기" 류 문제는 이 삽입 위치 계산이 핵심입니다. (예: Climbing the Leaderboard에서 내 점수가 들어갈 자리 찾기)

## 6. 집계 및 축약 (Aggregation)
숫자 배열 통계나 누적 계산에 사용합니다.

| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `sum()` | `Int`/`Long`/`Double` | 전체 합 | 숫자 배열 전용 |
| `average()` | `Double` | 평균 | 숫자 배열 전용 |
| `max()` / `min()` | `T` | 최댓값 / 최솟값 | 빈 배열이면 예외 |
| `maxOrNull()` / `minOrNull()` | `T?` | 빈 배열이면 null | **권장** (max/min은 deprecated 경고 가능) |
| `maxByOrNull { ... }` | `T?` | 특정 키가 최대인 요소 | |
| `sumOf { ... }` | `Int`/`Long`/`Double` | 변환 후 합 | `sumOf { it.length }` |
| `reduce { acc, x -> ... }` | `T` | 첫 요소부터 누적 | 빈 배열이면 예외 |
| `fold(initial) { acc, x -> ... }` | `R` | **초깃값**부터 누적 | 타입 변환 가능, 빈 배열 안전 |
| `runningFold(...)` / `scan(...)` | `List<R>` | 누적 과정을 전부 기록 | 누적합 배열 만들 때 |

```kotlin
val nums = intArrayOf(1, 2, 3, 4)
nums.sum()                          // 10
nums.maxOrNull()                    // 4
nums.fold(100) { acc, x -> acc + x } // 110 (초깃값 100 + 합 10)
nums.runningFold(0) { acc, x -> acc + x } // [0, 1, 3, 6, 10] 누적합(prefix sum)
```

## 7. 변환 (Transformation)
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `map { ... }` | `List<R>` | 각 요소를 변환 | 결과는 `List` |
| `mapIndexed { i, x -> ... }` | `List<R>` | 인덱스와 함께 변환 | |
| `filter { ... }` | `List<T>` | 조건에 맞는 요소만 | 결과는 `List` |
| `filterNot { ... }` | `List<T>` | 조건에 안 맞는 요소만 | |
| `filterNotNull()` | `List<T>` | null 제거 | `Array<T?>` → `List<T>` |
| `flatten()` | `List<T>` | 중첩 배열을 1차원으로 | `Array<Array<T>>` 평탄화 |
| `flatMap { ... }` | `List<R>` | 변환 후 평탄화 | |
| `zip(other)` | `List<Pair>` | 두 배열을 짝지음 | 짧은 쪽 길이까지 |
| `joinToString(separator)` | `String` | 문자열로 합침 | 기본 구분자 `", "` |
| `toList()` / `toMutableList()` | `List`/`MutableList` | 리스트로 변환 | |
| `toSet()` / `toHashSet()` | `Set` | 집합으로 변환 (중복 제거) | |
| `copyOf()` | `Array<T>` | 배열 복사 (얕은 복사) | |
| `reversedArray()` | `Array<T>` | 순서가 반전된 새 배열 | |

```kotlin
val arr = arrayOf(1, 2, 3)
arr.joinToString(" ")            // "1 2 3" (출력 포맷 맞출 때 자주 씀)
arr.map { it * it }              // [1, 4, 9]
arrayOf(1, 2).zip(arrayOf("a", "b")) // [(1, a), (2, b)]
```

## 8. 중복 제거 및 그룹화
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `distinct()` | `List<T>` | 중복 제거 (입력 순서 유지) | 내부적으로 `LinkedHashSet`, `O(n)` |
| `distinctBy { ... }` | `List<T>` | 특정 키 기준 중복 제거 | |
| `groupBy { ... }` | `Map<K, List<T>>` | 키별로 묶기 | |
| `partition { ... }` | `Pair<List, List>` | 조건 만족/불만족 둘로 분리 | |
| `chunked(size)` | `List<List<T>>` | 고정 크기로 자르기 | |
| `windowed(size, step)` | `List<List<T>>` | 슬라이딩 윈도우 | 연속 구간 문제에 유용 |
| `associateWith { ... }` | `Map<T, V>` | 요소를 키로, 값을 람다로 | |

```kotlin
// 정렬된 배열의 중복 제거: distinct() 한 줄이면 충분 (순서 유지)
intArrayOf(100, 100, 50, 40, 40).distinct() // [100, 50, 40]

// 단, 이미 정렬돼 있다면 인접 비교가 메모리상 더 효율적
val sorted = intArrayOf(100, 100, 50, 40, 40)
val unique = buildList {
    sorted.forEachIndexed { i, v -> if (i == 0 || v != sorted[i - 1]) add(v) }
}

listOf(1, 2, 3, 4, 5).partition { it % 2 == 0 } // ([2, 4], [1, 3, 5])
listOf(1, 2, 3, 4).windowed(2)                  // [[1,2], [2,3], [3,4]]
```

## 9. 부분 배열 및 슬라이싱
| 함수 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `slice(indices)` | `List<T>` | 인덱스 범위/목록으로 잘라냄 | `arr.slice(1..3)` |
| `copyOfRange(from, to)` | `Array<T>` | `[from, to)` 범위 복사 | to는 미포함 |
| `copyOf(newSize)` | `Array<T>` | 크기를 바꿔 복사 (확장/축소) | 늘어난 칸은 기본값/null |
| `take(n)` / `takeLast(n)` | `List<T>` | 앞/뒤에서 n개 | |
| `drop(n)` / `dropLast(n)` | `List<T>` | 앞/뒤 n개 제외 | |
| `takeWhile { ... }` | `List<T>` | 조건 만족하는 동안 앞에서 | 처음 실패하면 중단 |
| `dropWhile { ... }` | `List<T>` | 조건 만족하는 동안 앞에서 제외 | |

```kotlin
val arr = intArrayOf(10, 20, 30, 40, 50)
arr.slice(1..3)          // [20, 30, 40]
arr.copyOfRange(1, 3)    // [20, 30] (인덱스 3은 미포함)
arr.take(2)              // [10, 20]
arr.takeWhile { it < 35 } // [10, 20, 30]
```

## 10. 순회 (Iteration)
| 함수 | 설명 | 비고 |
| :--- | :--- | :--- |
| `for (x in arr)` | 요소 순회 | 가장 기본 |
| `for (i in arr.indices)` | 인덱스 순회 | 인덱스가 필요할 때 |
| `forEach { ... }` | 람다로 각 요소 처리 | |
| `forEachIndexed { i, x -> ... }` | 인덱스와 함께 처리 | |
| `withIndex()` | `IndexedValue` 시퀀스 | `for ((i, x) in arr.withIndex())` |

```kotlin
for ((index, value) in arr.withIndex()) {
    println("$index 번째: $value")
}
```

## 11. 2차원 배열 (행렬)
| 생성 방법 | 설명 |
| :--- | :--- |
| `Array(rows) { IntArray(cols) }` | 0으로 초기화된 행렬 (권장) |
| `Array(rows) { r -> IntArray(cols) { c -> r * c } }` | 좌표로 초기화 |
| `arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))` | 값 직접 지정 |

```kotlin
val rows = 3; val cols = 4
val grid = Array(rows) { IntArray(cols) }   // 3x4, 전부 0
grid[1][2] = 7                              // 접근/수정
val h = grid.size                           // 행 개수
val w = grid[0].size                        // 열 개수

// ⚠️ 흔한 함정: 같은 참조가 복제됨
val wrong = Array(3) { IntArray(3) }        // ✅ 각 행이 독립
// val bad = Array(3) { row }              // ❌ 모든 행이 같은 배열을 가리킴
```

## 12. 기본 타입 전용 배열 (Primitive Arrays)
성능 최적화를 위해 박싱(Boxing) 오버헤드가 없는 전용 배열 클래스가 존재합니다.
- `IntArray`, `LongArray`, `BooleanArray`, `DoubleArray`, `CharArray` 등
- 생성: `intArrayOf(1, 2, 3)`, `IntArray(5) { 0 }`
- 일반 `Array<Int>`보다 메모리 사용량과 속도 면에서 유리합니다.
- `Array<Int>` ↔ `IntArray` 변환: `.toIntArray()` / `.toTypedArray()`

> 💡 PS에서 수만~수십만 개의 정수를 다룰 땐 `Array<Int>` 대신 **`IntArray`** 를 쓰는 것이 박싱 비용을 줄여 시간 초과를 피하는 데 도움이 됩니다.

## 13. ⚠️ 배열 비교 및 출력 주의점 (자주 하는 실수)
배열은 `==`와 `toString()`이 **직관과 다르게 동작**합니다. PS에서 디버깅할 때 가장 헷갈리는 부분입니다.

| 하고 싶은 것 | ❌ 잘못된 방법 | ✅ 올바른 방법 |
| :--- | :--- | :--- |
| 내용 비교 | `a == b` (참조 비교) | `a.contentEquals(b)` |
| 내용 출력 | `println(a)` (`[I@1b6d3586`) | `println(a.contentToString())` |
| 2차원 내용 비교 | `a.contentEquals(b)` (얕음) | `a.contentDeepEquals(b)` |
| 2차원 내용 출력 | `a.contentToString()` | `a.contentDeepToString()` |

```kotlin
val a = intArrayOf(1, 2, 3)
val b = intArrayOf(1, 2, 3)
println(a == b)                 // false  ← 참조가 다름!
println(a.contentEquals(b))     // true   ← 내용 비교
println(a)                      // [I@1b6d3586  ← 의미 없는 해시
println(a.contentToString())    // [1, 2, 3]
```

> 반면 `List`는 `==`가 내용 비교, `toString()`이 `[1, 2, 3]`으로 잘 나옵니다. 이 차이 때문에 디버깅 편의상 `List`를 선호하기도 합니다.

## 14. 주요 연산 시간 복잡도
| 연산 | 복잡도 | 비고 |
| :--- | :--- | :--- |
| 인덱스 접근 `arr[i]` | `O(1)` | 배열의 가장 큰 장점 |
| 끝에서 탐색 `indexOf`, `contains` | `O(n)` | 선형 탐색 |
| 정렬된 배열 `binarySearch` | `O(log n)` | 사전 정렬 필요 |
| `sort()` | `O(n log n)` | |
| 전체 순회 / `map` / `filter` | `O(n)` | |
| 중간 삽입/삭제 | 불가 (크기 고정) | 필요하면 `MutableList` |

## 15. Array vs List 선택 가이드
| 기준 | `Array` | `List` / `MutableList` |
| :--- | :--- | :--- |
| 크기 | **고정** | 가변 (add/remove 가능) |
| 인덱스 접근 | `O(1)` | `ArrayList`도 `O(1)` |
| 내용 비교 `==` | ❌ 참조 비교 | ✅ 내용 비교 |
| 출력 `toString()` | ❌ 해시 | ✅ 보기 좋음 |
| 기본 타입 최적화 | ✅ `IntArray` 등 | ❌ 항상 박싱 |
| 주 용도 | 크기 고정 + 성능 민감 (행렬, DP 테이블) | 크기 가변 + 가독성 |

> **정리**: 크기가 정해진 채 빠른 인덱스 접근·기본 타입 성능이 필요하면 `Array`(특히 `IntArray`). 동적으로 늘어나거나 비교·출력 편의가 중요하면 `List`를 쓰세요.
