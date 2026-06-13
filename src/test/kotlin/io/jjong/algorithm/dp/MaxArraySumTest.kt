package io.jjong.algorithm.dp

import kotlin.test.Test
import kotlin.test.assertEquals

class MaxArraySumTest {

    @Test
    fun `예제 1 — 비인접 최대합`() {
        assertEquals(13, maxArraySum(intArrayOf(3, 7, 4, 6, 5), true)) // 7 + 6
    }

    @Test
    fun `예제 2`() {
        assertEquals(11, maxArraySum(intArrayOf(2, 1, 5, 8, 4))) // 2 + 5 + 4
    }

    @Test
    fun `음수가 섞여도 큰 합을 고른다`() {
        assertEquals(15, maxArraySum(intArrayOf(3, 5, -7, 8, 10))) // 5 + 10
    }

    @Test
    fun `음수는 건너뛴다`() {
        assertEquals(8, maxArraySum(intArrayOf(-2, 1, 3, -4, 5))) // 3 + 5
    }

    @Test
    fun `단일 원소`() {
        assertEquals(5, maxArraySum(intArrayOf(5)))
    }

    @Test
    fun `두 원소는 인접이라 큰 쪽 하나만`() {
        assertEquals(5, maxArraySum(intArrayOf(5, 1)))
    }

    @Test
    fun `트레이스 데모 — 헷갈렸던 케이스를 단계별로 출력`() {
        // 콘솔에서 take·skip이 한 칸씩 어떻게 흐르는지 눈으로 따라가 보세요.
        assertEquals(15, maxArraySum(intArrayOf(3, 5, -7, 8, 10), trace = true))
    }
}
