// NOTE: Option protects from NullPointerException
//       Option will return None if there is no value and Some with a value if there is a value

object Options extends App {

  def unsafeMethod(): String = null

  def safeMethod(): String = "There is a String"

  val unsafeRes = Option(unsafeMethod())
  val safeRes = Option(safeMethod())

  println(unsafeMethod()) // null
  println(unsafeRes) // None
  println(safeRes) // Some(There is a String)

  val someOption: Option[String] = Some("Success")
  val noneOption: Option[Int] = None

  println(someOption.isEmpty) // false
  println(noneOption.isEmpty) // true

  // It could be used for external API
  val chainedResult = Option(unsafeMethod()).orElse(Option(safeMethod()))
  println(chainedResult) // Some(There is a String)

  def unsafeMethod2(): Option[String] = None

  def safeMethod2(): Option[String] = Some("Everything is Ok")

  // It could be used for out API since unsafeMethod2 and safeMethod2
  // would have already been designed with Option
  val chainedResult2 = unsafeMethod2() orElse safeMethod2()
  println(chainedResult2) // Some(Everything is Ok)

  // println(noneOption.get) NoSuchElementException
}
