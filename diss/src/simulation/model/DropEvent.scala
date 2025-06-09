package simulation.model

import scala.collection.immutable.TreeMap
import scala.util.Random
import simulation.util.DropTableLoader.loadDropTable

final case class DropEvent(
    dropTable: String
) {
  val pool: List[Arcane] = loadDropTable(dropTable)
  // turns all weights into cumulative value, needed for rng implementation
  // @scala.annotation.tailrec - only called once for arcane list, CBA making it tail recursive -_-
  def helper(l: List[Int], acc: Int = 0): List[Int] = {
    l match {
      case Nil          => Nil
      case head :: next => head + acc :: helper(next, head + acc)
    }
  }

  // get total weight bc official drop tables arent 100% xdd
  val totalWeight: Int = (pool.map(_.dropChance).sum*10000).toInt

  // get weights
  val accumulatedWeights: List[Int] =
    helper(pool.map(a => (a.dropChance * 10000).toInt))

  // attach weights to arcanes
  val weightedPool = TreeMap(accumulatedWeights.zip(pool): _*)

  // get arcane from correct bucket
  def getRandom: Arcane = {
    val rand = Random.nextInt(totalWeight)
    weightedPool.dropWhile(_._1 <= rand).head._2
  }

  // get N random arcanes
  def getRandom(n: Int): List[Arcane] = (1 to n).toList.map(_ => getRandom)
}
