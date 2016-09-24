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
  h.isDefinedAt(List(1, 2))                       //> res7: Boolean = true

  def isPrime(n: Int): Boolean = ((1 to n) filter (x => n % x == 0)).length == 2
                                                  //> isPrime: (n: Int)Boolean

  val n = 10                                      //> n  : Int = 10
  val i = 10                                      //> i  : Int = 10
  (1 until n) flatMap (i =>
    (1 until i) filter (j => isPrime(i + j)) map (j => (i, j)))
                                                  //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5), (7,4), (7,6), (8,3), (8,5), (9,2), (9,
                                                  //| 4), (9,8))

  case class Book(title: String, authors: List[String])
  val books = Set(
    Book(title = "SIC Program", authors = List("Abelson, Harald", "Sussman, Gerald", "Spoon, Lex")),
    Book(title = "F Programming", authors = List("Bird, Harald", "Wadler, Gerald", "Spoon, Lex")),
    Book(title = "Scala", authors = List("Odersky, Martin", "Spoon, Lex")))
                                                  //> books  : scala.collection.immutable.Set[week1.Book] = Set(Book(SIC Program,
                                                  //| List(Abelson, Harald, Sussman, Gerald, Spoon, Lex)), Book(F Programming,Lis
                                                  //| t(Bird, Harald, Wadler, Gerald, Spoon, Lex)), Book(Scala,List(Odersky, Mart
                                                  //| in, Spoon, Lex)))
  
  
  
  for (b <- books; a <- b.authors; if a startsWith "Bird,")
    yield b.title                                 //> res9: scala.collection.immutable.Set[String] = Set(F Programming)

	books
		.flatMap(b => for (a <- b.authors; if a startsWith "Bird,") yield b.title)
                                                  //> res10: scala.collection.immutable.Set[String] = Set(F Programming)
	books.flatMap(b =>
		for (a <- b.authors withFilter( x => x startsWith "Spoon,")) yield b.title
	)                                         //> res11: scala.collection.immutable.Set[String] = Set(SIC Program, F Programm
                                                  //| ing, Scala)
	
	
	books
		.flatMap(b => b.authors withFilter (x => x startsWith "Spoon,") map ( y => b.title))
                                                  //> res12: scala.collection.immutable.Set[String] = Set(SIC Program, F Programm
                                                  //| ing, Scala)
	
	

  for (b <- books if (b.title indexOf "Program") >= 0)
    yield b.title                                 //> res13: scala.collection.immutable.Set[String] = Set(SIC Program, F Programm
                                                  //| ing)

  for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res14: scala.collection.immutable.Set[String] = Set(Spoon, Lex)

  // 1.2 translation of for

  //flatmap using for-ex

  val l = List(List(1, 2, 3), List(4, 5, 6))      //> l  : List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6))
  for (x <- l; y <- (x map (_ + 1))) yield y      //> res15: List[Int] = List(2, 3, 4, 5, 6, 7)

  // translation rules from for-ex to map, withFilter, flatMap
  // rule 1. generator
  // take every element and add one to it
  for (x <- 1 to 10) yield x + 1                  //> res16: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 3, 4, 5, 6, 7
                                                  //| , 8, 9, 10, 11)
  (1 to 10)
    .map(x => x + 1)                              //> res17: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 3, 4, 5, 6, 7
                                                  //| , 8, 9, 10, 11)
  // rule 2. generator, filter
  // find factors of 2 and multiply by 2
  for (x <- 1 to 10; if (x % 2 == 0)) yield x * 2 //> res18: scala.collection.immutable.IndexedSeq[Int] = Vector(4, 8, 12, 16, 20
                                                  //| )
  (1 to 10)
    .withFilter(x => (x % 2 == 0))
    .map(y => y * 2)                              //> res19: scala.collection.immutable.IndexedSeq[Int] = Vector(4, 8, 12, 16, 20
                                                  //| )
  // rule 3. generator, generator

  for {
    x <- 2 to 5
    y <- 1 to 3
  } yield (x, y)                                  //> res20: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (2
                                                  //| ,2), (2,3), (3,1), (3,2), (3,3), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3))

  // if just use map instead of flatMap, will be chunked
  // according to x
  (2 to 5).map(x => for (y <- 1 to 3) yield (x, y))
                                                  //> res21: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Ind
                                                  //| exedSeq[(Int, Int)]] = Vector(Vector((2,1), (2,2), (2,3)), Vector((3,1), (3
                                                  //| ,2), (3,3)), Vector((4,1), (4,2), (4,3)), Vector((5,1), (5,2), (5,3)))
  // using flatMap will concatenate the chunks in to a singleton
  (2 to 5).flatMap(x => for (y <- 1 to 3) yield (x, y))
                                                  //> res22: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (2
                                                  //| ,2), (2,3), (3,1), (3,2), (3,3), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3))
  (2 to 5)
    .flatMap(x => (1 to 3)
      .map(y => (x, y)))                          //> res23: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (2
                                                  //| ,2), (2,3), (3,1), (3,2), (3,3), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3))

  // combo of rule 3 and 2
  // displays all factors of n in the form (n, factor)
  val d = for {
    x <- 2 to 10
    y <- 2 to x
    if (x % y == 0)
  } yield (x, y)                                  //> d  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,2), (3,3
                                                  //| ), (4,2), (4,4), (5,5), (6,2), (6,3), (6,6), (7,7), (8,2), (8,4), (8,8), (9
                                                  //| ,3), (9,9), (10,2), (10,5), (10,10))
  (2 to 10).flatMap(x =>
  	for (y <- 2 to x; if (x % y == 0)) yield (x, y))
                                                  //> res24: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,2), (3
                                                  //| ,3), (4,2), (4,4), (5,5), (6,2), (6,3), (6,6), (7,7), (8,2), (8,4), (8,8), 
                                                  //| (9,3), (9,9), (10,2), (10,5), (10,10))
  (2 to 10).flatMap(x =>
    (2 to x)
      withFilter (y => x % y == 0)
      map (z => (x, z)))                          //> res25: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,2), (3
                                                  //| ,3), (4,2), (4,4), (5,5), (6,2), (6,3), (6,6), (7,7), (8,2), (8,4), (8,8), 
                                                  //| (9,3), (9,9), (10,2), (10,5), (10,10))
     
     

  // get all the primes
  ((d groupBy (_._1) toList) map (x => (x._1, x._2.length))) filter (x => x._2 == 1)
                                                  //> res26: List[(Int, Int)] = List((5,1), (2,1), (7,1), (3,1))



// generators

trait Generator[+T] {
  self =>
	def generate: T
	def map[S](f: T => S): Generator[S] = new Generator[S] {
			def generate = f(self.generate)
		}
}

val integers = new Generator[Int] {
	val rand = new java.util.Random
	def generate = rand.nextInt()
}                                                 //> integers  : week1.Generator[Int]{val rand: java.util.Random} = week1$$anonf
                                                  //| un$main$1$$anon$2@3c0ecd4b

integers.generate                                 //> res27: Int = 2086505100

val booleans = new Generator[Boolean] {
	def generate = integers.generate > 0
}                                                 //> booleans  : week1.Generator[Boolean] = week1$$anonfun$main$1$$anon$3@14bf97
                                                  //| 59

booleans.generate                                 //> res28: Boolean = true

val b = integers map {x => x > 0}                 //> b  : week1.Generator[Boolean] = week1$$anonfun$main$1$Generator$1$$anon$1@5
                                                  //| f341870
b.generate                                        //> res29: Boolean = true







}