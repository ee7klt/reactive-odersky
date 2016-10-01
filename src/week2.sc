object week2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  (1 to 10).toStream                              //> res0: scala.collection.immutable.Stream[Int] = Stream(1, ?)

  def streamRange(lo: Int, hi: Int): Stream[Int] = {
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))
  }                                               //> streamRange: (lo: Int, hi: Int)Stream[Int]

  streamRange(1, 10)(3)                           //> res1: Int = 4
  streamRange(1, 10).take(3)                      //> res2: scala.collection.immutable.Stream[Int] = Stream(1, ?)

  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)              //> listRange: (lo: Int, hi: Int)List[Int]

  listRange(1, 10)                                //> res3: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  def isPrime(n: Int): Boolean =
    ((1 to n) filter (x => n % x == 0)).length == 2
                                                  //> isPrime: (n: Int)Boolean

  ((1000 to 10000).toStream filter isPrime)(2)    //> res4: Int = 1019

  val xs1 = Stream.cons(1, Stream.cons(2, Stream.empty))
                                                  //> xs1  : Stream.Cons[Int] = Stream(1, ?)
  val xs2 = Stream.cons(3, Stream.cons(4, Stream.empty))
                                                  //> xs2  : Stream.Cons[Int] = Stream(3, ?)
  val xs3 = 1 #:: xs2                             //> xs3  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
  xs3(0)                                          //> res5: Int = 1

  // 2.3 lazy evaluation
  def expr = {
    val x = { print("x"); 1 }
    lazy val y = { print("y"); 2 }
    def z = { print("z"); 3 }
    print("\n")
    z + y + x
    print("\n")

  }                                               //> expr: => Unit
  expr                                            //> x
                                                  //| zy
  /**
   * object Stream {
   * def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
   * def isEmpty = false
   * def head = hd
   * def tail = tl
   * }
   * def empty = new Stream[Nothing] {
   * def isEmpty = true
   * def head = throw new NoSuchElementException("empty.head")
   * def tail = throw new NoSuchElementException("empty.tail")
   * }
   *
   * }
   */

  val s1 = Stream.empty                           //> s1  : scala.collection.immutable.Stream[Nothing] = Stream()
  s1.isEmpty                                      //> res6: Boolean = true
  val s2 = Stream.cons(1, Stream.empty)           //> s2  : Stream.Cons[Int] = Stream(1, ?)
  s2.head                                         //> res7: Int = 1
  s2.tail                                         //> res8: scala.collection.immutable.Stream[Int] = Stream()

  (streamRange(1000, 10000) filter isPrime) apply 1
                                                  //> res9: Int = 1013

  def from(n: Int): Stream[Int] = n #:: from(n + 1)
                                                  //> from: (n: Int)Stream[Int]
  from(1) apply 2                                 //> res10: Int = 3
  from(1)                                         //> res11: Stream[Int] = Stream(1, ?)

  val nats = from(0)                              //> nats  : Stream[Int] = Stream(0, ?)
  val m4s = nats map (_ * 4)                      //> m4s  : scala.collection.immutable.Stream[Int] = Stream(0, ?)
  (m4s take 100).toList                           //> res12: List[Int] = List(0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52
                                                  //| , 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100, 104, 108, 112, 116, 120,
                                                  //|  124, 128, 132, 136, 140, 144, 148, 152, 156, 160, 164, 168, 172, 176, 180,
                                                  //|  184, 188, 192, 196, 200, 204, 208, 212, 216, 220, 224, 228, 232, 236, 240,
                                                  //|  244, 248, 252, 256, 260, 264, 268, 272, 276, 280, 284, 288, 292, 296, 300,
                                                  //|  304, 308, 312, 316, 320, 324, 328, 332, 336, 340, 344, 348, 352, 356, 360,
                                                  //|  364, 368, 372, 376, 380, 384, 388, 392, 396)
  def sieve(s: Stream[Int]): Stream[Int] =
  	s.head #:: sieve(s.tail filter (_ % s.head != 0))
                                                  //> sieve: (s: Stream[Int])Stream[Int]
  	
 
  (sieve(from(2)) take 100).toList                //> res13: List[Int] = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
                                                  //|  47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 
                                                  //| 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 
                                                  //| 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 
                                                  //| 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 
                                                  //| 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 
                                                  //| 479, 487, 491, 499, 503, 509, 521, 523, 541)
  
  
}