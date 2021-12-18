import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LifeSupportRatingTests : FunSpec({
    /*test("Life Support Rating") {
        calculateLifeSupportRating(10, 10) shouldBe 100
    }*/

    test("Oxygen Generator Rating 12 numbers" ) {
        val binaries = listOf(
            "00100", //1. discard
            "11110",
            "10110",
            "10111",
            "10101",
            "01111", //1. discard
            "00111", //1. discard
            "11100",
            "10000",
            "11001",
            "00010", //", //1. discard
            "01010") //1. discard

        calculateOxygenGeneratorRating(binaries) shouldBe 23
    }

    test("CO2 Scrubber Rating 12 numbers" ) {
        val binaries = listOf(
            "00100", //1. discard
            "11110",
            "10110",
            "10111",
            "10101",
            "01111", //1. discard
            "00111", //1. discard
            "11100",
            "10000",
            "11001",
            "00010", //", //1. discard
            "01010") //1. discard

        calculateCO2ScrubberRating(binaries) shouldBe 10
    }

    test("Calculate Life Support Rating 12 numbers") {
        val binaries = listOf(
            "00100", //1. discard
            "11110",
            "10110",
            "10111",
            "10101",
            "01111", //1. discard
            "00111", //1. discard
            "11100",
            "10000",
            "11001",
            "00010", //", //1. discard
            "01010") //1. discard

        calculateLifeSupportRating(binaries) shouldBe 230

    }
}

)