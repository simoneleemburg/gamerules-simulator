package nl.sass.gamesimulator

object ActionPerforming {
  def perform[S](action: Action[S])(duel: Duel[S], gameRules: GameRules[S]): ResolvableAction[Effect, S] = action.actionType match {
    case Attack(attackType) => {
      val actor = duel.getCharacter(action.from)
      val target = duel.getCharacter(action.target)
      ResolvableAction(
        actor = actor,
        target = target,
        challenge = gameRules.attackChallenge(actor.stats, target.stats, attackType),
        source = gameRules.damageSource(actor.stats))
    }
  }
}

case class ResolvableAction[+E <: Effect, S](actor: Character[S], target: Character[S], challenge: Challenge[E], source: EffectSource[E])

trait Check

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

trait GameRules[S] {
  def attackChallenge(actor: S, target: S, attackType: AttackType[S]): Challenge[Effect]
  def damageSource(actor: S): DamageSource
}