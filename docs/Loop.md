# Kotlin 반복문(Loop) 및 제어 흐름 정리

Kotlin에서 데이터를 순회하거나 반복 작업을 수행할 때 사용하는 `for`, `while` 문법과 관련 제어 기법들을 정리합니다.

## 1. for 문 기본 (Range & Collection)
| 형태 | 입력 | 출력 (반복 값) | 설명 | 예시 |
| :--- | :--- | :--- | :--- | :--- |
| `for (item in list)` | `Iterable<T>` | `T` | 리스트/배열의 각 요소 순회 | `for (x in items) { ... }` |
| `for (i in 1..5)` | `IntRange` | `Int` | 1부터 5까지 (5 포함) | `1, 2, 3, 4, 5` |
| `for (i in 1 until 5)` | `IntRange` | `Int` | 1부터 4까지 (5 미포함) | `1, 2, 3, 4` |
| `for (i in 5 downTo 1)` | `IntProgression` | `Int` | 5부터 1까지 감소 | `5, 4, 3, 2, 1` |
| `for (i in 1..10 step 2)` | `IntProgression` | `Int` | 2씩 건너뛰며 반복 | `1, 3, 5, 7, 9` |

## 2. 인덱스와 함께 순회
컬렉션이나 배열을 순회할 때 인덱스가 필요한 경우 사용합니다.

| 방법 | 입력 | 출력 | 설명 | 예시 |
| :--- | :--- | :--- | :--- | :--- |
| `withIndex()` | (없음) | `Iterable<IndexedValue<T>>` | 인덱스와 값을 동시에 추출 | `for ((index, value) in list.withIndex())` |
| `indices` | (프로퍼티) | `IntRange` | 인덱스 범위만 순회 | `for (i in array.indices)` |
| `forEachIndexed { i, v -> }` | `(Int, T) -> Unit` | `Unit` | 함수형 인덱스 순회 | |

## 3. while & do-while
| 형태 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `while (condition)` | `Boolean` | `Unit` | 조건이 참인 동안 반복 | 조건 먼저 검사 |
| `do { ... } while (condition)` | `Boolean` | `Unit` | 최소 한 번 실행 후 조건 검사 | |

## 4. 흐름 제어 (Break, Continue, Labels)
Kotlin에서는 중첩 반복문에서 특정 루프를 지정해 탈출할 수 있는 Label 기능을 제공합니다.

| 키워드 | 설명 |
| :--- | :--- |
| `break` | 가장 가까운 반복문 탈출 |
| `continue` | 가장 가까운 반복문의 다음 단계로 이동 |
| `label@` / `break@label` | 지정한 라벨 위치의 반복문을 탈출/계속 |

### Label 예시
```kotlin
loop@ for (i in 1..3) {
    for (j in 1..3) {
        if (i == 2 && j == 2) break@loop // loop@ 가 붙은 바깥쪽 for문을 종료
        println("i: $i, j: $j")
    }
}
```

## 5. Functional Loops (forEach)
함수형 스타일로 컬렉션을 순회할 때 자주 사용합니다.

| 함수 | 입력 | 출력 | 설명 |
| :--- | :--- | :--- | :--- |
| `forEach { ... }` | `(T) -> Unit` | `Unit` | 각 요소에 대해 람다 실행 |
| `forEachIndexed { i, v -> }` | `(Int, T) -> Unit` | `Unit` | 인덱스와 함께 람다 실행 |
| `repeat(n) { ... }` | `Int`, `(Int) -> Unit` | `Unit` | 지정한 횟수만큼 반복 |

```kotlin
list.forEach { println(it) }
list.forEachIndexed { index, value -> println("$index: $value") }
repeat(3) { println("hello $it") }

// 인덱스 범위만 순회
for (i in array.indices) {
    println(array[i])
}

// 특정 인덱스부터 순회 (예: index 1부터)
for (i in array.indices.drop(1)) {
    println(array[i])
}
```
