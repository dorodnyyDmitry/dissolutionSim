package simulation.util

import simulation.model.Arcane

object ShowOps {
// utility to count hot much of each arcane in the list
  def countOccs(arcanes: List[Arcane]): Map[Arcane, Int] =
    Map.from(arcanes.groupBy(identity).mapValues(_.size))

  def showOccs(arcanes: Map[Arcane, Int]): Unit = 
    println(
      arcanes.toSeq.sortBy(_._1.vosforValue).foreach {
        case (a, n) =>
          println(s"$a\t$n")
      }
    )
}
