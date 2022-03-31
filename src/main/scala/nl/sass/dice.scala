package nl.sass


sealed abstract class d(val sides: Int)

object d {

  case object d4 extends d(4)

  case object d6 extends d(6)

  case object d8 extends d(8)

  case object d10 extends d(10)

  case object d12 extends d(12)

  case object d20 extends d(20)

}

sealed trait dice

object dice {

  sealed trait simple extends dice

  case class die(d: d, number: Int) extends simple

  case class constant(amount: Int) extends simple

  case class addition(simples: List[simple]) extends dice

}

object resolution {

  def resolve(d: dice, dieResolution: dieResolution): result =
    d match {
      case dice.die(die, n) => (0 until n).foldLeft(result(0)) {
        case (collect, _) => collect + dieResolution.roll(die)
      }
      case dice.constant(c) => result(c)
      case dice.addition(sums) => sums.foldLeft(result(0))(_ + resolve(_, dieResolution))
    }
}

trait dieResolution {
  def roll(d: d): result
}

class randomResolution extends dieResolution {
  override def roll(d: d): result = result(scala.util.Random.nextInt(d.sides) + 1)
}

case class result(value: Int) {
  def +(other: result) = result(value + other.value)
}