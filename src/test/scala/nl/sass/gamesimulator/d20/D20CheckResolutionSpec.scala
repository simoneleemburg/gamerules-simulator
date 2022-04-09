package nl.sass.gamesimulator.d20

import nl.sass.gamesimulator.Outcome._
import nl.sass.gamesimulator.d20.model.{ AttackBonus, DifficultyCheck }
import nl.sass.gamesimulator.dice.FixedRollResolution
import nl.sass.gamesimulator.BaseSpec

class D20CheckResolutionSpec extends BaseSpec {

  "given a trivial check" when {
    val aTrivialCheck = DifficultyCheck(bonus = AttackBonus(25), challenge = 5)

    "when rolling a 1" should {
      "result in a critical failure" in {
        implicit val r = FixedRollResolution(1)
        new D20CheckResolution().resolve(aTrivialCheck) shouldBe CriticalFailure
      }
    }
    "when rolling a 20" should {
      "result in a critical success" in {
        implicit val r = FixedRollResolution(20)
        new D20CheckResolution().resolve(aTrivialCheck) shouldBe CriticalSuccess
      }
    }
    "when rolling a 2" should {
      "result in a success" in {
        implicit val r = FixedRollResolution(2)
        new D20CheckResolution().resolve(aTrivialCheck) shouldBe Success
      }
    }
  }
  "given a nearly impossible check" when {
    val aNearlyImpossibleCheck = DifficultyCheck(bonus = AttackBonus(0), challenge = 25)

    "when rolling a 1" should {
      "result in a critical failure" in {
        implicit val r = FixedRollResolution(1)
        new D20CheckResolution().resolve(aNearlyImpossibleCheck) shouldBe CriticalFailure
      }
    }
    "when rolling a 20" should {
      "result in a critical success" in {
        implicit val r = FixedRollResolution(20)
        new D20CheckResolution().resolve(aNearlyImpossibleCheck) shouldBe CriticalSuccess
      }
    }
    "when rolling a 19" should {
      "result in a failure" in {
        implicit val r = FixedRollResolution(19)
        new D20CheckResolution().resolve(aNearlyImpossibleCheck) shouldBe Failure
      }
    }
  }
  "given a challenging check" when {
    val aChallengingCheck = DifficultyCheck(bonus = AttackBonus(3), challenge = 18)

    "when rolling a 1" should {
      "result in a critical failure" in {
        implicit val r = FixedRollResolution(1)
        new D20CheckResolution().resolve(aChallengingCheck) shouldBe CriticalFailure
      }
    }
    "when rolling a 20" should {
      "result in a critical success" in {
        implicit val r = FixedRollResolution(20)
        new D20CheckResolution().resolve(aChallengingCheck) shouldBe CriticalSuccess
      }
    }
    "when rolling a 14" should {
      "result in a failure" in {
        implicit val r = FixedRollResolution(14)
        new D20CheckResolution().resolve(aChallengingCheck) shouldBe Failure
      }
    }
    "when rolling a 15" should {
      "result in a success" in {
        implicit val r = FixedRollResolution(15)
        new D20CheckResolution().resolve(aChallengingCheck) shouldBe Success
      }
    }
  }
}
