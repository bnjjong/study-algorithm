# Kotlin List 주요 함수 정리

`List`는 순서가 있는 요소들의 컬렉션입니다. 읽기 전용 `List`와 수정 가능한 `MutableList`가 구분됩니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `listOf(vararg elements)` | `T...` | `List<T>` | 읽기 전용 리스트 생성 | ⭐ 기본 |
| `mutableListOf(vararg elements)` | `T...` | `MutableList<T>` | 수정 가능한 리스트 생성 | ⭐ |
| `List(n) { index -> ... }` | `Int`, `(Int) -> T` | `List<T>` | 크기+람다로 초기화 | ⭐ `List(5){0}` |
| `MutableList(n) { index -> ... }` | `Int`, `(Int) -> T` | `MutableList<T>` | 수정 가능, 크기+람다 초기화 | |
| `ArrayList<T>()` | (없음) | `ArrayList<T>` | Java ArrayList 직접 생성 | `MutableList` 구현체 |
| `emptyList<T>()` | (없음) | `List<T>` | 빈 불변 리스트 | 싱글톤, 메모리 효율 |
| `buildList { add(...) }` | `MutableList.() -> Unit` | `List<T>` | 빌더로 불변 리스트 조립 | ⭐ DSL 스타일 |
| `listOfNotNull(...)` | `T?...` | `List<T>` | null 제외하고 생성 | nullable 원소 필터링 |
| `size` | (프로퍼티) | `Int` | 리스트 요소 개수 | |
| `indices` | (프로퍼티) | `IntRange` | 인덱스 범위 `0..size-1` | ⭐ for 루프 |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 인덱스 (`size - 1`) | |
| `isEmpty()` / `isNotEmpty()` | (없음) | `Boolean` | 비었는지 여부 | |

## 2. 요소 접근 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(index)` / `[index]` | `Int` | `T` | 인덱스로 요소 접근 | 범위 벗어나면 예외 |
| `getOrNull(index)` | `Int` | `T?` | 범위 벗어나면 null | ⭐ 안전한 접근 |
| `getOrElse(index) { ... }` | `Int`, `(Int) -> T` | `T` | 범위 벗어나면 람다 결과 | 기본값 계산 가능 |
| `first()` / `last()` | (없음) | `T` | 첫/마지막 요소 | 빈 리스트면 예외 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 첫/마지막 요소 | 빈 리스트면 null |
| `first { ... }` / `last { ... }` | `(T) -> Boolean` | `T` | 조건 첫/마지막 요소 | 없으면 예외 |
| `firstOrNull { ... }` / `lastOrNull { ... }` | `(T) -> Boolean` | `T?` | 조건 첫/마지막 | 없으면 null ⭐ |
| `indexOf(element)` | `T` | `Int` | 첫 번째 인덱스 | 없으면 -1 |
| `lastIndexOf(element)` | `T` | `Int` | 마지막 인덱스 | 없으면 -1 |
| `indexOfFirst { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 첫 인덱스 | 없으면 -1 ⭐ |
| `indexOfLast { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 마지막 인덱스 | 없으면 -1 |
| `find { ... }` | `(T) -> Boolean` | `T?` | 조건 만족 첫 요소 | `firstOrNull`과 동일 |
| `findLast { ... }` | `(T) -> Boolean` | `T?` | 조건 만족 마지막 요소 | |
| `contains(element)` / `in` | `T` | `Boolean` | 포함 여부 | ⭐ `e in list` |
| `containsAll(collection)` | `Collection<T>` | `Boolean` | 모두 포함 여부 | |
| `binarySearch(element)` | `T` | `Int` | 이진 탐색 인덱스 | ⭐ 정렬된 리스트 필수, O(log n) |
| `binarySearch { ... }` | 비교 람다 | `Int` | 커스텀 이진 탐색 | 음수면 `-(insertionPoint+1)` |

## 3. 정렬
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `sort()` | (없음) | `Unit` | 원본 오름차순 정렬 (in-place) | MutableList 전용 ⭐ |
| `sortDescending()` | (없음) | `Unit` | 원본 내림차순 정렬 (in-place) | MutableList 전용 |
| `sortBy { ... }` | `(T) -> R` | `Unit` | 선택자 기준 오름차순 (in-place) | MutableList 전용 ⭐ |
| `sortByDescending { ... }` | `(T) -> R` | `Unit` | 선택자 기준 내림차순 (in-place) | MutableList 전용 |
| `sortWith(comparator)` | `Comparator<T>` | `Unit` | 커스텀 정렬 (in-place) | MutableList 전용 |
| `sorted()` | (없음) | `List<T>` | 오름차순 새 리스트 반환 | ⭐ |
| `sortedDescending()` | (없음) | `List<T>` | 내림차순 새 리스트 반환 | |
| `sortedBy { ... }` | `(T) -> R?` | `List<T>` | 선택자 기준 오름차순 새 리스트 | ⭐ |
| `sortedByDescending { ... }` | `(T) -> R?` | `List<T>` | 선택자 기준 내림차순 새 리스트 | ⭐ |
| `sortedWith(comparator)` | `Comparator<T>` | `List<T>` | 커스텀 정렬 새 리스트 | ⭐ 다중 기준 |
| `reversed()` | (없음) | `List<T>` | 순서 반전 새 리스트 | |
| `reverse()` | (없음) | `Unit` | 원본 순서 반전 (in-place) | MutableList 전용 |
| `shuffled()` | (없음) | `List<T>` | 무작위 섞인 새 리스트 | |

## 4. MutableList 전용 (수정) ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `add(element)` | `T` | `Boolean` | 끝에 요소 추가 | 항상 true |
| `add(index, element)` | `Int`, `T` | `Unit` | 특정 위치에 삽입 | O(n) |
| `addAll(elements)` | `Collection<T>` | `Boolean` | 컬렉션 모두 추가 | |
| `addAll(index, elements)` | `Int`, `Collection<T>` | `Boolean` | 특정 위치에 모두 삽입 | |
| `set(index, element)` / `[index]=` | `Int`, `T` | `T` | 해당 위치 교체 | 이전 요소 반환 |
| `removeAt(index)` | `Int` | `T` | 인덱스로 삭제 | ⭐ 삭제된 요소 반환 |
| `remove(element)` | `T` | `Boolean` | 첫 번째 일치 요소 삭제 | 성공 시 true |
| `removeAll { ... }` | `(T) -> Boolean` | `Boolean` | 조건 만족 요소 모두 삭제 | |
| `removeIf { ... }` | `(T) -> Boolean` | `Boolean` | Java 스타일 조건 삭제 | `removeAll`과 유사 |
| `retainAll { ... }` | `(T) -> Boolean` | `Boolean` | 조건 만족 요소만 유지 | |
| `clear()` | (없음) | `Unit` | 모든 요소 삭제 | |
| `fill(element)` | `T` | `Unit` | 모든 요소를 동일 값으로 채움 | |
| `replaceAll { ... }` | `(T) -> T` | `Unit` | 모든 요소 변환 (in-place) | |
| `+= element` / `-= element` | `T` | `Unit` | 추가 / 삭제 연산자 | |

## 5. 변환 및 집계 ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `map { ... }` | `(T) -> R` | `List<R>` | 각 요소 변환 | ⭐ |
| `mapIndexed { i, e -> }` | `(Int, T) -> R` | `List<R>` | 인덱스+요소 변환 | ⭐ |
| `mapNotNull { ... }` | `(T) -> R?` | `List<R>` | 변환 후 null 제거 | ⭐ |
| `mapIndexedNotNull { i, e -> }` | `(Int, T) -> R?` | `List<R>` | 인덱스+변환+null 제거 | |
| `flatMap { ... }` | `(T) -> Iterable<R>` | `List<R>` | 중첩 리스트 펼치기 | ⭐ |
| `flatten()` | (없음) | `List<T>` | `List<List<T>>` 펼치기 | |
| `filter { ... }` | `(T) -> Boolean` | `List<T>` | 조건 필터링 | ⭐ |
| `filterNot { ... }` | `(T) -> Boolean` | `List<T>` | 조건 반대 필터링 | |
| `filterNotNull()` | (없음) | `List<T>` | null 제거 | |
| `filterIsInstance<R>()` | (없음) | `List<R>` | 타입 필터링 | |
| `reduce { acc, e -> }` | `(T, T) -> T` | `T` | 누적 집계 (초기값 없음) | 빈 리스트면 예외 |
| `reduceOrNull { acc, e -> }` | `(T, T) -> T` | `T?` | 빈 리스트면 null | |
| `fold(init) { acc, e -> }` | `R`, `(R, T) -> R` | `R` | 초기값으로 누적 | ⭐ 타입 변환 가능 |
| `foldRight(init) { e, acc -> }` | `R`, `(T, R) -> R` | `R` | 오른쪽부터 누적 | |
| `runningFold(init) { acc, e -> }` | `R`, `(R, T) -> R` | `List<R>` | 중간 누적값 전부 포함 | ⭐ 누적합 배열 |
| `runningReduce { acc, e -> }` | `(T, T) -> T` | `List<T>` | 초기값 없는 중간 누적 | |
| `sum()` | (없음) | `Int/Long/...` | 합계 | 숫자 타입만 |
| `sumOf { ... }` | `(T) -> N` | `N` | 선택자 합계 | ⭐ |
| `average()` | (없음) | `Double` | 평균 | |
| `count()` | (없음) | `Int` | 요소 개수 | |
| `count { ... }` | `(T) -> Boolean` | `Int` | 조건 만족 개수 | ⭐ |
| `max()` / `min()` | (없음) | `T` | 최대/최소 | `Comparable` 필요 |
| `maxOrNull()` / `minOrNull()` | (없음) | `T?` | 최대/최소 (빈 리스트 safe) | |
| `maxByOrNull { ... }` | `(T) -> R` | `T?` | 선택자 기준 최대 요소 | ⭐ |
| `minByOrNull { ... }` | `(T) -> R` | `T?` | 선택자 기준 최소 요소 | ⭐ |
| `maxOf { ... }` / `minOf { ... }` | `(T) -> R` | `R` | 선택자 결과 최대/최소값 | |
| `any { ... }` | `(T) -> Boolean` | `Boolean` | 하나라도 만족하면 true | ⭐ |
| `all { ... }` | `(T) -> Boolean` | `Boolean` | 모두 만족하면 true | |
| `none { ... }` | `(T) -> Boolean` | `Boolean` | 하나도 만족 안 하면 true | |
| `distinct()` | (없음) | `List<T>` | 중복 제거 | ⭐ |
| `distinctBy { ... }` | `(T) -> K` | `List<T>` | 선택자 기준 중복 제거 | |
| `joinToString(sep)` | `String` | `String` | 문자열 합치기 | ⭐ |

## 6. 슬라이싱 및 범위 추출 ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `subList(from, to)` | `Int`, `Int` | `List<T>` | 뷰(view) 반환 (원본 공유) | ⭐ `[from, to)` |
| `take(n)` | `Int` | `List<T>` | 앞에서 n개 | ⭐ |
| `takeLast(n)` | `Int` | `List<T>` | 뒤에서 n개 | |
| `takeWhile { ... }` | `(T) -> Boolean` | `List<T>` | 조건 만족하는 동안 앞부터 | |
| `takeLastWhile { ... }` | `(T) -> Boolean` | `List<T>` | 조건 만족하는 동안 뒤부터 | |
| `drop(n)` | `Int` | `List<T>` | 앞에서 n개 제외 나머지 | ⭐ |
| `dropLast(n)` | `Int` | `List<T>` | 뒤에서 n개 제외 나머지 | |
| `dropWhile { ... }` | `(T) -> Boolean` | `List<T>` | 조건 만족하는 동안 앞부터 제외 | |
| `slice(indices)` | `IntRange` / `Iterable<Int>` | `List<T>` | 인덱스 범위로 추출 | `list.slice(1..3)` |
| `chunked(size)` | `Int` | `List<List<T>>` | n개씩 묶어 리스트 분할 | ⭐ |
| `chunked(size) { ... }` | `Int`, `(List<T>) -> R` | `List<R>` | 묶은 뒤 변환 | |
| `windowed(size)` | `Int` | `List<List<T>>` | 슬라이딩 윈도우 | ⭐ 코테 단골 |
| `windowed(size, step, partial)` | `Int`, `Int`, `Boolean` | `List<List<T>>` | 크기·보폭·부분 포함 여부 | |
| `windowed(size) { ... }` | `Int`, `(List<T>) -> R` | `List<R>` | 윈도우마다 변환 | |

## 7. 그룹화 및 변환 (List → Map) ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `groupBy { ... }` | `(T) -> K` | `Map<K, List<T>>` | 키별 묶음 | ⭐ |
| `groupBy({ key }, { value })` | 키/값 선택자 | `Map<K, List<V>>` | 키·값 동시 변환 | |
| `groupingBy { }.eachCount()` | `(T) -> K` | `Map<K, Int>` | 원소별 개수 | ⭐ 빈도 한 줄 |
| `partition { ... }` | `(T) -> Boolean` | `Pair<List<T>, List<T>>` | 조건 true/false 두 리스트로 분리 | ⭐ |
| `associate { it to ... }` | `(T) -> Pair<K, V>` | `Map<K, V>` | 원소 → Pair로 Map 생성 | |
| `associateBy { ... }` | `(T) -> K` | `Map<K, T>` | 선택자를 키로 Map 생성 | ⭐ id로 인덱싱 |
| `associateWith { ... }` | `(T) -> V` | `Map<T, V>` | 원소를 키, 변환값을 값으로 | |
| `zip(other)` | `Iterable<R>` | `List<Pair<T, R>>` | 두 리스트 Pair로 묶기 | ⭐ |
| `zip(other) { a, b -> }` | `Iterable<R>`, 람다 | `List<V>` | zip 후 즉시 변환 | |
| `unzip()` | (없음) | `Pair<List<A>, List<B>>` | `Pair` 리스트를 두 리스트로 분리 | |

## 8. 스택 / 큐 — ArrayDeque 활용 ⭐⭐
> 코테에서 스택·큐는 `ArrayDeque`로 통일. `Stack`/`LinkedList` 대신 사용.

| 함수 | 역할 | 설명 |
| :--- | :--- | :--- |
| `ArrayDeque<T>()` | 생성 | 스택·큐 모두 사용 가능 |
| `addLast(e)` | 큐 enqueue / 스택 push | 끝에 추가 |
| `addFirst(e)` | 덱 앞에 추가 | 앞에 추가 |
| `removeLast()` | 스택 pop | 끝 요소 제거 후 반환, 비면 예외 |
| `removeFirst()` | 큐 dequeue | 앞 요소 제거 후 반환, 비면 예외 |
| `removeLastOrNull()` | 스택 safe pop | 비면 null ⭐ |
| `removeFirstOrNull()` | 큐 safe dequeue | 비면 null ⭐ |
| `last()` | 스택 peek | 제거 없이 끝 요소 확인 |
| `first()` | 큐 peek | 제거 없이 앞 요소 확인 |
| `isEmpty()` | 종료 조건 | 비었는지 확인 |

### MutableList로 스택 대체 (간단한 경우)
| 관용구 | 설명 |
| :--- | :--- |
| `list.add(e)` | push |
| `list.removeAt(list.lastIndex)` | pop ⭐ |
| `list.last()` | peek |

## 9. 순회
| 방식 | 예시 |
| :--- | :--- |
| for 루프 | `for (e in list) { }` |
| 인덱스 for 루프 | `for (i in list.indices) { }` |
| 인덱스+값 | `for ((i, e) in list.withIndex()) { }` |
| forEach | `list.forEach { e -> }` |
| forEachIndexed | `list.forEachIndexed { i, e -> }` ⭐ |

---

## 10. 실전 사용법 (코드)

### 리스트 초기화 패턴 ⭐
```kotlin
// 크기 n, 0으로 초기화
val zeros = MutableList(n) { 0 }

// 2차원 배열 (n×m)
val grid = MutableList(n) { MutableList(m) { 0 } }

// buildList: 조건부 초기화
val result = buildList {
    if (someCondition) add(1)
    addAll(otherList)
}
```

### 투 포인터 (Two Pointer)
```kotlin
fun twoSum(nums: IntArray, target: Int): IntArray {
    val sorted = nums.indices.sortedBy { nums[it] }   // 인덱스 정렬
    var left = 0; var right = sorted.lastIndex
    while (left < right) {
        val sum = nums[sorted[left]] + nums[sorted[right]]
        when {
            sum == target -> return intArrayOf(sorted[left], sorted[right])
            sum < target  -> left++
            else          -> right--
        }
    }
    return intArrayOf()
}
```

### 슬라이딩 윈도우 — windowed 활용 ⭐
```kotlin
// 길이 k의 연속 부분합 최대값
val maxSum = list.windowed(k) { it.sum() }.max()

// 직접 구현 (sum 변수 재활용, O(n))
fun maxSlidingSum(list: List<Int>, k: Int): Int {
    var windowSum = list.take(k).sum()
    var maxSum = windowSum
    for (i in k..list.lastIndex) {
        windowSum += list[i] - list[i - k]
        maxSum = maxOf(maxSum, windowSum)
    }
    return maxSum
}
```

### 스택 — 괄호 짝 맞추기 ⭐
```kotlin
fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val match = mapOf(')' to '(', ']' to '[', '}' to '{')
    for (c in s) {
        if (c in match.values) {
            stack.addLast(c)
        } else {
            if (stack.isEmpty() || stack.last() != match[c]) return false
            stack.removeLast()
        }
    }
    return stack.isEmpty()
}
```

### 다중 기준 정렬 ⭐
```kotlin
// 점수 내림차순, 동점이면 이름 오름차순
data class Student(val name: String, val score: Int)
val sorted = students.sortedWith(
    compareByDescending<Student> { it.score }.thenBy { it.name }
)
```

### 누적합 (Prefix Sum) — runningFold ⭐
```kotlin
// prefix[i] = list[0..i-1] 의 합
val prefix = list.runningFold(0) { acc, e -> acc + e }
// 구간 합 [l, r] = prefix[r+1] - prefix[l]
fun rangeSum(l: Int, r: Int) = prefix[r + 1] - prefix[l]
```

### groupBy + 빈도 카운팅
```kotlin
// 방법 A: 한 줄
val freq = list.groupingBy { it }.eachCount()          // Map<T, Int>

// 방법 B: fold
val freq2 = list.fold(mutableMapOf<String, Int>()) { acc, e ->
    acc.also { it[e] = (it[e] ?: 0) + 1 }
}
```

### partition — 홀짝 분리 ⭐
```kotlin
val (evens, odds) = numbers.partition { it % 2 == 0 }
```

### chunked — 배치 처리
```kotlin
// 3개씩 묶어 처리
list.chunked(3).forEach { batch -> process(batch) }

// 3개씩 묶어 합산
val chunkSums = list.chunked(3) { it.sum() }
```

### zip — 두 리스트 병렬 순회 ⭐
```kotlin
val names = listOf("Alice", "Bob")
val scores = listOf(90, 85)
val combined = names.zip(scores) { name, score -> "$name: $score" }
// ["Alice: 90", "Bob: 85"]
```

### binarySearch — 정렬 리스트 탐색 ⭐
```kotlin
val sorted = listOf(1, 3, 5, 7, 9)
val idx = sorted.binarySearch(5)     // 2 (찾은 인덱스)
val notFound = sorted.binarySearch(4) // 음수: -(insertionPoint+1) = -3
val insertAt = if (notFound < 0) -(notFound + 1) else notFound  // 삽입 위치
```

---

## 11. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| n개 0으로 초기화 | `MutableList(n) { 0 }` |
| 2차원 배열 | `MutableList(n) { MutableList(m) { 0 } }` |
| 빈도 세기 | `groupingBy { it }.eachCount()` |
| 조건 만족 첫 인덱스 | `indexOfFirst { ... }` |
| 안전한 인덱스 접근 | `getOrNull(i)` / `getOrElse(i) { default }` |
| 누적합 배열 | `runningFold(0) { acc, e -> acc + e }` |
| 슬라이딩 윈도우 값 | `windowed(k) { it.sum() }` |
| 앞/뒤 n개 | `take(n)` / `takeLast(n)` / `drop(n)` / `dropLast(n)` |
| 조건별 두 리스트 | `partition { ... }` |
| 선택자 최대 요소 | `maxByOrNull { ... }` |
| 다중 기준 정렬 | `sortedWith(compareByDescending{...}.thenBy{...})` |
| 스택 push/pop | `ArrayDeque`: `addLast(e)` / `removeLast()` |
| 큐 enqueue/dequeue | `ArrayDeque`: `addLast(e)` / `removeFirst()` |
| MutableList pop | `removeAt(lastIndex)` |
| 중복 제거 | `distinct()` / `distinctBy { ... }` |
| 조건 개수 | `count { ... }` |
| 구간 합 | `prefix[r+1] - prefix[l]` (runningFold 사전 계산) |
| n개씩 배치 | `chunked(n)` |
| 두 리스트 묶기 | `zip(other)` / `zip(other) { a, b -> }` |
| id → 객체 Map | `associateBy { it.id }` |

> ⚠️ `subList`는 원본 리스트의 **뷰(view)**를 반환합니다. 원본을 수정하면 뷰도 바뀌고, 뷰를 수정하면 원본도 바뀝니다. 독립 복사본이 필요하면 `.toList()`를 호출하세요.
>
> ⚠️ `sort()` / `sortBy()` 는 **in-place**(원본 변경), `sorted()` / `sortedBy()` 는 **새 리스트 반환**. 이름 끝에 `-ed`가 붙으면 새 리스트입니다.
>
> 🎯 **코테 3종 세트**: ① 투 포인터 (`left`/`right` 인덱스) → ② 슬라이딩 윈도우 (`windowed` 또는 직접 구현) → ③ 스택·큐 (`ArrayDeque`). 패턴 인식 → 도구 선택 → 조립은 직접!
