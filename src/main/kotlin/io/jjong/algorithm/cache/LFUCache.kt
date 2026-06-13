package io.jjong.algorithm.cache

/**
 * LeetCode #460 — LFU Cache (Hard) · Day 2 캐시 심화(도전 문제)
 *
 * 용량 [capacity]의 LFU(Least Frequently Used) 캐시. `get`/`put` 모두 O(1)을 목표로 한다.
 * 용량이 가득 차면 **사용 빈도가 가장 낮은** 키를 제거하고, 빈도가 같으면 **가장 오래 안 쓴(LRU)** 키를 제거한다.
 *
 * 복습 포인트:
 *   LRUCache는 "최근성" 한 축이었지만, LFU는 "빈도 + (동률 시) 최근성" 두 축이다.
 *   핵심 아이디어 — 빈도별로 LRU 컬렉션을 따로 두고, 현재 최소 빈도(minFreq)를 O(1)로 추적한다.
 *   get/put 때마다 해당 키의 빈도를 +1 하며 "빈도 f 묶음 → 빈도 f+1 묶음"으로 옮긴다.
 *
 * 권장 자료구조:
 *   - keyToVal:   key -> value
 *   - keyToFreq:  key -> 현재 빈도
 *   - freqToKeys: freq -> 그 빈도의 key들(LinkedHashSet — 삽입 순서 = 오래된 것이 앞 = LRU 후보)
 *   - minFreq:    현재 최소 빈도(가득 찼을 때 freqToKeys[minFreq]의 맨 앞이 제거 대상)
 *
 * 엣지: [capacity]가 0이면 아무것도 저장하지 않는다.
 */
class LFUCache(private val capacity: Int) {

    // 권장 자료구조 (직접 선언부터 채워보세요)
    //   keyToVal:   key -> value
    //   keyToFreq:  key -> 현재 빈도
    //   freqToKeys: freq -> 그 빈도의 key들 (LinkedHashSet → 맨 앞 = 그 층의 LRU)
    //   minFreq:    현재 최소 빈도

    /** 키가 있으면 빈도를 1 올리고 값을 반환한다. 없으면 -1. */
    fun get(key: Int): Int {
        TODO("없으면 -1 / 있으면 touch(key)로 빈도 올리고 값 반환")
    }

    /**
     * 키를 삽입하거나 값을 갱신한다.
     * 새 키인데 가득 찼으면 freqToKeys[minFreq]의 가장 오래된 키를 제거한 뒤, 빈도 1로 추가한다.
     */
    fun put(key: Int, value: Int) {
        TODO("capacity<=0 무시 / 기존 키면 값 갱신 후 touch / 새 키면 (가득 차면 evict 후) 1층 추가 + minFreq=1")
    }

    // 헬퍼로 쪼개면 get/put이 단순해진다 — 직접 구현해보세요.
    //   private fun touch(key: Int) { /* f층에서 빼고 f+1층으로. 비운 게 minFreq면 minFreq++ (함정1) */ }
    //   private fun evict() { /* freqToKeys[minFreq] 맨 앞 key를 모든 맵에서 제거 */ }
}
