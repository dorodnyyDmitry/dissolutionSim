import scala.util.Random
import scala.collection.mutable.TreeMap
import simulation.model.{Arcane, DropEvent}
import simulation.model.LoidEidolonEvent
import simulation.scenarios.LoidEidolonCasino
import simulation.scenarios.TridolonHuntKeepAll
import simulation.scenarios.TridolonHuntWithCasino
import simulation.scenarios.LoidEidolonKeepAll

object Diss extends App {
  // LoidEidolonCasino(200000, 9).run()
  // TridolonHuntKeepAll(1000000).run()
  TridolonHuntWithCasino(1000, 9).run()
  // LoidEidolonKeepAll(200000000).run()
}
