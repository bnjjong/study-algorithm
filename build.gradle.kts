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

// ── 알고리즘 데모 main 실행 ──────────────────────────────────────────────────
//   사용:  ./gradlew -q runClass -PmainClass=io.jjong.algorithm.queue.RPNExpressionKt
tasks.register<JavaExec>("runClass") {
    group = "algorithm"
    description = "main 함수 실행 (-PmainClass=FQCN)"
    classpath = sourceSets["main"].runtimeClasspath
    (project.findProperty("mainClass") as String?)?.let { mainClass.set(it) }
}
