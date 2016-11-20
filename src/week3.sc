object week3 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val problem = new Pouring(Vector(4, 9))         //> problem  : Pouring = Pouring@4909b8da
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with w
                                                  //| eek3.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(0,1),
                                                  //|  Pour(1,0))
  List(1, 2, 3) apply 2                           //> res1: Int = 2

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

  val BA = new BankAccount                      //> BA  : week3.BankAccount = week3$$anonfun$main$1$BankAccount$1@337d0578
  BA deposit 40
  BA withdraw 10
  BA withdraw 20


  val x = new BankAccount
  val y = x
  x deposit 30
  x withdraw 20



def power (x: Double, exp: Int): Double = {
  var r = 1.0
  var i = exp
  while (i > 0) {r = r*x; i = i-1}
  r
}

power(3,4)

  def WHILE(condition: => Boolean)(command: => Unit): Unit = {
    if (condition) {
      command
      WHILE(condition)(command)
    }
    else ()

  }

  def power2(x: Double, exp: Int ): Double = {
    var r = 1.0
    var i = exp
    WHILE (i > 0) {r = r*x; i = i-1}
    r
  }

  power2(3,4)


  def REPEAT(command: => Unit)(condition: => Boolean): Unit = {
    command
    if (!condition) {
      REPEAT(command)(condition)
    }
    else ()
  }


  var i = 1
  WHILE (i > 0) {println(i); i = i - 1}

  i = 3
  REPEAT {println(i); i = i - 1}  (i == 0)


class Repeat(command: => Unit) {
  def until(condition: => Boolean): Unit = {
    command
    if (condition) () else until(condition)
  }
}

  object Repeat {
    def apply(command: => Unit) = new Repeat(command)
  }

  var p = 5
  Repeat {
    println(p);
    p -= 1
  } until (p < 1)


  //println(s"i:$i")

for (i <- 1 until 3; j <- "abc") println(i + " " + j)
 (1 until 3) foreach (i => "abc" foreach(j => println(i + " " + j)))


}