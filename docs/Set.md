# Kotlin Set 주요 함수 정리

`Set`은 중복을 허용하지 않는 고유한 요소들의 컬렉션입니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `setOf(vararg elements)` | `T...` | `Set<T>` | 읽기 전용 Set 생성 | 내부적으로 `LinkedHashSet` |
| `mutableSetOf(...)` | `T...` | `MutableSet<T>` | 수정 가능한 Set | 내부적으로 `LinkedHashSet` |
| `hashSetOf(...)` | `T...` | `HashSet<T>` | HashSet 생성 | 순서 보장 안 함, 평균 O(1) |
| `linkedSetOf(...)` | `T...` | `LinkedHashSet<T>` | 삽입 순서 유지 Set | 순서 필요할 때 |
| `sortedSetOf(...)` | `T...` | `TreeSet<T>` | 자연 오름차순 정렬 Set | get/add O(log n) |
| `emptySet<T>()` | (없음) | `Set<T>` | 빈 불변 Set | |
| `buildSet { add(...) }` | `MutableSet.() -> Unit` | `Set<T>` | 빌더로 불변 Set 조립 | |
| `size` | (프로퍼티) | `Int` | 요소 개수 | |
| `isEmpty()` / `isNotEmpty()` | (없음) | `Boolean` | 비었는지 여부 | |

## 2. 요소 조회 및 검사 ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `contains(element)` / `in` | `T` | `Boolean` | 요소 포함 여부 | ⭐ `x in set` 권장, HashSet O(1) |
| `containsAll(collection)` | `Collection<T>` | `Boolean` | 모든 요소 포함 여부 | 부분집합 판별 |
| `first()` / `last()` | (없음) | `T` | 첫/마지막 요소 | 빈 Set이면 예외 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 빈 Set이면 null | |
| `elementAt(index)` | `Int` | `T` | 인덱스 위치 요소 | 순서 있는 Set에서만 유의미 |
| `any { }` / `all { }` / `none { }` | `(T) -> Boolean` | `Boolean` | 조건 검사 | |
| `count { }` | `(T) -> Boolean` | `Int` | 조건 충족 개수 | |

## 3. 집합 연산 ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `union(other)` / `+` | `Iterable<T>` | `Set<T>` | 합집합 | 중복 자동 제거 |
| `intersect(other)` | `Iterable<T>` | `Set<T>` | 교집합 | 양쪽 모두에 있는 요소 |
| `subtract(other)` / `-` | `Iterable<T>` | `Set<T>` | 차집합 | this에만 있는 요소 |
| `a + element` | `T` | `Set<T>` | 요소 추가한 새 Set 반환 | 불변 Set에서 사용 |
| `a - element` | `T` | `Set<T>` | 요소 제거한 새 Set 반환 | 불변 Set에서 사용 |

## 4. MutableSet 전용 (수정) ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `add(element)` | `T` | `Boolean` | 요소 추가 | 이미 있으면 false, 추가되면 true |
| `addAll(elements)` | `Collection<T>` | `Boolean` | 여러 요소 추가 | 변경 발생 시 true |
| `remove(element)` | `T` | `Boolean` | 요소 삭제 | 성공 시 true |
| `removeAll(elements)` | `Collection<T>` | `Boolean` | 여러 요소 일괄 삭제 | |
| `retainAll(elements)` | `Collection<T>` | `Boolean` | 교집합만 남기기 | 나머지 삭제 |
| `clear()` | (없음) | `Unit` | 전체 삭제 | |
| `+= element` / `-= element` | `T` | `Unit` | 추가 / 제거 연산자 | |

## 5. 변환 ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `toSet()` | (없음) | `Set<T>` | 불변 Set 복사 | List에서 중복 제거 ⭐ |
| `toMutableSet()` | (없음) | `MutableSet<T>` | MutableSet 복사 | |
| `toHashSet()` | (없음) | `HashSet<T>` | HashSet으로 변환 | |
| `toSortedSet()` | (없음) | `TreeSet<T>` | 정렬된 Set으로 변환 | |
| `toList()` | (없음) | `List<T>` | List로 변환 | 순서는 구현체에 따라 다름 |
| `distinct()` | (없음) | `List<T>` | List 중복 제거 → List 반환 | Set이 아닌 List 반환 |
| `filter { }` | `(T) -> Boolean` | `List<T>` | 조건 필터 → List | |
| `map { }` | `(T) -> R` | `List<R>` | 변환 → List | |
| `sorted()` / `sortedDescending()` | (없음) | `List<T>` | 정렬된 List | |
| `sortedBy { }` / `sortedByDescending { }` | `(T) -> R` | `List<T>` | 기준 정렬 | |
| `joinToString(sep)` | `String` | `String` | 문자열 결합 | |

## 6. 구현체 비교
| 구현체 | 순서 | add/contains | 특징 |
| :--- | :--- | :--- | :--- |
| `HashSet` | 없음 | 평균 O(1) | 가장 빠름, 코테 방문 체크에 최적 |
| `LinkedHashSet` | 삽입 순서 유지 | 평균 O(1) | `setOf`/`mutableSetOf` 기본값 |
| `TreeSet` | 자연 오름차순 | O(log n) | 정렬 필요 시 사용, `sortedSetOf()` |

---

## 7. 실전 사용법 (코드)

### 중복 제거 — List → Set ⭐
```kotlin
val list = listOf(1, 2, 2, 3, 3, 3)
val unique = list.toSet()            // {1, 2, 3}  — 불변
val uniqueList = list.distinct()     // [1, 2, 3]  — 순서 유지 List
val mutable = list.toMutableSet()    // {1, 2, 3}  — 수정 가능
```

### 방문 체크 (BFS/DFS) ⭐
```kotlin
val visited = HashSet<Int>()         // O(1) 조회

fun bfs(graph: Map<Int, List<Int>>, start: Int) {
    val queue = ArrayDeque<Int>()
    queue.add(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for (next in graph[node] ?: emptyList()) {
            if (next !in visited) {   // ⭐ in 연산자 = contains
                visited.add(next)
                queue.add(next)
            }
        }
    }
}
```

### 집합 연산 — 교집합·합집합·차집합 ⭐⭐
```kotlin
val a = setOf(1, 2, 3, 4)
val b = setOf(3, 4, 5, 6)

val union     = a union b            // {1, 2, 3, 4, 5, 6}  — 합집합
val intersect = a intersect b        // {3, 4}               — 교집합
val diff      = a subtract b         // {1, 2}               — 차집합 (a - b)

// 연산자 표기
val union2    = a + b                // 합집합
val diff2     = a - b                // 차집합
```

### 두 리스트의 공통 원소 / 고유 원소
```kotlin
val x = listOf("apple", "banana", "cherry")
val y = listOf("banana", "cherry", "date")

val common  = x.toSet() intersect y.toSet()   // {banana, cherry}
val onlyInX = x.toSet() subtract y.toSet()    // {apple}
val onlyInY = y.toSet() subtract x.toSet()    // {date}
```

### retainAll — 교집합 in-place 수정
```kotlin
val set = mutableSetOf(1, 2, 3, 4, 5)
set.retainAll(setOf(2, 4, 6))        // set = {2, 4}
```

### buildSet — 조건부 불변 Set 조립
```kotlin
val allowed = buildSet {
    add("READ")
    add("WRITE")
    if (isAdmin) add("DELETE")
}
```

### sortedSetOf — 자동 정렬 Set
```kotlin
val scores = sortedSetOf(50, 20, 80, 30)   // [20, 30, 50, 80]
scores.first()   // 20  — 최솟값
scores.last()    // 80  — 최댓값
```

---

## 8. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 중복 제거 (Set 반환) | `list.toSet()` |
| 중복 제거 (List 반환) | `list.distinct()` |
| 방문 체크 자료구조 | `val visited = HashSet<Int>()` |
| 빠른 존재 확인 | `x in set` (HashSet O(1)) |
| 교집합 | `a intersect b` |
| 합집합 | `a union b` |
| 차집합 | `a subtract b` |
| 두 리스트 공통 원소 | `a.toSet() intersect b.toSet()` |
| 교집합 in-place | `set.retainAll(other)` |
| 정렬된 Set | `sortedSetOf(...)` / `list.toSortedSet()` |
| 조건부 불변 Set 조립 | `buildSet { add(...) }` |

> ⚠️ `HashSet`은 **순서 보장 안 됨**. 순서가 필요하면 `LinkedHashSet`(삽입순), 정렬이 필요하면 `TreeSet`(`sortedSetOf`).
> `subtract`/`intersect`/`union`은 **새 Set을 반환** — 원본을 수정하려면 `removeAll` / `retainAll` / `addAll` 사용.
>
> 🎯 **코테 핵심 도구**: ① 중복 제거 → `toSet()` ② 방문 체크 → `HashSet` + `x in visited` ③ 공통/고유 원소 → `intersect`/`subtract`. 집합 연산 세 개만 외우면 충분!
