package io.jjong.algorithm.ranking



/**
 * [D1 모의 · 문제 1] 인기 검색어 Top K
 *
 * 검색어 로그 [logs]에서 가장 많이 검색된 키워드 [k]개를 빈도 내림차순으로 반환한다.
 * 빈도가 같으면 사전순(오름차순)으로 타이브레이크한다.
 *
 * - Input  : [logs] (소문자 영문 키워드, 최대 10^5개), [k]
 * - Output : 상위 [k]개 키워드 (빈도 내림차순, 동점 시 사전순)
 * - 예시   : (["nike","adidas","nike","puma","nike","adidas"], 2) → ["nike","adidas"]
 *
 * 무신사 검색/랭킹 직무의 "실시간 인기 검색어" 시나리오.
 */
fun topKKeywords(logs: List<String>, k: Int): List<String> {
    // 복잡도 o(n)
    val logMap = logs.groupingBy { it }.eachCount()
    return logMap.entries
        // n log n
        .sortedWith(
            compareByDescending<Map.Entry<String, Int>> { it.value }
                .thenBy { it.key }
        )
        .map {it.key}
        .take(k)
}

/**
 * [심화] Bucket sort 버전 — 비교 정렬(O(m log m))을 빈도 버킷으로 우회한다.
 *
 * 빈도가 `1..n` 범위의 정수라는 점을 이용해 "빈도를 인덱스로 쓰는 통(bucket)"에
 * 키워드를 담는다. 비교 없이 빈도별로 그룹화되므로 정렬 단계가 사라진다.
 *
 * 복잡도: 빈도 O(n) + 버킷 채우기 O(m) + 수집 O(n).
 *   단, 동점 사전순 때문에 *같은 버킷 안*만 정렬해야 한다 → 그 부분만 비교 정렬이 남는다.
 *   (동점이 전혀 없으면 완전한 O(n). 모두 한 버킷에 몰리는 극단에서만 O(m log m).)
 */
fun topKKeywordsBucket(logs: List<String>, k: Int): List<String> {
    // ① 빈도 카운트 — O(n)
    val freq = logs.groupingBy { it }.eachCount()

    // ② 빈도를 인덱스로 하는 버킷. buckets[c] = "빈도가 정확히 c인 키워드들".
    //    한 키워드의 최대 빈도는 logs.size 이므로 크기 size+1 이면 충분하다.
    val buckets = Array(logs.size + 1) { mutableListOf<String>() }
    for ((word, count) in freq) {
        buckets[count].add(word) // O(m)
    }

    // ③ 높은 빈도(큰 인덱스)부터 거꾸로 내려오며 k개를 모은다 — O(n)
    val result = ArrayList<String>(k)
    for (count in buckets.indices.reversed()) { // size → 0
        val bucket = buckets[count]
        if (bucket.isEmpty()) continue
        bucket.sort() // 동점 → 사전순(오름). '걸림돌' 비용은 여기에만 남는다.
        for (word in bucket) {
            result.add(word)
            if (result.size == k) return result
        }
    }
    return result
}
