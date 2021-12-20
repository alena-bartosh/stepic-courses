package week_3

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success


object FuturesPart2 extends App {

  // -------------------- Await --------------------

  // NOTE: The purpose of await is to block threads for a given period of time.
  //       It is usually recommended to avoid await, choosing onComplete. The reason is deadlocks

  case class User(name: String)
  case class Transaction(user: String, flight: String, status: String)

  object FlightNetwork {
    val users = Map(
      "u1-b" -> "Bob",
      "u2-a" -> "Alice"
    )

    val flight = "Msc - StP"

    def fetchUser(id: String): Future[User] = Future {
      Thread.sleep(100)
      User(users(id))
    }

    def createTransaction(user: User, flight: String): Future[Transaction] = Future {
      Thread.sleep(2000)
      Transaction(user.name, flight, "SUCCESS")
    }


    // bookFlight return status as string
    def bookFlight(userId: String, flight: String): String = {
      val transactionStatus = for {
        user <- fetchUser(userId)
        transaction <- createTransaction(user, flight)
      } yield transaction.status

      Await.result(transactionStatus, 3.seconds)
    }
  }

  println(FlightNetwork.bookFlight("u1-b", "Msc - StP")) // SUCCESS

  // -------------------- Promises --------------------

  // NOTE: Promise - the way to control Future
  //       and WHOLE WORLD HA-HA-HA (don't panic, just a tired joke)

  val promise = Promise[String]()
  val future = promise.future

  val producer = new Thread(() => {
    Thread.sleep(100)
    promise.success("Produced String")
  })

  future.onComplete {
    case Success(v) => println("consumed:" + v) // consumer is called after producer
  }

  producer.start()
  Thread.sleep(500)
}
