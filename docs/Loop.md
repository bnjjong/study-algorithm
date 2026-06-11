# Kotlin 반복문(Loop) 정리

Kotlin 반복문·범위·제어 흐름 빠른 참조.
무신사 라이브 코딩(HackerRank·Kotlin) 대비. ⭐ = 코테 단골.

## 1. 범위(Range) for ⭐

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `..` (rangeTo) | `for (i in 0..n)` | 0 이상 n **이하** | 끝 포함 |
| `until` ⭐ | `for (i in 0 until n)` | 0 이상 n **미만** | 배열 인덱스 순회 정석 |
| `downTo` ⭐ | `for (i in n-1 downTo 0)` | 큰 수에서 작은 수로 | 역순 |
| `step` | `for (i in 0..n step 2)` | 지정 간격으로 진행 | `downTo`와 조합 가능 |
| `a..b step k` | `for (i in 1..10 step 3)` | a부터 b까지 k씩 | |
| `n downTo 0 step 2` | `for (i in 10 downTo 0 step 2)` | 역순 + 건너뛰기 | |

## 2. 컬렉션 for ⭐

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| 요소 직접 순회 ⭐ | `for (x in list)` | 각 요소 순서대로 | |
| `withIndex()` ⭐ | `for ((i, x) in list.withIndex())` | 인덱스 + 값 구조 분해 | `i`는 0-based |
| `indices` | `for (i in list.indices)` | 인덱스 범위만 | `0 until size` 동일 |
| Map 구조 분해 ⭐ | `for ((k, v) in map)` | 키·값 동시 추출 | `map.entries` for도 동일 |
| 배열 인덱스 | `for (i in arr.indices)` | 배열 인덱스 순회 | |
| `lastIndex` | `for (i in 0..list.lastIndex)` | 마지막 인덱스 포함 | `size - 1` 대신 |

## 3. while / do-while

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `while` ⭐ | `while (condition) { }` | 조건 참인 동안 반복 | 두 포인터, BFS 핵심 |
| `do-while` | `do { } while (condition)` | 최소 1회 실행 후 조건 검사 | 입력 처리에 유용 |

## 4. repeat

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `repeat(n)` ⭐ | `repeat(n) { }` | n번 반복, 람다 인자는 0-based 인덱스 `it` | `for (i in 0 until n)` 대체 |
| `repeat(n) { i -> }` | `repeat(5) { i -> println(i) }` | 인덱스 명시 사용 | |

## 5. 함수형 순회 ⭐

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `forEach` ⭐ | `list.forEach { }` | 각 요소에 람다 실행 | 반환값 없음 |
| `forEachIndexed` ⭐ | `list.forEachIndexed { i, v -> }` | 인덱스 + 값 람다 | |
| 범위 forEach | `(a..b).forEach { }` | IntRange에 forEach | `for (i in a..b)` 동일 |
| `map` | `list.map { it * 2 }` | 요소 변환 → 새 List 반환 | **새 리스트 반환, 부수효과 없음** |
| `filter` | `list.filter { it > 0 }` | 조건 일치 요소만 → 새 List | |
| `forEach` vs `map` | - | `forEach`: 부수효과 목적 | `map`: 변환 목적, 반환 있음 |

## 6. 제어: break / continue / 라벨 ⭐

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `break` ⭐ | `break` | 가장 가까운 루프 탈출 | |
| `continue` | `continue` | 현재 이터레이션 건너뜀 | |
| 라벨 선언 | `loop@ for (...)` | 라벨 붙이기 | `@` 뒤 이름 자유 |
| `break@라벨` ⭐ | `break@loop` | 지정 루프 탈출 | 중첩 루프 조기 종료 |
| `continue@라벨` | `continue@outer` | 지정 루프의 다음 이터레이션 | |
| `return` (람다) | `return@forEach` | forEach 내 현재 요소 건너뜀 | `continue` 역할 |

## 7. 범위 객체 유틸

| 문법/함수 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `..` | `1..5` | 1 이상 5 이하 `IntRange` | `rangeTo` 동일 |
| `until` | `0 until n` | 0 이상 n 미만 `IntRange` | |
| `downTo` | `5 downTo 1` | 5 이상 1 이하 역순 `IntProgression` | |
| `step` | `(1..10 step 2)` | 간격 지정 `IntProgression` | `downTo`에도 사용 |
| `rangeTo` | `1.rangeTo(5)` | `1..5`와 동일 | 연산자 형태 선호 |
| `reversed()` | `(1..5).reversed()` | 리스트로 역순 변환 | `List<Int>` 반환 |
| `in` 범위 검사 | `x in 1..10` | 포함 여부 Boolean | `if (x in range)` |

---

## 8. 실전 사용법 (코드)

### 기본 범위 순회
```kotlin
// 0 ~ n-1 (배열 인덱스 정석) ⭐
for (i in 0 until n) { println(i) }

// 1 ~ n (1-based 카운터)
for (i in 1..n) { println(i) }

// 역순 ⭐
for (i in n - 1 downTo 0) { println(i) }

// 짝수 인덱스만
for (i in 0 until n step 2) { println(i) }

// 역순 + 건너뛰기
for (i in n - 1 downTo 0 step 2) { println(i) }
```

### 컬렉션 + 인덱스 동시 순회 ⭐
```kotlin
val list = listOf("a", "b", "c")

for ((i, x) in list.withIndex()) {
    println("$i: $x")   // 0: a, 1: b, 2: c
}

list.forEachIndexed { i, v -> println("$i: $v") }
```

### Map 구조 분해 순회 ⭐
```kotlin
val map = mapOf("alice" to 3, "bob" to 1)

for ((k, v) in map) {
    println("$k -> $v")
}

map.forEach { (k, v) -> println("$k=$v") }
```

### repeat vs for
```kotlin
repeat(5) { println("hello $it") }   // it: 0..4
// 동일
for (i in 0 until 5) { println("hello $i") }
```

### forEach 안에서 continue 효과 (return@forEach) ⭐
```kotlin
listOf(1, 2, 3, 4, 5).forEach { n ->
    if (n % 2 == 0) return@forEach   // 짝수는 건너뜀 → continue 역할
    println(n)                        // 1 3 5
}
```

### 라벨로 중첩 루프 탈출 ⭐⭐
```kotlin
outer@ for (i in 0 until rows) {
    for (j in 0 until cols) {
        if (board[i][j] == target) {
            println("found at $i, $j")
            break@outer   // 바깥 루프까지 한 번에 종료
        }
    }
}
```

### 2차원 격자 순회 ⭐⭐
```kotlin
val rows = 4; val cols = 5
val board = Array(rows) { IntArray(cols) }

// 기본 행·열 순회
for (r in 0 until rows) {
    for (c in 0 until cols) {
        print(board[r][c])
    }
    println()
}

// 상하좌우 델타
val dr = intArrayOf(-1, 1, 0, 0)
val dc = intArrayOf(0, 0, -1, 1)
for (d in 0 until 4) {
    val nr = r + dr[d]; val nc = c + dc[d]
    if (nr in 0 until rows && nc in 0 until cols) { /* valid */ }
}
```

### 역순 순회 ⭐
```kotlin
val list = listOf(1, 2, 3, 4, 5)

// 방법 A: downTo
for (i in list.lastIndex downTo 0) { println(list[i]) }

// 방법 B: reversed()
for (x in list.reversed()) { println(x) }

// 방법 C: asReversed() — 뷰, 복사 없음 (효율적)
for (x in list.asReversed()) { println(x) }
```

### 두 포인터 while ⭐⭐
```kotlin
var left = 0; var right = list.lastIndex
while (left < right) {
    when {
        list[left] + list[right] == target -> {
            println("$left, $right")
            left++; right--
        }
        list[left] + list[right] < target -> left++
        else -> right--
    }
}
```

### 슬라이딩 윈도우 while / for
```kotlin
// 고정 크기 윈도우
for (i in 0..n - k) {
    val window = list.subList(i, i + k)
    // process window
}

// 가변 크기 (투포인터)
var lo = 0; var sum = 0
for (hi in 0 until n) {
    sum += list[hi]
    while (sum > limit) { sum -= list[lo++] }
    // process [lo..hi]
}
```

### 중첩 루프 + break로 소수 판별
```kotlin
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}
```

### 함수형: map / filter vs forEach 차이
```kotlin
val nums = listOf(1, 2, 3, 4, 5)

// forEach: 부수효과 목적 (반환값 없음)
nums.forEach { println(it) }

// map: 변환 → 새 List 반환
val doubled = nums.map { it * 2 }           // [2, 4, 6, 8, 10]

// filter: 조건 일치만 → 새 List 반환
val evens = nums.filter { it % 2 == 0 }     // [2, 4]

// 체이닝 (코테 단골 패턴) ⭐
val result = nums.filter { it % 2 == 0 }.map { it * it }   // [4, 16]
```

---

## 9. 코테 치트 요약

| 상황 | 한 줄 |
| :--- | :--- |
| 0 ~ n-1 인덱스 순회 | `for (i in 0 until n)` |
| n-1 ~ 0 역순 | `for (i in n-1 downTo 0)` |
| 짝수 인덱스만 | `for (i in 0 until n step 2)` |
| 인덱스 + 값 동시 | `for ((i, x) in list.withIndex())` |
| Map 키·값 순회 | `for ((k, v) in map)` |
| n번 단순 반복 | `repeat(n) { }` |
| 함수형 부수효과 | `list.forEach { }` |
| 함수형 인덱스 | `list.forEachIndexed { i, v -> }` |
| forEach 안 continue | `return@forEach` |
| 중첩 루프 탈출 | `outer@ for(...) { break@outer }` |
| 2차원 격자 순회 | `for (r in 0 until rows) for (c in 0 until cols)` |
| 역순 (뷰, 복사 없음) | `list.asReversed()` |
| 두 포인터 | `var l = 0; var r = n-1; while (l < r)` |
| 범위 포함 검사 | `x in 0 until n` |

> ⚠️ `forEach` 안에서 `break`/`continue` 직접 사용 불가. `break`이 필요하면 `for` 문 + 라벨 조합을 쓸 것. `continue` 대체는 `return@forEach`.
>
> 🎯 **중첩 루프 조기 종료 도구**: ① 라벨 `outer@` 선언 → ② 내부에서 `break@outer`. 함수 안에서는 `return`으로 전체 탈출도 가능. 가독성이 중요하면 별도 함수로 추출하는 것도 고려.
