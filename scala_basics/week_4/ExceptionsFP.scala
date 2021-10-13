package week_4

import scala.util.{Try, Failure, Success}

// NOTE: In Scala, it is ok to use Try, avoiding try / catch
//       Try is a monad. It allows you to chain several methods together, catching exceptions in parallel
//       try / catch catches everything, even OutOfMemoryError or StackOverFlowException
//       But it makes no sense to continue executing the program after such errors.
//       Therefore, the best practice is to use Try, because with Try the program will not be able
//       to ignore fatal errors and continue working.

object ExceptionsFP extends App {

  def unsafeMethod(): String = throw new RuntimeException("Sorry, not your day")

  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure) // Failure(java.lang.RuntimeException: Sorry, not your day)

  println(potentialFailure.isSuccess) // false

  def myMethod(): String = "My method is valid"

  val executeWithTry = Try(unsafeMethod()).orElse(Try(myMethod()))

  println(executeWithTry) // Success(My method is valid)

  def methodWhichFails(): Try[String] = Failure(new RuntimeException("Ooops..."))

  def methodWhichSucceeds(): Try[String] = Success("Everything is OK")

  val tryMethods = methodWhichFails() orElse methodWhichSucceeds()

  println(tryMethods) // Success(Everything is OK)
}
