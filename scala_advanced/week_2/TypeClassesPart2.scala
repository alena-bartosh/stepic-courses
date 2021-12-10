package week_2


object TypeClassesPart2 extends App {

  // -------------------- Magnet Pattern --------------------

  trait BadMathLibrary {
    def increment(x: Int): Int = x + 1
    def increment(x: String): Int = x.toInt + 1
  }

  trait MathMagnet {
    def apply(): Int
  }

  def increment(magnet: MathMagnet): Int = magnet() // call apply

  implicit class IncrementInt (x: Int) extends MathMagnet {
    override def apply(): Int = x + 1
  }

  implicit class IncrementStr (x: String) extends MathMagnet {
    override def apply(): Int = x.toInt+ 1
  }

  println(increment("5")) // 6
  println(increment(7)) // 8

  val inc = increment _
  println(inc(9))
}
