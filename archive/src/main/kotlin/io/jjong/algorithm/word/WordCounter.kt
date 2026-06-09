package io.jjong.algorithm.word

/**
 * 문자열에서 각 문자의 등장 횟수를 센다. 맵 누적 방식과 grouping 방식 두 가지를 제공한다.
 */
object WordCounter {
    /** 맵에 문자를 키, 빈도를 값으로 누적한다. */
    fun countDuplicateCharacters(str: String): Map<Char, Int> {
        val result = HashMap<Char, Int>()
        for (c in str.toCharArray()) {
            result[c] = (result[c] ?: 0) + 1
        }
        return result
    }

    /** grouping으로 문자별 개수를 센다. */
    fun countDuplicateCharacters2(str: String): Map<Char, Long> {
        return str.toList()
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, count) -> count.toLong() }
    }
}

fun main() {
    val str = "aaabbbccceeefff12345"

    println(WordCounter.countDuplicateCharacters(str))
    println(WordCounter.countDuplicateCharacters2(str))
}
