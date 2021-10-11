package week_1

import scala.annotation.tailrec

object MyRecursion2 extends App {

  // Task 2.6.10
  def increase(x: Int, y: Int, n: Int): Int = {
    @tailrec
    def loop(step: Int, acc: Int = x, y: Int = y): Int = {
      if (step <= 0) acc
      else loop(step - 1, acc + y)
    }

    loop(n)
  }

  def printNumbersResult(fArgs: Array[String]): Unit = {
    val result = increase(x = fArgs(0).toInt, y = fArgs(1).toInt, n = fArgs(2).toInt)
    println(s"${(result.toString :+ ' ') * result.toString.length ++ "is the result"}")
  }

  printNumbersResult(Array("1", "1", "10"))
  printNumbersResult(Array("10", "1", "5"))
}
