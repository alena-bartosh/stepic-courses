package week_3

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object FuturesPart1 extends App {

  // NOTE: We do not know which Thread will execute Future
  val rdm = new Random()

  case class FlightProfile(id: String, city: String) {
    def search(destination: FlightProfile): Unit =
      println(s"found flights from ${this.city} to ${destination.city}")
  }

  object FlightNetwork {
    val sources = Map(
      "r1-msc" -> "Moscow",
      "r2-spb" -> "St Petersburg"
    )
    val routes = Map(
      "r1-msc" -> "r2-spb"
    )

    def fetchSource(id: String): Future[FlightProfile] = Future {
      Thread.sleep(rdm.nextInt(100)) // imitates computation
      FlightProfile(id, sources(id))
    }

    def fetchDestination(profile: FlightProfile): Future[FlightProfile] = Future {
      Thread.sleep(rdm.nextInt(200))
      val destinationId: String = routes(profile.id)
      FlightProfile(destinationId, sources(destinationId))
    }
  }

  val sourceCity = FlightNetwork.fetchSource("r1-msc")

  sourceCity.onComplete {
    case Success(sourceProfile) =>
      val destination = FlightNetwork.fetchDestination(sourceProfile)

      destination.onComplete {
        case Success(destinationProfile) => sourceProfile.search(destinationProfile)
        case Failure(e) => e.printStackTrace()
      }
    case Failure(ex) => ex.printStackTrace()
  }

  Thread.sleep(rdm.nextInt(2000)) // wait for Future

  // -------------------- Functional Composition --------------------

  // map - transforms a Future of a given type into a Future of another type
  // flatMap - transforms a given Future into another Future of the same type
  // filter - returns a filtered value or NoSuchElementException

  val source = FlightNetwork.fetchSource("r1-msc")

  val sourceCityMap: Future[String] = source.map(profile => profile.city)
  val destination: Future[FlightProfile] = source.flatMap(profile => FlightNetwork.fetchDestination(profile))
  val destinationFiltered: Future[FlightProfile] = destination.filter(profile => profile.city.startsWith("S"))

  // -------------------- Fallback --------------------

  val unknownSource = FlightNetwork.fetchSource("unknown-id").recover {
    case e: Throwable => FlightProfile("r0-default", "Not Found")
  }

  val someSource = FlightNetwork.fetchSource("unknown-id").recoverWith {
    case e: Throwable => FlightNetwork.fetchSource("r1-msc")

  }

  val fallbackResult =  FlightNetwork
    .fetchSource("unknown id")
    .fallbackTo(FlightNetwork.fetchSource("r0-default"))
}
