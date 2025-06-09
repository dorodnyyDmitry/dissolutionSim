import Data._
import scala.util.Random
import scala.collection.mutable.TreeMap

final case class Arcane(
    name: String,
    platValue: Int,
    vosforValue: Int,
    dissolutionChance: Double
)

final case class DropEvent(
    pool: List[Arcane]
) {
  // turns all weights into cumulative value, needed for rng implementation
  // @scala.annotation.tailrec - only called once for arcane list, CBA making it tail recursive -_-
  def helper(l: List[Int], acc: Int = 0): List[Int] = {
    l match {
      case Nil          => Nil
      case head :: next => head + acc :: helper(next, head + acc)
    }
  }

  // get weights
  val accumulatedWeights: List[Int] =
    helper(pool.map(a => (a.dissolutionChance * 10000).toInt))

  // attach weights to arcanes
  val weightedPool = TreeMap(accumulatedWeights.zip(pool): _*)

  // get arcane from correct bucket
  def getRandom: Arcane = {
    val rand = Random.nextInt(10000)
    weightedPool.dropWhile(_._1 <= rand).head._2
  }

  // get N random arcanes
  def getRandom(n: Int): List[Arcane] = (1 to n).toList.map(_ => getRandom)
}

object Diss extends App {
  val res = casino(loidEvent, 100000)
  println(
    Data.countOccs(res).toSeq.sortBy(_._1.vosforValue).foreach { case (a, n) =>
      println(s"${a.name}\t$n")
    }
  )
  println(s"final profit: ${res.map(_.platValue).sum}")
}

object Data {
  // flag to print debug misc info
  val debug = false

  // all Eidolon arcanes (weights from Loid casino)
  val loidEvent = DropEvent(
    List(
      Arcane("Consequence", 3, 14, 0.0667),
      Arcane("Ice", 2, 14, 0.0667),
      Arcane("Momentum", 3, 14, 0.0667),
      Arcane("Nullifier", 4, 14, 0.0667),
      Arcane("Tempo", 3, 14, 0.0667),
      Arcane("Warmth", 2, 14, 0.0667),
      Arcane("Acceleration", 6, 21, 0.0269),
      Arcane("Agility", 4, 21, 0.0269),
      Arcane("Awakening", 3, 21, 0.0269),
      Arcane("Deflection", 3, 21, 0.0269),
      Arcane("Eruption", 4, 21, 0.0269),
      Arcane("Guardian", 6, 21, 0.0269),
      Arcane("Healing", 4, 21, 0.0269),
      Arcane("Phantasm", 3, 21, 0.0269),
      Arcane("Resistance", 4, 21, 0.0269),
      Arcane("Strike", 6, 21, 0.0269),
      Arcane("Trickery", 4, 21, 0.0269),
      Arcane("Velocity", 11, 21, 0.0269),
      Arcane("Victory", 3, 21, 0.0269),
      Arcane("Aegis", 15, 28, 0.025),
      Arcane("Arachne", 4, 28, 0.025),
      Arcane("Avenger", 9, 28, 0.025),
      Arcane("Fury", 11, 28, 0.025),
      Arcane("Precision", 10, 28, 0.025),
      Arcane("Pulse", 3, 28, 0.025),
      Arcane("Rage", 5, 28, 0.025),
      Arcane("Ultimatum", 4, 28, 0.025),
      Arcane("Barrier", 6, 98, 0.0167),
      Arcane("Energize", 20, 98, 0.0167),
      Arcane("Grace", 10, 98, 0.0167)
    )
  )

  // simulate a casino trip to Loid
  def casino(dropEvent: DropEvent, vosfor: Int): List[Arcane] = {
    // buy 3 arcanes, dissolve shit ones, repeat until out of vosfor
    @scala.annotation.tailrec
    def helper(vosfor: Int, loot: List[Arcane]): List[Arcane] = {
      val numDissolutions: Int = vosfor / 200
      val dissolutionResult: List[Arcane] = dropEvent.getRandom(3*numDissolutions)
      val (valuables, dissolved) = dissolutionResult.partition(_.platValue >= 9)
      val newVosfor = vosfor - numDissolutions*200 + dissolved.map(_.vosforValue).fold(0)(_ + _)

      if (debug)
        println(
          s"got arcanes:\n${dissolutionResult.map(_.name)}\n vosfor left: $newVosfor"
        )

      if (newVosfor >= 200) helper(newVosfor, loot ::: valuables) else loot
    }

    helper(vosfor, Nil)
  }

  // utility to count hot much of each arcane in the list
  def countOccs(arcanes: List[Arcane]): Map[Arcane, Int] =
    Map.from(arcanes.groupBy(identity).mapValues(_.size))

}
