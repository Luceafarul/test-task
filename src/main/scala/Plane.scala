import java.time.LocalDate

case class Plane(year: Int,
                 quarter: Int,
                 month: Int,
                 dayOfMonth: Int,
                 dayOfWeek: Int,
                 flyDate: LocalDate,
                 origin: String,
                 dest: String)

object Plane {
  implicit def toPlane(tuple: Tuple8[String, String, String, String, String, String, String, String]): Plane = {
    val (year: String, quarter: String, month: String, dayOfMonth: String, dayOfWeek: String, flyDate: String, origin: String, dest: String) = tuple
    Plane.apply(Integer.valueOf(year), Integer.valueOf(quarter), Integer.valueOf(month), Integer.valueOf(dayOfMonth), Integer.valueOf(dayOfWeek), LocalDate.parse(flyDate), origin, dest)
  }
}

//"YEAR","QUARTER","MONTH","DAY_OF_MONTH","DAY_OF_WEEK","FL_DATE","ORIGIN","DEST",