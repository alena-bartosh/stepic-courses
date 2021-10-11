package week_1

object StringOperations extends App {
  val aString: String = "Hello, world!"

  println(aString.length) // 13
  println(aString.charAt(1)) // e
  println(aString.substring(0, 2)) // He
  println(aString.split(" ").toList) // List(Hello,, world!)
  println(aString.startsWith("He")) // true
  println(aString.replace("!", ".")) // Hello, world.
  println(aString.toLowerCase) // hello, world!
  println(aString.toUpperCase) // HELLO, WORLD!
  println("abcd".reverse) // dcba
  println("abcd".take(2)) // ab

  val bString = "Scala course"
  println(bString.replace("a", "").take(3).reverse)

  val aNumber = "42".toInt
  println(aNumber) // 42
  println(aNumber.getClass) // int

  val cString = 42.toString

  // NOTE: +: or :+ cancatenate single elements (Char). For Char use ''
  println('1' +: "42" :+ '3') // 1423
  println('a' +: "bc" :+ 'd') // abcd
  println("a" ++ "bc" :++ "d") // abcd

  // Additional
  println(1 +: List(2, 3)) // List(1, 2, 3)
  println(List(1, 2) ++ List(3, 4)) // List(1, 2, 3, 4)
  println(List(1, 2) +: List(3, 4)) //List(List(1, 2), 3, 4)

  // Interpolation
  val name = "John"
  println(s"Hello, $name") // Hello, John

  val surname = "Smith"
  println(s"Hello, ${name + surname}") // Hello, JohnSmith

  println(raw"name is some \n name")
  println(s"name is some \n name")
}
