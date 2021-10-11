package week_3

// Task 4.1
class Logger(val msgNum: Int = 0) {
  def info: Logger = {
    println("INFO New Message")
    new Logger(msgNum + 1)
  }

  def info(n: Int): Logger = {
    if (n <= 0) this
    else info.info(n - 1)
  }

  def print: Unit = println(msgNum)
}

object LogSystem extends App {
  val logger = new Logger
  logger.info.print
  logger.info(3).print
  logger.info.info.info.print
}
