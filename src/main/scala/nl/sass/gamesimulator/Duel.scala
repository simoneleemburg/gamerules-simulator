package nl.sass.gamesimulator

case class Duel(character1: Character, character2: Character) {
  def getCharacter(byName: Name): Character =
    if (character1.name == byName) character1 else character2
}

case class Combat(initiativeSequence: InitiativeSequence)

case class InitiativeSequence(characters: List[Character])

case class Turn(active: Character, upNext: Character)

