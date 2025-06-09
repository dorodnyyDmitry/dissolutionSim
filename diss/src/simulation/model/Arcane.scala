package simulation.model

final case class Arcane(
      name: String,
      platValue: Int,
      vosforValue: Int,
      dissolutionChance: Double
  ) {
    override def toString() = this.name
  }
