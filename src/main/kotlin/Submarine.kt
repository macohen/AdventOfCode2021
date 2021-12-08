data class Submarine(var position: Position) {
    private var aim: Int = 0

    fun down(units: Int) {
        aim += units
        println("Moving aim down: $aim")
    }

    fun up(units: Int) {
        aim -= units
        println("Moving aim up: $aim")
    }

    fun forward(units: Int) {
        position.horizontalPosition += units
        position.depth += aim * units
    }

    fun move(instructions: String) {
        val instructionList: List<String> = instructions.split(" ")
        val direction = instructionList[0]
        val movement = instructionList[1].toInt()

        when (direction) {
            "forward" -> {
                forward(movement)
            }
            "up" -> {
                up(movement)
            }
            "down" -> {
                down(movement)
            }
        }
    }

    fun unitsMoved(): Int {
        return position.horizontalPosition * position.depth
    }

}