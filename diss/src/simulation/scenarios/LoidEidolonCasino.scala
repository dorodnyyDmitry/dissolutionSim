package simulation.scenarios

import simulation.model.DropEvent
import simulation.model.Arcane
import simulation.model.EventType
import simulation.model.LoidEidolonEvent
import simulation.util.ShowOps.countOccs
import simulation.util.ShowOps.showOccs
import simulation.scenarios.model._

/*roll eidolon arcane dissolution until, keep expensive arcanes and dissolve the rest.
 Continue until out of vosfor
*/
case class LoidEidolonCasino(vosfor: Int, platThreshold: Int)
    extends Scenario
    with CasinoScenario {
  val dropEvent = LoidEidolonEvent.dropEvent

  override def run(): Unit = {
    val res = casino(vosfor, platThreshold, dropEvent)
    showOccs(countOccs(res))
    println(s"final profit: ${res.map(_.platValue).sum}")
  }

}
