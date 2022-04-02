package nl.sass.gamesimulator

case class Character[S](name: Name, stats: S)

case class Name(value: String)
