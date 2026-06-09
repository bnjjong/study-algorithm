package io.jjong.algorithm.mock


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
    val logMap = logs.groupingBy { it }.eachCount()
    logMap.
}
