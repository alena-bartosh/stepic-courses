package week_1

object Expressions extends App {
  // NOTE: expressions have some value and type
  val aVal = 1 + 2 * 3 // aVal is int that = 7

  print(1 + 2 * 3) // prinln adds a new line but print doesn't

  // NOTE: Expression return value. Instruction do not return value
  //       All in Scala is Expression!

  val aCondition = true
  val ifExpressionValue = if (aCondition) "True Condition" else "False Condition"
  println(ifExpressionValue) // True Condition


  // NOTE: Unit (empty tuple) is used when expression has not value
  val someVal: Unit = println("I just want to print")

  val aNumber = if (('1' +: "23").toInt == 24) 24 else 123
  println(aNumber)

  val someVal2 = print("It is just a value")
  print(someVal2) // "It is just a value()" since we print someVal2 at first and then print Unit that = ()
}
