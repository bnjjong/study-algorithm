package io.jjong.algorithm.mock

/**
 * [D3 모의 · 문제 1] Circuit Breaker (장애 격리)
 *
 * 외부 의존성(추천/검색 API 등)이 흔들릴 때, 실패가 반복되는 호출을 빠르게 차단해
 * 장애가 시스템 전체로 번지는 것을 막는 "회로 차단기".
 *
 * 상태 전이
 * - CLOSED    : 정상. 호출을 통과시킨다. 연속 실패가 [failureThreshold]에 도달하면 → OPEN.
 * - OPEN      : 차단. 호출을 즉시 거부(빠른 실패). OPEN 전환 후 [openMillis]가 지나면 → HALF_OPEN.
 * - HALF_OPEN : 시험. 호출 하나만 통과시켜 본다. 성공하면 → CLOSED(복구), 실패하면 → OPEN(재차단).
 *
 * 연산
 * - [call]  : 보호된 작업 [operation]을 실행한다. 차단(OPEN) 상태면 실행하지 않고 거부한다.
 *             [operation]이 예외를 던지면 '실패'로 집계한다.
 * - [state] : 현재 유효 상태. (OPEN이라도 [openMillis]가 지났으면 HALF_OPEN으로 본다)
 *
 * 시간은 테스트 가능하도록 [now](현재 시각 ms 공급자)로 주입한다 — 실제 시계 대신
 * 가짜 시간을 넣어 결정적으로 검증하기 위함.
 *
 * 무신사 Search&Rec: 추천/검색 백엔드 장애 시 지연·실패 전파를 끊는 시나리오.
 */
class CircuitBreaker(
    private val failureThreshold: Int,
    private val openMillis: Long,
    private val now: () -> Long,
) {
    private var failureCount = 0
    private var currentState: State = State.CLOSED
    private var openedAt: Long = 0

    /** 회로 차단기의 세 가지 상태. */
    enum class State { CLOSED, OPEN, HALF_OPEN }

    // TODO: 어떤 가변 상태가 필요할까? 직접 정해보자.
    //   힌트 후보 — 현재 상태 / 연속 실패 횟수 / OPEN으로 전환된 시각

    private fun recordSuccess() {
        this.failureCount = 0
        this.currentState = State.CLOSED
    }

    /** 보호된 작업을 실행한다. 차단 상태면 실행 없이 거부한다. */
    fun <T> call(operation: () -> T): T {
        if (currentState == State.OPEN && now() - openedAt >= openMillis) {
            currentState = State.HALF_OPEN
        }
        if  (currentState == State.OPEN) throw Exception("서킷 브레이커 동작중. 잠시후 시도 하세요.")
        return try {
            val result = operation()
            recordSuccess()
            result
        } catch (e: Exception) {
            recordFailure()
            throw e
        }


    }

    private fun recordFailure() {
        this.failureCount++
        if (currentState == State.HALF_OPEN || failureCount >= failureThreshold) {
            this.currentState = State.OPEN
            this.openedAt = now()
        }
    }

    /** 현재 유효 상태를 반환한다. */
    fun state(): State {
        if (currentState == State.OPEN && (openedAt+openMillis) <= now()) {
            return State.HALF_OPEN
        }
        return currentState
    }
}
