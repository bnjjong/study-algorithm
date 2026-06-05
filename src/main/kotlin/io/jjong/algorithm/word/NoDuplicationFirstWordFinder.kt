package io.jjong.algorithm.word

/**
 * 문자열에서 한 번만 등장하는 첫 문자를 찾는다. ASCII 배열 방식, LinkedHashMap 방식,
 * 코드포인트 방식 세 가지를 제공한다. 반복 문자만 있거나 비어 있으면 [Char.MIN_VALUE]/빈 문자열을 반환한다.
 */
object NoDuplicationFirstWordFinder {
    const val EXTENDED_ASCII_CODES = 256

    /** 확장 ASCII 인덱스 배열에 첫 등장 인덱스를 기록하고, 중복이면 -2로 표시한다. */
    fun firstRepeatedCharacter(str: String): Char {
        val flags = IntArray(EXTENDED_ASCII_CODES) { -1 }

        for (i in str.indices) {
            val ch = str[i]
            if (flags[ch.code] == -1) {
                flags[ch.code] = i // index 저장
            } else {
                flags[ch.code] = -2
            }
        }

        var position = Int.MAX_VALUE
        for (i in 0 until EXTENDED_ASCII_CODES) {
            if (flags[i] >= 0) {
                position = minOf(position, flags[i])
            }
        }
        return if (position == Int.MAX_VALUE) Char.MIN_VALUE else str[position]
    }

    /** LinkedHashMap으로 삽입 순서를 보존하며 빈도를 세고, 빈도 1인 첫 키를 반환한다. */
    fun firstRepeatedCharacter2(str: String): Char {
        val chars = LinkedHashMap<Char, Int>()
        for (i in str.indices) {
            val ch = str[i]
            chars[ch] = (chars[ch] ?: 0) + 1
        }
        println(chars)

        for ((key, value) in chars) {
            if (value == 1) {
                return key // 순서가 보장되므로 가장 먼저 찾은 key를 반환한다.
            }
        }
        return Char.MIN_VALUE
    }

    /** 코드포인트 기준으로 빈도가 1인 첫 문자를 반환한다(유니코드 안전). */
    fun firstRepeatedCharacter3(str: String): String {
        val counts = str.codePoints().toArray()
            .toList()
            .groupingBy { it }
            .eachCount() // 순서 보존: LinkedHashMap 기반

        println(counts)

        val cp = counts.entries.firstOrNull { it.value == 1 }?.key ?: Char.MIN_VALUE.code
        return String(Character.toChars(cp))
    }
}

fun main() {
    val word = "my name is jongsang han. my"

    println(NoDuplicationFirstWordFinder.firstRepeatedCharacter(word))
    println(NoDuplicationFirstWordFinder.firstRepeatedCharacter2(word))
    println(NoDuplicationFirstWordFinder.firstRepeatedCharacter3(word))
}
