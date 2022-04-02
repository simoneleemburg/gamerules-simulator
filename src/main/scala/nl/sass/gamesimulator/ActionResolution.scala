package nl.sass.gamesimulator

object ActionResolution {

  def resolve[E <: Effect, S](resolvableAction: ResolvableAction[E, S])(checkResolution: CheckResolution, effectResolution: EffectResolution[S]): Seq[Event] = {
    val effect = resolvableAction.challenge.resolve(checkResolution)
    effectResolution.resolve(resolvableAction, effect)
  }
}

trait CheckResolution {
  def resolve(check: Check): Outcome
}

trait EffectResolution[S] {
  def resolve[E <: Effect](resolvableAction: ResolvableAction[E, S], effect: E): Seq[Event]
}