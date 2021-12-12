import java.io.File

fun readRatesTestInput(fileName: String): List<String> {
    val moveList = mutableListOf<String>()
    File(fileName).forEachLine { moveList.add(it) }
    return moveList
}

fun main() {
    val rates = readRatesTestInput("/Users/markcoh/IdeaProjects/AdventOfCode2021/src/test/resources/day3_rates_input.txt")
    val powerConsumption: Int = calculatePowerConsumption(rates)
    println(powerConsumption)
}


fun calculatePowerConsumption(binaryNums: List<String>): Int {
    return calculateEpsilonRate(binaryNums) * calculateGammaRate(binaryNums)
}

fun calculateRate(binaryNums: List<String>, type: String): Int {
    var onesCount = 0
    var zerosCount = 0
    val binaryLength = binaryNums[0].length - 1
    var finalDecimal = ""
    for (digitPosition in 0..binaryLength) {
        for (binary in binaryNums) {
            val digit = binary[digitPosition]
            if (digit == '0') zerosCount++ else onesCount++
        }
        if (type == "epsilon") {
            finalDecimal += (if (onesCount > zerosCount) '0' else '1')
        } else if(type == "gamma") {
            finalDecimal += (if (onesCount > zerosCount) '1' else '0')
        }
        onesCount = 0
        zerosCount = 0
    }
    return calculateDecimal(finalDecimal)
}

fun calculateEpsilonRate(binaryNums: List<String>): Int {
    return calculateRate(binaryNums, type="epsilon")
}

fun calculateGammaRate(binaryNums: List<String>): Int {
    return calculateRate(binaryNums, type="gamma")
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
