import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.collections.listOf

class DepthIncreaseTests : FunSpec( {
    test("CountDepthIncreaseWhen0") {
        val depths = listOf<Int>()
        measureDepthIncrease(depths) shouldBe 0
    }

    test("CountDepthIncreaseWhenTwoItemsOneIncrease") {
        val depths = listOf(0, 1)
        measureDepthIncrease(depths) shouldBe 1
    }

    test("CountDepthIncreaseWhenItDrops") {
        val depths = listOf(0, 1, 2, 1)
        measureDepthIncrease(depths) shouldBe 2
    }

    test("CountDepthIncreaseWhenItDropsTwice") {
        val depths = listOf(0, 1, 10, 20, 10, 5)
        measureDepthIncrease(depths) shouldBe 3
    }

    test("CountDepthIncreaseAsThreeItemWindowOneWindow") {
        val depths = listOf(199, 200, 208) //, 210, 200, 207, 240, 269, 260, 263)
        measureDepthIncreaseWindows(depths, 3) shouldBe 0
    }

    test("CountDepthIncreaseAsThreeItemWindowTwoWindows") {
        val depths = listOf(199, 200, 208, 210, 240, 269, 260, 263)
        // 199 + 200 + 208 = 607
        // 200 + 208 + 210 = 618
        measureDepthIncreaseWindows(depths, 3) shouldBe 5
    }

    test("CountDepthIncreaseAsThreeItemWindowThreeWindows") {
        val depths = listOf(1, 2, 3, 1, 2, 3, 2, 2, 4)
        measureDepthIncreaseWindows(depths, 3) shouldBe 2
    }
})

