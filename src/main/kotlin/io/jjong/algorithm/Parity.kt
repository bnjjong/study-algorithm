package io.jjong.algorithm

/**
 * 비트 패리티(1인 비트 개수의 홀짝)를 반환한다. 1이 홀수 개면 1, 짝수 개면 0.
 * 최하위 비트를 xor로 누적하며 우측 시프트로 비트를 하나씩 소거한다.
 */
object Parity {
    fun parity(x: Long): Short {
        var n = x
        var result = 0 // Short xor 연산은 Int를 반환하므로 Int로 누적 후 변환한다.
        while (n != 0L) {
            result = result xor (n and 1L).toInt() // 최하위 비트를 검사해 xor 누적
            println("x & 1 : ${n and 1L}, result : $result")
            n = n ushr 1 // x / 2^1
            println("x : $n")
        }
        return result.toShort()
    }
}

fun main() {
    val x = 220L
    val parity = Parity.parity(x)
    println("parity : $parity")
}
