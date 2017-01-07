

/**
 * @author macgeekalex
 */
  object accounts {
    def consolidated(accts: List[BankAccount]): Signal[Int] =
      Signal(accts.map(_.balance()).sum)          //> consolidated: (accts: List[BankAccount])Signal[Int]
      
    val a = new BankAccount()                     //> a  : BankAccount = BankAccount@3796751b
    val b = new BankAccount()                     //> b  : BankAccount = BankAccount@67b64c45
    val c = consolidated(List(a,b))               //> c  : Signal[Int] = Signal@4157f54e
    c()                                           //> res0: Int = 0
    a deposit 20                                  //> java.lang.AssertionError: assertion failed: cyclic signal definition
                                                  //| 	at scala.Predef$.assert(Predef.scala:165)
                                                  //| 	at Signal.apply(Signal.scala:36)
                                                  //| 	at BankAccount$$anonfun$deposit$1.apply$mcI$sp(BankAccount.scala:10)
                                                  //| 	at BankAccount$$anonfun$deposit$1.apply(BankAccount.scala:10)
                                                  //| 	at BankAccount$$anonfun$deposit$1.apply(BankAccount.scala:10)
                                                  //| 	at Signal$$anonfun$1.apply(Signal.scala:17)
                                                  //| 	at scala.util.DynamicVariable.withValue(DynamicVariable.scala:58)
                                                  //| 	at Signal.computeValue(Signal.scala:17)
                                                  //| 	at Signal.update(Signal.scala:31)
                                                  //| 	at Var.update(Signal.scala:43)
                                                  //| 	at BankAccount.deposit(BankAccount.scala:10)
                                                  //| 	at accounts$$anonfun$main$1.apply$mcV$sp(accounts.scala:14)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.lib
                                                  //| Output exceeds cutoff limit.
  }
  