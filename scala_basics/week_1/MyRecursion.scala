package week_1

import scala.annotation.tailrec

object MyRecursion extends App {

  // Task 2.6.9
  def powerOfTwo(power: Int): BigInt = {
    @tailrec
    def loop(x: Int, acc: BigInt = 1): BigInt = {
      if (x <= 0) acc // only for positive numbers
      else loop(x - 1, 2 * acc)
    }

    loop(power)
  }

  println(powerOfTwo(2))
  println(powerOfTwo(4))
  println(powerOfTwo(32))
}
