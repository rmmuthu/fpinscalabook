package coursera.stream

/**
  * Created by bcarlson on 3/15/17.
  */

class Pouring(capacity:Vector[Int]) {
  type State = Vector[Int]

  val initialState: State = capacity map (_ => 0)

  trait Move {
    def change(s: State): State
  }

  case class Empty(glass: Int) extends Move {
    override def change(s: State):State = s updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    override def change(s: State):State = s updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    override def change(s: State):State = {
      val amount = (capacity(to) - s(to)) min s(from)
      s updated(from, s(from) - amount) updated(to, s(to) + amount)
    }
  }

  val glasses:Range = 0 until capacity.length
  val moves:IndexedSeq[Move] = (for (glass <- glasses) yield Empty(glass)) ++
    (for (glass <- glasses) yield Fill(glass)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  case class Path(history: List[Move], endState: State) {
    def extend(move: Move) = Path(move :: history, move change endState)

    override def toString: String = (history.reverse mkString " ") + "--->" + endState + "\n"
  }

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] = {
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves map path.extend
        if !(explored contains next.endState)
      } yield next

      paths #:: from(more, explored ++ (more map (_.endState)))
    }
  }


  val initialPath = Path(Nil, initialState)

  val pathSets:Stream[Set[Path]] = from(Set(initialPath), Set(initialState))

  def solve(target: Int): Stream[Path] = {
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path


  }
}
