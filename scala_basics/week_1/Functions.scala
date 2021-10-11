package week_1

object Functions extends App {
  def aPerson(name: String, surname: String): String = {
    s"$name $surname" // return last expression value & type
  }

  println(aPerson("John", "Smith"))

  def aPerson2(name: String, surname: String): Unit = println(s"$name $surname")

  aPerson2("John", "Smith")

  def aParameterlessFunction(): Unit = println("Function with no parameters")

  aParameterlessFunction()

  def aFunctionWithDefaultParameter(x: Int, y: String = "Default Parameter"): String = {
    s"x = $x and y = $y"
  }

  println(aFunctionWithDefaultParameter(1)) // x = 1 and y = Default Parameter

  // Call by value means evaluating the value of the passed expression before calling the function.
  // Advantage: The value is calculated only once.
  def callByValue(x: Long): Unit = {
    println(s"call by value: x1 = $x")
    println(s"call by value: x2 = $x")
  }

  // Calling by name implies evaluating the value of the expression at the time it is called in the function.
  // Advantage: The value is not evaluated unless used in the body of the function.
  def callByName(x: => Long): Unit = {
    println(s"call by name: x1 = $x")
    println(s"call by name: x2 = $x")
  }

  callByValue(System.nanoTime()) // x1 == x2
  callByName(System.nanoTime()) // x1 != x2

  def aBossFunction(): String = {
    def aHelperFunction(): String = "I'm here to help"

    aHelperFunction()
  }
}
