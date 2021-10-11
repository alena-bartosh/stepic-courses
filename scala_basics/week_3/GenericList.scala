package week_3

import scala.annotation.tailrec

// Task 4.3
abstract class LogList[+A] {
  def last: A

  def previous: LogList[A]

  def isEmpty: Boolean

  def all: String

  def add[B >: A](msg: B): LogList[B]
}

class Log[+A](head: A, tail: LogList[A]) extends LogList[A] {
  override def add[B >: A](msg: B): LogList[B] = new Log(msg, this)

  def last: A = head

  def previous: LogList[A] = tail

  def isEmpty: Boolean = false

  def all: String = {
    s"$head ${tail.all}"
  }
}

object Empty extends LogList[Nothing] {
  def last = throw new NoSuchElementException

  def previous = throw new NoSuchElementException

  def add[B >: Nothing](msg: B): LogList[B] = new Log(msg, Empty)

  def isEmpty: Boolean = true

  def all: String = " "
}

object LinkedList extends App {
  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))
  println(list.all)

  val intLogs: LogList[Int] = Empty
  val strLogs: LogList[String] = Empty

  println(intLogs.all)

  val list2 = new Log(1, new Log(2, new Log(3, Empty)))
  println(list2.all)
}
