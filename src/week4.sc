


object week4 {

 trait Publisher {
 	private var subscribers: Set[Subscriber] = Set()
 	
 	def subscribe(subscriber: Subscriber): Unit =
 		subscribers += subscriber
 	def unsubscribe(subscriber: Subscriber): Unit =
 		subscribers -= subscriber
 	def publish(): Unit =
 		subscribers.foreach(_.handler(this))
 }
  
  
  trait Subscriber {
  	def handler(pub: Publisher)
  }
  
  class BankAccount extends Publisher {
  	private var balance = 0
  	
  	def deposit(amount: Int): Unit =
  		if (amount > 0) {
  			balance = balance + amount
  			publish()
  		}
  		
  	def withdraw(amount: Int): Unit =
  		if (0 < amount && amount <= balance) {
  			balance = balance - amount
  			publish()
  		} else throw new Error("Insufficient Funds")
  	
  	def currentBalance = balance
  }
  
  class Consolidator(observed: List[BankAccount]) extends Subscriber {
  	observed.foreach(_.subscribe(this))
  	private var total: Int = _
  	compute()
  	
 		private def compute() =
    	total = observed.map(_.currentBalance).sum
  		
  	def handler(pub: Publisher) = compute()
  	def totalBalance = total
  }
  
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val a = new BankAccount                         //> a  : week4.BankAccount = week4$BankAccount@6fadae5d
  val b = new BankAccount                         //> b  : week4.BankAccount = week4$BankAccount@17f6480
  val c = new Consolidator(List(a,b))             //> c  : week4.Consolidator = week4$Consolidator@614c5515
  c.totalBalance                                  //> res0: Int = 0
  a deposit 20
  c.totalBalance                                  //> res1: Int = 20
  b deposit 30
  c.totalBalance                                  //> res2: Int = 50
  
  
}