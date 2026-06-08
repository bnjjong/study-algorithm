# Kotlin 클래스(Class) 선언 및 사용 정리

Kotlin에서 클래스를 선언하고 사용하는 기본 문법과, 중첩/내부/익명 클래스, 그리고 베스트 프랙티스를 정리합니다. (Java와 디폴트가 반대인 부분이 많아 헷갈리기 쉬우니 차이를 함께 표기)

## 1. 기본 선언과 프로퍼티

| 형태 | 설명 | 예시 |
| :--- | :--- | :--- |
| `class Foo` | 본문 없는 빈 클래스 (중괄호 생략 가능) | `class Empty` |
| `class Foo(val x: Int)` | 주생성자에서 바로 프로퍼티 선언 | `class User(val name: String)` |
| `val` / `var` | 읽기전용 / 가변 프로퍼티 | `var age: Int = 0` |
| 기본값 | 생성자 파라미터 기본값 | `class P(val x: Int = 0)` |

```kotlin
class User(val name: String, var age: Int = 0) {  // 주생성자 + 기본값
    val isAdult: Boolean get() = age >= 19         // 커스텀 getter (계산 프로퍼티)
}

val u = User("jongsang")     // new 키워드 없음
println(u.name)              // 프로퍼티 직접 접근 (Java getter 자동)
u.age = 30
```

## 2. 생성자 (주생성자 · init · 보조생성자)

| 요소 | 설명 |
| :--- | :--- |
| 주생성자(primary) | 클래스 헤더에 선언. 로직은 못 넣음 |
| `init { }` | 초기화 블록. 주생성자 실행 시 동작. 여러 개 가능(선언 순서대로) |
| 보조생성자(secondary) | `constructor(...)`. 반드시 주생성자에 `this(...)`로 위임 |

```kotlin
class Rectangle(val width: Int, val height: Int) {
    val area: Int                        // 선언만

    init {
        require(width > 0 && height > 0) // 검증은 init에서
        area = width * height            // 프로퍼티 초기화
    }

    constructor(side: Int) : this(side, side)  // 정사각형용 보조생성자
}
```

> **주의:** 프로퍼티·`init`는 **본문에 나타난 순서대로** 실행된다. 아직 초기화 안 된 프로퍼티를 `init`에서 참조하면 컴파일 에러.

## 3. 클래스의 종류

| 키워드 | 용도 | 핵심 특징 |
| :--- | :--- | :--- |
| `class` | 일반 클래스 | 기본은 **final** (상속 불가) |
| `data class` | 데이터 보관 | `equals`/`hashCode`/`toString`/`copy`/`componentN` 자동 |
| `enum class` | 고정된 상수 집합 | `Status.ACTIVE` |
| `sealed class` | 제한된 하위 타입 집합 | `when` 망라성 보장 (else 불필요) |
| `abstract class` | 추상 클래스 | 인스턴스화 불가, 상속용 |
| `open class` | 상속 허용 | Kotlin 클래스는 명시해야 상속 가능 |
| `interface` | 인터페이스 | 다중 구현, 기본 구현·프로퍼티 가능 |
| `object` | 싱글톤 | 4장 참고 |

```kotlin
data class Point(val x: Int, val y: Int)
val p = Point(1, 2)
val p2 = p.copy(y = 3)        // copy로 일부만 변경 → Point(1, 3)
val (x, y) = p                // 구조 분해 (componentN)

enum class Status { ACTIVE, INACTIVE, PENDING }

sealed class Result
data class Success(val data: String) : Result()
data class Failure(val error: String) : Result()

fun handle(r: Result) = when (r) {       // else 없이도 컴파일 OK
    is Success -> r.data
    is Failure -> r.error
}
```

## 4. 중첩(Nested) vs 내부(Inner) 클래스 ★

가장 헷갈리는 부분. **Kotlin은 Java와 디폴트가 반대다.**

| 구분 | 키워드 | 외부(Outer) 인스턴스 참조 | 생성 방법 | Java 대응 |
| :--- | :--- | :--- | :--- | :--- |
| **중첩 클래스** | `class` (기본) | **없음** (외부 멤버 접근 불가) | `Outer.Nested()` | `static` nested class |
| **내부 클래스** | `inner class` | **있음** (외부 멤버 접근 가능) | `Outer().Inner()` | (non-static) inner class |

```kotlin
class Outer {
    private val secret = 42

    class Nested {              // 중첩: Outer와 무관 (외부 인스턴스 불필요)
        fun work() = 10         // secret 접근 ❌ (컴파일 에러)
    }

    inner class Inner {         // 내부: Outer 인스턴스에 묶임
        fun reveal() = secret   // 외부 private 멤버 접근 ✅
        fun outerRef() = this@Outer   // 외부 인스턴스 참조: this@라벨
    }
}

val n = Outer.Nested()          // 외부 인스턴스 없이 생성
val i = Outer().Inner()         // 반드시 외부 인스턴스를 통해 생성
```

> **베스트 프랙티스:** 외부를 참조할 필요가 없으면 **기본 중첩(`class`)을 써라.** `inner`는 외부 인스턴스를 붙들고 있어 메모리 누수·결합도 증가의 원인이 된다. *"정말 외부 상태가 필요할 때만 `inner`."*
>
> Java에서 넘어온 사람이 가장 자주 하는 실수: Java의 non-static inner class 습관으로 무심코 외부를 참조하려다 막힌다 — Kotlin 기본은 외부 참조가 **없다**는 걸 기억.

## 5. object — 싱글톤 · companion · 익명 클래스

`object` 키워드는 세 가지로 쓰인다.

### 5-1. object 선언 (싱글톤)
인스턴스가 단 하나. 스레드 안전하게 지연 초기화된다.

```kotlin
object Registry {                       // 싱글톤
    private val items = mutableListOf<String>()
    fun add(item: String) { items.add(item) }
}
Registry.add("shoes")                   // 인스턴스화 없이 바로 사용
```

### 5-2. companion object (정적 멤버 대용)
Kotlin엔 `static`이 없다. 클래스 레벨 멤버·팩토리는 동반 객체로.

```kotlin
class User private constructor(val name: String) {
    companion object {
        const val MAX_NAME = 20             // 상수 (SCREAMING_SNAKE_CASE)
        fun of(name: String): User = User(name)   // 팩토리 함수
    }
}
User.of("kim")                          // 클래스명으로 직접 호출
```

### 5-3. object expression (익명 클래스)
인터페이스/클래스를 **즉석에서 구현**한 이름 없는 객체. Java의 anonymous class에 해당.

```kotlin
// 인터페이스 즉석 구현
val comparator = object : Comparator<Int> {
    override fun compare(a: Int, b: Int) = a - b
}

// 여러 타입 동시 구현 + 추가 상태도 가능 (Java 익명 클래스보다 유연)
val handler = object : Runnable, AutoCloseable {
    var count = 0
    override fun run() { count++ }
    override fun close() { /* ... */ }
}
```

| 구분 | 인스턴스 수 | 이름 | 용도 |
| :--- | :--- | :--- | :--- |
| `object Name { }` | 1개 (싱글톤) | 있음 | 전역 단일 객체 |
| `companion object` | 클래스당 1개 | (선택) | 정적 멤버·팩토리 |
| `object : Type { }` | 표현식마다 생성 | 없음 | 일회성 구현체 |

> **람다 우선:** 함수형 인터페이스(SAM) 하나만 구현한다면 익명 클래스보다 **람다**가 간결하다. `Runnable { ... }` 또는 `setOnClick { ... }`. 익명 클래스는 *여러 메서드/상태가 필요할 때* 쓴다.

## 6. 가시성 한정자 (Visibility)

| 한정자 | 범위 | 비고 |
| :--- | :--- | :--- |
| `public` | 어디서나 (기본값) | 명시 생략 가능 |
| `private` | 선언된 클래스/파일 내부 | 가장 좁음 |
| `protected` | 선언 클래스 + 하위 클래스 | 최상위 선언엔 사용 불가 |
| `internal` | 같은 **모듈** 내부 | Java엔 없는 개념 |

> 기본은 **가장 좁게**. 외부에 꼭 열어야 하는 것만 `public`. 모듈 경계 안에서만 쓰는 API는 `internal`.

## 7. 베스트 프랙티스

### 7-1. 클래스 멤버 순서 (개인 컨벤션)
1. **속성 / `init` 블록**
2. **보조 생성자**
3. **메서드** — 알파벳순·가시성별이 아니라 **관련 로직끼리 그룹화**
4. **`companion object`**

- 인터페이스 구현 시 **인터페이스 정의 순서와 동일하게** 배치, private 헬퍼는 해당 구현 바로 옆에
- 오버로드 메서드는 **반드시 인접** 배치

### 7-2. 종류 선택 가이드
| 상황 | 선택 |
| :--- | :--- |
| 단순히 값을 묶어 나르기 | `data class` |
| 고정된 상태 몇 개 | `enum class` |
| 상태별로 다른 데이터를 담는 제한된 집합 | `sealed class` |
| 전역 단일 인스턴스 | `object` |
| 정적 멤버·팩토리 | `companion object` |
| 외부 참조 불필요한 중첩 타입 | `class` (중첩, **`inner` 아님**) |

### 7-3. 명명 규칙
- 클래스/객체: **UpperCamelCase** 명사 (`User`, `PaymentReceipt`)
- 서비스: **동사+명사** (`OrderProcessor`, `UserCreator`) — `Manager`/`Handler`/`Wrapper` 같은 모호한 이름 지양
- DTO 접미어로 역할 구분: `Data`(범용) · `Info`(읽기전용 요약) · `Request`/`Response`(API) · `Dto`(레이어 간) · `Payload`(이벤트) · `Command`(액션) · `Result`(실행 결과)
- 설정: `~Config`, `~Properties` / 테스트 더블: `Fake~`, `Mock~`, `Stub~`

### 7-4. 그 외
- **상속은 신중히** — Kotlin 클래스는 기본 `final`. 상속이 꼭 필요할 때만 `open`. 가능하면 **상속보다 컴포지션**.
- **KDoc** — `public` 클래스/함수는 **반드시** 작성(무엇을+왜). `private`는 선택.
- **불변 우선** — 프로퍼티는 가능한 `val`. 가변 상태(`var`)는 최소화.
- **주생성자에서 프로퍼티 선언** — 보일러플레이트를 줄이고 의도를 명확히.

```kotlin
/**
 * 주문을 받아 결제까지 처리한다. 한정판 대량 주문을 안정적으로 다루기 위해 분리되었다.
 */
class OrderProcessor(
    private val payment: PaymentGateway,   // 의존성은 주생성자로 (불변 val)
) {
    // 1) 속성/init  2) 보조생성자  3) 메서드(관련 로직끼리)  4) companion
    fun process(order: Order): Result { /* ... */ }

    companion object {
        const val MAX_BATCH = 100
    }
}
```
