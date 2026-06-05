# study-algorithm

알고리즘 학습 저장소. 기존 **Java** 풀이 모음과, 무신사 코테 대비용 **Kotlin** 연습 세트(Day별)를 함께 둔다.

## 요구 환경
- JDK 21. Gradle은 Wrapper(`./gradlew`)가 8.7을 자동으로 받아 사용하므로 별도 설치 불필요.
- 빌드: Kotlin DSL + `kotlin("jvm")` 플러그인 — `src/main/java`의 Java 코드와 `src/main/kotlin`의 Kotlin 코드를 함께 컴파일한다.

## 구조
```
src/main/java/io/jjong/algorithm/      ← 기존 Java 알고리즘 풀이
  linked/  queue/  tree/  word/  ...      (LinkedList, Queue, Tree, Word 등 주제별)

src/main/kotlin/io/jjong/algorithm/    ← 무신사 코테 대비 Kotlin 연습
  day1/                                   Day 1: Linked List & DP
    ListNode.kt                             연결 리스트 노드 + toListNode()/toList() 헬퍼
    AddTwoNumbers.kt        (LC #2)
    ReverseLinkedList.kt    (LC #206)
    LinkedListCycleII.kt    (LC #142)
    MergeTwoSortedLists.kt  (LC #21, 보너스)
    MaximumProductSubarray.kt (LC #152)
    runner/                                 HackerRank식 stdin/stdout 러너
src/test/kotlin/io/jjong/algorithm/    ← Day 1 검증 테스트

samples/day1/        러너용 샘플 입력(.in) / 기대출력(.out)
verify-day1.sh       러너를 샘플로 돌려 자동 채점 (HackerRank 흉내)
SOLUTIONS.md         Day 1 참고 풀이
docs/                Kotlin 기초 문법 노트 (Array/List/Map/Set/String/Loop/Char)
```

## 빌드 & 테스트
```bash
./gradlew build      # Java + Kotlin 전체 컴파일
./gradlew test       # 전체 테스트

# Day 1 문제 하나만 집중
./gradlew test --tests "io.jjong.algorithm.day1.AddTwoNumbersTest"
```
테스트 리포트(HTML): `build/reports/tests/test/index.html`

## stdin/stdout 러너 (HackerRank 실전 감각)
표준입력으로 입력을 받아 결과를 표준출력으로 찍는다.
```bash
./gradlew -q runAddTwoNumbers < samples/day1/add-two-numbers.in
./gradlew -q runReverseList   < samples/day1/reverse-list.in
./gradlew -q runDetectCycle   < samples/day1/detect-cycle.in
./gradlew -q runMaxProduct    < samples/day1/max-product.in
./gradlew -q runMergeLists    < samples/day1/merge-two-lists.in

# 샘플 입력/기대출력 자동 채점
./verify-day1.sh
```
각 문제의 입출력 형식은 `src/main/kotlin/io/jjong/algorithm/day1/runner/*Main.kt` 상단 주석 참고.
