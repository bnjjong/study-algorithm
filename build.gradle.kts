plugins {
    kotlin("jvm") version "2.1.0"
}

group = "io.jjong"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // day1 연습 테스트는 kotlin.test 사용
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // leetcode 테스트는 Kotest 사용 (JUnit5 플랫폼 위에서 동작)
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    // Kotest classpath autoscan 비활성화 (시작 비용 제거; 6.0 기본값)
    systemProperty("kotest.framework.classpath.scanning.autoscan.disable", "true")
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

// ── HackerRank식 stdin/stdout 러너 ───────────────────────────────────────────
// 표준입력으로 입력을 받아 풀이를 호출하고 표준출력으로 결과를 찍는다.
//   사용:  ./gradlew -q runAddTwoNumbers < samples/day1/add-two-numbers.in
//   또는 그냥 ./gradlew -q runAddTwoNumbers 후 키보드로 입력 (끝나면 Ctrl-D)
fun registerRunner(taskName: String, mainClassName: String, taskGroup: String = "day1-runner") {
    tasks.register<JavaExec>(taskName) {
        group = taskGroup
        description = "stdin/stdout 러너: $mainClassName"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set(mainClassName)
        standardInput = System.`in`
    }
}

registerRunner("runAddTwoNumbers", "io.jjong.algorithm.day1.runner.AddTwoNumbersMainKt")
registerRunner("runReverseList", "io.jjong.algorithm.day1.runner.ReverseLinkedListMainKt")
registerRunner("runDetectCycle", "io.jjong.algorithm.day1.runner.LinkedListCycleIIMainKt")
registerRunner("runMaxProduct", "io.jjong.algorithm.day1.runner.MaximumProductSubarrayMainKt")
registerRunner("runMergeLists", "io.jjong.algorithm.day1.runner.MergeTwoSortedListsMainKt")
registerRunner("runMaxArraySum", "io.jjong.algorithm.day1.runner.MaxArraySumMainKt")
registerRunner("runAddLargeNumbers", "io.jjong.algorithm.day1.runner.LargeNumberAdditionMainKt")

// day2 (binary search) 러너
registerRunner("runClimbingLeaderboard", "io.jjong.algorithm.day2.runner.ClimbingLeaderboardMainKt", "day2-runner")

// ── 알고리즘 데모 main 실행 ──────────────────────────────────────────────────
//   사용:  ./gradlew -q runClass -PmainClass=io.jjong.algorithm.queue.RPNExpressionKt
tasks.register<JavaExec>("runClass") {
    group = "algorithm"
    description = "main 함수 실행 (-PmainClass=FQCN)"
    classpath = sourceSets["main"].runtimeClasspath
    (project.findProperty("mainClass") as String?)?.let { mainClass.set(it) }
}
