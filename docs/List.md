# Kotlin List 주요 함수 정리

`List`는 순서가 있는 요소들의 컬렉션입니다. Kotlin에서는 읽기 전용 `List`와 수정 가능한 `MutableList`가 구분됩니다.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `listOf(vararg elements: T)` | `T...` | `List<T>` | 읽기 전용 리스트 생성 | |
| `mutableListOf(vararg elements: T)` | `T...` | `MutableList<T>` | 수정 가능한 리스트 생성 | |
| `List(size) { index -> ... }` | `Int`, `(Int) -> T` | `List<T>` | 크기와 초기화 로직으로 생성 | |
| `size` | (프로퍼티) | `Int` | 리스트의 요소 개수 | |
| `isEmpty()` | (없음) | `Boolean` | 리스트가 비어있는지 확인 | |
| `isNotEmpty()` | (없음) | `Boolean` | 리스트가 비어있지 않은지 확인 | |
| `indices` | (프로퍼티) | `IntRange` | 인덱스 범위 반환 (`0..size-1`) | |
| `lastIndex` | (프로퍼티) | `Int` | 마지막 요소의 인덱스 | `size - 1` |

## 2. 요소 접근 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(index)` / `[index]` | `Int` | `T` | 해당 인덱스의 요소 접근 | 범위 벗어나면 예외 |
| `getOrNull(index)` | `Int` | `T?` | 범위를 벗어나면 null 반환 | 안전한 접근 |
| `first()` / `last()` | (없음) | `T` | 첫 번째 / 마지막 요소 반환 | 빈 리스트면 예외 발생 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 첫 번째 / 마지막 요소 반환 | 빈 리스트면 null |
| `indexOf(element)` | `T` | `Int` | 요소의 첫 번째 인덱스 반환 | 없으면 -1 |
| `contains(element)` | `T` | `Boolean` | 특정 요소 포함 여부 | `element in list` |

## 3. 필터링 및 변환
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `filter { ... }` | `(T) -> Boolean` | `List<T>` | 조건에 맞는 요소만 추출 | |
| `map { ... }` | `(T) -> R` | `List<R>` | 요소를 특정 형태로 변환 | |
| `distinct()` | (없음) | `List<T>` | 중복 요소 제거 | |
| `sorted()` | (없음) | `List<T>` | 오름차순 정렬된 새 리스트 | `Comparable` 필요 |
| `sortedDescending()` | (없음) | `List<T>` | 내림차순 정렬된 새 리스트 | |
| `reversed()` | (없음) | `List<T>` | 순서가 반전된 새 리스트 반환 | |
| `shuffled()` | (없음) | `List<T>` | 무작위로 섞인 새 리스트 반환 | |

## 4. MutableList 전용 (수정)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `add(element)` | `T` | `Boolean` | 요소 추가 | 항상 true 반환 |
| `add(index, element)` | `Int`, `T` | `Unit` | 특정 위치에 요소 삽입 | |
| `remove(element)` | `T` | `Boolean` | 특정 요소 삭제 | 성공 시 true |
| `removeAt(index)` | `Int` | `T` | 특정 인덱스의 요소 삭제 | 삭제된 요소 반환 |
| `set(index, element)` | `Int`, `T` | `T` | 특정 위치의 요소 교체 | 이전 요소 반환, `list[index] = value` |
| `clear()` | (없음) | `Unit` | 모든 요소 삭제 | |
| `sort()` | (없음) | `Unit` | 리스트 자체를 정렬 | 원본 변경, 반환값 없음 |
