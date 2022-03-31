package nl.sass.gamesimulator

sealed trait Event

sealed trait EffectEvent extends Event

case class CharacterWasDamaged(hit: Name, by: Name, damageReceived: Int) extends EffectEvent