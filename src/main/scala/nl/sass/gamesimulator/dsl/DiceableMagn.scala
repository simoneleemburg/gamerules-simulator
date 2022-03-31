package nl.sass.gamesimulator.dsl

import nl.sass.gamesimulator.dice.Dice

trait DiceableMagn[-D] {
  def toDice[SD <: D](value: SD): Dice
}

trait SimpleDiceableMagn[-D] extends DiceableMagn[D] {
  final def toDice[SD <: D](value: SD): Dice = toSimpleDice(value)

  def toSimpleDice[SD <: D](value: SD): Dice.simple
}