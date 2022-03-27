package best_practices


object DoNotUseReturn extends App {

  // Want to get sum of all elements in the list

  def add: Int = List(1, 2, 4).foldRight(0)((a, b) => a + b)
  def addR: Int = List(1, 2, 4).foldRight(0)((a, b) => return a + b)

  println(add) // 7
  println(addR) // 4

  def x: Int = {
    val someVal: String = return 2
    1
  }
  println(x) // 2
  // NOTE: We have not message about type mismatch so "return" returns type Nothing
}
