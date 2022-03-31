package nl.sass.gamesimulator

import nl.sass.gamesimulator.dice.Dice.{addition, constant, die, simple}
import nl.sass.gamesimulator.dice.{DieResolution, Result, d, resolution}

package object dsl {

  implicit class number(number: Int) {
    def d4 = die(d.d4, number)

    def d6 = die(d.d6, number)

    def d8 = die(d.d8, number)

    def d10 = die(d.d10, number)

    def d12 = die(d.d12, number)

    def d20 = die(d.d20, number)
  }

  implicit class dHelper[D](d: D) {
    def +[DD](other: DD)(implicit magnet: DiceableMagn[D], magnet2: SimpleDiceableMagn[DD]): addition =
      magnet.toDice(d) match {
        case simple: simple =>
          addition(List(
            simple,
            magnet2.toSimpleDice(other)))
        case addition(items) =>
          addition(items :+ magnet2.toSimpleDice(other))
      }
  }

  implicit val simpleDiceMagnet: SimpleDiceableMagn[simple] = new SimpleDiceableMagn[simple] {
    def toSimpleDice[SD <: simple](value: SD): simple = value
  }

  implicit val additionDiceMagnet: DiceableMagn[addition] = new DiceableMagn[addition] {
    def toDice[SD <: addition](value: SD): addition = value
  }
  implicit val dMagnet: SimpleDiceableMagn[d] = new SimpleDiceableMagn[d] {
    def toSimpleDice[SD <: d](value: SD): simple = die(value, 1)
  }
  implicit val numMagnet: SimpleDiceableMagn[Int] = new SimpleDiceableMagn[Int] {
    def toSimpleDice[SD <: Int](value: SD): simple = constant(value)
  }

  implicit class diceHelper[D](diceable: D) {
    def roll()(implicit dieResolution: DieResolution, magnet: DiceableMagn[D]): Result =
      resolution.resolve(magnet.toDice(diceable), dieResolution)
  }

}
