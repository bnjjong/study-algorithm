# Kotlin TreeMap (정렬된 Map) 정리

`TreeMap`은 **키가 항상 정렬된 상태로 유지**되는 Map(레드-블랙 트리). 주요 연산이 전부 **O(log n)**.
`HashMap`이 못 하는 **"이웃 키 질의(floor/ceiling)"·"범위 질의"·"정렬 순회"**가 필요할 때 쓴다 — 랭킹, 구간 검색, 가장 가까운 값.


> Kotlin 리터럴은 `sortedMapOf(...)`. 단, **이웃/범위 메서드(floorKey 등)는 `java.util.TreeMap` 타입에만** 있으므로
> 코테에선 보통 `import java.util.TreeMap` 후 `TreeMap<K, V>()`를 직접 쓴다.

---

## 1. 생성
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `TreeMap<K, V>()` | (없음) | `TreeMap<K, V>` | 키 자연 오름차순 | `import java.util.TreeMap` |
| `TreeMap<K, V>(comparator)` | `Comparator<K>` | `TreeMap<K, V>` | 정렬 기준 지정 | `TreeMap(reverseOrder())` = 내림 |
| `sortedMapOf(vararg pairs)` | `Pair<K, V>...` | `TreeMap` | Kotlin 리터럴 | 이웃 메서드 쓰려면 캐스팅 필요 |
| `sortedMapOf(cmp, ...pairs)` | `Comparator`, `Pair...` | `TreeMap` | 비교자 + 초기값 | |
| `someMap.toSortedMap()` | (없음) | `SortedMap<K, V>` | 기존 Map → 정렬 Map | |

```kotlin
val asc  = TreeMap<Int, String>()                  // 키 오름차순
val desc = TreeMap<Int, String>(reverseOrder())    // 키 내림차순
```

## 2. 이웃 키 질의 — TreeMap의 진짜 무기 ⭐⭐
"k에서 가장 가까운 키"를 O(log n)에 찾는다. **부등호 방향**만 외우면 끝.

| 메서드 | 의미 | 부등호 | 없으면 |
| :--- | :--- | :--- | :--- |
| `lowerKey(k)` | k보다 **작은** 키 중 최대 | `< k` (엄격) | `null` |
| `floorKey(k)` | k **이하** 키 중 최대 | `≤ k` | `null` |
| `ceilingKey(k)` | k **이상** 키 중 최소 | `≥ k` | `null` |
| `higherKey(k)` | k보다 **큰** 키 중 최소 | `> k` (엄격) | `null` |
| `firstKey()` / `lastKey()` | 최소 / 최대 키 | — | **예외** |
| `firstEntry()` / `lastEntry()` | 최소 / 최대 엔트리 | — | `null` |

> 🧠 기억법: **floor**(바닥)=이하 `≤`, **ceiling**(천장)=이상 `≥`. **lower/higher**는 등호 없음(엄격 `< / >`).
> 각 `~Key`에는 엔트리 버전 `~Entry`도 있다 (`floorEntry`, `ceilingEntry`, ...).

```kotlin
val t = TreeMap<Int, String>()
listOf(10, 20, 30).forEach { t[it] = "v$it" }

t.floorKey(25)    // 20  (25 이하 최대)
t.ceilingKey(25)  // 30  (25 이상 최소)
t.floorKey(20)    // 20  (이하라 자기 자신 포함)
t.lowerKey(20)    // 10  (엄격히 작은)
t.higherKey(30)   // null
```

## 3. 범위·부분 뷰 (뷰라서 원본과 연동) ⭐
| 메서드 | 출력 | 설명 |
| :--- | :--- | :--- |
| `headMap(to)` / `headMap(to, incl)` | `SortedMap` | `< to` (또는 `≤ to`) 부분 |
| `tailMap(from)` / `tailMap(from, incl)` | `SortedMap` | `≥ from` (또는 `> from`) 부분 |
| `subMap(from, to)` | `SortedMap` | `[from, to)` 반열린 구간 |
| `subMap(from, fIncl, to, tIncl)` | `NavigableMap` | 경계 포함 여부 직접 지정 |
| `descendingMap()` | `NavigableMap` | 키 **역순** 뷰 (큰 키부터) ⭐ |
| `descendingKeySet()` | `NavigableSet<K>` | 역순 키 집합 |

```kotlin
// "20 이상 40 미만 키들" 순회
for ((k, v) in t.subMap(20, 40)) { /* ... */ }

// 큰 점수부터 (랭킹 합산 등)
for ((score, cnt) in scoreCount.descendingMap()) { /* ... */ }
```

## 4. 양끝 꺼내기 (제거하며 반환)
| 메서드 | 출력 | 설명 |
| :--- | :--- | :--- |
| `pollFirstEntry()` | `Entry?` | 최소 키 엔트리 **제거 후 반환** |
| `pollLastEntry()` | `Entry?` | 최대 키 엔트리 **제거 후 반환** |

> 우선순위 큐처럼 양끝을 빼내면서, 중간 키 검색·범위 질의까지 되는 게 차별점.

## 5. 기본 연산 (일반 Map과 동일 — 단 O(log n))
`get`/`[ ]`, `put`/`[ ]=`, `getOrDefault`, `merge`, `compute`, `remove`, `containsKey` 모두 `Map`과 같다.
→ 자세한 표는 [Map.md](Map.md) 참고. **차이는 복잡도(O(1)→O(log n))와 "정렬 유지"뿐.**

```kotlin
scoreCount.merge(score, 1, Int::plus)   // 점수별 인원 누적 (TreeMap이라 정렬 유지)
```

---

## 6. 언제 무엇을? (Map 4형제 + PriorityQueue)
| 자료구조 | 순서 | 조회 | 강점 | 쓸 때 |
| :--- | :--- | :--- | :--- | :--- |
| `HashMap` | 없음 | O(1) | 가장 빠름 | 순서 불필요 (대부분) ⭐ |
| `LinkedHashMap` | **삽입순** | O(1) | 순서 보존 | LRU, 입력순 유지 |
| `TreeMap` | **키 정렬** | O(log n) | 이웃·범위·정렬 순회 | 랭킹·구간·최근접 ⭐ |
| `PriorityQueue` | 부분(힙) | peek O(1) | 최소/최대 뽑기 | Top-K, 다익스트라 |

> 🎯 **TreeMap vs PriorityQueue**: 둘 다 "정렬"이지만 — PQ는 *최소/최대 하나만* 빠르게 뽑는다.
> **임의 키 검색 + 범위/이웃 질의 + 중간 삭제**가 필요하면 TreeMap.

## 7. 실전 사용법

### 랭킹 — "점수 정렬 유지" (Leaderboard 패턴) ⭐
```kotlin
// 점수 → 인원수. TreeMap이라 항상 정렬됨 → top(k)를 정렬 없이 큰 점수부터 합산
val scoreCount = TreeMap<Int, Int>()
fun addScore(s: Int) = scoreCount.merge(s, 1, Int::plus)   // O(log D)

fun topKSum(k: Int): Int {
    var remain = k; var sum = 0
    for ((score, cnt) in scoreCount.descendingMap()) {       // 큰 점수부터
        if (remain == 0) break
        val taken = minOf(remain, cnt)
        sum += score * taken
        remain -= taken
    }
    return sum
}
```

### 가장 가까운 값 찾기 (최근접 질의)
```kotlin
// 예약 시각 t에 가장 가까운 직전/직후 예약
val before = times.floorKey(t)     // t 이하 최근접
val after  = times.ceilingKey(t)   // t 이상 최근접
```

### 구간 개수 세기 (범위 질의)
```kotlin
// [lo, hi) 범위에 든 키 개수
val countInRange = treeMap.subMap(lo, hi).size
```

### 정렬 순회 / 최댓값 꺼내기
```kotlin
for ((k, v) in treeMap) { }              // 키 오름차순 자동
val maxEntry = treeMap.lastEntry()       // 최대 키
val popMin = treeMap.pollFirstEntry()    // 최소 제거+반환
```

---

## 8. 코테 치트 요약
| 상황 | 한 줄 |
| :--- | :--- |
| 정렬 유지 Map | `TreeMap<K, V>()` (`import java.util.TreeMap`) |
| 내림차순 키 | `TreeMap(reverseOrder())` 또는 `descendingMap()` |
| k 이하 최근접 | `floorKey(k)` / k 이상 최근접 `ceilingKey(k)` |
| k 미만/초과(엄격) | `lowerKey(k)` / `higherKey(k)` |
| 최소·최대 키 | `firstKey()` / `lastKey()` (빈 맵 예외 주의) |
| 구간 뷰 | `subMap(from, to)` / `headMap` / `tailMap` |
| 양끝 꺼내기 | `pollFirstEntry()` / `pollLastEntry()` |
| 랭킹 합산 | `descendingMap()` 순회 + `min(remain, cnt)` |

> ⚠️ **null 키 불가**: 자연 순서 TreeMap에 `null` 키를 넣으면 `NullPointerException`. (HashMap은 null 키 1개 허용)
>
> ⚠️ `firstKey()`/`lastKey()`는 **빈 맵이면 예외**(`NoSuchElementException`). 안전하게는 `firstEntry()`/`lastEntry()`(→ `null`).
>
> ⚠️ `headMap`/`subMap` 등은 **복사본이 아니라 뷰** — 원본을 바꾸면 같이 바뀐다.
>
> 🎯 **HashMap이 기본**, "정렬·이웃·범위"가 필요할 때만 TreeMap. 그 한 줄 판단이 핵심.
