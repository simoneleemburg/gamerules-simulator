package nl.sass.gamesimulator.d20.model

import nl.sass.gamesimulator.Name
import nl.sass.gamesimulator.d20.model.ActionDuration._

sealed abstract class ActionType(val duration: ActionDuration)

object ActionType {

  case object SingleAttack extends ActionType(Standard)

}

case class Action(actionType: ActionType, from: Name, target: Name)

sealed trait ActionDuration

object ActionDuration {

  case object MoveEquivalent extends ActionDuration

  case object Standard extends ActionDuration

  case object FullRound extends ActionDuration

}