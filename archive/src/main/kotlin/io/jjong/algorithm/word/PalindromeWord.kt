package io.jjong.algorithm.word

/**
 * 영숫자만 비교(대소문자 무시)했을 때 회문인지 검사한다. 양끝에서 투 포인터로 좁혀간다.
 */
object PalindromeWord {
    fun isPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1
        while (i < j) {
            // 영숫자가 아니면 건너뛴다.
            while (!s[i].isLetterOrDigit() && i < j) {
                ++i
                println("i = $i")
            }
            while (!s[j].isLetterOrDigit() && i < j) {
                --j
                println("j = $j")
            }
            println("start comparing word,  i : $i, j : $j")
            if (s[i].lowercaseChar() != s[j].lowercaseChar()) {
                return false
            }
            ++i
            --j
        }
        return true
    }
}

fun main() {
    val word = "!! ablevelba   !!"
    val palindrome = PalindromeWord.isPalindrome(word)
    println("is palindrome : $palindrome")
}
