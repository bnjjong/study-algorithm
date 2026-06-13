package io.jjong.algorithm.resilience

import io.jjong.algorithm.resilience.CircuitBreaker.State
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class CircuitBreakerTest {

    // 항상 실패하는 작업(외부 API 장애를 흉내).
    private val boom: () -> Int = { throw RuntimeException("boom") }

    @Test
    fun `임계 미만 연속 실패는 차단하지 않고, 도달하는 순간 OPEN 된다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }

        runCatching { cb.call(boom) }
        runCatching { cb.call(boom) }
        assertEquals(State.CLOSED, cb.state()) // 2번 실패 — 아직 임계(3) 미만

        runCatching { cb.call(boom) }          // 3번째 → 임계 도달
        assertEquals(State.OPEN, cb.state())
    }

    @Test
    fun `OPEN 상태면 작업을 실행하지 않고 즉시 거부한다 (fail fast)`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }
        repeat(3) { runCatching { cb.call(boom) } } // OPEN으로 전환
        assertEquals(State.OPEN, cb.state())

        var executed = false
        assertFailsWith<Exception> {
            cb.call { executed = true; "x" } // 실행되면 안 됨
        }
        assertFalse(executed) // 차단되어 작업 자체가 실행되지 않았다
    }

    @Test
    fun `OPEN 후 openMillis가 지나면 HALF_OPEN으로 본다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }
        repeat(3) { runCatching { cb.call(boom) } } // openedAt = 0, OPEN

        time = 4999
        assertEquals(State.OPEN, cb.state())      // 아직 안 지남

        time = 5000
        assertEquals(State.HALF_OPEN, cb.state()) // 경과 → 시험 가능
    }

    @Test
    fun `HALF_OPEN에서 시험 호출이 성공하면 CLOSED로 복구된다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }
        repeat(3) { runCatching { cb.call(boom) } }
        time = 5000 // HALF_OPEN

        val result = cb.call { "recovered" } // 시험 호출 성공
        assertEquals("recovered", result)
        assertEquals(State.CLOSED, cb.state())
    }

    @Test
    fun `HALF_OPEN에서 시험 호출이 실패하면 다시 OPEN 되고 차단 시간이 갱신된다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }
        repeat(3) { runCatching { cb.call(boom) } } // OPEN (openedAt = 0)
        time = 5000                                 // HALF_OPEN

        runCatching { cb.call(boom) }               // 시험 실패 → 재 OPEN (openedAt = 5000)
        assertEquals(State.OPEN, cb.state())

        time = 9999
        assertEquals(State.OPEN, cb.state())        // 새 차단 구간 아직 안 지남
        time = 10000
        assertEquals(State.HALF_OPEN, cb.state())   // openedAt(5000) + 5000 도달
    }

    @Test
    fun `중간에 한 번 성공하면 연속 실패 카운트가 리셋된다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }

        runCatching { cb.call(boom) } // 실패 1
        runCatching { cb.call(boom) } // 실패 2
        cb.call { "ok" }              // 성공 → 카운트 0으로 리셋
        runCatching { cb.call(boom) } // 실패 1 (다시 셈)
        runCatching { cb.call(boom) } // 실패 2

        assertEquals(State.CLOSED, cb.state()) // 연속 3이 아니므로 아직 차단 안 함
    }

    @Test
    fun `정상 작업은 결과를 그대로 반환하고 CLOSED를 유지한다`() {
        var time = 0L
        val cb = CircuitBreaker(failureThreshold = 3, openMillis = 5000) { time }

        assertEquals(2, cb.call { 1 + 1 })
        assertEquals(State.CLOSED, cb.state())
    }
}
