package io.jjong.algorithm

/**
 * 자릿수 리스트로 표현된 수에 [plusNum](10 이하)을 더하고 받아올림을 처리한다.
 * 예: [1,9,9,9,6] + 10 → [2,0,0,0,6].
 */
object ArrayNumberCalculator {
    fun plusArray(numbers: MutableList<Int>, plusNum: Int): MutableList<Int> {
        require(plusNum <= 10) { "plus number is not over than 10." }

        val lastIndex = numbers.size - 1
        numbers[lastIndex] = numbers[lastIndex] + plusNum // 마지막 자리에 더한다.
        print("first num : ")
        printArray(numbers)

        // 받아올림 처리
        var i = lastIndex
        while (i > 0 && numbers[i] >= 10) {
            numbers[i] = numbers[i] % 10        // 나머지 자리
            numbers[i - 1] = numbers[i - 1] + 1 // 앞자리 올림
            printArray(numbers)
            --i
        }
        // 앞자리 올림
        if (numbers[0] >= 10) {
            println("앞 자리 올림.")
            numbers[0] = 1
            numbers.add(1, 0)
        }
        return numbers
    }

    fun printArray(numbers: List<Int>) {
        numbers.forEach { print(it) }
        println()
    }
}

fun main() {
    val plusNum = 10
    val numbers = mutableListOf(1, 9, 9, 9, 6)
    val result = ArrayNumberCalculator.plusArray(numbers, plusNum)

    println("result num : ")
    result.forEach { println(it) }
}
