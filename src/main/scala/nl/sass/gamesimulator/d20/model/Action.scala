package nl.sass.gamesimulator.d20.model

import nl.sass.gamesimulator.{ AttackType, Check }
import nl.sass.gamesimulator.d20.model.ActionDuration._

sealed abstract class ActionType(val duration: ActionDuration)

object ActionType {

  case object SingleAttack extends ActionType(Standard) with AttackType[Stats]

}

sealed trait ActionDuration

object ActionDuration {

  case object MoveEquivalent extends ActionDuration

  case object Standard extends ActionDuration

  case object FullRound extends ActionDuration

}

case class DifficultyCheck(bonus: Bonus, challenge: Int) extends Check