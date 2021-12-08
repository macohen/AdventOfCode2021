import java.io.File
import kotlin.text.split

data class Position(var horizontalPosition: Int = 0, var depth: Int = 0) {
  fun calculateUnitsMoved(): Int {
      return horizontalPosition * depth
  }
}

fun readCourseTestInput(fileName: String): List<String> {
    val moveList = mutableListOf<String>()
    File(fileName).forEachLine { moveList.add(it) }
    return moveList
}

fun main() {
    val movements = readCourseTestInput("/Users/markcoh/IdeaProjects/AdventOfCode2021/src/test/resources/day2_diveinput.txt")
    val finalPosition: Position = travelPlannedCourse(movements)
    println(finalPosition)
    println(finalPosition.calculateUnitsMoved())
}


fun travelPlannedCourse(instructions: List<String>): Position {
    var position = Position(0,0)
    for (instruction in instructions) {
        position = moveSubmarine(position, instruction)
    }
    return position
}

fun moveSubmarine(currentPosition: Position, instructions: String): Position {
    val instructionList: List<String> = instructions.split(" ")
    val direction = instructionList[0]
    val movement = instructionList[1].toInt()

    when (direction) {
        "forward" -> {
            currentPosition.horizontalPosition += movement
        }
        "up" -> {
            currentPosition.depth -= movement
        }
        "down" -> {
            currentPosition.depth += movement
        }
    }
    return currentPosition
}




