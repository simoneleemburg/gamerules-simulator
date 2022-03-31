package nl.sass.gamesimulator.dice

import nl.sass.gamesimulator.Outcome._
import nl.sass.gamesimulator.dsl._
import nl.sass.gamesimulator.{CheckResolution, _}

class DiceBasedCheckResolution(implicit dieResolution: DieResolution) extends CheckResolution {
  override def resolve(challenge: Check): Outcome = challenge match {
    case dc: DifficultyCheck => DiceResolutions.d20Challenge(dc)
  }

}

object DiceResolutions {
  def d20Challenge(dc: DifficultyCheck)(implicit dieResolution: DieResolution): Outcome =
    d.d20.roll() match {
      case Result(20) => CriticalSuccess
      case Result(1) => CriticalFailure
      case Result(value) if value + dc.bonus.value >= dc.challenge => Success
      case _ => Failure
    }
}