package io.jjong.algorithm.linkedlist

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * prepend(머리에 끼워넣기)를 단계별로 출력해 이해를 돕는 데모.
 *
 *   head = SinglyLinkedListNode(v).also { it.next = head }
 *
 * 위 '한 줄'을 ①②③ 세 단계로 풀어서 println으로 보여준다.
 * 핵심: 오른쪽 식이 '먼저' 계산되고 그 결과가 head에 대입된다.
 *       그래서 블록 안의 head는 아직 '옛날 head'(지금까지 만든 리스트)다.
 */
class PrependTraceTest {

    private fun prependTrace(digitsLeastFirst: List<Int>): SinglyLinkedListNode? {
        var head: SinglyLinkedListNode? = null
        println("시작: head = null  (빈 리스트 [])")

        for (v in digitsLeastFirst) {
            println("\n── 숫자 $v 를 prepend ──")

            val node = SinglyLinkedListNode(v)        // ①
            println("  ① new SinglyLinkedListNode($v) 생성  (node.next 는 아직 null)")

            val oldHead = head.toList()
            node.next = head                          // ②  node.next = '옛날 head'
            println("  ② node.next = 기존 head $oldHead  → 지금까지 만든 리스트를 새 노드 '뒤'에 매단다")

            head = node                               // ③  head 를 새 노드로 교체
            println("  ③ head = node                  ⇒ 현재 리스트: ${head.toList()}")
        }
        return head
    }

    @Test
    fun `prepend 단계별 추적`() {
        // 7243 + 564 = 7807.
        // 덧셈은 '최하위 자리부터' 계산되므로 7, 0, 8, 7 순서로 숫자가 나온다(거꾸로!).
        val digitsLeastFirst = listOf(7, 0, 8, 7)
        println("덧셈이 최하위 자리부터 뱉는 숫자 순서(거꾸로): $digitsLeastFirst\n")

        val result = prependTrace(digitsLeastFirst)

        println("\n✅ 최종(정방향): ${result.toList()}   // 거꾸로 들어왔지만 prepend 덕분에 바로 섰다")
        assertEquals(listOf(7, 8, 0, 7), result.toList())
    }
}
