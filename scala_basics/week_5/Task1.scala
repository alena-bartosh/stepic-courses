// Task 6.1

def connect(config: Map[String, String]): Unit = {
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)
}

def connectWithFor(config: Map[String, String]): Unit = {
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)
}
