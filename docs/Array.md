# Kotlin Array 주요 함수 및 특징 정리

Kotlin에서 `Array`는 고정된 크기를 가지며, 내부 요소의 변경이 가능한(Mutable) 자료구조입니다.

## 1. 변수 선언 및 초기화
Kotlin에서는 `val`(읽기 전용 참조) 또는 `var`(변경 가능한 참조) 키워드를 사용하여 배열을 선언합니다.

```kotlin
// 1. 초기값을 직접 지정하여 생성
val numbers = arrayOf(1, 2, 3, 4, 5) // Array<Int>
val strings = arrayOf("A", "B", "C") // Array<String>

// 2. 크기만 지정하고 람다로 초기화 (추천)
val squares = Array(5) { i -> i * i } // [0, 1, 4, 9, 16]

// 3. 특정 타입의 빈 배열 생성
val empty = emptyArray<String>()

// 4. Null을 허용하는 배열
val nulls = arrayOfNulls<Int>(3) // [null, null, null]

// 5. 기본 타입 전용 배열 (박싱 오버헤드 없음)
val intArray = intArrayOf(1, 2, 3)
val doubleArray = DoubleArray(5) { 0.0 }
```

## 2. 생성 및 기본 정보
| 함수/프로퍼티 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `arrayOf(vararg elements: T)` | `T...` | `Array<T>` | 특정 요소들을 포함하는 배열 생성 | `arrayOf(1, 2, 3)` |
| `Array(size) { index -> ... }` | `Int`, `(Int) -> T` | `Array<T>` | 크기와 초기화 로직으로 배열 생성 | `Array(5) { it * 2 }` |
| `emptyArray<T>()` | (없음) | `Array<T>` | 빈 배열 생성 | |
| `arrayOfNulls<T>(size)` | `Int` | `Array<T?>` | null로 초기화된 배열 생성 | |
| `size` | (프로퍼티) | `Int` | 배열의 크기 | 고정 크기 |
| `indices` | (프로퍼티) | `IntRange` | 인덱스 범위 반환 (`0..size-1`) | |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 요소의 인덱스 | `size - 1` |

## 3. 요소 접근 및 수정
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(index)` / `[index]` | `Int` | `T` | 해당 인덱스의 요소 접근 | 범위 벗어나면 예외 |
| `set(index, value)` / `[index] = value` | `Int`, `T` | `Unit` | 해당 인덱스의 요소 수정 | |
| `first()` / `last()` | (없음) | `T` | 첫 번째 / 마지막 요소 반환 | 빈 배열이면 예외 |
| `getOrNull(index)` | `Int` | `T?` | 범위를 벗어나면 null 반환 | 안전한 접근 |
| `fill(element)` | `T` | `Unit` | 모든 요소를 특정 값으로 채움 | 원본 변경 |

## 4. 정렬 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `sort()` | (없음) | `Unit` | 배열 자체를 오름차순 정렬 | 원본 변경 |
| `sortDescending()` | (없음) | `Unit` | 배열 자체를 내림차순 정렬 | 원본 변경 |
| `sortedArray()` | (없음) | `Array<T>` | 정렬된 **새 배열** 반환 | 원본 유지 |
| `indexOf(element)` | `T` | `Int` | 요소의 첫 인덱스 반환 | 없으면 -1 |
| `contains(element)` | `T` | `Boolean` | 특정 요소 포함 여부 | `element in array` |

## 5. 변환 및 기타
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `toList()` | (없음) | `List<T>` | 읽기 전용 리스트로 변환 | |
| `toMutableList()` | (없음) | `MutableList<T>` | 수정 가능한 리스트로 변환 | |
| `joinToString(separator)` | `CharSequence` | `String` | 요소를 문자열로 합침 | 기본 구분자 `", "` |
| `filter { ... }` | `(T) -> Boolean` | `List<T>` | 조건에 맞는 요소만 추출 | 결과는 `List` |
| `map { ... }` | `(T) -> R` | `List<R>` | 요소를 특정 형태로 변환 | 결과는 `List` |
| `copyOf()` | (없음) | `Array<T>` | 배열 복사 | |
| `reversedArray()` | (없음) | `Array<T>` | 순서가 반전된 새 배열 반환 | |

## 6. 기본 타입 전용 배열 (Primitive Arrays)
성능 최적화를 위해 박싱(Boxing) 오버헤드가 없는 전용 배열 클래스가 존재합니다.
- `IntArray`, `LongArray`, `BooleanArray`, `DoubleArray`, `CharArray` 등
- 생성: `intArrayOf(1, 2, 3)`, `IntArray(5) { 0 }`
- 일반 `Array<Int>`보다 메모리 사용량과 속도 면에서 유리합니다.
