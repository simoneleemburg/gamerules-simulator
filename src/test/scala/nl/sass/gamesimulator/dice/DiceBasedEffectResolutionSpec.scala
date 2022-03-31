package nl.sass.gamesimulator.dice

import nl.sass.gamesimulator.DamageAmount._
import nl.sass.gamesimulator.Effect._
import nl.sass.gamesimulator._
import nl.sass.gamesimulator.d20.inventory.characters
import nl.sass.gamesimulator.dsl._
import org.scalatest.{Matchers, WordSpec}

class DiceBasedEffectResolutionSpec extends WordSpec with Matchers {

  val dice = 2.d4 + 2

  val action = ResolvableAction(
    characters.dave,
    characters.chuck,
    null,
    DamageSource(dice))

  "given maximum resolution" when {
    implicit val r = MaximumRollResolution
    val res = new DiceBasedEffectResolution()
    "resolving damage of a normal hit" should {
      "result in 10 damage received" in {
        val event = res.resolve(action, DealDamage(Full)).characterWasDamaged
        event.damageReceived shouldBe 10
      }
    }
    "resolving damage of a critical x3 hit" should {
      "result in 30 damage received" in {
        val event = res.resolve(action, DealDamage(Triple)).characterWasDamaged
        event.damageReceived shouldBe 30
      }
    }
    "resolving damage of a half hit" should {
      "result in 5 damage received" in {
        val event = res.resolve(action, DealDamage(Half)).characterWasDamaged
        event.damageReceived shouldBe 5
      }
    }
    "resolving damage of a miss" should {
      "result in no events" in {
        val events = res.resolve(action, Miss)
        events shouldEqual Nil
      }
    }
  }

  implicit class EventHelper(events: Seq[Event]) {
    def characterWasDamaged = events.head.asInstanceOf[CharacterWasDamaged]
  }

}
