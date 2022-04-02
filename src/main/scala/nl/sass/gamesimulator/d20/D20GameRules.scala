package nl.sass.gamesimulator.d20

import nl.sass.gamesimulator.Effect.{ DealDamage, Miss }
import nl.sass.gamesimulator.Outcome._
import nl.sass.gamesimulator._
import nl.sass.gamesimulator.d20.model._

trait D20GameRules {
  def attackRoll(weapon: Weapon, attackBonus: AttackBonus, armorClass: ArmorClass): Challenge[DamageEffect]

  def damageRoll(weapon: Weapon): DamageSource
}

object DefaultD20GameRules extends D20GameRules {
  override def attackRoll(weapon: Weapon, attackBonus: AttackBonus, armorClass: ArmorClass) =
    Challenge(
      DifficultyCheck(attackBonus, armorClass.baseValue),
      {
        case CriticalSuccess => DealDamage(weapon.criticalDamage)
        case Success => DealDamage(DamageAmount.Full)
        case _ => Miss
      })

  override def damageRoll(weapon: Weapon): DamageSource =
    DamageSource(weapon.damage)
}

object D20GameRules {
  def toGenericGameRules(d20GameRules: D20GameRules) = new GameRules[Stats] {
    override def attackChallenge(actor: Stats, target: Stats): Challenge[Effect] =
      d20GameRules.attackRoll(actor.attacks.weapon, actor.attacks.toHit.head, target.armorClass)

    override def damageSource(actor: Stats): DamageSource =
      d20GameRules.damageRoll(actor.attacks.weapon)
  }
}