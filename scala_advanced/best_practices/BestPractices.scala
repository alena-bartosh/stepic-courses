package best_practices

import scala.util.Try


object BestPractices extends App {

  // -------------------- Use sameElements() for arrays --------------------

  // NO
  print(Array(1) == Array(1)) // false

  // YES
  println(Array(1).sameElements(Array(1))) // true

  // -------------------- Use isNaN for Nan --------------------

  // NO
  println(Double.NaN == Double.NaN) // false

  // YES
  println(Double.NaN.isNaN) // true

  // -------------------- Use .isEmpty instead of .size to check empty --------------------
  // -------------------- Use .lastOption instead of .last --------------------

  // NO
  println(Try(List.empty[Int].head)) // NoSuchElementException

  // YES
  println(List(1, 2, 3).headOption) // Some(1)
  println(List.empty[Int].headOption) // None

  // -------------------- Use .dropRight(1) instead of init --------------------
  // -------------------- Use .drop(1) instead of tail --------------------
  // -------------------- Use .reduceOption instead of reduce --------------------

  println(Try(List.empty[Int].init)) // UnsupportedOperationException
  println(List(1, 2, 3).dropRight(1)) // List(1, 2)
  println(List.empty[Int].dropRight(1)) // List()

  println(Try(List.empty[Int].tail)) // UnsupportedOperationException : tail of empty list
  println(List(1, 2, 3).drop(1)) // List(2, 3)
  println(List.empty[Int].drop(1)) // List()

  println(Try(List.empty[Int].reduce(_ + _))) // UnsupportedOperationException
  println(List(1, 2, 3).reduceOption(_ + _)) // Some(6)
  println(List.empty[Int].reduceOption(_ + _)) // None

  // -------------------- Use final for case classes --------------------

  case class Customer(id: Int)
  class VipCustomer(id: Int, name: String) extends Customer(id)

  println(new VipCustomer(1, "Bob") == new VipCustomer(1, "Alice")) // true
}
