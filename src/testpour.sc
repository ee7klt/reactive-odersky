object testpour {
  val problem = new Pouring(Vector(4, 9))         //> problem  : Pouring = Pouring@327471b5
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with te
                                                  //| stpour.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(0,1)
                                                  //| , Pour(1,0))
  problem.pathSets                                //> res1: Stream[Set[testpour.problem.Path]] = Stream(Set(--> Vector(0, 0)), ?)
  problem.pathSets apply 2                        //> res2: Set[testpour.problem.Path] = Set(Fill(0) Fill(1)--> Vector(4, 9), Fill
                                                  //| (0) Pour(0,1)--> Vector(0, 4), Fill(1) Fill(0)--> Vector(4, 9), Fill(1) Pour
                                                  //| (1,0)--> Vector(4, 5))
  problem.solutions(6)                            //> res3: Stream[testpour.problem.Path] = Stream(Fill(1) Pour(1,0) Empty(0) Pour
                                                  //| (1,0) Empty(0) Pour(1,0) Fill(1) Pour(1,0)--> Vector(4, 6), ?)

  List(1, 2, 3) apply 2                           //> res4: Int = 3

}