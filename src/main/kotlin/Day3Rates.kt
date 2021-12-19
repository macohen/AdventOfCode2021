import java.io.File
import kotlin.reflect.KFunction2

fun readRatesTestInput(fileName: String): List<String> {
    val moveList = mutableListOf<String>()
    File(fileName).forEachLine { moveList.add(it) }
    return moveList
}

fun main() {
    val rates = readRatesTestInput("/Users/markcoh/IdeaProjects/AdventOfCode2021/src/test/resources/day3_rates_input.txt")
    val powerConsumption: Int = calculatePowerConsumption(rates)

    val lifeSupportRating = calculateLifeSupportRating(rates)
    println("power: $powerConsumption")
    println("life Support: $lifeSupportRating")
}

fun calculateLifeSupportRating(binaries: List<String>): Int {
    return calculateCO2ScrubberRating(binaries) * calculateOxygenGeneratorRating(binaries)
}

fun calculatePowerConsumption(binaryNums: List<String>): Int {
    return calculateEpsilonRate(binaryNums) * calculateGammaRate(binaryNums)
}

fun calculateOxygenGeneratorRating(binaries: List<String>): Int {
    val reducedBinaryList: MutableList<String> = reduceBinaries(binaries, ::selectO2GeneratorRating)
    return calculateDecimal(reducedBinaryList.get(0))
}

fun selectO2GeneratorRating(l1: MutableList<String>, l2: MutableList<String>): MutableList<String> = if (l1.size >= l2.size) l1 else l2
fun selectCO2Rating(l1: MutableList<String>, l2: MutableList<String>): MutableList<String> = if (l2.size <= l1.size) l2 else l1

    private fun reduceBinaries(binaries: List<String>, comparator: KFunction2<MutableList<String>, MutableList<String>, MutableList<String>>): MutableList<String> {
        var reducedBinaryList: MutableList<String> = binaries as MutableList<String>
        val binaryLength = binaries[0].length
        for (digitPosition in 0 until binaryLength) {
            if (reducedBinaryList.size == 1) {
                break
            }
            val binariesWithOneAtPosition: MutableList<String> = mutableListOf()
            val binariesWithZeroAtPosition: MutableList<String> = mutableListOf()
            for (binary in reducedBinaryList) {
                if (binary[digitPosition] == '0') {
                    binariesWithZeroAtPosition.add(binary)
                } else {
                    binariesWithOneAtPosition.add(binary)
                }
            }
            reducedBinaryList = comparator(binariesWithOneAtPosition, binariesWithZeroAtPosition)
        }
        return reducedBinaryList
    }
    fun calculateCO2ScrubberRating(binaries: List<String>): Int {
        val reducedBinaryList: MutableList<String> = reduceBinaries(binaries, ::selectCO2Rating)
        return calculateDecimal(reducedBinaryList.get(0))
    }

    fun selectMostCommon(onesCount: Int, zeroesCount: Int) :Char = if (onesCount > zeroesCount) '0' else '1'
    fun selectLeastCommon(onesCount: Int, zeroesCount: Int) :Char = if (onesCount > zeroesCount) '1' else '0'


    fun calculateRate(binaryNums: List<String>, appendToFinalBinary: (Int, Int) -> Char): String {
        var onesCount = 0
        var zerosCount = 0
        val binaryLength = binaryNums[0].length - 1
        var finalBinary = ""
        for (digitPosition in 0..binaryLength) {
            for (binary in binaryNums) {
                val digit = binary[digitPosition]
                if (digit == '0') zerosCount++ else onesCount++
            }
            finalBinary += appendToFinalBinary(onesCount, zerosCount)
            onesCount = 0
            zerosCount = 0
        }
        return finalBinary
    }

    fun calculateEpsilonRate(binaryNums: List<String>): Int {
        val binaryRate = calculateRate(binaryNums, ::selectMostCommon)
        return calculateDecimal(binaryRate)
    }

    fun calculateGammaRate(binaryNums: List<String>): Int {
        val binaryRate = calculateRate(binaryNums, ::selectLeastCommon)
        return calculateDecimal(binaryRate)
    }

    private fun calculateDecimal(binary: String): Int {
        var decimalNumber = 0
        var num: Long? = binary.toLongOrNull()
        if (num != null) {

            var i = 0
            var remainder: Long
            while (num.toInt() != 0) {
                remainder = num % 10
                num /= 10
                decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
                ++i
            }
        }
        return decimalNumber
    }

