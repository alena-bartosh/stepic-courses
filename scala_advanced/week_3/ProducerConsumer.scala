package week_3

import scala.collection.mutable
import scala.util.Random


object ProducerConsumer extends App {

  class Container {
    private var num: Int = 0

    def isEmpty: Boolean = num == 0

    // producer
    def set(newVal: Int): Unit = num = newVal

    // consumer
    def get: Int = {
      val result: Int = num
      num = 0
      result
    }
  }

  def work(): Unit = {
    val container = new Container

    val consumer = new Thread(() => {

      while(container.isEmpty) {
        println("consumer: waiting...")
      }
      println("consumer: get " +  container.get)
    })

    val producer = new Thread(() => {
      println("producer: computation...")
      Thread.sleep(500) // sleep imitates computation

      val value = 12
      println("producer: done " + value)
      container.set(value)
    })
    consumer.start()
    producer.start()
  }

  work()


  // Better implementation:

  def workBetter(): Unit = {
    val container = new Container

    val consumer = new Thread(() => {
      println("consumer: waiting...")

      container.synchronized {
        container.wait() // waiting for signal from notify
      }

      println("consumer: get " +  container.get)
    })

    val producer = new Thread(() => {
      println("producer: computation...")
      Thread.sleep(1000)
      val value = 13

      container.synchronized {
        println("producer: done " + value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }

  workBetter()

  def workWithBuffer(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(() => {
      val random = new Random()

      while (true) {
        buffer.synchronized {
          if (buffer.isEmpty) {
            println("consumer: buffer is empty - waiting...")
            buffer.wait()
          }

          val x = buffer.dequeue()
          println("consumer: get " + x)

          buffer.notify()
        }

        Thread.sleep(random.nextInt(250))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0

      while (true) {
        buffer.synchronized {
          if (buffer.size == capacity) {
            println("producer: buffer is full - waiting...")
            buffer.wait()
          }

          println("producer: produce value " + i)
          buffer.enqueue(i)

          buffer.notify()

          i += 1
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
  }

  workWithBuffer()
}
