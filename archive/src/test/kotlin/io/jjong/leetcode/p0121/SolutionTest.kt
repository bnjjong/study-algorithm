package io.jjong.leetcode.p0121

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SolutionTest : StringSpec({

    val solution = Solution()

    // ---- 기본 예시 ----
    "문제 예시 1 - 일반적인 케이스" { solution.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)) shouldBe 5 }
    "문제 예시 1 - 응용 케이스" { solution.maxProfit(intArrayOf(7, 3, 4, 5, 1, 6)) shouldBe 5 }
    "문제 예시 2 - 계속 떨어지면 0" { solution.maxProfit(intArrayOf(7, 6, 4, 3, 1)) shouldBe 0 }

    // ---- 길이 경계값 ----
    "원소 1개면 거래 불가능 0" { solution.maxProfit(intArrayOf(5)) shouldBe 0 }
    "원소 2개 - 오르면 차이만큼 이익" { solution.maxProfit(intArrayOf(1, 5)) shouldBe 4 }
    "원소 2개 - 내리면 0" { solution.maxProfit(intArrayOf(5, 1)) shouldBe 0 }

    // ---- 단조 케이스 ----
    "계속 오르기만 하면 처음에 사서 마지막에 판 차이" { solution.maxProfit(intArrayOf(1, 2, 3, 4, 5)) shouldBe 4 }
    "모두 같은 값이면 0" { solution.maxProfit(intArrayOf(3, 3, 3, 3)) shouldBe 0 }

    // ---- 정답 위치 다양화 ----
    "최저가가 중간에 있고 그 뒤 최고가가 등장" { solution.maxProfit(intArrayOf(3, 2, 6, 5, 0, 3, 100)) shouldBe 100 }
    "최저가가 중간 - V자 패턴" { solution.maxProfit(intArrayOf(3, 2, 1, 2, 3, 4)) shouldBe 3 }
    "최저가가 처음 - 최고가가 마지막" { solution.maxProfit(intArrayOf(1, 2, 3, 4, 5, 6, 7)) shouldBe 6 }
    "최저가가 처음이지만 그 뒤 큰 변동" { solution.maxProfit(intArrayOf(2, 4, 1, 5)) shouldBe 4 }
    "여러 번의 골짜기와 봉우리" { solution.maxProfit(intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)) shouldBe 8 }
    "최고 이익 페어가 인접하지 않은 경우" { solution.maxProfit(intArrayOf(10, 1, 100, 50, 200)) shouldBe 199 }
    "최저가 이후로 새로운 최저가가 등장하지만 이익은 안 늘어남" { solution.maxProfit(intArrayOf(5, 1, 3, 0, 1)) shouldBe 2 }
    "최저가 이후 새로운 최저가에서 더 큰 이익이 나오는 경우" { solution.maxProfit(intArrayOf(4, 5, 1, 10)) shouldBe 9 }

    // ---- 0과 큰 값 ----
    "0에서 사서 큰 값에 팔기" { solution.maxProfit(intArrayOf(0, 10000)) shouldBe 10000 }
    "모두 0이면 0" { solution.maxProfit(intArrayOf(0, 0, 0, 0)) shouldBe 0 }
    "최고가가 처음에 있고 이후 떨어지기만" { solution.maxProfit(intArrayOf(10000, 5, 3, 1, 0)) shouldBe 0 }

    // ---- 중복 값 패턴 ----
    "같은 가격 반복 후 상승" { solution.maxProfit(intArrayOf(1, 1, 1, 1, 5)) shouldBe 4 }
    "상승 후 같은 가격 반복" { solution.maxProfit(intArrayOf(1, 5, 5, 5, 5)) shouldBe 4 }
})
