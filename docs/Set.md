# Kotlin Set 주요 함수 정리

`Set`은 중복을 허용하지 않는 고유한 요소들의 컬렉션입니다. 순서가 보장되지 않는 경우가 많습니다(단, `LinkedHashSet`은 삽입 순서 보장).

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `setOf(vararg elements: T)` | `T...` | `Set<T>` | 읽기 전용 Set 생성 | |
| `mutableSetOf(vararg elements: T)` | `T...` | `MutableSet<T>` | 수정 가능한 Set 생성 | |
| `hashSetOf(vararg elements: T)` | `T...` | `HashSet<T>` | HashSet 생성 | 순서 보장 안 함 |
| `linkedSetOf(vararg elements: T)` | `T...` | `LinkedHashSet<T>` | LinkedHashSet 생성 | 삽입 순서 보장 |
| `size` | (프로퍼티) | `Int` | 요소 개수 | |
| `isEmpty()` | (없음) | `Boolean` | 비어있는지 확인 | |
| `isNotEmpty()` | (없음) | `Boolean` | 비어있지 않은지 확인 | |

## 2. 요소 검사 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `contains(element)` | `T` | `Boolean` | 요소 포함 여부 | `in` 연산자로 사용 가능 |
| `containsAll(collection)` | `Collection<T>` | `Boolean` | 모든 요소 포함 여부 | |
| `first()` / `last()` | (없음) | `T` | 첫 번째 / 마지막 요소 반환 | 순서가 있는 경우 의미 있음, 빈 Set이면 예외 |
| `firstOrNull()` / `lastOrNull()` | (없음) | `T?` | 빈 Set이면 null | |

## 3. 집합 연산
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `intersect(other)` | `Iterable<T>` | `Set<T>` | 교집합 | 양쪽에 있는 요소 |
| `union(other)` | `Iterable<T>` | `Set<T>` | 합집합 | 중복 제거 |
| `subtract(other)` | `Iterable<T>` | `Set<T>` | 차집합 | this - other |

## 4. MutableSet 전용 (수정)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `add(element)` | `T` | `Boolean` | 요소 추가 | 이미 있으면 false, 새로 추가되면 true |
| `addAll(elements)` | `Collection<T>` | `Boolean` | 여러 요소 추가 | 변경 발생 시 true |
| `remove(element)` | `T` | `Boolean` | 요소 삭제 | 삭제 성공 시 true |
| `removeAll(elements)` | `Collection<T>` | `Boolean` | 여러 요소 삭제 | |
| `clear()` | (없음) | `Unit` | 모든 요소 삭제 | |
