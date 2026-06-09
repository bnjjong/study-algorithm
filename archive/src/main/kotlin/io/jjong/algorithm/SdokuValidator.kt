package io.jjong.algorithm

import kotlin.math.sqrt

/**
 * 부분적으로 채워진 스도쿠 보드(0은 빈 칸)가 유효한지 검사한다.
 * 행/열/격자(region) 각각에서 0을 제외한 값의 중복이 없어야 유효하다.
 */
object SdokuValidator {
    fun isValid(partialAssignment: List<List<Int>>): Boolean {
        // 행 제한
        println("Start row validation")
        for (i in partialAssignment.indices) {
            if (hasDuplicate(partialAssignment, i, i + 1, 0, partialAssignment.size)) {
                return false
            }
        }
        println("Completed row validation.")

        // 열 제한
        println("Start column validation")
        for (i in partialAssignment.indices) {
            if (hasDuplicate(partialAssignment, 0, partialAssignment.size, i, i + 1)) {
                return false
            }
        }
        println("Completed column validation.")

        // 격자(region) 제한
        val regionSize = sqrt(partialAssignment.size.toDouble()).toInt()
        for (i in 0 until regionSize) {
            for (j in 0 until regionSize) {
                if (hasDuplicate(
                        partialAssignment,
                        regionSize * i, regionSize * (i + 1),
                        regionSize * j, regionSize * (j + 1),
                    )
                ) {
                    return false
                }
            }
        }
        return true
    }

    private fun hasDuplicate(
        partialAssignment: List<List<Int>>,
        startRow: Int,
        endRow: Int,
        startCol: Int,
        endCol: Int,
    ): Boolean {
        val numbers = HashSet<Int>()
        for (i in startRow until endRow) {
            println("start row : $i")
            for (j in startCol until endCol) {
                val value = partialAssignment[i][j]
                print("$value, ")
                if (value != 0 && numbers.contains(value)) {
                    return true
                }
                numbers.add(value)
            }
            println()
        }
        return false
    }
}

fun main() {
    val partialAssignment = listOf(
        listOf(1, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(2, 1, 3, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(3, 2, 0, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
        listOf(0, 2, 3, 4, 0, 0, 0, 0, 0),
    )
    val valid = SdokuValidator.isValid(partialAssignment)
    println("is valid : $valid")
}
