package io.jjong.algorithm.day2

/**
 * Cache Pagination ① — Cursor(keyset) 기반 페이지네이션.
 *
 * 정렬된 데이터에서 "마지막으로 본 [cursor] 이후"의 원소들을 페이지 단위로 반환한다.
 * Offset 방식(`OFFSET n`)과 달리 깊은 페이지에서도 이진 탐색으로 O(log N) 점프가 가능하고,
 * 중간에 삽입/삭제가 일어나도 기준점(cursor)이 고정이라 항목이 밀리지 않는다.
 */
class CursorPaginator(data: List<Int>) {
    // 어떤 정렬일까?
    private val sortedData = data.sorted()


    /**
     * [cursor]를 **초과**하는 첫 원소부터 최대 [size]개를 반환한다.
     * [cursor]가 null이면 가장 앞에서부터, 더 줄 원소가 없으면 빈 리스트를 반환한다.
     */
    fun page(cursor: Int?, size: Int): List<Int> {
//        sortedData.forEach {
//            print("$it, ")
//        }
//        println()
        val start = if (cursor == null) 0
        else upperBound(sortedData, cursor)

        val end = minOf(start + size, sortedData.size) // start+size <- 이게 end 값이지만, overflow 될수 있으므로 전체 size 값이 들어감.
//        println("start: $start, end: $end")
        return sortedData.subList(start, end)
    }

    private fun upperBound(inputData: List<Int>, cursor: Int): Int {
        var lo = 0
        var hi = inputData.size
        while (lo < hi) {
            var mid = (lo + hi) ushr 1 //비트 shift, 우측으로 옮긴다. 나눗셈 하는 것임. 오버플로를 위해서 사용함.
            if (inputData[mid] <= cursor) lo = mid+1 // 찾아낸 값이 커서보다 작거나 같으면 lo값을 업데이트 한다.
            else hi = mid // 커서가 더 클 경우 우측을 날림.
//            println("mid : $mid, value: ${inputData[mid]}, cursor: $cursor")
//            println("lo: $lo, hi: $hi")
        }
        return lo
    }
}
