package io.jjong.algorithm.word

/**
 * 단어 단위로 순서를 뒤집는다. 전체를 한 번 뒤집은 뒤 각 단어를 다시 뒤집는 고전적 기법.
 * 예: "abc de" → "de abc". 입력 [CharArray]를 제자리(in-place)에서 변경한다.
 */
object ReverseWord {
    fun reverseWord(input: CharArray) {
        val wordLength = input.size

        // 전체를 뒤집는다.
        reverse(input, 0, wordLength - 1)
        print("reverse : ")
        println(String(input))

        // 각 단어를 다시 뒤집는다.
        var start = 0
        var end = 0
        while (start < wordLength) {
            // 시작 위치: 공백을 건너뛴다.
            while (start < end || (start < wordLength && input[start] == ' ')) {
                print("start : ${start + 1}")
                println(", char : ${input[start]}")
                start++
            }
            // 끝 위치: 공백이 아닌 동안 전진한다.
            while (end < start || (end < wordLength && input[end] != ' ')) {
                print("end : ${end + 1}")
                println(", char : ${input[end]}")
                end++
            }
            reverse(input, start, end - 1)
        }
    }

    private fun reverse(input: CharArray, start: Int, end: Int) {
        var s = start
        var e = end
        while (s < e) {
            val tmp = input[s]
            input[s++] = input[e]
            input[e--] = tmp
        }
    }
}

fun main() {
    val word = "jonsang is king is power"
    val charArray = word.toCharArray()
    ReverseWord.reverseWord(charArray)

    println(String(charArray))
}
