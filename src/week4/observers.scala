package week4

/**
 * @author macgeekalex
 */
object observers {
      val BA = new BankAccount
  BA deposit 40
  BA withdraw 10
  BA withdraw 20
  BA.show()
}