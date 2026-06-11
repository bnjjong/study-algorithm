# Kotlin Map 주요 함수 정리

`Map`은 키(Key)와 값(Value)의 쌍으로 이루어진 컬렉션입니다. 각 키는 유일해야 합니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `mapOf(vararg pairs)` | `Pair<K, V>...` | `Map<K, V>` | 읽기 전용 Map 생성 | `mapOf("a" to 1)` |
| `mutableMapOf(...)` | `Pair<K, V>...` | `MutableMap<K, V>` | 수정 가능한 Map | |
| `hashMapOf(...)` | `Pair<K, V>...` | `HashMap<K, V>` | HashMap 생성 | 순서 보장 안 함, 평균 O(1) |
| `linkedMapOf(...)` | `Pair<K, V>...` | `LinkedHashMap` | 삽입 순서 유지 | 순서 필요할 때 |
| `sortedMapOf(...)` | `Pair<K, V>...` | `TreeMap` | 키 오름차순 정렬 | get/put O(log n) |
| `emptyMap<K, V>()` | (없음) | `Map<K, V>` | 빈 불변 Map | |
| `buildMap { put(...) }` | `MutableMap.() -> Unit` | `Map<K, V>` | 빌더로 불변 Map 조립 | |
| `size` | (프로퍼티) | `Int` | 쌍의 개수 | |
| `keys` | (프로퍼티) | `Set<K>` | 모든 키 | |
| `values` | (프로퍼티) | `Collection<V>` | 모든 값 | |
| `entries` | (프로퍼티) | `Set<Map.Entry<K, V>>` | 모든 키-값 쌍 | |
| `isEmpty()` / `isNotEmpty()` | (없음) | `Boolean` | 비었는지 여부 | |

## 2. 요소 접근 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(key)` / `[key]` | `K` | `V?` | 키에 해당하는 값 | 없으면 null |
| `getOrDefault(key, default)` | `K`, `V` | `V` | 없으면 기본값 반환 | 맵은 안 바뀜 |
| `getOrElse(key) { ... }` | `K`, `() -> V` | `V` | 없으면 람다 결과 | 기본값 계산이 무거울 때 |
| `getValue(key)` | `K` | `V` | 키의 값 반환 | 없으면 예외 |
| `containsKey(key)` | `K` | `Boolean` | 키 포함 여부 | `key in map` |
| `containsValue(value)` | `V` | `Boolean` | 값 포함 여부 | |

## 3. 필터링 및 변환
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `filterKeys { ... }` | `(K) -> Boolean` | `Map<K, V>` | 키 조건 필터링 | |
| `filterValues { ... }` | `(V) -> Boolean` | `Map<K, V>` | 값 조건 필터링 | |
| `filter { (k, v) -> }` | `(Entry) -> Boolean` | `Map<K, V>` | 엔트리 조건 필터링 | |
| `filterNot { ... }` | `(Entry) -> Boolean` | `Map<K, V>` | 조건 반대 | |
| `mapKeys { ... }` | `(Entry) -> R` | `Map<R, V>` | 키 변환 | |
| `mapValues { ... }` | `(Entry) -> R` | `Map<K, R>` | 값 변환 | |
| `map { (k, v) -> }` | `(Entry) -> R` | `List<R>` | List로 변환 | |
| `toList()` | (없음) | `List<Pair<K, V>>` | Pair 리스트 | |
| `toMap()` / `toMutableMap()` | (없음) | `Map` / `MutableMap` | 복사본 | |
| `toSortedMap()` | (없음) | `SortedMap<K, V>` | 키 정렬 TreeMap | |

## 4. MutableMap 전용 (수정) ⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `put(key, value)` / `[key]=` | `K`, `V` | `V?` | 추가/갱신 | 이전 값 반환 |
| `putIfAbsent(key, value)` | `K`, `V` | `V?` | 키 없을 때만 추가 | |
| `putAll(otherMap)` | `Map<K, V>` | `Unit` | 다른 Map 병합 | |
| `getOrPut(key) { ... }` | `K`, `() -> V` | `V` | 없으면 넣고 반환 | ⭐ 캐시/멀티맵 |
| `merge(key, value) { old, new -> }` | `K`, `V`, 람다 | `V?` | 있으면 병합, 없으면 value | ⭐ 빈도 누적 |
| `compute(key) { k, v -> }` | `K`, 람다 | `V?` | 키 기준 재계산 | `v`는 nullable |
| `computeIfAbsent(key) { }` | `K`, `(K) -> V` | `V` | 없을 때만 계산해 넣음 | ⭐ 멀티맵 |
| `computeIfPresent(key) { }` | `K`, 람다 | `V?` | 있을 때만 재계산 | |
| `remove(key)` | `K` | `V?` | 키 삭제 | 삭제된 값 반환 |
| `remove(key, value)` | `K`, `V` | `Boolean` | 키·값 둘 다 맞을 때만 삭제 | |
| `clear()` | (없음) | `Unit` | 전체 삭제 | |
| `+= pair` / `-= key` | `Pair` / `K` | `Unit` | 추가 / 제거 | |

## 5. 빈도·그룹화 (리스트 → Map) ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `groupingBy { }.eachCount()` | `(T) -> K` | `Map<K, Int>` | 원소별 개수 세기 | ⭐ 빈도 한 줄 |
| `groupBy { }` | `(T) -> K` | `Map<K, List<T>>` | 키별 묶음 | |
| `groupBy({key}, {value})` | 키/값 선택자 | `Map<K, List<V>>` | 키·값 동시 변환 | |
| `associate { it to ... }` | `(T) -> Pair` | `Map<K, V>` | 원소 → 쌍 | |
| `associateBy { it.id }` | `(T) -> K` | `Map<K, T>` | id로 인덱싱 | ⭐ |
| `associateWith { ... }` | `(T) -> V` | `Map<T, V>` | 원소 → 속성 | |

## 6. 정렬·집계 (랭킹/Top-K) ⭐⭐
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `maxByOrNull { it.value }` | `(Entry) -> R` | `Entry?` | 기준 최대 엔트리 | `?.key`로 키 추출 |
| `minByOrNull { it.value }` | `(Entry) -> R` | `Entry?` | 기준 최소 엔트리 | |
| `entries.sortedByDescending { }` | `(Entry) -> R` | `List<Entry>` | 내림차순 정렬 | |
| `entries.sortedWith(comparator)` | `Comparator` | `List<Entry>` | 다중 기준 정렬 | ⭐ 1순위+타이브레이크 |
| `values.sum()/maxOrNull()/average()` | (없음) | 숫자 | 값 집계 | |
| `count { (k, v) -> }` | `(Entry) -> Boolean` | `Int` | 조건 개수 | |
| `any/all/none { }` | `(Entry) -> Boolean` | `Boolean` | 조건 검사 | |

## 7. 순회
| 방식 | 예시 |
| :--- | :--- |
| 구조 분해 for | `for ((k, v) in map) { }` |
| forEach | `map.forEach { (k, v) -> }` |
| 키/엔트리 | `map.keys.forEach { }` / `map.entries.forEach { e -> e.key }` |

---

## 8. 실전 사용법 (코드)

### 빈도 카운팅 — "있으면 +1, 없으면 1"
```kotlin
// 방법 A: 한 줄 (가장 Kotlin스러움)
val freq = list.groupingBy { it }.eachCount()        // Map<T, Int>

// 방법 B: 직접 누적 (3가지)
val map = HashMap<String, Int>()
list.forEach {
    map[it] = (map[it] ?: 0) + 1                     // ① 제일 읽기 쉬움
    // map[it] = map.getOrDefault(it, 0) + 1         // ② Java식
    // map.merge(it, 1, Int::plus)                   // ③ merge
}
```

### 멀티맵 (한 키 → 여러 값)
```kotlin
val mm = HashMap<String, MutableList<Int>>()
mm.computeIfAbsent("a") { mutableListOf() }.add(10)
mm.computeIfAbsent("a") { mutableListOf() }.add(20)   // {"a"=[10, 20]}
```

### 값 기준 최대 키 찾기
```kotlin
val topKey = freq.maxByOrNull { it.value }?.key       // 가장 빈도 높은 키
```

### 다중 기준 정렬 (1순위 + 동점 타이브레이크) ⭐
```kotlin
// 값 내림차순, 동점이면 키 오름차순
val sorted = freq.entries.sortedWith(
    compareByDescending<Map.Entry<String, Int>> { it.value }   // 1순위
        .thenBy { it.key }                                     // 동점 처리
)
// List<Pair>로도 가능:
freq.toList().sortedWith(compareByDescending { it.second })
```

### Map → 정렬된 키 리스트
```kotlin
val keys = freq.entries
    .sortedByDescending { it.value }
    .map { it.key }
```

---

## 9. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 빈도 세기 | `groupingBy { it }.eachCount()` |
| 빈도 누적(직접) | `map.merge(k, 1, Int::plus)` |
| 없으면 기본값 조회 | `getOrDefault(k, 0)` / `getOrElse(k){0}` |
| 없으면 넣고 반환 | `getOrPut(k){ ... }` |
| 멀티맵 | `computeIfAbsent(k){ mutableListOf() }.add(v)` |
| 값 최대 키 | `maxByOrNull { it.value }?.key` |
| 다중 기준 정렬 | `sortedWith(compareByDescending{...}.thenBy{...})` |
| 맵 → 정렬 리스트 | `toList().sortedByDescending { it.second }` |

> ⚠️ `HashMap`은 **순서 보장 안 됨**. 순서가 중요하면 `LinkedHashMap`, 키 정렬은 `toSortedMap()`(TreeMap).
>
> 📎 **키 정렬·이웃(floor/ceiling)·범위 질의**가 필요하면 → [TreeMap.md](TreeMap.md) (랭킹·구간·최근접 도구).
>
> 🎯 **지금 Top-K 문제 도구**: ① `groupingBy{}.eachCount()` → ② `compareByDescending{값}.thenBy{키}` → ③ `take(k).map{키}`. 도구는 다 여기 있어요 — **조립은 직접!**
