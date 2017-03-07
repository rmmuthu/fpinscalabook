package coursera.tree

/**
  * Created by bcarlson on 3/7/17.
  */
sealed trait Tree[+A] {
  def isEmpty:Boolean
  def left:Tree[A]
  def right:Tree[A]
  def add[B >: A](b:B)(implicit ord: Ordering[B]):Tree[B]
  def combine[B >: A](b:Tree[B])(implicit ord: Ordering[B]): Tree[B]
  def thisValue:A
}


case object Empty extends Tree[Nothing] {
  override def isEmpty:Boolean = true
  override def left: Tree[Nothing] = throw sys.error("Calling left on empty tree")
  override def right: Tree[Nothing] = throw sys.error("Calling right on empty tree")
  def add[A](a:A)(implicit ord: Ordering[A]):Tree[A]=Node(Empty, Empty, a)
  override def combine[A](t:Tree[A])(implicit ord: Ordering[A]): Tree[A] = t
  override def toString=""
  override def thisValue=sys.error("Cannot get node value for Empty")
}
case class Node[+A](val l:Tree[A], val r:Tree[A], val value:A) extends Tree[A] {
  override def isEmpty: Boolean = false
  override def left:Tree[A] = l
  override def right:Tree[A] = r
  val thisValue:A=value
  override def add[B >: A](b:B)(implicit ord: Ordering[B]):Tree[B]={
    if(ord.equiv(b,thisValue)) this
    else if( ord.lt(thisValue, b)) { /*println( thisValue + "less than "+b);*/Node(l , r add b, thisValue)}
    else { /*println( thisValue + "not less than "+b);*/Node(l add b, r, thisValue)}
  }
  override def combine[B >: A](b:Tree[B])(implicit ord: Ordering[B]): Tree[B]=
    b combine left combine right add thisValue

  override def toString="("+left.toString+"."+thisValue+"."+right.toString+")"
}

object Tree{
  def apply[A](args:A*)(implicit ordering: Ordering[A]):Tree[A]={

    def go(a:A*):Tree[A]= {
      if (a.isEmpty) Empty
      else go(a.tail: _*).add (a.head )(ordering)
    }
    go(args.reverse:_*)
  }

}
