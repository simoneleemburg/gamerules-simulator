package nl.sass.gamesimulator.d20.model

import nl.sass.gamesimulator.DamageAmount
import nl.sass.gamesimulator.dice.Dice

case class Weapon(damage: Dice, damageType: DamageType, size: WeaponSize, criticalDamage: DamageAmount)

sealed trait DamageType

object DamageType {

  case object Piercing extends DamageType
  case object Slashing extends DamageType

  case object Bludgeoning extends DamageType

}

sealed trait WeaponSize

object WeaponSize {

  case object Light extends WeaponSize

  case object OneHanded extends WeaponSize

  case object TwoHanded extends WeaponSize

}

case class Armor(armorClassBonus: Int, padded: Boolean, category: ArmorCategory)

sealed trait ArmorCategory

object ArmorCategory {

  case object Light extends ArmorCategory

  case object Medium extends ArmorCategory

  case object Heavy extends ArmorCategory

}