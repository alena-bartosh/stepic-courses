package week_1

object TaskWithString extends App {

  def reverse_and_remove_spaces(str: String): Array[String] = {
    str.replaceAll(raw"\s+", " ").split(" ").reverse
  }

  // print(reverse_and_remove_spaces(input))
  println(reverse_and_remove_spaces("I like     Scala").mkString(" "))

  // Second solution
  println("I like     Scala".split("\\s+").reverse.mkString(" "))
}
