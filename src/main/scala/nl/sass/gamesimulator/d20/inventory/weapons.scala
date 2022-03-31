package nl.sass.gamesimulator.d20.inventory

import nl.sass.gamesimulator.DamageAmount.Double
import nl.sass.gamesimulator.d20.model.DamageType._
import nl.sass.gamesimulator.d20.model.Weapon
import nl.sass.gamesimulator.d20.model.WeaponSize._
import nl.sass.gamesimulator.dsl._

object weapons {

  val dagger = Weapon(1.d4, Piercing, Light, Double)
  val longsword = Weapon(1.d10, Slashing, TwoHanded, Double)
  val spear = Weapon(1.d10, Piercing, TwoHanded, Double)
  val mace = Weapon(1.d8, Bludgeoning, OneHanded, Double)
  val armingsword = Weapon(1.d8, Slashing, OneHanded, Double)
  val kanabo = Weapon(1.d12, Bludgeoning, TwoHanded, Double)
  val greatsword = Weapon(2.d6, Slashing, TwoHanded, Double)
}
