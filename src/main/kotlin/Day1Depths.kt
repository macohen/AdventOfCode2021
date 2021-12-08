import java.io.File

fun main() {
    val depths: List<Int> = readTestInput("/Users/markcoh/IdeaProjects/AdventOfCode2021/src/test/resources/sonar_input.txt")
    println(measureDepthIncrease(depths))

    println("Windows: " + measureDepthIncreaseWindows(depths, 3))

}

fun readTestInput(fileName: String): List<Int> {
    val depthList = mutableListOf<Int>()
    File(fileName).forEachLine { depthList.add(it.toInt()) }
    println(depthList.size)
    return depthList
}


fun measureDepthIncreaseWindows(depths: List<Int>, windowSize:Int): Int {
    var currentSum = 0 //depths.take(3).sum()
    var lastSum = currentSum
    var totalDepthIncreases = 0

    var startWindow = 0
    val windowSizeIdx = windowSize - 1
    var endWindow = startWindow + windowSizeIdx
    while (startWindow < depths.size - 1 && depths.size - startWindow > windowSizeIdx) {
        for (i in startWindow..endWindow) {
            currentSum += depths[i]
        }
        if (currentSum > lastSum && startWindow > 0) {
            totalDepthIncreases += 1
        }
        startWindow +=1
        endWindow += 1
        lastSum = currentSum
        currentSum = 0
    }
    return totalDepthIncreases
}

fun measureDepthIncrease(depths: List<Int>): Int {
    var totalDepthIncreases = 0
    var previousDepth: Int = if(depths.isEmpty()) 0 else depths[0]
    for(depth in depths) {
        totalDepthIncreases += compareDepths(depth, previousDepth)
        previousDepth = depth
    }
    return totalDepthIncreases
}

private fun compareDepths(depth: Int, previousDepth: Int): Int {
    var increase = false
    if (depth > previousDepth) {
        increase = true
    }
    return if(increase) 1 else 0
}