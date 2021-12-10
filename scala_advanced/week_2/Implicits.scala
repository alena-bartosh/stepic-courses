package week_2

import scala.language.implicitConversions


object Implicits extends App {

  // NOTE: The main purpose of Implicits is to eliminate code duplication
  //       when several methods need to pass the same parameters or when type conversion is required

  case class Course(title: String) {
    def info = s"This is $title course"
    def platform(implicit name: String) = s"Course platform: $name"
  }

  implicit def toCourse(title: String): Course = Course(title)
  implicit val defaultPlatform: String = "Stepik"

  println("Scala".info)  // This is Scala course
  println("Scala".platform) // Course platform: Stepik

  case class Course2(id: Int, title: String)

  val courses = List(
    Course2(0, "Scala"),
    Course2(1, "Advanced Scala"),
    Course2(4, "Spark"),
    Course2(3, "Cats"),
    Course2(3, "Acourse")
  )

//  implicit val titleOrdering: Ordering[Course2] = Ordering.by(c => (c.title))
//  println(courses.sorted) // List(Course(1,Advanced Scala), Course(3,Cats), Course(0,Scala), Course(4,Spark))

  // Task 2.1.3
  implicit val courseOrdering: Ordering[Course2] = Ordering.by(c => (c.id, c.title))
  println(courses.sorted)

  // Task 2.1.7
  case class Person(age: Int) {
    def increaseAge(): Unit = println(age + 1)
  }

  object Person {
    implicit def str2Person(param: String): Person = Person(param.toInt)
  }

  object Helper {
    implicit def int2Str(v: Int): String = v.toString
  }

  import Person._

  val age: String = "42"
  println(age.increaseAge())

  // Task 2.1.8
  case class Price(price: Int)

  object Price {
    implicit class PriceOps(p: Price)  {
      def -(n: Int): Price = Price(p.price - n)
    }
  }

  val price = 5
  val discount = 1
  println(Price(price) - discount)
}
