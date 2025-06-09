package simulation.scenarios.model

import simulation.model.Arcane
import simulation.model.DropEvent

trait CasinoScenario {
  def casino(vosfor: Int, platThreshold: Int = 0, dropEvent: DropEvent): List[Arcane] = {
    // buy 3 arcanes, dissolve shit ones, recursively repeat until out of vosfor
    @scala.annotation.tailrec
    def helper(vosfor: Int, loot: List[Arcane]): List[Arcane] = {
      val numDissolutions: Int = vosfor / 200
      val dissolutionResult: List[Arcane] =
        dropEvent.getRandom(3 * numDissolutions)
      val (valuables, dissolved) =
        dissolutionResult.partition(_.platValue >= platThreshold)
      val newVosfor = vosfor - numDissolutions * 200 + dissolved
        .map(_.vosforValue)
        .fold(0)(_ + _)

      if (false)
        println(
          s"got arcanes:\n${dissolutionResult.map(_.name)}\n vosfor left: $newVosfor"
        )

      if (newVosfor >= 200) helper(newVosfor, loot ::: valuables) else loot ::: valuables
    }

    helper(vosfor, Nil)
  }
}
