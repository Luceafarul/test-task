import java.io.File
import java.time.Month

import statistic.Statistics
import utils.CSVOps

object App extends App {
  private val path = "/Users/yaroslav/Documents/scala/test-task/src/main/resources/planes_log.csv"

  val listOfPlanes = CSVOps.loadCsv(path)

  val destStatWholeTime = Statistics.destWholeTime(listOfPlanes)

  val nonZeroDifference = Statistics.nonZeroDifferenceInTotalOfPlanes(listOfPlanes)

  val destStatPerWeek = Statistics.destPerWeek(listOfPlanes)

  import kantan.csv._
  import kantan.csv.ops._
  var out: File = new File("src/main/resources/dest_stat_whole_time.csv")
  var writerOne = out.asCsvWriter[(String, Int)](rfc.withHeader("DEST", "COUNT"))
  writerOne.write(destStatWholeTime)
  writerOne.close()

  out = new File("src/main/resources/non_zero_diff_stat.csv")
  val writerTwo = out.asCsvWriter[(String, Int)](rfc.withHeader("DEST", "COUNT"))
  writerTwo.write(nonZeroDifference)
  writerTwo.close()

  out = new File("src/main/resources/dest_stat_per_week.csv")
  val writerThree = out.asCsvWriter[(String, Month, Int, Int)](rfc.withHeader("DEST", "MONTH", "WEEK_OF_MONTH", "COUNT"))
  writerThree.write(destStatPerWeek.toArray.sortBy(_._1))
  writerThree.close()
}