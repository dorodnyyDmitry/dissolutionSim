package simulation.scenarios

import simulation.scenarios.model.Scenario
import simulation.model.TeralystCapturedEvent
import simulation.model.GantulystCapturedEvent
import simulation.model.HydrolystCapturedEvent
import simulation.model.Arcane
import simulation.model.DropEvent
import simulation.util.ShowOps._
import simulation.scenarios.model.CasinoScenario
import simulation.model.LoidEidolonEvent

//do numHunts hunts, then dissolve shit arcanes and gamble
case class TridolonHuntWithCasino(numHunts: Int, platThreshold: Int)
    extends Scenario
    with CasinoScenario {
  override def run(): Unit = {
    println(totalLoot.length)
    showOccs(countOccs(totalLoot))
    println(s"final profit: ${totalLoot.map(_.platValue).sum}")
  }

  val events: List[DropEvent] = List(
    TeralystCapturedEvent.dropEvent,
    GantulystCapturedEvent.dropEvent,
    HydrolystCapturedEvent.dropEvent
  )

  val loidCasinoEvent: DropEvent = LoidEidolonEvent.dropEvent

  val huntsLoot: List[Arcane] = events.flatMap(_.getRandom(numHunts))

  val (valuables, dissolution) =
    huntsLoot.partition(_.platValue >= platThreshold)

  def huntsVosfor: Int = dissolution.map(_.vosforValue).sum

  def casinoLoot: List[Arcane] =
    casino(huntsVosfor, platThreshold, loidCasinoEvent)

  def totalLoot: List[Arcane] = casinoLoot ++ valuables
}
