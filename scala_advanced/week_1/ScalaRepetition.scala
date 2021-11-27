package week_1

object ScalaRepetition extends App {

  // Task 1.2.1
  def duplicate[A](someList:List[A], nDups: Int): List[A] = {
    someList.flatMap(el => List.fill(nDups)(el))
  }

  println(duplicate(List(1, 2, 3), 2))

  // Task 1.2.3
  def countChars(someString: String): Map[Char, Int] = {
    someString.toLowerCase().groupBy(c => c).mapValues(str => str.length)
  }

  val inputString: String = "Heloooo_I_am_crazy_Striiing!"
  println(countChars(inputString).toList.sortBy(_._2))

  // Task 1.2.5
  def isBalanced(aString: String): Boolean = {
    aString.count(_ == '(') == aString.count(_ == ')')
  }

  println(isBalanced("df(g)g)"))

  // -------------------- Eta-Expansion --------------------

  val multiplyFunction = (x: Int, y: Int) => x * y
  def multiplyMethod(x: Int, y: Int) = x * y
  def curriedMultiplyMethod(x: Int)(y: Int): Int = x * y

  // Ways to define val multiplyByTwo: Int => Int
  val multiplyByTwo = (x: Int) => curriedMultiplyMethod(2)(x)
//  val multiplyByTwo = curriedMultiplyMethod(2) _
//  val multiplyByTwo = (x: Int) => multiplyFunction(2, x)
//  val multiplyByTwo = curriedMultiplyMethod(2)(_)
//  val multiplyByTwo = multiplyMethod(2, _: Int) // _ means Eta-expansion
//  val multiplyByTwo = (x: Int) => multiplyMethod(2, x)
//  val multiplyByTwo = multiplyFunction.curried(2)
//  val multiplyByTwo = multiplyFunction(2, _: Int) // _ means Eta-expansion

  // Task 1.2.10
  def formatter(formatN: String)(number: Double): String = formatN.format(number)

  val simpleFormat: Double => String = (number: Double) => formatter("%.2f")(number)
  val numbers = List(3.1415)
  println(numbers.map(simpleFormat))

  // -------------------- Generics --------------------

  trait Fruit
  class Apple extends Fruit
  class Banana extends Fruit

  // Invariance
  // Have fruits and only fruits
  class FruitBasket[T]
  val invariantBasket: FruitBasket[Fruit] = new FruitBasket[Fruit]

  // Covariance
  // Apples are also fruits
  class FruitBasket2[+T]
  val covBasket: FruitBasket2[Fruit] = new FruitBasket2[Apple]

  //  Contravariance
  class FruitBasket3[-T]
  val contrBasket: FruitBasket3[Apple] = new FruitBasket3[Fruit]

  // -------------------- Syntactic Sugar --------------------

  // Method with one argument
  def methodWithSingleArg(arg: Int): String = s"Get number $arg"

  val someNumb: String = methodWithSingleArg {
    val someString = "complex calculations"
    someString.length
  }
  println(someNumb) // 49

  // Traits

  trait SomeTrait {
    def doSomething(s: String): String
  }

  val rugularInstance: SomeTrait = new SomeTrait {
    override def doSomething(s: String): String = s"I'm doing $s"
  }

  val betterInstance: SomeTrait = (s: String) => s"I'm doing $s"

  def someMethod(a: Int): Int = a * 2

  val aNumber: Int = someMethod {
    val aList1 = List("a", "b")
    val aList2 = List("c", "d")

    val aList: List[String] = aList1 ++: aList2 :+ "p"

    aList.zipWithIndex.filter(_._2 != 0).map(_._1).length // zipWithIndex return // (a,0)(b,1)(...)

  }

  println(aNumber) // 8

  // Work with setter/getter

//  class Modifier(val someVar: A) {
//    def get: A = someVar
//
//    def set(value): A = someVar = value
//  }

  class Modifier[A](var someVar: A) {
    def modifier: A = someVar
    def modifier_=(value: A): Unit = someVar = value
  }

  val m = new Modifier[Int](5)
  println(m.modifier) // 5
  m.modifier = 10
  println(m.modifier) // 10

  // -------------------- Lazy calculations --------------------

  // NOTE: lazy is used only for val
  lazy val someVal = throw new RuntimeException // val is not called so exception will not thrown

  lazy val greeting = {
    println("Let's greet")
    "I'm lazy Val"
  }

  println(greeting) // Let's greet I'm lazy Val
  println(greeting) // I'm lazy Val (value will not be evaluated again)

  // Task 1.6.6
  def showString(someStr: => String): Unit = {
    lazy val lazyStr = someStr
    print(s"$lazyStr$lazyStr")
  }

  // filter - return collection
  // withFilter - return interable; less times we go through the collection

  def greaterThanTen(i: Int): Boolean = i > 10
  def smallerThanTwenty(i: Int): Boolean = i < 10

  val aList = List(1, 5, 15, 100, 17)

  val filteredList = aList.filter(greaterThanTen).filter(smallerThanTwenty)
  println(filteredList)

  val lazyFilteredList = aList.withFilter(greaterThanTen).withFilter(smallerThanTwenty)
  filteredList.foreach(println) // to run need use .foreach


  val abc = List("a", "b", "c")
  def add(res: String, x: String) = res + x

  println(abc.reduceLeft(add)) // abc
  println(abc.foldLeft("D")(add)) // Dabc
  println(abc.foldRight("D")(add)) // abcD

  println(abc.scanLeft("D")(add)) // List(D, Da, Dab, Dabc)
  println(abc.scanRight("D")(add)) // List(abcD, bcD, cD, D)

  // (1 to 1000000000).toList.map(_ * 3).take(3).filter(_ <= 6).foreach(println) // will be too long
  (1 to 1000000000).toStream.map(_ * 3).take(5).filter(_ <= 6).foreach(println) // can be run fast
  // LazyList = Stream = List with lazy computations

  // -------------------- Monads --------------------

  // NOTE: Monads are just a concept, not types or data structures!
  //       Examples: List(), Option(), Try(), Set()
  //       Main operations:
  //           - unit (allows to construct a monad from one or more variables)
  //           - flatMap
  //       Rules:
  //           - left-identity (unit(x).flatMap(f) = f(x))
  //           - right-identity (aMonad.flatMap(unit) = aMonad)
  //           - associativity (aMonad.flatMap(f).flatMap(g) = aMonad(x => f(x).flatMap(g)))

  // Task 1.7.3
  case class User(name: String, bf: User)

  val users = List(
    User("Mike", User("Sam", User("Bob", User("Alice", null)))),
    User("John", User("Joe", null)),
    User("Lonely user", null),
  )

  object Service {
    def findUser(users: List[User], name: String): Option[User] = users.find(_.name == name)
  }

  def getBf(user: User): Option[User] = Option(user.bf)

  def getBfBfName(name: String): String =
    Service
      .findUser(users, name)
      .map {
        getBf(_)
          .flatMap(getBf)
          .map(_.name)
          .getOrElse("No bf")
      }
      .getOrElse("No user")

  // The second approach
  // -------------------
  //  def getBfBfName(user: User): String =
  //    getBf(user)
  //      .flatMap(getBf)
  //      .map(_.name)
  //      .getOrElse("No bf")
  //
  //  def getBfBfName(name: String): String =
  //    Service
  //      .findUser(users, name)
  //      .map(getBfBfName)
  //      .getOrElse("No user")

  def test(name: String): Unit = {
    val result: String = getBfBfName(name)

    println(s"Test for [$name]: [$result]")
  }

  test("Mike")
  test("John")
  test("Lonely user")
  test("User from another place")
}
