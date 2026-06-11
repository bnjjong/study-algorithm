# Kotlin 함수형 컬렉션 연산 (Stream식 체이닝)

`.map { }.filter { }.sortedWith(...)` 처럼 컬렉션을 **변환·정렬·집계**하는 함수형 연산 모음.
Kotlin엔 Java의 `Stream`이 따로 없고, **컬렉션 확장 함수**(즉시 평가) + **Sequence**(지연 평가)로 같은 일을 한다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비. ⭐ = 코테 단골.

---

## 0. Java Stream ↔ Kotlin 매핑 ⭐ (Java 쓰던 분 필독)

| Java Stream | Java 반환 | Kotlin | Kotlin 반환 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `.stream()` | `Stream<T>` | (불필요) | — | 컬렉션에 바로 `.map{}` |
| `.map(f)` | `Stream<R>` | `.map { }` | `List<R>` | eager |
| `.filter(p)` | `Stream<T>` | `.filter { }` | `List<T>` | eager |
| `.sorted(cmp)` | `Stream<T>` | `.sortedWith(cmp)` ⭐ | `List<T>` | 단일 기준은 `.sortedBy{}` |
| `.distinct()` | `Stream<T>` | `.distinct()` | `List<T>` | |
| `.limit(n)` | `Stream<T>` | `.take(n)` | `List<T>` | |
| `.skip(n)` | `Stream<T>` | `.drop(n)` | `List<T>` | |
| `.count()` | `long` | `.count()` / `.size` | `Int` | 종단 |
| `.anyMatch(p)` | `boolean` | `.any { }` | `Boolean` | 종단 |
| `.allMatch(p)` | `boolean` | `.all { }` | `Boolean` | 종단 |
| `.reduce(...)` | `Optional<T>` / `T` | `.reduce { }` | `S`(요소 타입) | 빈 컬렉션 예외 |
| `.reduce(init,..)` | `R` | `.fold(init) { }` | `R`(누적 타입) | init 타입으로 시작 |
| `.collect(toList())` | `List<T>` | `.toList()` | `List<T>` | 대개 이미 List |
| `.collect(groupingBy(f))` | `Map<K,List<T>>` | `.groupBy { }` | `Map<K,List<T>>` | 즉시 Map |
| (collector) | — | `.groupingBy { }` | `Grouping<T,K>` | 중간 객체, `.eachCount()` 연결 |
| `.mapToInt().sum()` | `int` | `.sumOf { }` | `Int`/`Long`/`Double` | 람다 반환 따라 |
| `.flatMap(f)` | `Stream<R>` | `.flatMap { }` | `List<R>` | eager |
| `Comparator.comparing(f)` | `Comparator<T>` | `compareBy { f }` | `Comparator<T>` | 비교자 생성 |
| `.comparing(f).reversed()` | `Comparator<T>` | `compareByDescending { f }` ⭐ | `Comparator<T>` | |
| `.thenComparing(g)` | `Comparator<T>` | `.thenBy { g }` ⭐ | `Comparator<T>` | 동점 보조 키 |
| `.peek(f)` | `Stream<T>` | `.onEach { }` | `C`(수신 타입) | List면 `List<T>` |
| (lazy 스트림) | `Stream<T>` | `.asSequence()` | `Sequence<T>` | 아래 7번 |

> 💡 Java처럼 `.stream()` 안 붙여도 돼요. Kotlin 컬렉션엔 이 함수들이 **바로** 달려 있어요.
>
> ⚠️ **결정적 차이**: Java 중간 연산은 전부 `Stream<T>`(지연 평가). Kotlin 컬렉션 함수는 매 단계 **새 `List`를 즉시 생성**(eager). 같은 지연 실행을 원하면 `.asSequence()`(→ `Sequence<T>`)로 시작 → 7번 참고.

---

## 1. 변환 (중간 연산 — 새 컬렉션 반환)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `map { }` | `(T) -> R` | `List<R>` | 각 원소 변환 | ⭐ |
| `mapIndexed { i, x -> }` | `(Int, T) -> R` | `List<R>` | 인덱스와 함께 변환 | |
| `mapNotNull { }` | `(T) -> R?` | `List<R>` | 변환 후 null 제거 | ⭐ |
| `flatMap { }` | `(T) -> Iterable<R>` | `List<R>` | 변환 후 평탄화 | |
| `flatten()` | (없음) | `List<R>` | 중첩 리스트 펴기 | |
| `filter { }` | `(T) -> Boolean` | `List<T>` | 조건 통과만 | ⭐ |
| `filterNot { }` | `(T) -> Boolean` | `List<T>` | 조건 반대 | |
| `filterIndexed { i, x -> }` | `(Int, T) -> Boolean` | `List<T>` | 인덱스 조건 | |
| `filterNotNull()` | (없음) | `List<T>` | null 제거 | |
| `distinct()` / `distinctBy { }` | (없음) / `(T) -> K` | `List<T>` | 중복 제거 | ⭐ |
| `take(n)` / `takeLast(n)` | `Int` | `List<T>` | 앞/뒤 n개 | ⭐ |
| `takeWhile { }` | `(T) -> Boolean` | `List<T>` | 조건 깨질 때까지 | |
| `drop(n)` / `dropLast(n)` | `Int` | `List<T>` | 앞/뒤 n개 버림 | |
| `chunked(n)` | `Int` | `List<List<T>>` | n개씩 묶기 | |
| `windowed(n, step)` | `Int, Int` | `List<List<T>>` | 슬라이딩 윈도우 | ⭐ |
| `zip(other)` / `unzip()` | `Iterable` | `List<Pair>` | 짝짓기/풀기 | |
| `withIndex()` | (없음) | `Iterable<IndexedValue>` | (i, value) | |
| `onEach { }` | `(T) -> Unit` | `List<T>` | 부수효과(peek) | 체인 디버깅 |

## 2. 정렬 ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `sorted()` | (없음) | `List<T>` | 자연 순서 오름 | T: Comparable |
| `sortedDescending()` | (없음) | `List<T>` | 자연 순서 내림 | |
| `sortedBy { }` | `(T) -> R?` | `List<T>` | 셀렉터 기준 오름 | ⭐ |
| `sortedByDescending { }` | `(T) -> R?` | `List<T>` | 셀렉터 기준 내림 | ⭐ |
| `sortedWith(cmp)` | `Comparator<T>` | `List<T>` | **임의 Comparator** | ⭐⭐ 다중 기준 |
| `reversed()` | (없음) | `List<T>` | 순서 뒤집기 | |

> ⚠️ `sorted*`(과거형)는 **새 리스트 반환**(원본 불변). MutableList의 `sort*`(현재형)는 **제자리 정렬**.

## 3. 집계·종단 연산 (값 반환)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `forEach { }` / `forEachIndexed` | `(T) -> Unit` | `Unit` | 순회 | |
| `count { }` | `(T) -> Boolean` | `Int` | 조건 개수 | |
| `sum()` / `sumOf { }` | (없음) / `(T) -> Int` | 숫자 | 합 | ⭐ |
| `average()` | (없음) | `Double` | 평균 | |
| `maxOrNull()` / `minOrNull()` | (없음) | `T?` | 최대/최소 | |
| `maxByOrNull { }` / `minByOrNull { }` | `(T) -> R` | `T?` | 셀렉터 기준 최대/최소 | ⭐ |
| `maxOf { }` / `minOf { }` | `(T) -> R` | `R` | 변환값의 최대/최소 | |
| `reduce { acc, x -> }` | `(T, T) -> T` | `T` | 초기값 없이 누적 | 빈 컬렉션 예외 |
| `fold(init) { acc, x -> }` | `R, (R, T) -> R` | `R` | 초기값 두고 누적 | ⭐ |
| `runningFold` / `scan` | `R, ...` | `List<R>` | 누적 과정 전부 | 누적합 |
| `any { }` / `all { }` / `none { }` | `(T) -> Boolean` | `Boolean` | 존재/전체/부재 | ⭐ |
| `first { }` / `firstOrNull { }` | `(T) -> Boolean` | `T` / `T?` | 첫 매칭 | |
| `find { }` / `findLast { }` | `(T) -> Boolean` | `T?` | 매칭 탐색 | |
| `joinToString(sep) { }` | `String, (T)->CharSeq` | `String` | 문자열 결합 | ⭐ 출력 |

## 4. 그룹·연관 (→ Map) ⭐
| 함수 | 출력 | 설명 |
| :--- | :--- | :--- |
| `groupingBy { }.eachCount()` | `Map<K, Int>` | ⭐ 빈도 세기 |
| `groupBy { }` | `Map<K, List<T>>` | 키별 묶음 |
| `groupBy({key}, {value})` | `Map<K, List<V>>` | 키·값 변환 |
| `associate { it to ... }` | `Map<K, V>` | 원소 → 쌍 |
| `associateBy { }` | `Map<K, T>` | 키로 인덱싱 |
| `associateWith { }` | `Map<T, V>` | 원소 → 속성 |
| `partition { }` | `Pair<List, List>` | 조건 만족/불만족 분리 |

---

## 5. Comparator 만들기 — `sortedWith`의 핵심 ⭐⭐

`sortedWith`는 `Comparator<T>`를 받는다. 이걸 만드는 빌더가 핵심:

| 빌더 | 설명 |
| :--- | :--- |
| `compareBy { it.x }` | x 기준 **오름차순** |
| `compareByDescending { it.x }` | x 기준 **내림차순** |
| `.thenBy { it.y }` | 동점 시 y 오름차순 (타이브레이크) |
| `.thenByDescending { it.y }` | 동점 시 y 내림차순 |
| `compareBy({ it.a }, { it.b })` | 셀렉터 여러 개 (모두 오름) |
| `naturalOrder()` / `reverseOrder()` | 자연/역 순서 |
| `nullsFirst(cmp)` / `nullsLast(cmp)` | null 위치 지정 |
| `Comparator { a, b -> ... }` | 직접 람다 (음수=a앞, 0=같음, 양수=b앞) |

```kotlin
// 단일 기준
list.sortedWith(compareByDescending { it.score })

// 다중 기준: 점수 내림 → 동점이면 이름 오름 → 또 동점이면 나이 내림
list.sortedWith(
    compareByDescending<Person> { it.score }
        .thenBy { it.name }
        .thenByDescending { it.age }
)

// 셀렉터 여러 개 한 번에 (모두 오름차순)
list.sortedWith(compareBy({ it.score }, { it.name }))

// 직접 람다 (SAM 변환)
list.sortedWith { a, b -> a.score - b.score }   // 오름차순

// null 안전
list.sortedWith(nullsLast(compareBy { it.score }))
```

> ⚠️ 체인 시작이 `compareBy`/`compareByDescending`일 때 **첫 비교자에 타입 파라미터**가 필요할 수 있다:
> `compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key }`

---

## 6. 실전 사용법 (체인 예시)

### 정렬 + 추출 (Top-K)
```kotlin
freq.entries
    .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }.thenBy { it.key })
    .take(k)
    .map { it.key }
```

### 필터 → 변환 → 집계
```kotlin
val total = items
    .filter { it.price > 0 }
    .sumOf { it.price * it.qty }
```

### 빈도 + 상위 1개
```kotlin
val mostCommon = list.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key
```

### fold로 누적
```kotlin
val sum = nums.fold(0) { acc, x -> acc + x }
val prefix = nums.runningFold(0) { acc, x -> acc + x }   // 누적합 리스트
```

### partition으로 두 갈래 분리
```kotlin
val (pass, fail) = scores.partition { it >= 60 }
```

---

## 7. Sequence — 지연 평가 (Java Stream에 가장 가까움)

```kotlin
val result = list.asSequence()          // 지연 시작
    .map { heavy(it) }                   // 즉시 실행 안 함
    .filter { it > 0 }
    .take(10)
    .toList()                            // ← 여기서 한 번에 평가 (종단 연산)
```

- **즉시(컬렉션) 연산**: 각 단계마다 중간 리스트를 *새로 만든다* → 체인이 길거나 데이터 크면 비효율
- **지연(Sequence) 연산**: 원소 하나가 전체 체인을 통과 → 중간 리스트 없음, `take` 만나면 조기 종료
- 생성: `asSequence()` · `generateSequence(seed) { }` · `sequence { yield(x) }`

> 🎯 코테에선 대부분 그냥 컬렉션 함수로 충분. **데이터가 매우 크고 + 체인이 길고 + 일부만 필요(take)** 할 때 `asSequence()`가 이득.

---

## 8. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 다중 기준 정렬 | `sortedWith(compareByDescending{...}.thenBy{...})` |
| 상위 K개 | `.sortedXxx().take(k)` |
| 변환 + null 제거 | `mapNotNull { }` |
| 빈도 세기 | `groupingBy { it }.eachCount()` |
| 조건 분리 | `partition { }` |
| 누적합 | `runningFold(0) { a, x -> a + x }` |
| 합/평균 | `sumOf { }` / `average()` |
| 최대 원소 | `maxByOrNull { }` |
| 문자열 결합 | `joinToString(", ") { it.name }` |
| 슬라이딩 윈도우 | `windowed(n)` |
| 큰 데이터 지연 처리 | `asSequence(). ... .toList()` |

> ⚠️ `sorted*`/`map`/`filter`는 **새 리스트**를 만든다(원본 불변). 매 단계 리스트 생성 비용이 있으니, 무거운 체인은 `asSequence()` 고려.
>
> 🎯 `.sortedWith`가 막히면 → **5번 Comparator 빌더**부터 보면 된다. `compareBy(오름)` / `compareByDescending(내림)` + `thenBy`(타이브레이크) 조합이 거의 전부.
