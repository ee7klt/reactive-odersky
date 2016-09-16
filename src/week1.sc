object week1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val x = 1                                       //> x  : Int = 1
  def increase(i: Int) = i + 1                    //> increase: (i: Int)Int
  increase(x)                                     //> res0: Int = 2

  val f: PartialFunction[String, String] = { case "ping" => "pong" }
                                                  //> f  : PartialFunction[String,String] = <function1>
  f("ping")                                       //> res1: String = pong
  f.isDefinedAt("abc")                            //> res2: Boolean = false

  val g: PartialFunction[List[Int], String] = {
    case Nil            => "one"
    case x :: y :: rest => "two"
  }                                               //> g  : PartialFunction[List[Int],String] = <function1>

  g(Nil)                                          //> res3: String = one
  g(List(1, 2))                                   //> res4: String = two
  g(List(1, 2, 3))                                //> res5: String = two

  val h: PartialFunction[List[Int], String] = {
    case Nil => "one"
    case x :: rest =>
      rest match {
        case Nil => "two"
      }
  }                                               //> h  : PartialFunction[List[Int],String] = <function1>

  h(List(1))                                      //> res6: String = two
h.isDefinedAt(List(1,2))                          //> res7: Boolean = true

}