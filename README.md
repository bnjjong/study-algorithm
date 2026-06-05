# study-algorithm

알고리즘 학습 저장소. 기존 **Java** 풀이 모음과 **LeetCode Kotlin** 풀이를 함께 둔다.

## 요구 환경
- JDK 21. Gradle은 Wrapper(`./gradlew`)가 8.5를 자동으로 받아 사용하므로 별도 설치 불필요.
- 빌드: Kotlin DSL + `kotlin("jvm")` 플러그인 — `src/main/java`의 Java 코드와 `src/main/kotlin`의 Kotlin 코드를 함께 컴파일한다.

## 구조
```
src/main/java/io/jjong/algorithm/        ← Java 알고리즘 풀이
  linked/  queue/  tree/  word/  ...        (LinkedList, Queue, Tree, Word 등 주제별)

src/main/kotlin/io/jjong/leetcode/       ← LeetCode Kotlin 풀이 (문제 번호별)
  p0001/  Solution.kt + README.md           # 1. Two Sum
  p0003/                                     # 3. Longest Substring Without Repeating Characters
  p0121/                                     # 121. Best Time to Buy and Sell Stock
  p0125/                                     # 125. Valid Palindrome
  p0217/                                     # 217. Contains Duplicate
  p0242/                                     # 242. Valid Anagram
src/test/kotlin/io/jjong/leetcode/       ← 각 문제별 SolutionTest

docs/        Kotlin 기초 문법 노트 (Array/List/Map/Set/String/Loop/Char)
```
각 문제 폴더의 `README.md`에 문제 설명과 접근법을 정리한다.

## 빌드 & 테스트
```bash
./gradlew build      # Java + Kotlin 전체 컴파일
./gradlew test       # 전체 테스트

# 문제 하나만 집중
./gradlew test --tests "io.jjong.leetcode.p0001.SolutionTest"
```
테스트 리포트(HTML): `build/reports/tests/test/index.html`
