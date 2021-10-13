package week_4

import scala.annotation.tailrec

// NOTE: Functional programming is writing code using only pure functions and immutable variables
//       Pure function - is
//       1) the result depends only on what func receives as input and the algorithm described inside it
//       2) there is no reading or writing to a file or any other interaction with an external data source
//       E.g. toUpperCase()

class OrdinaryMultiplication {
  def multiply(x: Int): Int = x * 2
}

trait GoodMultiplication[A, B] {
  def apply(x: A): B
}

object FunctionsFP extends App {

  val res = new GoodMultiplication[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  println(res(2))

  // NOTE: Similar traits are already written in Scala from 0 to 22 (count of parameters)

  val res2 = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // last type - is a type of result
  val product = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x * y
  }

  println(product(3, 4)) // 12

  // Examples of anonymous functions (or lambda expressions)

  val product2 = (x: Int, y: Int) => x * y
  val product3: (Int, Int) => Int = (x, y) => x * y
  val product4: (Int, Int) => Int = _ * _

  val res3 = (x: Int) => x * 2
  val res4: Int => Int = x => x * 2
  val res5 = { (x: Int) =>
    x * 2
  }
  val res6: Int => Int = _ * 2

  val strlen = (x: String) => x.length

  // NOTE: High Order Functions have another function as input, or return a function as a result

  @tailrec
  def nTimes(f: Int => Int, x: Int, n: Int): Int = {
    if (n <= 0) x
    else nTimes(f, f(x), n - 1)
  }

  // NOTE:  Mostly def is used for methods, val - for fucns
  //        After compilation, def is translated into a Java method
  //        val is translated into a functional value (i.e. an object created with new Function1 {def apply (a) ....})

  val increment = (x: Int) => x + 1 // it is better to use val for High Order Functions as it gives better performance

  println(nTimes(increment, 0, 3)) // 3

  // NOTE: Currying Functions example: result = f(x)(y)(z)

  def ordinaryAdd(x: Int, y: Int) = x + y

  println(ordinaryAdd(1, 2)) // 3

  def curryingAdd(x: Int) = (y: Int) => x + y

  def curryingAdd2(x: Int)(y: Int) = x + y

  println(curryingAdd(1)(2))
  println(curryingAdd2(1)(2))

  def curryingNTimes(f: Int => Int, n: Int): Int => Int = {
    if (n <= 0) (x: Int) => x // lambda func that returns value
    else (x: Int) => curryingNTimes(f, n - 1)(f(x))
  }

  println(curryingNTimes(increment, 3)(0))

  // NOTE: In Scala A => B is like Function1[A, B]
  //         (A1, A2) => B is like Function2[A1, A2, B]
  //          A => B => C  is like Function1[A, Function1[B, C]]
}
