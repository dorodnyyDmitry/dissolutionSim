package simulation.util

import kantan.csv._
import simulation.model.Arcane
import kantan.csv.ops._
import kantan.csv.generic._
import scala.util.Using

object DropTableLoader {
  def loadDropTable(path: String): List[Arcane] = {
    val resourceUrl = getClass.getResource(s"/droptables/$path")
    Using.resource(scala.io.Source.fromURL(resourceUrl)) { source =>
      source.mkString
        .asCsvReader[Arcane](rfc.withoutHeader)
        .toList
        .partitionMap(identity) match {
        case (Nil, items) => items
        case (errors, _) => {
          println(s"Error while parsing: $errors"); Nil
        } // cba error handling :clown_face:
      }
    }
  }
}
