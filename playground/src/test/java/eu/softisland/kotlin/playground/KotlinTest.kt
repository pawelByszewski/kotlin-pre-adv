package eu.softisland.kotlin.playground

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat


class BenchmarkTest {
    val iterationsCount = 200
    var totalTime = 0L

    fun test(a: Long, b: String, c: LongRange) {
        ++totalTime
        test(a,b,c)
    }

    private fun calculate() = (1L..5000000L).asSequence().map { it * 40 }.reduce { a,b -> (a*b)%19 + 1 }

    @Test
    fun almostBenchmark() {
        //        iterationsCount.downTo(1).forEach { test() }
        try {
            test(1234, "iusdhfpaisdhgpaiouwrhgaoergnaoig", (1L..5000000L))
        } catch( t: Throwable) {
            println(totalTime)
        }
        println("Avg time: " + (totalTime.toDouble() / iterationsCount))
    }
}