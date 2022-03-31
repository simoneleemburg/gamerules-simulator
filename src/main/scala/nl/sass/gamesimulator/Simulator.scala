package nl.sass.gamesimulator

import nl.sass.gamesimulator.d20.DefaultGameRules
import nl.sass.gamesimulator.d20.inventory.characters
import nl.sass.gamesimulator.d20.model.{Action, ActionType}
import nl.sass.gamesimulator.dice._

object Simulator extends App {

  implicit val random: DieResolution = RandomResolution
  val rules = DefaultGameRules

  val checkResolution = new DiceBasedCheckResolution()
  val effectResolution = new DiceBasedEffectResolution()

  val action = Action(ActionType.SingleAttack, characters.dave.name, characters.chuck.name)
  val duel = Duel(characters.dave, characters.chuck)

  val resolvable = ActionPerforming.perform(action)(duel, rules)

  val events = ActionResolution.resolve(resolvable)(checkResolution, effectResolution)

  println(events)
}
