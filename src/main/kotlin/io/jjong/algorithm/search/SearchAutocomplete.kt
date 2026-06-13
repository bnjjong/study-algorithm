package io.jjong.algorithm.search

import java.util.TreeMap

/**
 * [D3 모의 · 문제 2] 검색어 자동완성 (Search Autocomplete)
 *
 * 검색창에 접두사를 입력하면, 그 접두사로 시작하는 인기 검색어를 추천한다.
 * 사용자가 검색할 때마다 해당 검색어의 인기도(빈도)가 올라간다.
 *
 * 연산
 * - [input]   : 검색어 [keyword]가 한 번 검색됨(빈도 +1). 새 검색어면 등록한다.
 * - [suggest] : 접두사 [prefix]로 시작하는 검색어를 인기순으로 최대 [k]개 반환한다.
 *               빈도 내림차순, 동점이면 사전순(오름차순).
 *
 * 예시
 *   input("nike"); input("nike"); input("nike air"); input("new balance")
 *   suggest("ni", 2)  → ["nike", "nike air"]   // 'ni' 접두사, 빈도 nike(2) > nike air(1)
 *   suggest("new", 5) → ["new balance"]
 *   suggest("zzz", 5) → []                      // 매칭 없음
 *
 * 무신사 Search&Rec의 "검색어 자동완성/추천" 시나리오.
 * 핵심: 접두사 매칭을 어떻게? (전체 스캔 vs Trie) + Top-K 정렬은 지난 문제 그대로.
 */
class SearchAutocomplete {
    private val keywordTree = TreeMap<String, Int>()

    /** 검색어가 한 번 검색됨 — 빈도를 누적한다. */
    fun input(keyword: String) {
        keywordTree.merge(keyword, 1, Int::plus)
    }

    /** 접두사로 시작하는 검색어를 인기순 상위 [k]개 반환한다. */
    fun suggest(prefix: String, k: Int): List<String> {
        return keywordTree.entries
            .filter { (k, _) -> k.startsWith(prefix) }
            .sortedWith(
                compareByDescending<MutableMap.MutableEntry<String, Int>> { it.value } // 내림 차순
                    .thenBy { it.key } // 오름 차순
            )

            .take(k)
            .map { (k,v) -> k }
    }
}
