import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SubmarineAimControlTests: FunSpec({
    test("Move Submarine Forward 5") {
        val submarine = Submarine(Position(0,0))
        val instructions = "forward 5"

        val expectedPosition = Position(5, 0)
        submarine.move(instructions)

        submarine.position shouldBe expectedPosition
        submarine.unitsMoved() shouldBe 0

    }


    test("From Instructions") {
        val submarine = Submarine(Position(0,0))

        submarine.move("forward 5")
        submarine.position shouldBe Position(5, 0)
        submarine.unitsMoved() shouldBe 0

        submarine.move("down 5")
        submarine.position shouldBe Position(5, 0)
        submarine.unitsMoved() shouldBe 0

        submarine.move("forward 8")
        submarine.position shouldBe Position(13, 40)
        submarine.unitsMoved() shouldBe 13 * 40

        submarine.move("up 3")
        submarine.position shouldBe Position(13, 40)
        submarine.unitsMoved() shouldBe 13 * 40

        submarine.move("down 8")
        submarine.position shouldBe Position(13, 40)
        submarine.unitsMoved() shouldBe 13 * 40

        submarine.move("forward 2")
        submarine.position shouldBe Position(15, 60)
        submarine.unitsMoved() shouldBe 15 * 60
    }



}
)