package nl.sass.gamesimulator

case class Duel[S](character1: Character[S], character2: Character[S]) {
  def getCharacter(byName: Name): Character[S] =
    if (character1.name == byName) character1 else character2
}