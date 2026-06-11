# Kotlin 클래스(Class) 문법 정리

Kotlin 클래스 선언·OOP 핵심 문법을 한눈에 정리했습니다.
무신사 라이브 코딩(HackerRank·Kotlin) 대비 빠른 참조용. ⭐ = 코테 단골.

---

## 1. 기본 선언과 프로퍼티

| 키워드/문법 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `class` | `class Foo` | 기본 클래스 선언 | 본문 없으면 중괄호 생략 가능 |
| 주생성자 프로퍼티 | `class Foo(val x: Int)` | 헤더에서 바로 프로퍼티 선언 | ⭐ 보일러플레이트 최소화 |
| `val` 프로퍼티 | `val name: String` | 읽기 전용 (불변) | 기본은 `val` 권장 |
| `var` 프로퍼티 | `var age: Int` | 읽기/쓰기 가변 | 필요할 때만 |
| 기본값 | `val x: Int = 0` | 생성자 파라미터 기본값 | 호출 시 생략 가능 |
| 커스텀 getter | `val isAdult get() = age >= 19` | 접근 시마다 계산 | 계산 프로퍼티에 사용 |
| `new` 없음 | `User("kim")` | 인스턴스 생성 | Java와 다름 |

## 2. 생성자 · init · 보조생성자

| 키워드/문법 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| 주생성자 | `class Foo(val x: Int)` | 클래스 헤더에 선언, 로직 불가 | `constructor` 생략 가능 |
| `init` 블록 | `init { require(x > 0) }` | 주생성자 실행 시 동작 | ⭐ 검증·초기화 |
| 복수 `init` | 여러 `init { }` 블록 | 선언 순서대로 실행 | |
| 보조생성자 | `constructor(s: Int) : this(s, s)` | 반드시 `this(…)`로 주생성자에 위임 | |
| `lateinit var` | `lateinit var db: DB` | 나중에 초기화할 `var` | `isInitialized`로 체크 |
| `by lazy` | `val x by lazy { ... }` | 최초 접근 시 한 번만 계산 | 스레드 안전 |

## 3. 클래스 종류

| 키워드 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `class` | `class Foo` | 일반 클래스, 기본은 **final** | 상속하려면 `open` 필요 |
| `data class` ⭐ | `data class Point(val x: Int, val y: Int)` | equals/hashCode/toString/copy/componentN 자동 | 좌표·엔트리 묶을 때 |
| `enum class` | `enum class Status { ACTIVE, PENDING }` | 고정 상수 집합 | `values()`, `valueOf()` |
| `sealed class` ⭐ | `sealed class Result` | 제한된 하위 타입, `when` 망라성 보장 | else 불필요 |
| `abstract class` | `abstract class Shape` | 인스턴스화 불가, 상속용 | |
| `open class` | `open class Animal` | 상속 허용 | Kotlin 기본은 final |
| `interface` | `interface Printable` | 다중 구현, 기본 구현·프로퍼티 가능 | |
| `object` | `object Registry` | 싱글톤 (단 하나의 인스턴스) | 스레드 안전 지연 초기화 |
| `companion object` | `companion object { }` | 클래스 레벨 멤버·팩토리 (`static` 대용) | ⭐ |

## 4. data class 상세 ⭐

| 자동 생성 | 설명 | 비고 |
| :--- | :--- | :--- |
| `equals()` / `hashCode()` | 모든 주생성자 프로퍼티 기준 비교 | ⭐ Map 키, Set 원소로 안전하게 사용 |
| `toString()` | `Point(x=1, y=2)` 형식 | 디버깅에 유용 |
| `copy(…)` | 일부 필드만 바꾼 복사본 생성 | ⭐ 불변 객체 수정 패턴 |
| `componentN()` | 구조 분해 지원 | ⭐ `val (x, y) = point` |

## 5. sealed class 상세 ⭐

| 특징 | 설명 |
| :--- | :--- |
| 하위 타입 봉인 | 같은 파일(또는 같은 패키지) 안에서만 하위 클래스 선언 가능 |
| `when` 망라성 | 모든 하위 타입을 커버하면 `else` 없어도 컴파일 통과 |
| 하위 타입 형태 | `data class`, `object`, `class` 모두 가능 |

## 6. enum class 상세

| 요소 | 형태 | 설명 |
| :--- | :--- | :--- |
| 상수 선언 | `enum class Dir { N, S, E, W }` | 기본 형태 |
| 프로퍼티 있는 enum | `enum class Color(val hex: String)` | 각 상수마다 데이터 보유 |
| 추상 메서드 | `abstract fun signal(): String` | 상수별로 구현 |
| `values()` | `Dir.values()` | 전체 배열 반환 |
| `valueOf(name)` | `Dir.valueOf("N")` | 이름으로 조회, 없으면 예외 |
| `ordinal` / `name` | (프로퍼티) | 순서 인덱스 / 이름 문자열 |
| `entries` (Kotlin 1.9+) | `Dir.entries` | `values()` 대체, List 반환 |

## 7. 상속과 인터페이스

| 키워드/문법 | 형태 | 설명 | 비고 |
| :--- | :--- | :--- | :--- |
| `open` | `open class Animal` | 상속 허용 선언 | 없으면 final |
| `:` 상속 | `class Dog : Animal()` | 부모 생성자 호출 필수 | |
| `override` | `override fun speak()` | 메서드/프로퍼티 재정의 | `open` 없으면 컴파일 에러 |
| `abstract` | `abstract fun draw()` | 본문 없는 메서드, 반드시 override | |
| `interface` 구현 | `class A : Printable` | 생성자 호출 없음, 다중 가능 | |
| `super` | `super.speak()` | 부모 구현 호출 | |
| `final` | `final override fun foo()` | 이 클래스에서 override 잠금 | |

## 8. 가시성 한정자

| 한정자 | 범위 | 비고 |
| :--- | :--- | :--- |
| `public` | 어디서나 (기본값) | 명시 생략 가능 |
| `private` | 선언된 클래스/파일 내부만 | 가장 좁음 |
| `protected` | 선언 클래스 + 하위 클래스 | 최상위 선언엔 사용 불가 |
| `internal` | 같은 **모듈** 내부 | Java에 없는 개념 |

## 9. object · companion object

| 구분 | 인스턴스 수 | 이름 | 용도 |
| :--- | :--- | :--- | :--- |
| `object Name { }` | 1개 (싱글톤) | 있음 | 전역 단일 객체 |
| `companion object` | 클래스당 1개 | 선택 | 정적 멤버·팩토리 |
| `object : Type { }` | 표현식마다 생성 | 없음 | 일회성 익명 구현체 |

## 10. 중첩(Nested) vs 내부(Inner) 클래스

**Kotlin은 Java와 기본이 반대.**

| 구분 | 키워드 | 외부 인스턴스 참조 | 생성 방법 | Java 대응 |
| :--- | :--- | :--- | :--- | :--- |
| **중첩 클래스** | `class` (기본) | **없음** | `Outer.Nested()` | `static` nested class |
| **내부 클래스** | `inner class` | **있음** | `Outer().Inner()` | non-static inner class |

## 11. 위임 (by)

| 문법 | 설명 | 비고 |
| :--- | :--- | :--- |
| `class A(b: B) : I by b` | 인터페이스 구현을 다른 객체에 위임 | 컴포지션 + 위임 패턴 |
| `val x by lazy { }` | 프로퍼티 지연 초기화 위임 | |
| `val x by Delegates.observable(init) { … }` | 값 변경 감지 위임 | |
| `val x by map` | Map에서 프로퍼티 값 위임 | |

---

## 12. 실전 사용법 (코드)

### 코테 단골 자료구조 정의 ⭐
```kotlin
// 연결 리스트 노드
class ListNode(val value: Int) {
    var next: ListNode? = null
}

// 좌표 (equals/hashCode 자동 → Set/Map 키로 바로 사용)
data class Point(val x: Int, val y: Int)

// 집계 엔트리 (정렬·비교용)
data class Entry(val key: String, val count: Int)
```

### data class — copy + 구조 분해 ⭐
```kotlin
data class Point(val x: Int, val y: Int)

val p = Point(1, 2)
val p2 = p.copy(y = 3)          // Point(1, 3)  — 불변 수정 패턴
val (x, y) = p                  // 구조 분해: x=1, y=2
println(p)                      // Point(x=1, y=2)  — toString 자동

// Map.Entry 구조 분해와 동일 패턴 (코테에서 자주 쓰임)
val points = listOf(Point(0,0), Point(1,2))
for ((px, py) in points) { /* ... */ }
```

### sealed class + when 망라 ⭐
```kotlin
sealed class Result
data class Success(val data: String) : Result()
data class Failure(val error: String) : Result()
object Loading : Result()

fun handle(r: Result): String = when (r) {   // else 없이 컴파일 OK
    is Success -> r.data
    is Failure -> "Error: ${r.error}"
    is Loading -> "Loading…"
}
```

### enum class — 방향 배열 (BFS/DFS 단골)
```kotlin
enum class Dir(val dr: Int, val dc: Int) {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    fun next(r: Int, c: Int) = r + dr to c + dc
}

// 사용
for (dir in Dir.values()) {
    val (nr, nc) = dir.next(r, c)
}
```

### enum class — 상수 배열 대신 쓰기 (가장 단순한 형태)
```kotlin
val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)
// ↑ 이것과 동일한 의미를 Dir enum으로 표현 가능
```

### object 싱글톤 + companion object 팩토리
```kotlin
object Counter {                        // 싱글톤: 어디서든 같은 인스턴스
    private var count = 0
    fun increment() = ++count
}

class User private constructor(val name: String) {
    companion object {
        const val MAX_NAME = 20
        fun of(name: String): User {
            require(name.length <= MAX_NAME)
            return User(name)
        }
    }
}

val u = User.of("kim")                 // 팩토리 메서드
```

### 상속 + abstract + interface
```kotlin
interface Drawable {
    fun draw(): String
    fun describe() = "drawable object"  // 기본 구현
}

abstract class Shape(val color: String) : Drawable {
    abstract fun area(): Double
}

open class Circle(color: String, val radius: Double) : Shape(color) {
    override fun area() = Math.PI * radius * radius
    override fun draw() = "Circle(r=$radius)"
}

class FilledCircle(color: String, radius: Double, val fill: String)
    : Circle(color, radius) {
    override fun draw() = "${super.draw()}, fill=$fill"
    final override fun area() = super.area()   // 하위에서 재정의 불가
}
```

### 중첩 vs 내부 클래스
```kotlin
class Graph {
    private val adj = mutableMapOf<Int, MutableList<Int>>()

    class Edge(val from: Int, val to: Int)  // 중첩: Graph 인스턴스 불필요

    inner class BFS(val start: Int) {       // 내부: adj 직접 접근
        fun run(): List<Int> {
            val visited = mutableListOf<Int>()
            // adj 접근 가능 (this@Graph.adj)
            return visited
        }
    }
}

val e = Graph.Edge(1, 2)           // 외부 인스턴스 없이 생성
val bfs = Graph().BFS(0)           // 반드시 외부 인스턴스 통해 생성
```

### 위임 (by) — 인터페이스 컴포지션
```kotlin
interface Logger {
    fun log(msg: String)
}

class ConsoleLogger : Logger {
    override fun log(msg: String) = println(msg)
}

// Service는 Logger 구현을 ConsoleLogger에 완전히 위임
class Service(logger: Logger) : Logger by logger {
    fun run() { log("running") }   // 직접 위임
}
```

### init 블록 검증 패턴
```kotlin
class Rectangle(val width: Int, val height: Int) {
    val area: Int

    init {
        require(width > 0) { "width must be positive, got $width" }
        require(height > 0) { "height must be positive, got $height" }
        area = width * height
    }

    constructor(side: Int) : this(side, side)  // 정사각형 보조생성자
}
```

---

## 13. 코테 치트 요약

| 상황 | 한 줄 / 패턴 |
| :--- | :--- |
| 좌표 묶기 | `data class Point(val x: Int, val y: Int)` |
| 집계 결과 묶기 | `data class Entry(val key: String, val count: Int)` |
| 연결 리스트 노드 | `class ListNode(val v: Int) { var next: ListNode? = null }` |
| 불변 복사+수정 | `point.copy(x = 3)` |
| 구조 분해 | `val (x, y) = point` / `for ((k, v) in map)` |
| 상태 봉인 + when | `sealed class` + `when(r) { is A -> … is B -> … }` |
| 4방향 탐색 상수 | `enum class Dir(val dr: Int, val dc: Int)` |
| 싱글톤 | `object Name { }` |
| 정적 상수·팩토리 | `companion object { const val X = …; fun of(…) }` |
| 클래스 final 기본 | 상속 허용 시 `open` 명시, 불필요하면 생략 |
| 주생성자 검증 | `init { require(…) { "메시지" } }` |
| 인터페이스 위임 | `class A(b: B) : I by b` |
| 익명 구현체 | `object : Comparator<Int> { override fun compare(…) }` |
| 중첩(외부 참조 불필요) | `class` (기본, `inner` 아님) |
| 내부(외부 상태 필요) | `inner class` + `this@Outer` |

> ⚠️ **Kotlin 클래스는 기본 `final`**. `open` 없이 상속하면 컴파일 에러.
> `inner class`는 외부 인스턴스를 붙들어 메모리 누수 위험 — 외부 참조 불필요하면 반드시 기본 중첩 `class` 사용.
> `data class`의 `equals`/`hashCode`는 **주생성자 프로퍼티만** 기준 — `body`에 선언한 프로퍼티는 포함되지 않음.
>
> 🎯 **코테 즉시 투입 도구**: ① `data class`로 좌표·엔트리 정의 → ② `copy()`로 불변 수정 → ③ `sealed class`로 결과 타입 봉인 → ④ `enum class Dir`로 4방향 배열 대체. 선언은 여기서, 조립은 직접!
