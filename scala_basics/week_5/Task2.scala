import scala.util.{Try, Failure, Success}

// Task 6.2

def safeConnection(host: String, port: String): Try[Connection] = Try(HttpService(host, port))

def safeUrl(connection: Connection, url: String): Try[String] = Try(connection.get(url))

val connectWithFor = for {
  connection <- safeConnection(host, port)
  page <- safeUrl(connection, "http://test.com")
} yield page

// 1st way to do it
connectWithFor.foreach(render)

// 2nd way to do it
safeConnection(host, port).flatMap(connection => safeUrl(connection, "http://test.com")).foreach(render)
