package nl.sass.dsl

import nl.sass.dice

trait DiceableMagn[-D] {
  def toDice[SD <: D](value: SD): dice
}

trait SimpleDiceableMagn[-D] extends DiceableMagn[D] {
  final def toDice[SD <: D](value: SD): dice = toSimpleDice(value)

  def toSimpleDice[SD <: D](value: SD): dice.simple
}