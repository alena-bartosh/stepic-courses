package week_2

class MyList[A] // type will be instantiated when the list is created

class Fruit

class Apple extends Fruit

class Banana extends Fruit

// NOTE: Example of Bounded types
//       Only parameters of type T are suitable for the class Food,
//       and this type must be a subtype of Fruit
class Food[T <: Fruit](fruit: T)

class InvariantList[A]

class ContravariantList[-A]

class CovariantList[+A]

object Generics extends App {

  val listOfStrings = new MyList[String]
  val listOfDoubles = new MyList[Double]
  val listOfInts = new MyList[Int]

  def randomInt(items: List[Int]): Int = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomInt(List(1, 2, 3, 4, 5)))

  def randomItem[A](items: List[A]): A = {
    val randomIndex = util.Random.nextInt(items.length)
    items(randomIndex)
  }

  println(randomItem(List("a", "bc", "def")))
  println(randomItem(List(1.5, 2.75, 3.8)))

  val food = new Food(new Banana)

  val invariantFruitList: InvariantList[Fruit] = new InvariantList[Fruit]
  val contravariantList: ContravariantList[Apple] = new ContravariantList[Fruit] // search for Apple but get Fruit
  val fruitList: CovariantList[Fruit] = new CovariantList[Apple]
}
