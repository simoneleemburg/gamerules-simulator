package nl.sass.gamesimulator.d20.inventory

import nl.sass.gamesimulator.d20.model.Armor
import nl.sass.gamesimulator.d20.model.ArmorCategory._

object armors {

  val clothing = Armor(1, padded = false, Light)
  val doublet = Armor(3, padded = false, Light)
  val gambeson = Armor(3, padded = true, Light)
  val mail = Armor(5, padded = true, Heavy)
  val brigandine = Armor(6, padded = false, Medium)
  val fullplate = Armor(8, padded = true, Heavy)
}
