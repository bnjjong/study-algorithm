# Kotlin Map 주요 함수 정리

`Map`은 키(Key)와 값(Value)의 쌍으로 이루어진 컬렉션입니다. 각 키는 유일해야 합니다.

## 1. 생성 및 기본 정보
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `mapOf(vararg pairs: Pair<K, V>)` | `Pair<K, V>...` | `Map<K, V>` | 읽기 전용 Map 생성 | `mapOf("a" to 1)` |
| `mutableMapOf(vararg pairs: Pair<K, V>)` | `Pair<K, V>...` | `MutableMap<K, V>` | 수정 가능한 Map 생성 | |
| `hashMapOf(...)` | `Pair<K, V>...` | `HashMap<K, V>` | HashMap 생성 | 순서 보장 안 함 |
| `size` | (프로퍼티) | `Int` | 쌍의 개수 | |
| `keys` | (프로퍼티) | `Set<K>` | 모든 키의 Set 반환 | |
| `values` | (프로퍼티) | `Collection<V>` | 모든 값의 Collection 반환 | |
| `entries` | (프로퍼티) | `Set<Map.Entry<K, V>>` | 모든 키-값 쌍의 Set 반환 | |

## 2. 요소 접근 및 검색
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `get(key)` / `[key]` | `K` | `V?` | 특정 키에 해당하는 값 반환 | 없으면 null |
| `getOrDefault(key, default)` | `K`, `V` | `V` | 키가 없으면 기본값 반환 | |
| `getOrElse(key) { ... }` | `K`, `() -> V` | `V` | 키가 없으면 람다 결과 반환 | |
| `getValue(key)` | `K` | `V` | 키의 값 반환 | 없으면 예외 |
| `containsKey(key)` | `K` | `Boolean` | 특정 키 포함 여부 | `key in map` |
| `containsValue(value)` | `V` | `Boolean` | 특정 값 포함 여부 | |

## 3. 필터링 및 변환
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `filterKeys { ... }` | `(K) -> Boolean` | `Map<K, V>` | 키 조건에 따른 필터링 | |
| `filterValues { ... }` | `(V) -> Boolean` | `Map<K, V>` | 값 조건에 따른 필터링 | |
| `mapKeys { ... }` | `(Map.Entry<K, V>) -> R` | `Map<R, V>` | 키를 변환한 새 Map 생성 | |
| `mapValues { ... }` | `(Map.Entry<K, V>) -> R` | `Map<K, R>` | 값을 변환한 새 Map 생성 | |
| `toList()` | (없음) | `List<Pair<K, V>>` | Pair 리스트로 변환 | |

## 4. MutableMap 전용 (수정)
| 함수 | 입력 | 출력 | 설명 | 비고 |
| :--- | :--- | :--- | :--- | :--- |
| `put(key, value)` | `K`, `V` | `V?` | 쌍 추가 또는 값 갱신 | 이전 값 반환, `map[key] = value` |
| `putAll(otherMap)` | `Map<K, V>` | `Unit` | 다른 Map의 모든 데이터 추가 | |
| `remove(key)` | `K` | `V?` | 특정 키의 쌍 삭제 | 삭제된 값 반환 |
| `getOrPut(key) { ... }` | `K`, `() -> V` | `V` | 키가 없으면 계산해서 넣고 반환 | 자주 쓰임 |
| `clear()` | (없음) | `Unit` | 모든 데이터 삭제 | |
