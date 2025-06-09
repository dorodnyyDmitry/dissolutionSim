package simulation.util

import simulation.model.Arcane

object ShowOps {
  //strip weight to group same arcanes from different sources
  case class Weightless(
      name: String,
      platValue: Int,
      vosforValue: Int
  ) {
    override def toString() = this.name
  }
  object Weightless {
    def apply(arcane: Arcane): Weightless = Weightless(
      arcane.name,
      arcane.platValue,
      arcane.vosforValue
    )
  }
// utility to count hot much of each arcane in the list
  def countOccs(arcanes: List[Arcane]): Map[Weightless, Int] =
    Map.from(
      arcanes.map(a => Weightless(a)).groupBy(identity).mapValues(_.size)
    )

  def showOccs(arcanes: Map[Weightless, Int]): Unit =
    println(
      arcanes.toSeq.sortBy(_._1.vosforValue).foreach { case (a, n) =>
        println(s"$a\t$n")
      }
    )
}
