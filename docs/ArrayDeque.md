# Kotlin ArrayDeque 주요 함수 정리

`ArrayDeque<T>`는 **양쪽 끝에서 O(1)** 추가·삭제가 가능한 **이중 연결 큐(double-ended queue)**다.
스택·큐·덱 어느 용도로든 쓸 수 있어 Kotlin 코딩 테스트의 **만능 컨테이너**.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

> 🎯 **핵심 한 줄**: Kotlin에선 `Stack`, `Queue`, `Deque` 다 잊고 그냥 `ArrayDeque<T>()` 하나로 끝낸다.

---

## 0. 무엇을 쓸까 — 선택지 비교 ⭐

| 방법 | 용도 | 비고 |
|------|------|------|
| **`kotlin.collections.ArrayDeque`** ⭐ | 스택·큐·덱 모두 | **기본 추천.** import 불필요, `MutableList` 이기도 함 |
| `java.util.ArrayDeque` | Java 식 이름(push/pop/peek) 선호 시 | `Stack`보다 빠름 |
| `java.util.LinkedList` | 큐 + 중간 삽입/삭제 | ArrayDeque보다 느림, 메모리 ↑ |
| `java.util.Stack` | 레거시 | synchronized로 느림. **쓰지 말 것** |
| `java.util.Queue` (인터페이스) | LinkedList/ArrayDeque의 다른 얼굴 | 직접 쓸 일 거의 없음 |

```kotlin
// 이 한 줄로 시작하면 코테 99% 해결
val dq = ArrayDeque<Int>()
```

---

## 1. 생성 및 기본 정보

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `ArrayDeque<T>()` ⭐ | (없음) | `ArrayDeque<T>` | 빈 덱 생성 | 가장 흔함 |
| `ArrayDeque<T>(capacity)` | `Int` | `ArrayDeque<T>` | 초기 용량 지정 | 큰 데이터 미리 알 때 |
| `ArrayDeque(collection)` ⭐ | `Collection<T>` | `ArrayDeque<T>` | 컬렉션으로 초기화 | `ArrayDeque(listOf(1,2,3))` |
| `arrayDequeOf(...)` ❌ | — | — | **없음**. `ArrayDeque(listOf(...))` 쓸 것 | 자주 헷갈림 |
| `size` ⭐ | (프로퍼티) | `Int` | 원소 개수 | |
| `isEmpty()` / `isNotEmpty()` ⭐ | (없음) | `Boolean` | 비었는지 | while 루프 조건 단골 |
| `indices` | (프로퍼티) | `IntRange` | `0..size-1` | 인덱스 접근 시 |
| `clear()` | (없음) | Unit | 전부 비움 | |

```kotlin
val a = ArrayDeque<Int>()                       // 빈 덱
val b = ArrayDeque(listOf(1, 2, 3))             // [1, 2, 3]
val c = ArrayDeque<Char>(100)                    // 용량 100 hint
b.size            // 3
b.isEmpty()       // false
```

---

## 2. 요소 접근 (peek — 제거 X)

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `first()` ⭐ | (없음) | `T` | 맨 앞 원소 | 비었으면 예외 |
| `last()` ⭐ | (없음) | `T` | 맨 뒤 원소 | 비었으면 예외 |
| `firstOrNull()` ⭐ | (없음) | `T?` | 맨 앞, 비었으면 null | 안전한 peek |
| `lastOrNull()` ⭐ | (없음) | `T?` | 맨 뒤, 비었으면 null | 안전한 peek |
| `get(i)` / `dq[i]` | `Int` | `T` | 인덱스 i 의 원소 | O(1) — ArrayList처럼 가능 |
| `getOrNull(i)` | `Int` | `T?` | 범위 밖이면 null | |

```kotlin
val dq = ArrayDeque(listOf(10, 20, 30))
dq.first()          // 10
dq.last()           // 30
dq[1]               // 20  ← 인덱스 접근도 O(1)!
```

> 💡 **ArrayDeque는 MutableList 이기도 하다** — `dq[i]`, `dq.indexOf(x)` 등 List 함수 그대로 됨.

---

## 3. 추가 (push)

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `addLast(e)` ⭐ | `T` | Unit | 뒤에 추가 — **스택 push / 큐 enqueue** | O(1) |
| `addFirst(e)` ⭐ | `T` | Unit | 앞에 추가 | O(1) |
| `add(e)` | `T` | Boolean | `addLast`와 동일 | always true |
| `add(i, e)` | `Int, T` | Unit | 인덱스 i 에 삽입 | **O(n)** — 중간 삽입은 비쌈 |
| `addAll(elements)` | `Collection<T>` | Boolean | 끝에 일괄 추가 | |
| `+= e` ⭐ | `T` | Unit | `addLast` syntactic sugar | |

```kotlin
val dq = ArrayDeque<Int>()
dq.addLast(1)       // [1]
dq.addLast(2)       // [1, 2]
dq.addFirst(0)      // [0, 1, 2]
dq += 3             // [0, 1, 2, 3]
```

---

## 4. 제거 (pop)

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `removeFirst()` ⭐ | (없음) | `T` | 앞에서 제거+반환 — **큐 dequeue** | 비었으면 예외 |
| `removeLast()` ⭐ | (없음) | `T` | 뒤에서 제거+반환 — **스택 pop** | 비었으면 예외 |
| `removeFirstOrNull()` ⭐ | (없음) | `T?` | 앞에서 제거, 비었으면 null | **안전, while 루프에 좋음** |
| `removeLastOrNull()` ⭐ | (없음) | `T?` | 뒤에서 제거, 비었으면 null | |
| `removeAt(i)` | `Int` | `T` | 인덱스 i 의 원소 제거 | **O(n)** — 중간 제거는 비쌈 |
| `remove(e)` | `T` | Boolean | 첫 일치 원소 제거 | O(n) 탐색 |
| `removeAll(elements)` | `Collection<T>` | Boolean | 일치하는 모두 제거 | |

```kotlin
val dq = ArrayDeque(listOf(1, 2, 3, 4))
dq.removeFirst()    // 1, dq = [2, 3, 4]
dq.removeLast()     // 4, dq = [2, 3]

// 안전한 dequeue 패턴 ⭐
while (true) {
    val x = dq.removeFirstOrNull() ?: break
    println(x)
}
```

---

## 5. 탐색 / 변환

| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `contains(e)` | `T` | Boolean | 포함 여부 | O(n) |
| `indexOf(e)` | `T` | `Int` | 첫 등장 인덱스, 없으면 -1 | |
| `lastIndexOf(e)` | `T` | `Int` | 마지막 등장 인덱스 | |
| `iterator()` ⭐ | (없음) | `MutableIterator<T>` | 정방향 순회 | for 루프 자동 사용 |
| `descendingIterator()` ⭐ | (없음) | `Iterator<T>` | 역방향 순회 | 큐를 거꾸로 |
| `toList()` / `toMutableList()` | (없음) | `List<T>` | 리스트로 복사 | |
| `toIntArray()` 등 | (없음) | `IntArray` | 원시 배열로 | 출력·정렬 시 유용 |
| `reversed()` | (없음) | `List<T>` | 역순 새 리스트 | ArrayDeque 반환 아님! |

```kotlin
val dq = ArrayDeque(listOf(1, 2, 3, 1))
dq.indexOf(1)                       // 0
dq.lastIndexOf(1)                   // 3
dq.forEach { println(it) }          // 1 2 3 1
dq.descendingIterator().forEach { } // 1 3 2 1
```

---

## 6. 코딩 테스트 단골 패턴 ⭐

### 6-1. 스택 (LIFO) — 괄호 짝맞추기

```kotlin
fun isValid(s: String): Boolean {
    val st = ArrayDeque<Char>()
    val pair = mapOf(')' to '(', ']' to '[', '}' to '{')
    for (c in s) {
        if (c in "([{") {
            st.addLast(c)
        } else {
            if (st.isEmpty() || st.removeLast() != pair[c]) return false
        }
    }
    return st.isEmpty()
}
```

### 6-2. 큐 (FIFO) — BFS ⭐ 빈출

```kotlin
fun bfs(start: Int, graph: Map<Int, List<Int>>) {
    val visited = mutableSetOf(start)
    val q = ArrayDeque<Int>()
    q.addLast(start)
    while (q.isNotEmpty()) {
        val cur = q.removeFirst()       // dequeue
        for (next in graph[cur].orEmpty()) {
            if (next !in visited) {
                visited += next
                q.addLast(next)         // enqueue
            }
        }
    }
}
```

### 6-3. 덱 — 슬라이딩 윈도우 최댓값 (Monotonic Deque) ⭐ 어려움

윈도우 크기 k 의 최댓값을 O(n) 에 구하기. **인덱스만 저장**하는 게 포인트.

```kotlin
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val dq = ArrayDeque<Int>()              // 인덱스 저장. 값 내림차순 유지
    val result = IntArray(nums.size - k + 1)
    for (i in nums.indices) {
        // 윈도우 밖 인덱스 제거
        while (dq.isNotEmpty() && dq.first() <= i - k) dq.removeFirst()
        // 새 값보다 작은 뒤쪽 인덱스 제거 (단조성 유지)
        while (dq.isNotEmpty() && nums[dq.last()] < nums[i]) dq.removeLast()
        dq.addLast(i)
        if (i >= k - 1) result[i - k + 1] = nums[dq.first()]
    }
    return result
}
```

### 6-4. 회전 (Rotation) — 양끝 활용

```kotlin
val dq = ArrayDeque(listOf(1, 2, 3, 4, 5))
// 오른쪽으로 2번 회전 → [4, 5, 1, 2, 3]
repeat(2) { dq.addFirst(dq.removeLast()) }

// 왼쪽으로 1번 회전 → [2, 3, 4, 5, 1]
dq.addLast(dq.removeFirst())
```

### 6-5. 회문(Palindrome) 체크

```kotlin
fun isPalindrome(s: String): Boolean {
    val dq = ArrayDeque<Char>()
    s.forEach { if (it.isLetterOrDigit()) dq.addLast(it.lowercaseChar()) }
    while (dq.size > 1) {
        if (dq.removeFirst() != dq.removeLast()) return false
    }
    return true
}
```

### 6-6. 첫 번째 음수 / 가장 가까운 작은 수 (Monotonic Stack)

```kotlin
// 각 원소의 오른쪽에 처음 나오는 더 큰 수
fun nextGreater(nums: IntArray): IntArray {
    val res = IntArray(nums.size) { -1 }
    val st = ArrayDeque<Int>()            // 인덱스 스택
    for (i in nums.indices) {
        while (st.isNotEmpty() && nums[st.last()] < nums[i]) {
            res[st.removeLast()] = nums[i]
        }
        st.addLast(i)
    }
    return res
}
```

---

## 7. 성능 & 내부 구조

### 시간 복잡도
| 연산 | 복잡도 | 비고 |
|------|--------|------|
| `addFirst` / `addLast` ⭐ | **O(1)** amortized | 가끔 배열 재할당 |
| `removeFirst` / `removeLast` ⭐ | **O(1)** | 항상 |
| `first()` / `last()` / `get(i)` | **O(1)** | 인덱스 접근 가능 |
| `add(i, e)` / `removeAt(i)` | **O(n)** | 중간 조작은 비쌈 |
| `contains(e)` / `indexOf(e)` | O(n) | 선형 탐색 |

### 내부 구조 — 원형 버퍼 (Circular Array)
```
인덱스:  0   1   2   3   4   5   6   7
        [ _ ][ B ][ C ][ D ][ _ ][ _ ][ _ ][ A ]
                ↑                            ↑
              head                         tail (wrap)
```
- `head` 와 `tail` 두 포인터가 원형 배열 위를 돈다
- 앞쪽 추가/제거 = head 포인터만 이동 → O(1)
- 용량 부족 시 2배 확장 (ArrayList 와 동일)
- LinkedList 와 달리 **연속 메모리** → 캐시 친화적, 메모리 절약

### LinkedList vs ArrayDeque
| 항목 | ArrayDeque | LinkedList |
|------|-----------|------------|
| 양끝 추가/제거 | O(1) | O(1) |
| 인덱스 접근 | **O(1)** ⭐ | O(n) |
| 메모리 | 작음 (원소만) | 큼 (노드 + prev/next 포인터) |
| 캐시 효율 | ⭐ 좋음 | 나쁨 |
| **결론** | ⭐ **거의 항상 더 빠름** | 중간 삽입/삭제 많을 때만 |

> 🎯 코테에선 **무조건 ArrayDeque**. LinkedList 는 거의 쓸 일 없음.

---

## 8. ArrayDeque vs ArrayList

| 시나리오 | 추천 |
|----------|------|
| 끝에서만 추가/제거 | 둘 다 OK (ArrayList 도 O(1) amortized) |
| **앞쪽 추가/제거** ⭐ | **ArrayDeque** (ArrayList 는 O(n)) |
| 인덱스 접근 위주 | 둘 다 O(1), ArrayList 가 미세하게 빠름 |
| 중간 삽입/삭제 多 | ArrayList (랜덤 접근 + 일괄 이동) |
| 스택/큐/덱 용도 | **ArrayDeque** ⭐ |
| 일반 리스트 | ArrayList |

---

## 9. 자주 하는 실수 ⚠️

### ① 비어있는 덱에 `first()` / `removeFirst()` 호출
```kotlin
val dq = ArrayDeque<Int>()
dq.first()              // ❌ NoSuchElementException
dq.firstOrNull()        // ✓ null
dq.removeFirstOrNull()  // ✓ null
```

### ② Iterator 도중 수정
```kotlin
for (x in dq) {
    if (x < 0) dq.removeFirst()   // ❌ ConcurrentModificationException
}
// → 새 컬렉션 만들거나 indices 로 거꾸로 순회
```

### ③ `arrayDequeOf` 없다
```kotlin
arrayDequeOf(1, 2, 3)           // ❌ 그런 함수 없음
ArrayDeque(listOf(1, 2, 3))     // ✓
```

### ④ `kotlin.collections.ArrayDeque` vs `java.util.ArrayDeque` 혼동
Kotlin 1.4+ 부터 `kotlin.collections.ArrayDeque` 가 import 없이 사용 가능.
`java.util.ArrayDeque` 는 `push/pop/peek` 같은 다른 이름을 쓰므로 헷갈리지 말 것.
```kotlin
// kotlin.collections.ArrayDeque ⭐ 기본 추천
val k = ArrayDeque<Int>()
k.addLast(1)
k.removeLast()

// java.util.ArrayDeque — push/pop 명칭 선호 시
import java.util.ArrayDeque as JArrayDeque
val j = JArrayDeque<Int>()
j.push(1)               // addFirst 와 같음 (Java 의 스택 관례)
j.pop()                 // removeFirst
j.peek()                // peekFirst
```

> ⚠️ Java 의 `push` 는 **앞에 추가**(스택의 top 이 앞쪽), Kotlin 의 `addLast` 는 뒤에 추가.
> 둘 다 스택으로 쓸 수 있지만 방향이 반대 — Kotlin 만 쓰자.

### ⑤ 큰 데이터 — 초기 용량 지정
```kotlin
// 원소 100만 개 들어올 거 아는 경우
val dq = ArrayDeque<Int>(1_000_000)   // 재할당 최소화
```

---

## 10. 한 줄 치트시트 ⭐

| 하고 싶은 것 | 코드 |
|--------------|------|
| 빈 덱 | `val dq = ArrayDeque<Int>()` |
| 초기화 | `ArrayDeque(listOf(1, 2, 3))` |
| **스택 push** | `dq.addLast(x)` |
| **스택 pop** | `dq.removeLast()` |
| **스택 peek** | `dq.last()` |
| **큐 enqueue** | `dq.addLast(x)` |
| **큐 dequeue** | `dq.removeFirst()` |
| 앞 보기 | `dq.first()` / `dq.firstOrNull()` |
| 뒤 보기 | `dq.last()` / `dq.lastOrNull()` |
| 인덱스 접근 | `dq[i]` (O(1)) |
| 비었나? | `dq.isEmpty()` |
| 안전 pop | `dq.removeFirstOrNull() ?: break` |
| 역순 순회 | `dq.descendingIterator()` |
| 배열로 | `dq.toIntArray()` |

---

## 11. 시리얼 — 함께 보면 좋은 자료

- [`Stack.md`](Stack.md) — 스택 관점의 ArrayDeque
- [`List.md`](List.md) — `ArrayDeque` 가 구현하는 `MutableList` 인터페이스
- [`Loop.md`](Loop.md) — `while (dq.isNotEmpty())` 패턴

> 🎯 **외워둘 단 한 줄**: `ArrayDeque<T>()` + `addLast` / `removeFirst` / `removeLast`. 이것만 알면 코테 스택·큐·BFS·덱 다 끝.
