package nl.sass.gamesimulator

import nl.sass.gamesimulator.d20.inventory.characters
import nl.sass.gamesimulator.d20.{D20CheckResolution, D20GameRules, DefaultD20GameRules}
import nl.sass.gamesimulator.d20.model.{Action, ActionType, Stats}
import nl.sass.gamesimulator.dice.{DiceBasedEffectResolution, DieResolution, RandomResolution}

object SimulationBuilder {

  def aDefaultD20Simulation = SimulationBuilder(
    gameRules = D20GameRules.toGenericGameRules(DefaultD20GameRules),
    scenario = D20Scenario.daveHitsChuck,
    resolutions = D20Resolutions.default(RandomResolution)
  )
}

case class SimulationBuilder(
  gameRules: GameRules[Stats],
  scenario: Scenario[Stats],
  resolutions: Resolutions[Stats],
) {
  def run(): Seq[Event] = {
    val resolvable = ActionPerforming.perform(scenario.action)(scenario.duel, gameRules)
    ActionResolution.resolve(resolvable)(resolutions.checkResolution, resolutions.effectResolution)
  }

  def withDieResolution(dieResolution: DieResolution) =
    copy(
      resolutions = D20Resolutions.default(dieResolution)
    )
}

object D20Scenario {
  val daveHitsChuck = Scenario(
    action = Action(
      actionType = ActionType.SingleAttack,
      from = characters.dave.name,
      target = characters.chuck.name
    ),
    duel = Duel(characters.dave, characters.chuck)
  )
}

case class Scenario[S](
  action: Action,
  duel: Duel[S]
)

object D20Resolutions {
  def default(implicit r: DieResolution) =
    Resolutions[Stats](
      checkResolution = new D20CheckResolution(),
      effectResolution = new DiceBasedEffectResolution(),
    )
}

case class Resolutions[S](
  checkResolution: CheckResolution,
  effectResolution: EffectResolution[S],
)