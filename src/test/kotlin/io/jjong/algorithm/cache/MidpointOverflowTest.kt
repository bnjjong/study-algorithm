package io.jjong.algorithm.cache

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * 이진 탐색의 중간값 관용구 `(lo + hi) ushr 1` 이 왜 오버플로에 안전한지 비트 단위로 검증한다.
 *
 * upperBound/이진 탐색에서 `lo`와 `hi`가 둘 다 크면 `lo + hi`가 Int 범위를 넘어 음수로 뒤집히는데,
 * 이때 `/ 2`·`shr`은 음수 인덱스를 내 깨지고 `ushr`만 올바른 중간값을 복구한다 — 그 차이를 고정한다.
 *
 * 주의: Kotlin에서 `shl`/`shr`/`ushr`은 중위 함수라 우선순위가 `+`보다 **낮다**.
 * `1 shl 30 + 1`은 `1 shl 31`로 해석되므로, 합은 반드시 변수로 분리해 계산한다.
 */
class MidpointOverflowTest {

    @Test
    fun `작은 양수에서는 세 연산 결과가 모두 같다`() {
        val sum = 13
        val sum1 = sum ushr 1
        val sum2 = sum shr 1
        println("sum ${sum.toBitString()}")
        println("sum1: ${sum1.toBitString()}")
        println("sum2: ${sum2.toBitString()}")
        // 0000...0000 1101, 부호 비트 0
        assertEquals(6, sum ushr 1)
        assertEquals(6, sum shr 1)
        assertEquals(6, sum / 2)           // 부호 비트가 0이라 셋 다 동일
    }

    @Test
    fun `lo와 hi의 합이 최댓값을 넘으면 음수로 오버플로한다`() {
        val lo = 1 shl 30                  // 2^30 = 1,073,741,824
        val hi = 1 shl 30
        val sum = lo + hi                  // 2^31 → Int 범위 초과
        println("lo: ${lo.toBitString()}")
        println("hi: ${hi.toBitString()}")
        println("sum: ${sum.toBitString()}")
        println("sum: ${(sum ushr 1).toBitString() }")
        assertTrue(lo > 0 && hi > 0)       // 분명 양수 둘인데
        assertTrue(sum < 0)                // 더하면 음수가 된다
        assertEquals(Int.MIN_VALUE, sum)   // 정확히 -2,147,483,648
    }

    @Test
    fun `오버플로 상황에서 나눗셈과 shr은 음수를 낸다`() {
        val lo = 1 shl 30
        val hi = 1 shl 30
        val sum = lo + hi
        assertEquals(-(1 shl 30), sum / 2)     // -1,073,741,824 (음수 인덱스 💥)
        assertEquals(-(1 shl 30), sum shr 1)   // shr은 부호 비트를 복사 → 여전히 음수 💥
    }

    @Test
    fun `ushr은 오버플로에도 올바른 중간값을 복구한다`() {
        val lo = 1 shl 30
        val hi = 1 shl 30
        val mid = (lo + hi) ushr 1
        assertEquals(1 shl 30, mid)        // 2^30 = lo와 hi의 진짜 중간값
        assertTrue(mid in lo..hi)          // 중간값은 [lo, hi] 안에 있어야 한다
    }

    @Test
    fun `차이 기반 중간값 계산도 오버플로에 안전하다`() {
        val lo = 1 shl 30
        val hi = 1 shl 30
        val mid = lo + (hi - lo) / 2        // hi - lo 는 절대 오버플로하지 않는다
        assertEquals(1 shl 30, mid)        // ushr 관용구와 동일한 결과
    }

    @Test
    fun `ushr은 부호 비트를 0으로 채워 민다`() {
        val bits = Int.MIN_VALUE           // 1000 0000 ... 0000 (부호 비트만 1)
        assertEquals(1 shl 30, bits ushr 1)    // 0100...0000 = 2^30 (0으로 채움 → 양수)
        assertEquals(-(1 shl 30), bits shr 1)  // 1100...0000 = -2^30 (부호 1을 복사)
    }

    @Test
    fun `Int를 32비트 2진 패턴으로 찍어 본다`() {
        val sum = (1 shl 30) + (1 shl 30)  // 오버플로된 합 (= Int.MIN_VALUE)
        // (값, 설명) 목록 — 손으로 그린 패턴과 실제 출력을 눈으로 대조한다
        val cases = listOf(
            13 to "작은 양수",
            (1 shl 30) to "2^30  (lo = hi)",
            sum to "2^30 + 2^30 → 오버플로",
            sum / 2 to "(합) / 2   → 음수 💥",
            sum shr 1 to "(합) shr 1 → 음수 💥",
            sum ushr 1 to "(합) ushr 1 → 복구 ✅",
        )
        println("값(10진)         |  32비트 2진 패턴                        |  설명")
        println("-".repeat(78))
        for ((value, desc) in cases) {
            println("%-15d  |  %s  |  %s".format(value, value.toBitString(), desc))
        }

        // toString(2)와의 차이도 함께 — 음수에서 갈린다
        val neg = -8
        println()
        println("[함정] (-8).toString(2)           = ${neg.toString(2)}        ← 부호+절대값, 비트 아님")
        println("[정답] (-8).toUInt().toString(2)  = ${neg.toUInt().toString(2)}")
        println("[정답] Integer.toBinaryString(-8) = ${Integer.toBinaryString(neg)}")
    }
}

/** Int를 32비트 2의 보수 2진 문자열로, 4비트씩 끊어 만든다(상위 0까지 표시). */
private fun Int.toBitString(): String =
    toUInt().toString(2).padStart(32, '0').chunked(4).joinToString(" ")
