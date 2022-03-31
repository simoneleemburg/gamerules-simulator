package nl

import nl.sass.dsl._

package object sass {

  implicit class number(number: Int) {
    def d4 = dice.die(d.d4, number)

    def d6 = dice.die(d.d6, number)

    def d8 = dice.die(d.d8, number)

    def d10 = dice.die(d.d10, number)

    def d12 = dice.die(d.d12, number)

    def d20 = dice.die(d.d20, number)
  }

  implicit class dHelper[D](d: D) {
    def +[DD](other: DD)(implicit magnet: DiceableMagn[D], magnet2: SimpleDiceableMagn[DD]): dice.addition =
      magnet.toDice(d) match {
        case simple: dice.simple =>
          dice.addition(List(
            simple,
            magnet2.toSimpleDice(other)
          ))
        case dice.addition(items) =>
          dice.addition(items :+ magnet2.toSimpleDice(other))
      }
  }

  implicit val simpleDiceMagnet: SimpleDiceableMagn[dice.simple] = new SimpleDiceableMagn[dice.simple] {
    def toSimpleDice[SD <: dice.simple](value: SD): dice.simple = value
  }

  implicit val additionDiceMagnet: DiceableMagn[dice.addition] = new DiceableMagn[dice.addition] {
    def toDice[SD <: dice.addition](value: SD): dice.addition = value
  }
  implicit val dMagnet: SimpleDiceableMagn[d] = new SimpleDiceableMagn[d] {
    def toSimpleDice[SD <: d](value: SD): dice.simple = dice.die(value, 1)
  }
  implicit val numMagnet: SimpleDiceableMagn[Int] = new SimpleDiceableMagn[Int] {
    def toSimpleDice[SD <: Int](value: SD): dice.simple = dice.constant(value)
  }

  implicit class diceHelper[D](diceable: D) {
    def roll()(implicit dieResolution: dieResolution, magnet: DiceableMagn[D]): result =
      resolution.resolve(magnet.toDice(diceable), dieResolution)
  }


}
