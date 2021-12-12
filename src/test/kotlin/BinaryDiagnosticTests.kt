import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class BinaryDiagnosticTests : FunSpec({
    test("Calculate Gamma Rate 1 String") {
        val gammaRate = calculateGammaRate(listOf("0101"))
        val expectedRate = 5
        gammaRate shouldBe expectedRate
    }

    test("Calculate Gamma Rate 3 Strings") {
        val gammaRate = calculateGammaRate(listOf("1111", "1111", "1111"))

        val expectedRate = 15
        gammaRate shouldBe expectedRate
    }

    test("Calculate Gamma Rate 3 Strings, Different First Digits") {
        val gammaRate = calculateGammaRate(listOf("0111", "0111", "1111"))

        val expectedRate = 7
        gammaRate shouldBe expectedRate
    }

    test("Calculate Gamma Rate 3 Strings, Different First and Third Digits") {
        val gammaRate = calculateGammaRate(listOf("0101", "0101", "1111"))

        val expectedRate = 5
        gammaRate shouldBe expectedRate
    }

    test("Calculate Gamma Rate 3 Strings, 5 digit binary, Different First and Third Digits") {
        val gammaRate = calculateGammaRate(listOf("01011", "01011", "11111"))

        val expectedRate = 11
        gammaRate shouldBe expectedRate
    }

    test("Calculate Epsilon Rate 3 Strings") {
        val gammaRate = calculateEpsilonRate(listOf("1111", "1111", "1111"))

        val expectedRate = 0
        gammaRate shouldBe expectedRate
    }

    test("Calculate Epsilon Rate 3 Strings, Different First Digits") {
        val gammaRate = calculateEpsilonRate(listOf("0111", "0111", "1111"))
        val expectedRate = 8
        gammaRate shouldBe expectedRate
    }

    test("Calculate Epsilon Rate 3 Strings, Different First and Third Digits") {
        val gammaRate = calculateEpsilonRate(listOf("0101", "0101", "1111"))

        val expectedRate = 10
        gammaRate shouldBe expectedRate
    }

    test("Calculate Epsilon Rate 3 Strings, 5 digit binary, Different First and Third Digits") {
        val gammaRate = calculateEpsilonRate(listOf("01011", "01011", "11111"))
        val expectedRate = 20
        gammaRate shouldBe expectedRate
    }

    test("Calculate Power Consumption 3 Strings, 5 digit binary, Different First and Third Digits") {
        val consumption = calculatePowerConsumption(listOf("01011", "01011", "11111"))
        val expectedRate = 20 * 11
        consumption shouldBe expectedRate
    }

})


