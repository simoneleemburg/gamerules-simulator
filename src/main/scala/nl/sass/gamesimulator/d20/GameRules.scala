package nl.sass.gamesimulator.d20

import nl.sass.gamesimulator.Effect.{ DealDamage, Miss }
import nl.sass.gamesimulator.Outcome._
import nl.sass.gamesimulator._
import nl.sass.gamesimulator.d20.model.Weapon

trait GameRules {
  def attackRoll(weapon: Weapon, attackBonus: AttackBonus, armorClass: ArmorClass): Challenge[DamageEffect]
}

object DefaultGameRules extends GameRules {
  override def attackRoll(weapon: Weapon, attackBonus: AttackBonus, armorClass: ArmorClass) =
    Challenge(
      DifficultyCheck(attackBonus, armorClass.baseValue),
      {
        case CriticalSuccess => DealDamage(weapon.criticalDamage)
        case Success => DealDamage(DamageAmount.Full)
        case _ => Miss
      })
}