package io.jjong.algorithm.day2

import kotlin.test.Test

/**
 * SearchRange의 이진 탐색 과정을 눈으로 따라가기 위한 학습용 테스트.
 *
 * 단언(assert)이 목적이 아니라 trace 로그 출력이 목적이다 — lo/hi/mid가 어떻게 좁혀지는지,
 * lowerBound(`<`)와 upperBound(`<=`)가 같은 입력에서 어디서 갈리는지 관찰한다.
 *
 * 실행:
 *   ./gradlew test --tests "*SearchRangeTraceTest" --rerun-tasks
 *
 * 주의: Gradle은 통과한 테스트를 캐싱(UP-TO-DATE)하므로, 두 번째 실행부터는
 * --rerun-tasks(또는 ./gradlew cleanTest test ...)를 붙여야 로그가 다시 출력된다.
 */
class SearchRangeTraceTest {

    private val tracer = SearchRange(trace = true)
    private val data = intArrayOf(5, 7, 7, 8, 8, 10)

    @Test
    fun `target=8 — 존재(중복), first와 last 모두 탐색`() {
        println("\n===== target = 8  (존재, 8이 두 개) — lowerBound로 first, upperBound로 last =====")
        tracer.searchRange(data, 8)
    }

    @Test
    fun `target=6 — 중간 빈틈이라 없음 (조건 B로 걸림)`() {
        println("\n===== target = 6  (중간 빈틈 → 없음) — lowerBound가 가리킨 자리 값이 6이 아님 =====")
        tracer.searchRange(data, 6)
    }

    @Test
    fun `target=11 — 최댓값 초과라 없음 (조건 A로 걸림, OOB 회피)`() {
        println("\n===== target = 11  (최댓값 초과 → first==size) — nums[first] 접근 없이 걸러짐 =====")
        tracer.searchRange(data, 11)
    }

    @Test
    fun `target=3 — 최솟값 미만이라 없음`() {
        println("\n===== target = 3  (최솟값 미만 → 없음) — lowerBound=0, nums[0]=5 ≠ 3 =====")
        tracer.searchRange(data, 3)
    }
}
