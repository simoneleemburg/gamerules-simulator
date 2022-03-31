package nl.sass.gamesimulator.dice

sealed abstract class d(val sides: Int)

object d {

  case object d4 extends d(4)

  case object d6 extends d(6)

  case object d8 extends d(8)

  case object d10 extends d(10)

  case object d12 extends d(12)

  case object d20 extends d(20)

}

sealed trait Dice

object Dice {

  sealed trait simple extends Dice

  case class die(d: d, number: Int) extends simple

  case class constant(amount: Int) extends simple

  case class addition(simples: List[simple]) extends Dice

}

object resolution {

  def resolve(d: Dice, dieResolution: DieResolution): Result =
    d match {
      case Dice.die(die, n) => (0 until n).foldLeft(Result(0)) {
        case (collect, _) => collect + dieResolution.roll(die)
      }
      case Dice.constant(c) => Result(c)
      case Dice.addition(sums) => sums.foldLeft(Result(0))(_ + resolve(_, dieResolution))
    }
}

trait DieResolution {
  def roll(d: d): Result
}

object RandomResolution extends DieResolution {
  override def roll(d: d): Result = Result(scala.util.Random.nextInt(d.sides) + 1)
}

object MaximumRollResolution extends DieResolution {
  override def roll(d: d): Result = Result(d.sides)
}

case class Result(value: Int) {
  def +(other: Result) = Result(value + other.value)
}