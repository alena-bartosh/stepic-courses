package week_3

object Threads extends App {

  val thread = new Thread {
    override def run {
      println("I run in parallel")
    }
  }
  thread.start()

  val runnable = new Runnable {
    override def run(): Unit = println("I run in parallel")
  }

  val betterThread = new Thread(runnable)
  betterThread.start() // send signal to JVM about run()

  val bastThread = new Thread(() => println("I run in parallel"))
  bastThread.start()

  // -------------------- Multi threads --------------------

  val firstThread = new Thread(() => (1 to 3).foreach(_ => println("1st thread: I run in parallel")))
  val secondThread = new Thread(() => (1 to 3).foreach(_ => println("2nd thread: I also run in parallel")))

  firstThread.start()
//  firstThread.join() // wait for firstThread finishing
  secondThread.start()

  // -------------------- Pool --------------------

  import java.util.concurrent.Executors

  // NOTE: After creating threads once, it is best to reuse them

  val pool = Executors.newFixedThreadPool(3)
  pool.execute(runnable)

  pool.shutdown()
  println(pool.isShutdown) // true
}
