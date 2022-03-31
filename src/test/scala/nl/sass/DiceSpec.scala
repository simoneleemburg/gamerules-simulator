package nl.sass

import org.scalatest._

class DiceSpec extends WordSpec with Matchers {

  "given random number resolution" when {
    implicit val r: dieResolution = new randomResolution
    "rolling a d20 dice" should {
      val dice = d.d20
      "result in at least a 1" in {
        dice.roll().value shouldBe >=(1)
      }
      "result in at most a 20" in {
        dice.roll().value shouldBe <=(20)
      }
    }
    "rolling a d20+1 dice" should {
      val dice = d.d20 + 1
      "result in at least a 2" in {
        dice.roll().value shouldBe >=(2)
      }
      "result in at most a 21" in {
        dice.roll().value shouldBe <=(21)
      }
    }
    "rolling 2d6+4 dice" should {
      val dice = 2.d6 + 4
      "result in at least a 6" in {
        dice.roll().value shouldBe >=(6)
      }
      "result in at most a 16" in {
        dice.roll().value shouldBe <=(16)
      }
    }
    "rolling 2d6+4+7 dice" should {
      val dice = 2.d6 + 4 + 7
      "result in at least a 6+7" in {
        dice.roll().value shouldBe >=(6 + 7)
      }
      "result in at most a 16+7" in {
        dice.roll().value shouldBe <=(16 + 7)
      }
    }
  }
}