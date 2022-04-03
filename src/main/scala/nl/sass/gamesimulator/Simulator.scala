package nl.sass.gamesimulator

// $COVERAGE-OFF$
object Simulator extends App {

  val events = SimulationBuilder.aDefaultD20Simulation.run()

  println(events)
}
// $COVERAGE-ON$