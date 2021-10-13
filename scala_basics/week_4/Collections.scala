package week_4

// NOTE: Scala Collections:
//       - Set (no duplicates; elements of the same type)
//       - Seq (each element has its own index: Vector, Range, List, Array)
//       - Map (key-value pairs)

object Collections extends App {

  // -------------------- SET --------------------

  val emptySet: Set[Int] = Set()
  val aSet = Set(10, 20, 30, 40) // immutable by default
  val anotherSet = Set(30, 40, 50, 60)

  println(emptySet.isEmpty)
  println(aSet.head) // 10
  println(aSet.tail) // Set(20, 30, 40)

  println(aSet.intersect(anotherSet))
  println(aSet.&(anotherSet)) // Set(30,40)

  println(aSet.++(anotherSet))
  println(aSet ++ anotherSet) // HashSet(10, 20, 60, 50, 40, 30)

  // -------------------- SEQUENCE --------------------

  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence) // List(1, 3, 2, 4)

  // -------------------- MAP --------------------

  val aMap: Map[String, Int] = Map()
  val colors: Map[String, String] =
    Map(("black", "#0"), "blue" -> "#0d").withDefaultValue("na")

  println(colors) // Map(black -> #0, blue -> #0d)
  println(colors.contains("black")) // true
  println(colors("red")) // na

  val color: (String, String) = "green" -> "#3"
  val newColors: Map[String, String] = colors + color

  println(newColors) // Map(black -> #0, blue -> #0d, green -> #3)

  println(colors.toList)
  println(List(("white", "#f")).toMap)

  // -------------------- ARRAY --------------------

  val anArray: Array[String] = Array("h", "e", "l", "l", "o", ".")
  anArray(5) = "!" // change in-place; anArray.update(5, "!")

  println(anArray.mkString("-")) // h-e-l-l-o-!

  val twoElements: Array[Boolean] = Array.ofDim[Boolean](2) // create empty Array with size 2
  twoElements.foreach(println) // false false

  val numberSeq: Seq[String] = anArray // transform type
  println(numberSeq) // ArraySeq(h, e, l, l, o, !)

  // -------------------- TUPLE --------------------

  val aTuple: (Int, String) = (100, "Scala") // types can be different
  val sameTuple: (Int, String) = Tuple2(100, "Scala") // where i from 1 to 22

  println(aTuple._1) // 100

  val copy: (Int, String) = aTuple.copy(_2 = "Python")
  println(copy) // (100,Python)

  println(aTuple.swap) // (Scala,100)

  // -------------------- RANGE --------------------

  val aRange: Seq[Int] = 1 until 3 // [1, 3)
  val bRange: Seq[Int] = 1 to 3 // [1, 3]

  aRange.foreach(print) // 12
  (1 to 3).foreach(x => print("Hello")) // HelloHelloHello

  val aRangeToVector: Vector[Int] = (1 to 5).toVector
  println(aRangeToVector) // Vector(1, 2, 3, 4, 5)

  // -------------------- FUNCTIONS FOR COLLECTIONS --------------------

  val list = List(1, 2, 3)

  // NOTE: foreach is like map, but as input it has funcs that return Unit
  list.foreach(print) // 123

  for {
    n <- list
  } print(n) // 123

  val fruits = List("apple", "banana")

  val mapResult = fruits.map(_.toUpperCase) // do smth with each el
  val flatResult = fruits.flatMap(_.toUpperCase) // transform each el to list and do smth with each el

  println(mapResult) // List(APPLE, BANANA)
  println(flatResult) // List(A, P, P, L, E, B, A, N, A, N, A)

  val s = "Hello"
  val newStr: String = s.flatMap(c => (c + "."))

  println(newStr) // H.e.l.l.o.
  println(s.map(c => (c + "."))) // ArraySeq(H., e., l., l., o.)

  val list1 = List(1, 2)
  val list2 = List("a", "b")

  val combinations = list1.flatMap(n => list2.map(c => c + n))
  println(combinations) // List(a1, b1, a2, b2)

  val forCombination = for {
    n <- list1
    c <- list2
  } yield c + n

  println(forCombination) // List(a1, b1, a2, b2)

  val filterCombination = list1.filter(_ > 1).flatMap(n => list2.map(c => c + n))
  println(filterCombination) // List(a2, b2)

  val forFilterCombination = for {
    n <- list1 if n > 1
    c <- list2
  } yield c + n

  println(forFilterCombination) // List(a2, b2)

  val sampleTuple = new Tuple2(2, "Hello, World")
}
