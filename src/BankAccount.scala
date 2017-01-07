

/**
 * @author macgeekalex
 */
 class BankAccount {
    val balance = Var(0)
    //but library also defines subclass Var of Signal for signals that can be changed
    def deposit(amount: Int): Unit = {
      if (amount > 0) balance() = balance() + amount
    }
 
    def show(): Unit = {
      println(balance)
    }

    def withdraw(amount:Int): Int =
      if(0<amount && amount<=balance()) {
        balance() = balance() - amount
        balance()
      } else throw new Error("Insufficient Funds")
  }