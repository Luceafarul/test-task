package model

import java.time.LocalDate

final case class Plane(year: Int,
                       quarter: Int,
                       month: Int,
                       dayOfMonth: Int,
                       dayOfWeek: Int,
                       flyDate: LocalDate,
                       origin: String,
                       dest: String)

object Plane {
  def toPlane(year: String,
              quarter: String,
              month: String,
              dayOfMonth: String,
              dayOfWeek: String,
              flyDate: String,
              origin: String,
              dest: String): Plane = {
    Plane(Integer.valueOf(year), Integer.valueOf(quarter),
      Integer.valueOf(month), Integer.valueOf(dayOfMonth),
      Integer.valueOf(dayOfWeek), LocalDate.parse(flyDate), origin, dest)
  }
}
