package week_3

import scala.annotation.tailrec

// Task 4.2
abstract class LogList {
  def last: String

  def previous: LogList

  def isEmpty: Boolean

  def all: String

  def add(msg: String): LogList
}

class Log(head: String, tail: LogList) extends LogList {
  def add(msg: String): LogList = new Log(msg, this)

  def last: String = head

  def previous: LogList = tail

  def isEmpty: Boolean = false

  def all: String = {
    @tailrec
    def loop(logs: LogList, acc: String = ""): String = {
      if (logs.isEmpty) acc
      else loop(logs.previous, acc ++ logs.last ++ " ")
    }

    loop(this)
  }
}

object Empty extends LogList {
  def last = throw new NoSuchElementException

  def previous = throw new NoSuchElementException

  def add(msg: String): LogList = new Log(msg, Empty)

  def isEmpty: Boolean = true

  def all: String = " "
}

object LinkedList extends App {
  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))
  println(list.all)
}
