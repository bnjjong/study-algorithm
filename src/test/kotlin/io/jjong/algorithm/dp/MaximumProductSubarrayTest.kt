package io.jjong.algorithm.dp

import kotlin.test.Test
import kotlin.test.assertEquals

class MaximumProductSubarrayTest {

    @Test
    fun `기본 예제`() {
        assertEquals(6, maxProduct(intArrayOf(2, 3, -2, 4)))
    }

    @Test
    fun `0이 포함되면 끊고 다시 시작`() {
        assertEquals(0, maxProduct(intArrayOf(-2, 0, -1)))
    }

    @Test
    fun `음수 두 개가 만나 양수가 커진다`() {
        assertEquals(24, maxProduct(intArrayOf(-2, 3, -4)))
    }

    @Test
    fun `단일 음수 원소`() {
        assertEquals(-2, maxProduct(intArrayOf(-2)))
    }

    @Test
    fun `단일 양수 원소`() {
        assertEquals(3, maxProduct(intArrayOf(3)))
    }

    @Test
    fun `홀수 개의 음수`() {
        // 음수 3개(홀수) → 끝의 음수 하나를 떼어 짝수 개로: [-3,-4]=12
        assertEquals(12, maxProduct(intArrayOf(-2, -3, -4)))
    }

    @Test
    fun `0을 사이에 둔 큰 곱`() {
        assertEquals(6, maxProduct(intArrayOf(2, 3, 0, -2, -3)))
    }
}
