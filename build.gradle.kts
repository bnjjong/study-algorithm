plugins {
    kotlin("jvm") version "2.1.0"
}

group = "io.jjong"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}

// ── HackerRank식 stdin/stdout 러너 ───────────────────────────────────────────
// 표준입력으로 입력을 받아 풀이를 호출하고 표준출력으로 결과를 찍는다.
//   사용:  ./gradlew -q runAddTwoNumbers < samples/day1/add-two-numbers.in
//   또는 그냥 ./gradlew -q runAddTwoNumbers 후 키보드로 입력 (끝나면 Ctrl-D)
fun registerRunner(taskName: String, mainClassName: String) {
    tasks.register<JavaExec>(taskName) {
        group = "day1-runner"
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
