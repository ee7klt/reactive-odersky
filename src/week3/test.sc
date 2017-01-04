import week3._

object test{
  println("Welcome")                              //> Welcome
  object sim extends Circuits with Parameters
  import sim._
  val in1, in2, sum, carry = new Wire             //> in1  : week3.test.sim.Wire = week3.Gates$Wire@880ec60
                                                  //| in2  : week3.test.sim.Wire = week3.Gates$Wire@3f3afe78
                                                  //| sum  : week3.test.sim.Wire = week3.Gates$Wire@7f63425a
                                                  //| carry  : week3.test.sim.Wire = week3.Gates$Wire@36d64342
  halfAdder(in1,in2,sum,carry)
  probe("sum", sum)                               //> (sum,0,false)
  probe("carry", carry)                           //> (carry,0,false)
  in1 setSignal true
  run()                                           //> *** simulation started, time = 0 ***
                                                  //| (sum,5,true)
                                                  //| (sum,10,false)
                                                  //| (sum,10,true)
  in2 setSignal true
  run()                                           //> *** simulation started, time = 10 ***
                                                  //| (carry,13,true)
                                                  //| (sum,18,false)
   
}