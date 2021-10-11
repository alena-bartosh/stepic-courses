package week_1

import scala.annotation.tailrec

object Recursion extends App {

  // NOTE: use while for it - is possible but bad practice
  var i = 0
  while (i < 3) {
    println("hello")
    i += 1
  }

  // In functional programming, we move away from var and, if possible, concentrate on val.
  // NOTE: Use recursion

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1) // intermediate calculations are stored on the stack
  }

  println(factorial(3)) // 6

  // NOTE: For long calculations Tail Recursion is used
  def factorialWithTailRecursion(n: Int): Int = {
    def loop(x: Int, accumulator: Int): Int = { // for name "loop" or "go" are used
      if (x <= 1) accumulator
      else loop(x - 1, x * accumulator)
    }

    loop(n, 1) // the last operation is the call of loop func.
    // And now intermediate calculations are accumulating in the accumulator
  }

  println(factorialWithTailRecursion(3)) // 6

  // NOTE: @tailrec annotation can bu used in these cases.
  //       This tells the compiler that the function should be tail recursive,
  //       and if it isn't, code simply won't run.

  def repeatWord(word: String, n: Int): String = {
    @tailrec
    def loop(i: Int, acc: String = word): String = {
      if (i <= 1) acc
      else loop(i - 1, s"$word $acc") // acc is also immutable because we create the new one each time
    }

    loop(n)
  }

  println(repeatWord("hello", 3))
}
