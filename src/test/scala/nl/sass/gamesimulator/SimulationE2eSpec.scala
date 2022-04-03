package nl.sass.gamesimulator

import nl.sass.gamesimulator.d20.inventory.characters._
import nl.sass.gamesimulator.dice.AverageRollResolution
import nl.sass.gamesimulator.dice.FixedRollResolution._

class SimulationE2eSpec extends BaseSpec {

  "given maximum roll resolution" when {

    val simulation = SimulationBuilder
      .aDefaultD20Simulation
      .withDieResolution(maximumRollResolution)

    "running the default scenario" should {

      "result in Chuck getting 20 damage (x2 critical) by Dave" in {
        simulation.run() shouldEqual Seq(
          CharacterWasDamaged(
            hit = chuck.name,
            by = dave.name,
            damageReceived = 20))
      }
    }
  }

  "given minimum roll resolution" when {

    val simulation = SimulationBuilder
      .aDefaultD20Simulation
      .withDieResolution(minimumRollResolution)

    "running the default scenario" should {

      "result in Chuck getting missed and taking no damage" in {
        simulation.run() shouldEqual Seq()
      }
    }
  }

  "given average roll resolution" when {

    val simulation = SimulationBuilder
      .aDefaultD20Simulation
      .withDieResolution(AverageRollResolution())

    "running the default scenario" should {

      "result in Chuck getting 6 damage by Dave" in {
        simulation.run() shouldEqual Seq(
          CharacterWasDamaged(
            hit = chuck.name,
            by = dave.name,
            damageReceived = 6))
      }
    }
  }
}
