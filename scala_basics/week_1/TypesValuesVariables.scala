package week_1

object TypesValuesVariables extends App {
  var aSrting: String = "Hello!"
  println(aSrting)

  val aChar = 'C'
  val aInt: Int = 11

  // NOTE: if aLong = 11 then aLong.getClass is Int;
  //       We add "L" to help the compiler create Long

  val aLong = 11L
  val aFloat = 2.0f
  val aDouble = 2.0
  val aBoolean = true

  // NOTE: val create read-only variable;
  //       var create variable that can be reassigned

  aSrting = "Hello, world!"

  val someFloatVal = 53.249
  println(someFloatVal.getClass)
}
