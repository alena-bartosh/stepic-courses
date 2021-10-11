package week_2

// Has no parameters. It is Singleton Object
object Number {
  val Pi = 3.14

  def apply(x: Number, y: Number): Number = new Number(x.num + y.num)
}

class Number(val num: Int)

object ObjectsOOP extends App {
  println(Number.Pi)

  val numA = Number
  val numB = Number

  println(numA == numB) // true because numA and numB refer to the same object Number

  val numA2 = new Number(0)
  val numB2 = new Number(0)

  println(numA2 == numB2) // false because we have two different instances of the class Number

  // NOTE: A companion object in Scala is an object that’s declared in the same file as a class,
  //       and has the same name as the class. A companion object and its class can access each other’s
  //       private members (fields and methods).

  // Factory method
  val numbA = new Number(1)
  val numbB = new Number(2)

  val numbC = Number(numbA, numbB) // call apply without ".apply()"

  println(numbA.num) // 1
  println(numbB.num) // 2
  println(numbC.num) // 3
}
