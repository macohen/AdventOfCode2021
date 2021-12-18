import java.io.File

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
        reducedBinaryList = if (binariesWithOneAtPosition.size >= binariesWithZeroAtPosition.size) {
            binariesWithOneAtPosition
        } else {
            binariesWithZeroAtPosition
        }
    }
    return calculateDecimal(reducedBinaryList.get(0))
}

fun calculateCO2ScrubberRating(binaries: List<String>): Int {
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
        reducedBinaryList = if (binariesWithZeroAtPosition.size <= binariesWithOneAtPosition.size) {
            binariesWithZeroAtPosition
        } else {
            binariesWithOneAtPosition
        }
    }
    return calculateDecimal(reducedBinaryList.get(0))
}

fun calculateRate(binaryNums: List<String>, type: String): String {
    var onesCount = 0
    var zerosCount = 0
    val binaryLength = binaryNums[0].length - 1
    var finalBinary = ""
    for (digitPosition in 0..binaryLength) {
        for (binary in binaryNums) {
            val digit = binary[digitPosition]
            if (digit == '0') zerosCount++ else onesCount++
        }
        if (type == "mostCommon") {
                finalBinary += (if (onesCount > zerosCount) '0' else '1')
        } else if(type == "leastCommon") {
                finalBinary += (if (onesCount > zerosCount) '1' else '0')
        }
        onesCount = 0
        zerosCount = 0
    }
    return finalBinary
}

fun calculateEpsilonRate(binaryNums: List<String>): Int {
    val binaryRate = calculateRate(binaryNums, type="mostCommon")
    return calculateDecimal(binaryRate)
}

fun calculateGammaRate(binaryNums: List<String>): Int {
    val binaryRate = calculateRate(binaryNums, type="leastCommon")
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
