package io.jjong.algorithm.queue

/**
 * 쉼표로 구분된 역폴란드 표기법(RPN) 수식을 평가한다.
 * 예: "3,4,+,2,*,1,+" → (3 + 4) * 2 + 1 = 15.
 */
object RPNExpression {
    fun eval(expression: String): Int {
        val results = ArrayDeque<Int>()
        val symbols = expression.split(",")

        for (s in symbols) {
            if (s.length == 1 && s in "+-*/") {
                val y = results.removeFirst()
                val x = results.removeFirst()
                when (s[0]) {
                    '+' -> results.addFirst(x + y)
                    '-' -> results.addFirst(x - y)
                    '*' -> results.addFirst(x * y)
                    '/' -> results.addFirst(x / y)
                    else -> throw IllegalArgumentException("Malformed RPN at :$s")
                }
            } else {
                // 숫자 타입
                results.addFirst(s.toInt())
            }
        }
        return results.removeFirst()
    }
}

fun main() {
    val expression = "3,4,+,2,*,1,+" // (3 + 4) * 2 + 1 = 15
    val result = RPNExpression.eval(expression)
    println("result : $result")
}
