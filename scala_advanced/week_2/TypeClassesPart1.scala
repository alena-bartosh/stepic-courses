package week_2


object TypeClassesPart1 extends App {

  // NOTE: Type Class is a trait that requires a type for which it describes possible actions.
  //       The purpose of a Type Class is to describe functionality for different types

  trait MyTypeClassTemplate[T] {
    def action(value: T): String
  }

  trait InfoProvider[T] {
    def info(value: T): String
  }

  case class Flight(from: String, to: String, flightTime: Int)

  object UserInfo extends InfoProvider[Flight] {
    def info(flight: Flight): String =
      s"Flight from ${flight.from} to ${flight.to} takes ${flight.flightTime} minutes"
  }

  val flightMscNizN = Flight("Moscow", "NizhniyNovgorod", 70)
  println(UserInfo.info(flightMscNizN))

  object StaffInfo extends InfoProvider[Flight] {
    def info(flight: Flight): String =
      s"For staff only: Flight from ${flight.from}"
  }

  println(StaffInfo.info(flightMscNizN))

  import java.util.Date
  object DateInfo extends InfoProvider[Date] {
    override def info(date: Date): String = s"Given date is ${date.toString}"
  }

  // Task 2.2.2
  case class Customer(id: String, name: String)

  case class Order(orderId: String, customer: Customer, date: String)

  trait OrderComparator[T] {
    def compare(a: T, b: T): Boolean
  }

  object CustomerCheck extends OrderComparator[Order] {
    override def compare(a: Order, b: Order): Boolean = a.customer.id == b.customer.id
  }

  object DateAndCustomerCheck extends OrderComparator[Order] {
    override def compare(a: Order, b: Order): Boolean =
      a.orderId == b.orderId && a.date == b.date
  }

  val order: Order = Order("1", Customer("37", "T"), "20211207")
  val newOrder: Order = Order("2", Customer("38", "NewT"), "20211207")
  println(DateAndCustomerCheck.compare(order, newOrder))

  trait MyTypeClassTemplateWithImplicit[T] {
    def action(value: T): String
  }

  object MyTypeClassTemplateWithImplicit {
    def apply[T](implicit instance: MyTypeClassTemplateWithImplicit[T]) = instance
  }

  // -------------------- Pimp My Library Pattern --------------------

  implicit class RichInt(val value: Int) extends AnyVal {
    def sqrt: Double = Math.sqrt(value)
  }

  println(16.sqrt)
  println(new RichInt(16).sqrt)
}
