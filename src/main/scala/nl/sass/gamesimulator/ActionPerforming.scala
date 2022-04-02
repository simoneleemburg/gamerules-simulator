package nl.sass.gamesimulator

import nl.sass.gamesimulator.d20.GameRules
import nl.sass.gamesimulator.d20.model.{ Action, ActionType }

object ActionPerforming {
  def perform(action: Action)(duel: Duel, gameRules: GameRules): ResolvableAction[Effect] = action.actionType match {
    case ActionType.SingleAttack => {
      val actor = duel.getCharacter(action.from)
      val target = duel.getCharacter(action.target)
      ResolvableAction(
        actor = actor,
        target = target,
        challenge = gameRules.attackRoll(actor.stats.attacks.weapon, actor.stats.attacks.toHit.head, target.stats.armorClass),
        source = DamageSource(actor.stats.attacks.weapon.damage))
    }
  }
}

case class ResolvableAction[+E <: Effect](actor: Character, target: Character, challenge: Challenge[E], source: EffectSource[E])

trait Check

case class DifficultyCheck(bonus: Bonus, challenge: Int) extends Check

case class Challenge[+E <: Effect](check: Check, effect: Outcome => E) {
  def resolve(checkResolution: CheckResolution): E = effect(checkResolution.resolve(check))
}

sealed abstract class Outcome(isSuccess: Boolean)

object Outcome {

  case object CriticalSuccess extends Outcome(true)

  case object Success extends Outcome(true)

  case object Failure extends Outcome(false)

  case object CriticalFailure extends Outcome(false)

}