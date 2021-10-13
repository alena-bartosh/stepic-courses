package week_4

// NOTE: Patterns are used when you need to determine some actions for specific values
//       It is like switch-case from other languages

object Patterns extends App {

  val someVal = 3

  val description = someVal match {
    case 1 => "action 1"
    case 2 => "action 2"
    case 3 => "action three"
    case _ => "default action" // no pattern matches found
  }

  println(description) // action three

  // NOTE: Patterns can be:
  //       - constants
  //       - tuples
  //       - lists
  //       - types
  //       - case classes

  val x: Any = "Scala"
  val constants = x match {
    case 1 => "number"
    case "Scala" => "string"
    case true => "bool value"
  }

  val aTuple = (5, 2)
  val matchATuple = aTuple match {
    case (1, 1) => "complete match"
    case (something, 2) => s"I find $something"
  }

  println(matchATuple) // I find 5

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"There is number $v"
  }

  println(matchNestedTuple) // There is number 3

  val aStandardList = List(5, 4)
  val listMatching = aStandardList match {
    case List(1, _, _, _) => "list with 4 elements, where 1st == 1"
    case List(2, _*) => "list with n elements, where 1st == 2"
    case List(3, 2, 1) :+ 0 => "List(3, 2, 1, 0)"
    case 5 :: List(_) => "list where 1st == 5 and one more el"
  }

  val unknown: Any = List(1, 2)
  val typeMatch = unknown match {
    case list: List[Int] => "list of INTs"
    case _ => "default"
  }

  println(typeMatch) // list of INTs

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 30)
  val greeting = bob match {
    case Person(n, a) if a < 18 => s"I'm $n and I'm under 18"
    case Person(n, a) => s"I'm $n and I am $a years old"
    case _ => "I have no name"
  }

  println(greeting) // I'm Bob and I am 30 years old

  // -------------------- NAME BINDING --------------------

  val nameBindingMatch = List(6, 2) match {
    case nonEmptyList@List(1, _, _, _) => s"find $nonEmptyList"
    case someList@List(6, _*) => s"find list $someList" // _* means n els where n from 0 to N
  }

  println(nameBindingMatch) // find list List(6, 2)

  // -------------------- PARTIAL FUNCTIONS --------------------

  // Are used to limit the values that can be supplied as input

  val whatToDo = (d: String) => d match {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  val aPartialFunction: PartialFunction[String, String] = {
    case "mon" => "Work!"
    case "fri" => "Party Time"
    case "sun" => "Relax a little"
  }

  println(whatToDo("sun")) // Relax a little
  println(aPartialFunction("sun")) // Relax a little

  //  println(whatToDo("sat")) // MatchError
  //  println(aPartialFunction("sat")) // MatchError

  println(aPartialFunction.isDefinedAt("tue")) // false

  val pfChain: PartialFunction[String, String] = aPartialFunction.orElse[String, String] {
    case "sat" => "It is just Saturday"
  }

  println(pfChain("mon")) // Work!
  println(pfChain("sat")) // It is just Saturday

  // -------------------- LIFTING --------------------

  // After lift - the function will return values of the Option type (MatchError problem is solved)

  val lifted = aPartialFunction.lift // return Option[String]

  println(lifted("fri")) // Some(Party Time)
  println(lifted("thu")) // None
}
