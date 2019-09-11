import java.time.{LocalDate, Month}

import model.Plane
import org.specs2.Specification
import org.specs2.matcher.MatchResult
import statistic.Statistics._

class StatisticSpec extends Specification {
  def is =
    s2"""

 This is a specification to check the 'Statistics' methods

 The 'Statistics' method should
   return list of airport and total planes that arrived to each one                                 $e1
   return non-zero difference in total number of planes that arrived to and left from the airport   $e2
   return arrived statistic by dest airport per week                                                $e3
                                                                 """

  val planes = List(
    Plane(2014, 1, 1, 1, 3, LocalDate.of(2014, 1, 2), "JFK", "LAX"),
    Plane(2014, 1, 1, 2, 4, LocalDate.of(2014, 6, 21), "JFK", "LAX"),
    Plane(2014, 1, 1, 12, 7, LocalDate.of(2014, 11, 30), "JFK", "LAX"),
    Plane(2014, 1, 1, 17, 5, LocalDate.of(2014, 7, 28), "LAX", "JFK"),
    Plane(2014, 1, 1, 14, 2, LocalDate.of(2014, 9, 7), "LAX", "JFK")
  )

  def e1: MatchResult[List[(String, Int)]] = destWholeTime(planes) must equalTo(List("LAX" -> 3, "JFK" -> 2))

  def e2: MatchResult[Set[(String, Int)]] = nonZeroDifferenceInTotalOfPlanes(planes) must equalTo(Set("LAX" -> -1, "JFK" -> 1))

  def e3: MatchResult[Set[(String, Month, Int, Int)]] = destPerWeek(planes) must equalTo(Set(
    ("LAX", Month.NOVEMBER, 6, 1), ("LAX", Month.JANUARY, 1, 1), ("LAX", Month.JUNE, 3, 1),
    ("JFK", Month.SEPTEMBER, 2, 1), ("JFK", Month.JULY, 5, 1)
  ))
}