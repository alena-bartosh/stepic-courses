package week_4

import scala.io.StdIn.readLine

// Task 5.6.8

// NOTE: extractor object is an object with unapply.
//       While the apply method usually acts like a constructor,
//       the unapply method takes an object and tries to fetch
//       and return the arguments from which it (possibly) was created

object Human {
  def apply(nameSurname: String): String = nameSurname

  def unapply(nameSurname: String): Option[String] = {
    if (nameSurname != null && nameSurname.nonEmpty) {
      Some(nameSurname.split(" ").map(_.head).flatMap(char => (s"$char. ")).mkString)
    } else None
  }
}

object MyPattern2 extends App {

  val inputData = readLine() // It will be a string like "Name Surname"

  val initials: String = Human.unapply(inputData) match {
    case Some(string) => string
    case None => "Oops, something is wrong"
  }

  println(initials)
}
