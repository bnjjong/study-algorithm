# Stack / Deque (Kotlin)

무신사 라이브 코딩(HackerRank·Kotlin) 대비. ⭐ = 코테 단골.

스택(LIFO, 마지막에 넣은 게 먼저 나옴)은 Kotlin에서 **전용 클래스가 따로 없다.** `ArrayDeque`를 스택처럼 쓰는 게 표준이다.

---

## 0. 무엇을 쓸까 — 선택지 비교 ⭐

| 방법 | 넣기 | 빼기 | 보기(peek) | 비고 |
|------|------|------|-----------|------|
| **`kotlin.collections.ArrayDeque`** ⭐ | `addLast` | `removeLast` | `last()` | **기본 추천.** import 없이 바로, 양끝 O(1), `MutableList`이기도 함 |
| `java.util.ArrayDeque` | `push` | `pop` | `peek` | Java식 이름 선호 시. `Stack`보다 빠름 |
| `java.util.Stack` | `push` | `pop` | `peek` | **레거시**(synchronized → 느림). 쓰지 말 것, 존재만 알기 |
| `MutableList`(ArrayList) | `add` | `removeAt(lastIndex)` | `last()` | 가능은 함. 굳이? |

> 🎯 **그냥 `ArrayDeque<T>()` 쓰세요.** Kotlin 표준 라이브러리라 import 불필요(`kotlin.collections`), 스택·큐·덱 다 됨.

```kotlin
val stack = ArrayDeque<Int>()
stack.addLast(1); stack.addLast(2)   // push
stack.last()                         // peek → 2
stack.removeLast()                   // pop  → 2
stack.isEmpty()                      // false
```

---

## 1. `kotlin.collections.ArrayDeque` ⭐ (스택으로)

| 함수 | 입력 | 출력 | 설명 | 비고 |
|------|------|------|------|------|
| `addLast(e)` ⭐ | 원소 | Unit | **push** — 뒤(top)에 추가 | O(1) |
| `removeLast()` ⭐ | – | 원소 | **pop** — 뒤에서 제거+반환 | 비었으면 **예외** |
| `removeLastOrNull()` ⭐ | – | 원소? | pop, 비었으면 `null` | 안전한 pop |
| `last()` ⭐ | – | 원소 | **peek** — 뒤 원소 보기(제거 X) | 비었으면 예외 |
| `lastOrNull()` | – | 원소? | peek, 비었으면 `null` | |
| `isEmpty()` / `isNotEmpty()` | – | Boolean | 비었는지 | |
| `size` | – | Int | 개수 | |
| `addFirst(e)` / `removeFirst()` | | | 앞쪽 조작 → **덱/큐**로 변신 | |

```kotlin
val st = ArrayDeque<Char>()
st.addLast('a')                 // push
val top = st.last()             // peek (제거 안 함)
val popped = st.removeLast()    // pop
while (st.isNotEmpty()) { /* ... */ st.removeLast() }
```

> ⚠️ `removeLast()`/`last()`는 **비었을 때 예외**. 루프에서 꺼낼 땐 `isNotEmpty()` 먼저 확인하거나 `removeLastOrNull()` 사용.

---

## 2. `java.util.ArrayDeque` (Java식 이름 선호 시)

```kotlin
import java.util.ArrayDeque
val st = ArrayDeque<Int>()
st.push(10)        // = addFirst
st.peek()          // top 보기 (비었으면 null)
st.pop()           // 제거+반환 (비었으면 예외)
st.isEmpty()
```

| 함수 | 설명 | 비고 |
|------|------|------|
| `push(e)` | 스택 push (앞에 추가) | |
| `pop()` | 스택 pop | 비었으면 `NoSuchElementException` |
| `peek()` | top 보기 | 비었으면 **`null`** (예외 아님) |
| `offer(e)`/`poll()` | **큐**로 쓸 때 (뒤 추가/앞 제거) | poll은 비면 null |

> 💡 `java.util.ArrayDeque`는 `peek`이 null 반환, `pop`은 예외 — 헷갈리지 말기.

---

## 3. `java.util.Stack` (레거시 — 알기만)

```kotlin
import java.util.Stack
val st = Stack<Int>()
st.push(1); st.pop(); st.peek(); st.empty()   // empty() = isEmpty
```
- `Vector` 상속 → **모든 메서드 synchronized → 느림.** 단일 스레드 코테에선 손해.
- 신규 코드는 `ArrayDeque` 쓰라고 Java 공식 문서도 권장. **면접에서 `Stack` 쓰면 "왜 ArrayDeque 안 쓰죠?" 들을 수 있음.**

---

## 4. 큐·덱도 같은 `ArrayDeque`로 ⭐

스택만이 아니라 **큐(FIFO)·덱(양끝)** 도 전부 `ArrayDeque` 하나로 해결.

```kotlin
// 큐 (FIFO): 뒤로 넣고 앞에서 빼기 — BFS 단골
val q = ArrayDeque<Int>()
q.addLast(start)                       // enqueue
while (q.isNotEmpty()) {
    val cur = q.removeFirst()          // dequeue
    // ... 이웃을 addLast
}

// 덱 (양끝): 슬라이딩 윈도우 최대값 등
val dq = ArrayDeque<Int>()
dq.addFirst(x); dq.addLast(y)
dq.removeFirst(); dq.removeLast()
```

| 용도 | 넣기 | 빼기 |
|------|------|------|
| 스택(LIFO) | `addLast` | `removeLast` |
| 큐(FIFO) | `addLast` | `removeFirst` |
| 덱 | `addFirst`/`addLast` | `removeFirst`/`removeLast` |

---

## 5. 실전 패턴 (코드) ⭐

### ① 괄호 매칭 (Valid Parentheses)
```kotlin
fun isValid(s: String): Boolean {
    val open = mapOf(')' to '(', ']' to '[', '}' to '{')
    val st = ArrayDeque<Char>()
    for (c in s) {
        if (c in open) {                       // 닫는 괄호
            if (st.removeLastOrNull() != open[c]) return false
        } else st.addLast(c)                   // 여는 괄호
    }
    return st.isEmpty()
}
```

### ② Add Two Numbers II — 정방향 저장 (지금 푸는 #1!)
```kotlin
// 두 리스트를 스택에 쌓으면, pop 순서가 곧 최하위 자리부터다.
fun addTwoNumbersII(l1: SinglyLinkedListNode?, l2: SinglyLinkedListNode?): SinglyLinkedListNode? {
    val s1 = ArrayDeque<Int>(); var p = l1; while (p != null) { s1.addLast(p.data); p = p.next }
    val s2 = ArrayDeque<Int>(); var q = l2; while (q != null) { s2.addLast(q.data); q = q.next }

    var carry = 0
    var head: SinglyLinkedListNode? = null
    while (s1.isNotEmpty() || s2.isNotEmpty() || carry > 0) {
        val sum = (s1.removeLastOrNull() ?: 0) + (s2.removeLastOrNull() ?: 0) + carry
        carry = sum / 10
        val node = SinglyLinkedListNode(sum % 10)
        node.next = head        // 앞에 붙이면 자연히 정방향 결과
        head = node
    }
    return head
}
```

### ③ 단조 스택 (Monotonic Stack) — Next Greater Element
```kotlin
// 각 원소의 "오른쪽에서 처음 나오는 더 큰 값". 없으면 -1.
fun nextGreater(nums: IntArray): IntArray {
    val res = IntArray(nums.size) { -1 }
    val st = ArrayDeque<Int>()                 // 인덱스를 쌓는다
    for (i in nums.indices) {
        while (st.isNotEmpty() && nums[st.last()] < nums[i]) {
            res[st.removeLast()] = nums[i]      // 더 큰 값을 만난 인덱스들 확정
        }
        st.addLast(i)
    }
    return res
}
```

### ④ 후위 표기법 계산 (Evaluate RPN)
```kotlin
fun evalRPN(tokens: List<String>): Int {
    val st = ArrayDeque<Int>()
    for (t in tokens) when (t) {
        "+", "-", "*", "/" -> {
            val b = st.removeLast(); val a = st.removeLast()
            st.addLast(when (t) { "+" -> a + b; "-" -> a - b; "*" -> a * b; else -> a / b })
        }
        else -> st.addLast(t.toInt())
    }
    return st.last()
}
```

### ⑤ DFS 반복형 (재귀 대신 스택)
```kotlin
fun dfs(start: Int, graph: Map<Int, List<Int>>) {
    val st = ArrayDeque<Int>().apply { addLast(start) }
    val seen = hashSetOf(start)
    while (st.isNotEmpty()) {
        val cur = st.removeLast()
        for (nxt in graph[cur].orEmpty()) if (seen.add(nxt)) st.addLast(nxt)
    }
}
```

---

## 6. 코테 치트 요약 ⭐

| 상황 | 한 줄 |
|------|------|
| 스택 필요 | `ArrayDeque<T>()` → `addLast`/`removeLast`/`last()` |
| 큐(BFS) 필요 | `ArrayDeque<T>()` → `addLast`/`removeFirst` |
| 안전하게 pop | `removeLastOrNull()` (비어도 예외 X) |
| peek | `last()` (또는 `lastOrNull()`) |
| 괄호/짝 맞추기 | 여는 건 push, 닫는 건 pop해서 짝 비교 |
| "다음 큰/작은 원소", 히스토그램 | **단조 스택**(인덱스를 쌓기) |
| 수식 계산·되돌리기·DFS | 스택 |
| `java.util.Stack` | **쓰지 말기**(느림). `ArrayDeque` 권장 |

> 🎯 **외울 것 딱 하나:** `ArrayDeque`로 스택(`addLast`/`removeLast`)·큐(`addLast`/`removeFirst`) 둘 다 된다.
