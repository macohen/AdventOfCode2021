import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class SubmarineControlTests: FunSpec( {
    test("MoveForward5FromStart") {
        val expectedOutput = Position()
        expectedOutput.horizontalPosition = 5
        expectedOutput.depth = 0
        moveSubmarine(Position(), "forward 5") shouldBe expectedOutput
    }

    test("MoveDown100FromStart") {
        val expectedOutput = Position()
        expectedOutput.horizontalPosition = 0
        expectedOutput.depth = 100
        moveSubmarine(Position(), "down 100") shouldBe expectedOutput
    }

    test("MoveDown100Forward1000FromStart") {
        val expectedOutput1 = Position()
        expectedOutput1.horizontalPosition = 0
        expectedOutput1.depth = 100
        val position1: Position = moveSubmarine(Position(), "down 100")
        position1 shouldBe expectedOutput1

        val expectedOutput2 = Position()
        expectedOutput2.horizontalPosition = 1000
        expectedOutput2.depth = 100
        val position2 = moveSubmarine(position1, "forward 1000")
        position2 shouldBe expectedOutput2
    }

    test("TravelPlannedCourseForwardDown") {
        val expectedOutput = Position(100, 200)
        val instructions = listOf("forward 100", "down 200")
        val lastPosition = travelPlannedCourse(instructions)
        lastPosition shouldBe expectedOutput

        lastPosition.calculateUnitsMoved() shouldBe 20000
    }
}
)


