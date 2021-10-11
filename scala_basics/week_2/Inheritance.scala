package week_2

class Person {
  val gender = "n/a"

  // Func is available only for this class
  private def greet: String = "Hello"

  // Func is available for this class & its subclass, but inaccessible outside of their bodies
  protected def parting: String = "Bye"

  // NOTE: method without private/protected modifier will be public
  def introduce: String = "I am a person"
}

class Boy(override val gender: String) extends Person {
  def say: String = this.parting

  override def introduce: String = s"${super.introduce} with gender - $gender"
}

object Inheritance extends App {
  val boy = new Boy("male")
  println(boy.say)

  // Example of polymorphism
  val aBoy: Person = new Boy("male") // declared with type Person, but it will be used as Boy
  println(aBoy.introduce) // I am a person with gender - male

  // NOTE: Overriding protection
  // 1) final def introduce
  // 2) final class Person (forbid extends)
  // 3) sealed class Person (allow extends only in this file)

  // Example of Anonymous class (needed when used only once)
  val someSource = new BaseDataSource("someDS") {
    override val user: String = "someSourceUser"

    override def connect: String = "someSource connection"
  }

}

abstract class BaseDataSource(dsName: String) {
  def save: String = "Save method implemented" // non-abstract member

  val user: String

  def connect: String
}

// NOTE: Traits describe specific behavior for a specific situation
trait PublicConn { // Traits are always without parameters
  def showNotification: String
}

trait PrivateConn {
  def checkCredentials: Boolean
}

class PublicDataSource(ds: String) extends BaseDataSource(ds) with PublicConn with PrivateConn {
  val user = "publicUser"

  override def connect: String = { // "override" is optional here
    "No passwors needed since it is public"
  }

  override def checkCredentials: Boolean = true

  override def showNotification: String = "This connection is public"
}
