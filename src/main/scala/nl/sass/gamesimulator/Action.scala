package nl.sass.gamesimulator

case class Action[S](actionType: ActionType[S], from: Name, target: Name)

sealed trait ActionType[S]
case class Attack[S](attackType: AttackType[S]) extends ActionType[S]

trait AttackType[S]