package nl.sass.gamesimulator.d20.inventory

import nl.sass.gamesimulator._
import nl.sass.gamesimulator.d20.inventory.weapons._

object characters {

  val dave = Character(
    Name("Dave"),
    Stats(
      Attacks(longsword, AttackBonus.default(1, 2)),
      ArmorClass(10, armors.doublet),
      HitPoints.full(20)
    )
  )

  val chuck = Character(
    Name("Chuck"),
    Stats(
      Attacks(dagger, AttackBonus.default(1, 2)),
      ArmorClass(10, armors.doublet),
      HitPoints.full(20),
    )
  )
}
