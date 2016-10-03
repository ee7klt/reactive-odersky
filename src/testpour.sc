object testpour {
  val problem = new Pouring(Vector(4, 7))         //> problem  : Pouring = Pouring@668bc3d5
  problem.moves                                   //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with te
                                                  //| stpour.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1), Pour(0,1)
                                                  //| , Pour(1,0))

}