package simulation.scenarios

import simulation.scenarios.model.Scenario
import simulation.scenarios.model.CasinoScenario
import simulation.util.ShowOps.showOccs
import simulation.util.ShowOps.countOccs
import simulation.model.LoidEidolonEvent

//arcane dissolution, keep all arcanes
case class LoidEidolonKeepAll(vosfor: Int) extends Scenario with CasinoScenario {
    val dropEvent = LoidEidolonEvent.dropEvent

    override def run(): Unit = {
        showOccs(countOccs(casino(vosfor, 0, dropEvent)))
    }
}