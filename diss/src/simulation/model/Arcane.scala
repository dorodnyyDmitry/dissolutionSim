package simulation.model

final case class Arcane(
      name: String,
      platValue: Int,
      vosforValue: Int,
      dropChance: Double
  ) {
    override def toString() = this.name
  }
