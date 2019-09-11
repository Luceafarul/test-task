package utils

import kantan.csv.ops._
import kantan.csv.{RowDecoder, rfc}
import model.Plane

import scala.collection.mutable
import scala.io.Source

object CSVOps {
  implicit val planeToDecoder: RowDecoder[Plane] = RowDecoder.decoder(0, 1, 2, 3, 4, 5, 6, 7)(Plane.toPlane)

  def loadCsv(path: String): List[Plane] = {
    val bufferedSource = Source.fromFile(path)
    val list: mutable.ArrayBuffer[Array[String]] = mutable.ArrayBuffer()
    for (line <- bufferedSource.getLines()) {
      val cols: Array[String] = line.split(",").map(_.trim)
      list += cols
    }
    bufferedSource.close()

    val res = list.map(as => as.mkString(",")).mkString("\n")

    val reader = res.asCsvReader[Plane](rfc.withHeader)
    val listOfPlanes = reader.filter(_.isRight).map(_.right.get).toList
    listOfPlanes
  }
}
