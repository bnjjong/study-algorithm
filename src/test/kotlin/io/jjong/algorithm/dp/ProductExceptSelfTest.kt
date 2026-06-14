package io.jjong.algorithm.dp

import kotlin.test.Test
import kotlin.test.assertEquals

class ProductExceptSelfTest {

    private fun run(vararg nums: Int): List<Int> = productExceptSelf(nums).toList()

    @Test
    fun `기본 예제 1234`() {
        assertEquals(listOf(24, 12, 8, 6), run(1, 2, 3, 4))
    }

    @Test
    fun `0이 하나 있으면 그 자리만 곱이 남고 나머진 0`() {
        assertEquals(listOf(12, 0, 0), run(0, 4, 3)) // 4*3=12, 나머진 0을 포함해 0
    }

    @Test
    fun `0이 둘 이상이면 전부 0`() {
        assertEquals(listOf(0, 0, 0), run(0, 0, 3))
    }

    @Test
    fun `음수 포함`() {
        assertEquals(listOf(0, 0, 9, 0, 0), run(-1, 1, 0, -3, 3))
    }

    @Test
    fun `원소 둘`() {
        assertEquals(listOf(5, 2), run(2, 5)) // [5, 2]
    }
}
