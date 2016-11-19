

class Pouring(capacity: Vector[Int]) {
  // States: the 'fullness' of each glass
  type State = Vector[Int]
  val initialState = capacity map (x => 0)
  
  // Moves: Empty, Fill, Pour
  trait Move {
    def change(state: State): State 
  }
  case class Empty(glass:Int) extends Move {
    def change(state:State) = state updated (glass, 0)
  }
  
  case class Fill(glass: Int) extends Move {
    def change(state:State) = state updated (glass, capacity(glass))
  }
  
  case class Pour(from: Int, to: Int) extends Move {
    def change(state: State) = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }
  
 
  
  // All possible moves
  val glasses = 0 until capacity.length
  
  val moves = 
   (for (g <- glasses) yield Empty(g)) ++
   (for (g <- glasses) yield Fill(g)) ++
   (for (from <- glasses; to <- glasses if from != to) yield Pour(from,to))
  
   
   // Path: sequence of moves
   // endState: final state after all moves are applied to initial state
   // extend: extend Path by one more move
   
   class Path(history: List[Move], val endState: State) {
    // endState is final state after applying all sequential moves to the initialState
    def extend(move: Move) = new Path(move :: history, move change endState )
    // print a list with space between elements
    override def toString = (history.reverse mkString " ") + "--> " + endState
  }
  
  // InitialPath has no moves on it
  val initialPath = new Path(Nil, initialState)
  
  // get the next longest path from current path
  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty  // nothing to evolve  
    else {
      // more is current set of paths, each extended by exactly one extra move
      val more = for {
        path <- paths
        next <- moves map path.extend   // extend the path by one more move. path length is now n+1
        if !(explored contains next.endState) //path included in stream only if it does not lead to an endState that has already been explored.
      } yield next  // a set of paths. for-ex always concatenates results from each pass thru loop
      // return a stream of paths. 
      paths #:: from(more, explored ++ (more map (_.endState)))  // recursive call concat using stream concat
    }
 
  val pathSets = from(Set(initialPath), Set(initialState))  
  // but how does it get out of the Nil state? won't it always see the path as empty and return Stream.empty?
  // wait, but maybe Path(Nil) isn't empty?
  // yes of course it's not!! it's a Nil stream, which is something! not nothing!
  // because if i did from Set(), THAT would return empty stream! 
  
  def solutions(target: Int): Stream[Path] = 
    for {
      pathSet <- pathSets
      path <- pathSet
       if (path.endState contains target)      
    } yield path
  
    
    
   
    
}


