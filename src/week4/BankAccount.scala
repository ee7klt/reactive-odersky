package week4

/**
 * @author macgeekalex
 */

    class BankAccount {
    private var balance = 0
    def deposit(amount: Int): Unit = {
      if (amount > 0) balance = balance+amount
    }
 
    def show(): Unit = {
      println(balance)
    }

    def withdraw(amount:Int): Int =
      if(0<amount && amount<=balance) {
        balance = balance - amount
        balance
      } else throw new Error("Insufficient Funds")
  }
