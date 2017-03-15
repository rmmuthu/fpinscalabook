package coursera.queue

/**
  * Created by bcarlson on 3/8/17.
  */
trait Queue[+T] {
  def head:T
  def tail:Queue[T]
  def enqueue[U>:T](x :U):Queue[U]
}
object Queue{
  def apply[T](as:T*): Queue[T] = new QueueImpl(Nil, as.toList)


  private class QueueImpl[T] (
    private val leading:List[T],
    private val trailing:List[T]
  )extends Queue[T]{
    def this()=this(Nil, Nil)

    val mirror=
      if(leading.isEmpty) new QueueImpl[T](trailing.reverse, Nil)
      else this

    override def head: T = mirror.leading.head

    override def tail: Queue[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }

    override def toString:String =leading.toString + "~"+ trailing.toString

    override def enqueue[U>:T](x: U): Queue[U] = new QueueImpl[U](leading, x::trailing)
  }
}