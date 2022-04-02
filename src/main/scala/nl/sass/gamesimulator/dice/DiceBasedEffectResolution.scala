package nl.sass.gamesimulator.dice

import nl.sass.gamesimulator.Effect._
import nl.sass.gamesimulator.d20.model.Stats
import nl.sass.gamesimulator.{ EffectResolution, _ }

class DiceBasedEffectResolution(implicit dieResolution: DieResolution) extends EffectResolution[Stats] {
  override def resolve[E <: Effect](resolvableAction: ResolvableAction[E, Stats], effect: E): Seq[Event] =
    (effect, resolvableAction.source) match {
      case (Miss, _) => Nil
      case (DealDamage(amount), DamageSource(dice)) => {
        Seq(
          CharacterWasDamaged(
            hit = resolvableAction.target.name,
            by = resolvableAction.actor.name,
            damageReceived = calculateDamage(resolution.resolve(dice, dieResolution), amount)))
      }
    }

  private def calculateDamage(result: Result, amount: DamageAmount): Int =
    amount match {
      case m: DamageAmount.Multiplied => result.value * m.multiplier
      case DamageAmount.Full => result.value
      case DamageAmount.Half => result.value / 2
    }
}
