package io.jjong.algorithm.word

/**
 * 유니코드 코드포인트 단위로 각 문자의 등장 횟수를 센다.
 * 이모지·서로게이트 쌍(charCount == 2)을 한 글자로 올바르게 집계한다.
 */
object WordCounterUniCode {
    /** 코드포인트를 직접 순회하며 서로게이트 쌍을 건너뛰어 집계한다. */
    fun countDuplicateCharacters(str: String): Map<String, Int> {
        val result = HashMap<String, Int>()
        var i = 0
        while (i < str.length) {
            val cp = str.codePointAt(i) // 해당 위치의 문자를 코드포인트(정수)로
            val ch = String(Character.toChars(cp))
            println("ch : $ch, cp : $cp, charCount : ${Character.charCount(cp)}")
            if (Character.charCount(cp) == 2) { // 2는 대리 쌍(surrogate pair)을 뜻한다.
                i++
            }
            result[ch] = (result[ch] ?: 0) + 1
            i++
        }
        return result
    }

    /** 코드포인트 스트림을 grouping으로 집계한다. */
    fun countDuplicateCharacters2(str: String): Map<String, Long> {
        return str.codePoints().toArray()
            .map { String(Character.toChars(it)) }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, count) -> count.toLong() }
    }
}

fun main() {
    val str = "aaabbbccceeefff12345👍🔥國小心♥♥♥♥♥"

    println(WordCounterUniCode.countDuplicateCharacters(str))
    println(WordCounterUniCode.countDuplicateCharacters2(str))
}
