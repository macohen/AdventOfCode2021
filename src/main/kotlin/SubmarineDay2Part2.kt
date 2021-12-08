import java.io.File

fun readAimCourseTestInput(fileName: String): List<String> {
    val moveList = mutableListOf<String>()
    File(fileName).forEachLine { moveList.add(it) }
    return moveList
}

fun main() {
    val movements = readAimCourseTestInput("/Users/markcoh/IdeaProjects/AdventOfCode2021/src/test/resources/day2_diveinput.txt")
    val submarine: Submarine = travelAimPlannedCourse(movements)
    println(submarine.position)
    println(submarine.unitsMoved())
}


fun travelAimPlannedCourse(instructions: List<String>): Submarine {
    var submarine = Submarine(Position(0, 0))
    for (instruction in instructions) {
        submarine = moveSubmarine(submarine, instruction)
    }
    return submarine
}

fun moveSubmarine(submarine: Submarine, instructions: String): Submarine {
    val instructionList: List<String> = instructions.split(" ")
    val direction = instructionList[0]
    val movement = instructionList[1].toInt()

    when (direction) {
        "forward" -> {
            submarine.forward(movement)
        }
        "up" -> {
            submarine.up(movement)
        }
        "down" -> {
            submarine.down(movement)
        }
    }

    return submarine
}




