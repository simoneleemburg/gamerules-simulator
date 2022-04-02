package nl.sass.gamesimulator.d20.model

import org.scalatest._

class AttackBonusSpec extends WordSpec with Matchers {

  "given a default attack bonus" when {
    "providing a base of 1 plus 2 bonus" should {
      "result in a single attack of +3" in {
        AttackBonus.default(1, 2).toList shouldEqual List(AttackBonus(3))
      }
    }
    "providing a base of 6 minus 2" should {
      "result in +4/-1" in {
        AttackBonus.default(6, -2).toList shouldEqual List(AttackBonus(4), AttackBonus(-1))
      }
    }
  }
}