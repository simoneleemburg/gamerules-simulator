package nl.sass.gamesimulator.d20.model

import cats.data.NonEmptyList

case class Stats(attacks: Attacks, armorClass: ArmorClass, hitPoints: HitPoints) {
  def isIncapacitated = hitPoints.amount <= 0
}

case class Attributes(strength: AttributeBonus)

case class AttributeBonus(value: Int) extends Bonus

trait Bonus {
  val value: Int
}

case class Attacks(weapon: Weapon, toHit: NonEmptyList[AttackBonus])

object AttackBonus {
  def default(base: Int, bonus: Int): NonEmptyList[AttackBonus] =
    NonEmptyList(base, 0.until(base / 5).toList.map(i => base - 5 * (i + 1)))
      .map(base => AttackBonus(base + bonus))
}

case class AttackBonus(value: Int) extends Bonus

case class ArmorClass(baseValue: Int, armor: Armor) {
  def value = baseValue + armor.armorClassBonus
}

object HitPoints {
  def full(amount: Int) = HitPoints(amount, amount)
}

case class HitPoints(amount: Int, maximum: Int)
