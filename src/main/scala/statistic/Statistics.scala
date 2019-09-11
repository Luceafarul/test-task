package statistic

import java.time.Month
import java.time.temporal.WeekFields
import java.util.Locale

import model.Plane

object Statistics {
  def destWholeTime(planes: List[Plane]): List[(String, Int)] = for {
    dest <- planes.map(_.dest).distinct
    count = planes.count(_.dest.equalsIgnoreCase(dest))
  } yield dest -> count

  def nonZeroDifferenceInTotalOfPlanes(planes: List[Plane]): Set[(String, Int)] = for {
    airport <- allDistinctAirports(planes)
    originCount = planes.count(_.origin.equalsIgnoreCase(airport))
    destCount = planes.count(_.dest.equalsIgnoreCase(airport))
    count = originCount - destCount
    if count != 0
  } yield airport -> count

  def destPerWeek(planes: List[Plane]): Set[(String, Month, Int, Int)] = {
    val weekFields = WeekFields.of(Locale.getDefault)
    val result = for {
      airport <- allDistinctAirports(planes)
      date <- planes.filter(_.dest.equalsIgnoreCase(airport)).map(_.flyDate).distinct
      weekOfMonth = date.get(weekFields.weekOfMonth())
      count = planes.count { plane =>
        plane.dest.equalsIgnoreCase(airport) &&
          plane.flyDate.get(weekFields.weekOfMonth()) == weekOfMonth
      }
      month = date.getMonth
    } yield (airport, month, weekOfMonth, count)
    result
  }

  private def allDistinctAirports(listOfPlanes: List[Plane]): Set[String] = {
    (listOfPlanes.map(_.origin) ++ listOfPlanes.map(_.dest)).toSet[String]
  }

}
