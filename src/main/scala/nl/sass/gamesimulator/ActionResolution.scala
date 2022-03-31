package nl.sass.gamesimulator

object ActionResolution {

  def resolve[E <: Effect](resolvableAction: ResolvableAction[E])(checkResolution: CheckResolution, effectResolution: EffectResolution): Seq[Event] = {
    val effect = resolvableAction.challenge.resolve(checkResolution)
    effectResolution.resolve(resolvableAction, effect)
  }
}

trait CheckResolution {
  def resolve(check: Check): Outcome
}

trait EffectResolution {
  def resolve[E <: Effect](resolvableAction: ResolvableAction[E], effect: E): Seq[Event]
}