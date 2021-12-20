package week_3

object SystemDesignMistakes extends App {

  // -------------------- Race condition --------------------

  class BankAccount(var amount: Int) {
    override def toString: String = s"Amount: $amount"
  }

  def payWithCard(account: BankAccount, price: Int, details: String): Unit = {
    account.amount -= price

    println(details)
    println(s"Account balance: ${account.amount}")
  }

  for (_ <- 1 to 50) {
    val account = new BankAccount(100)

    val thread1 = new Thread(() => payWithCard(account, 20, "t-shirt"))
    val thread2 = new Thread(() => payWithCard(account, 50, "boots"))

    thread1.start()
    thread2.start()

    Thread.sleep(10)

    if (account.amount != 30) println(s"Account balance: ${account.amount}")
  }
  // NOTE: there is no synchronization between the threads

  // -------------------- Access synchronization --------------------

  def synchronizedPayment(account: BankAccount, price: Int, thing: String): Unit =
    account.synchronized {
      account.amount -= price
    }

  // OR

  class SynchronizedBankAccount(@volatile var amount: Int) {
    override def toString: String = s"Amount: $amount"
  }

  // NOTE: @volatile is fine if the variable will be read by more than one thread at a time
  //       but changed only by one thread
  //       .synchronized and @volatile will only work if threads are trying to modify the same variable at the same time
}
