package io.jjong.algorithm

import kotlin.math.abs

/**
 * 정수의 자릿수를 뒤집은 값을 반환한다. 부호는 유지한다. 예: 123 → 321, -123 → -321.
 * 뒤집는 과정에서 자릿수가 늘 수 있으므로 [Long]으로 계산한다.
 */
object NumberReverse {
    const val CIPHER = 10

    fun reverse(x: Int): Long {
        var result = 0L
        var xRemaining = abs(x).toLong()
        while (xRemaining != 0L) {
            result = result * CIPHER + xRemaining % 10 // 자릿수를 올리기 위해 10을 곱한다.
            xRemaining /= CIPHER

            println("resut = $result")
            println("remain = $xRemaining")
        }
        return if (x < 0) -result else result
    }
}

fun main() {
    val input = 23131237
    val output = NumberReverse.reverse(input)
    println(output)
}
