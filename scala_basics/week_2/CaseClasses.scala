package week_2

// NOTE: We should to avoid ordinary classes and use case classes which have:
//       - immutability
//       - element-wise comparison
//       - copyability
//       - console readable output

case class CasePerson(name: String, occupation: String)
// 1) The default class parameters are val fields
// 2) Information is displayed in an understandable form
// 3) equals() method
// 4) copy() method
// 5) Any case class has a companion object with apply() method

object CaseClasses extends App {
  val aPerson = new CasePerson("Alena", "Data Science")
  println(aPerson)

  val aPersonDouble = new CasePerson("Alena", "Data Science")
  // It is content level equality. Ordinary classes use reference level equality
  println(aPerson == aPersonDouble) // true

  val aTwin = aPerson.copy(name = "John")
  println(aTwin) // Person(John,Data Science)

  val alice = CasePerson("Alice", "Engineer") // use apply() from Object instead of new
}
