package week_2

class Student(id: Int, val name: String) {
  val university = "University"

  def getId(name: String, id: Int): String = {
    s"${this.name} has ID ${this.id} and $name has ID $id"
  }

  def getId: String = "I am overloaded getId()"
}

class Human(id: Int, val name: String) {
  def this(name: String) = this(0, name)

  def this() = this(0, "Noname")
}

object OOPBasics extends App {
  val student = new Student(0, "Alena")
  println(student.getId("Noname", 1)) // Alena has ID 0 and Noname has ID 1
  println(student.getId) // I am overloaded getId()

  val noHuman = new Human() // 0, Noname
  val newHuman = new Human("Will") // 0, Will
  val human = new Human(1, "Sam") // 1, Sam
}
