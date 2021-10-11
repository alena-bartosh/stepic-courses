package week_2

import scala.language.postfixOps

class Persona(val name: String, occupation: String) {
  def worksAs(jobName: String): Boolean = jobName == occupation

  def speaksEnglish: Boolean = true

  def &(person: Persona): String = s"${this.name} and ${person.name} are colleagues"

  def unary_! : String = s"$name is not real"

  def apply(): String = s"$name works as a $occupation"
}

// Task 3.6.10
class PersonForTask(val name: String) {
  def unary_+ : PersonForTask = new PersonForTask(s"$name NoSurname")
}

object SyntacticSugar extends App {

  val bob = new Persona("Bob", "Developer")

  // NOTE: Dot notation is preferred on practice
  println(bob.worksAs("Developer")) // Dot notation
  println(bob worksAs "Developer") // Infix notation (only for methods with 1 param)
  println(bob speaksEnglish) // Postfix notation (only for methods with w/o params)

  // NOTE: operators in Scala are actually methods
  println(1 + 2)
  println(1.+(2))

  val alice = new Persona("Alice", "Data Engineer")

  println(bob.&(alice)) // Dot notation
  println(bob & alice) // Infix notation

  val x = -1 // Prefix notation
  val y = 1.unary_- // unary_ is used only for + - ~ !

  println(!bob) // Prefix notation
  println(bob.unary_!) // Dot notation
  println(bob unary_!) // Postfix notation

  println(bob.apply())
  println(bob()) // is used more often, call apply()

  val person = new PersonForTask("Bob")
  println((+person).name)
}
