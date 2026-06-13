package io.jjong.algorithm.cache

/**
 * Cache Pagination ① — Cursor(keyset) 기반 페이지네이션.
 *
 * 정렬된 데이터에서 "마지막으로 본 [cursor] 이후"의 원소들을 페이지 단위로 반환한다.
 * Offset 방식(`OFFSET n`)과 달리 깊은 페이지에서도 이진 탐색으로 O(log N) 점프가 가능하고,
 * 중간에 삽입/삭제가 일어나도 기준점(cursor)이 고정이라 항목이 밀리지 않는다.
 */
class CursorPaginator(data: List<Int>) {
    // 이진 탐색의 전제 — 오름차순(자연 정렬)으로 보관한다.
    private val sortedData = data.sorted()

    /**
     * [cursor]를 **초과**하는 첫 원소부터 최대 [size]개를 반환한다.
     * [cursor]가 null이면 가장 앞에서부터, 더 줄 원소가 없으면 빈 리스트를 반환한다.
     */
    fun page(cursor: Int?, size: Int): List<Int> {
        val start = if (cursor == null) 0
        else upperBound(sortedData, cursor)

        // start+size가 끝(size)을 넘으면 subList가 예외 → size로 클램핑 (오버플로가 아니라 경계 문제)
        val end = minOf(start + size, sortedData.size)
        return sortedData.subList(start, end)
    }

    private fun upperBound(inputData: List<Int>, cursor: Int): Int {
        var lo = 0
        var hi = inputData.size
        while (lo < hi) {
            val mid = (lo + hi) ushr 1 // 비트 shift로 나눗셈. ushr은 빈 자리를 0으로 채워 합의 오버플로에도 안전.
            if (inputData[mid] <= cursor) lo = mid + 1 // 찾은 값이 커서 이하면 lo를 올려 오른쪽으로
            else hi = mid // 커서 초과면 mid는 후보라 버리지 않고 hi만 좁힘
        }
        return lo
    }
}
