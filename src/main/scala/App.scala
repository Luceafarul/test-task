import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

import scala.collection.mutable
import scala.io.Source

object App extends App {
  println("YEAR, QUARTER, MONTH, DAY_OF_MONTH, DAY_OF_WEEK, FL_DATE, ORIGIN, DEST")
  val bufferedSource = Source.fromFile("/Users/yaroslav/Documents/scala/test-task/src/main/resources/simple_log.csv")
  val list: mutable.ArrayBuffer[Array[String]] = mutable.ArrayBuffer()
  for (line <- bufferedSource.getLines()) {
    val cols: Array[String] = line.split(",").map(_.trim)
    list += cols
  }
  bufferedSource.close()

  println("------------------------------")
  println("Task one")
  println("------------------------------")

  // Task one
  val dest = list.map(a => a(7)).distinct
  val origin = list.map(a => a(6)).distinct
  val airports = (dest ++ origin).toSet
  val uniq = for {
    d <- dest
    count = list.count(a => a(7).equalsIgnoreCase(d))
  } yield (d, count)
  uniq.foreach(println)

  println("------------------------------")
  println("Task two")
  println("------------------------------")

  // Task two
  val task2 = for {
    airport <- airports
    originCount = list.count(a => a(6).equalsIgnoreCase(airport))
    destCount = list.count(a => a(7).equalsIgnoreCase(airport))
    count = originCount - destCount
    if count != 0
  } yield (airport, count)
  task2.foreach(println)

  println("------------------------------")
  println("Task three")
  println("------------------------------")

  // Task three
  val weekFields = WeekFields.of(Locale.getDefault)

  val task3 = for {
    airport <- airports
    date <- list.filter(a => a(7).equalsIgnoreCase(airport)).map(a => LocalDate.parse(a(5)))
    weekOfMonth = date.get(weekFields.weekOfMonth())
    count = list.count(a => a(7).equalsIgnoreCase(airport) && LocalDate.parse(a(5)).get(weekFields.weekOfMonth()) == weekOfMonth)
    month = date.getMonth
  } yield (airport, month, weekOfMonth, count)
  task3.foreach(println)
}