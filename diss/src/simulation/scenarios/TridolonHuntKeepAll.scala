package simulation.scenarios

import simulation.scenarios.model.Scenario
import simulation.model.TeralystCapturedEvent
import simulation.model.GantulystCapturedEvent
import simulation.model.HydrolystCapturedEvent
import simulation.model.Arcane
import simulation.model.DropEvent
import simulation.util.ShowOps._

//loot from numHunts hunts
case class TridolonHuntKeepAll(numHunts: Int) extends Scenario {
  override def run(): Unit = {
    showOccs(countOccs(totalLoot))
  }

  val events: List[DropEvent] = List(
    TeralystCapturedEvent.dropEvent,
    GantulystCapturedEvent.dropEvent,
    HydrolystCapturedEvent.dropEvent
  )

  val totalLoot: List[Arcane] = events.flatMap(_.getRandom(numHunts))


}
