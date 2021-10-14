// Task 6.3

object Task3 extends App {

  type Destinations = Set[String]
  type Network = Map[String, Destinations]

  def add(network: Network, location: String): Network = network + (location -> Set())

  def remove(network: Network, location: String): Network = {
    def loop(destinations: Destinations, acc: Network): Network =
      if (destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))

    val disconnected = loop(network(location), network)
    disconnected - location
  }

  def connect(network: Network, pointA: String, pointB: String): Network = {
    val routesForA: Destinations = network(pointA)
    val routesForB: Destinations = network(pointB)

    network + (pointA -> (routesForA + pointB)) + (pointB -> (routesForB + pointA))
  }

  def nLocationsWithNoFlights(network: Network): Int = {
    network.count(pair => pair._2.isEmpty)
    // Another way: network.view.filterKeys(key => network(key).isEmpty).size
  }

  def disconnect(network: Network, pointA: String, pointB: String): Network = {
    val routesForA: Destinations = network(pointA)
    val routesForB: Destinations = network(pointB)

    network + (pointA -> (routesForA - pointB)) + (pointB -> (routesForB - pointA))
  }

  def nFlights(network: Network, location: String): Int = network(location).size

  def isConnected(network: Network, pointA: String, pointB: String): Boolean = {
    def go(queue: List[String]): Boolean = {
      queue match {
        case Nil => false
        case h +: _ if h == pointB => true
        case head +: tail => go(tail ++ network(head))
      }
    }

    go(List[String](pointA))
  }

  def mostFlights(network: Network): String = {
    network.keySet.maxBy(key => network(key).size)
    // Another way: network.view.mapValues(values => values.size).maxBy(_._2)._1
  }

  println(mostFlights(Map(
    "A" -> Set("B", "C"),
    "B" -> Set("A"),
    "C" -> Set("A", "G", "H")
  ))) // C

  println(isConnected(Map(
    "A" -> Set("B", "C"),
    "B" -> Set("A", "C"),
    "C" -> Set("A", "B", "S"),
    "S" -> Set("C", "D"),
    "D" -> Set("S"),
  ), "A", "D")) // true
}
