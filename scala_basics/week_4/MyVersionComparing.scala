package week_4

// Task 5.8.2
object MyVersionComparing extends App {

  def parseVersion(version: String): List[Int] = version.split('.').toList.map(_.toInt)

  def compare(v1: String, v2: String): Int = {
    val l1 = parseVersion(v1)
    val l2 = parseVersion(v2)

    val maxLenght = l1.length.max(l2.length)

    for (i <- 0 until maxLenght) {
      val digit1 = l1.lift(i).getOrElse(0)
      val digit2 = l2.lift(i).getOrElse(0)

      val res = digit1.compare(digit2)

      if (res != 0) return res
    }
    0
  }

  println(compare("0.9", "1.01")) // -1
  println(compare("1.0.2.03", "1.1.0")) // -1
  println(compare("4.0.1", "4.0.0.1")) // 1
  println(compare("3.0", "3.0.0.0")) // 0
}
