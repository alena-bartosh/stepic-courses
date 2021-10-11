package week_2

class Instructor(val id: Int, val name: String, val surname: String) {
  def fullName: String = {
    s"${this.name.toLowerCase().capitalize} ${this.surname.toLowerCase().capitalize}"
  }
}

class Course(courseID: Int, title: String, var releaseYear: String, instructor: Instructor) {
  def getID: String = s"${this.courseID}${instructor.id}"

  def isTaughtBy(instructor: Instructor): Boolean = this.instructor == instructor

  def copyCourse(newReleaseYear: String): Course = {
    new Course(this.courseID, this.title, newReleaseYear, this.instructor)
  }
}

// Task 3.1.13
object TaskWithInstructor extends App {
  val instructor = new Instructor(0, "alena", "bARTosh")
  println(instructor.fullName)

  val course = new Course(0, "Scala", "2021", instructor)
  println(course.getID)
  println(course.isTaughtBy(instructor))
  println(course.isTaughtBy(new Instructor(1, "Name", "Surname")))
  println(course.copyCourse("2022").releaseYear)
}
