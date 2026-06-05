package io.jjong.leetcode.p0121

/**
 * LeetCode #121 - Best Time to Buy and Sell Stock (Easy)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * 한 번 사고 한 번 팔 때 얻을 수 있는 최대 이익을 반환합니다.
 */
class Solution {
    fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0
        for (price in prices) {
            // 1. 오늘 사는 게 더 싸다면 minPrice 갱신
            minPrice = minOf(minPrice, price)
            // 2. 오늘 판다고 가정한 이익을 계산해 maxProfit 갱신
            maxProfit = maxOf(maxProfit, price - minPrice)

            println("price: $price minPrice: $minPrice, maxProfit: $maxProfit")
        }
        return maxProfit
    }
}
