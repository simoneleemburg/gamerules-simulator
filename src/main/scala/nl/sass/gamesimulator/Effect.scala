package nl.sass.gamesimulator

import nl.sass.gamesimulator.dice.Dice

sealed trait Effect

sealed trait DamageEffect extends Effect

object Effect {

  case class DealDamage(amount: DamageAmount) extends DamageEffect

  case object Miss extends DamageEffect

}

sealed trait DamageAmount

object DamageAmount {

  sealed abstract class Multiplied(val multiplier: Int) extends DamageAmount

  case object Quadruple extends Multiplied(4)

  case object Triple extends Multiplied(3)

  case object Double extends Multiplied(2)

  case object Full extends DamageAmount

  case object Half extends DamageAmount

}

sealed trait EffectSource[+E <: Effect]

case class DamageSource(dice: Dice) extends EffectSource[DamageEffect]