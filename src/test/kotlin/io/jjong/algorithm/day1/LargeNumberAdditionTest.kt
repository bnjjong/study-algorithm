package io.jjong.algorithm.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class LargeNumberAdditionTest {

    @Test
    fun `한 자리 받아올림`() {
        assertEquals("13", addLargeNumbers("5", "8"))
    }

    @Test
    fun `Add Two Numbers 예시 342 + 465`() {
        assertEquals("807", addLargeNumbers("342", "465"))
    }

    @Test
    fun `받아올림 연쇄 99 + 1`() {
        assertEquals("100", addLargeNumbers("99", "1"))
    }

    @Test
    fun `자릿수가 다른 두 수`() {
        assertEquals("12412", addLargeNumbers("12345", "67"))
    }

    @Test
    fun `0 더하기`() {
        assertEquals("123", addLargeNumbers("0", "123"))
        assertEquals("0", addLargeNumbers("0", "0"))
    }

    @Test
    fun `Long 범위를 넘는 큰 수`() {
        // 20자리 9 + 1 = 1 뒤에 0이 스무 개
        assertEquals("100000000000000000000", addLargeNumbers("99999999999999999999", "1"))
        // 두 큰 수의 합 (각각 10^19)
        assertEquals(
            "20000000000000000000",
            addLargeNumbers("10000000000000000000", "10000000000000000000"),
        )
    }
}
