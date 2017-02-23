package laziness

import laziness.Stream._

import scala.annotation.tailrec


/**
  * Created by bcarlson on 2/6/17.
  */
trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(x, _) => Some(x())
  }

  def toList: List[A] = {
    @tailrec
    def go(tmp: Stream[A], acc: List[A]): List[A] = tmp match {
      case Empty => acc
      case Cons(x, xs) => go(xs(), x() :: acc)
    }

    go(this, List()).reverse
  }

//  def take(n: Int): Stream[A] = this match {
//    case Cons(h, t) if n>0 => cons[A](h(), t().take(n-1))
//    case _ => empty
//  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n>0 => cons(h(), t().take(n-1))
    case _ => empty
  }
  def takeWithFold(n: Int): Stream[A] = unfold(this) {
    case Cons(h, t) if n>0 => Some(h(), t())
    case _ => None
  }
  def takeWhile(p: A=>Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case _ => empty
  }
  def takeWhileWithFold(p: A=>Boolean): Stream[A] = unfold(this) {
    case Cons(h, t) if p(h()) => Some(h(), t())
    case _ => None
  }
  @tailrec
  final def drop(n:Int):Stream[A]=this match{
    case Cons(_, t) if n>0 => t().drop(n-1)
    case _ => this
  }
  def exists(p:A=>Boolean):Boolean = this match{
    case Cons(h,t) => if (p(h())) {println("true");true} else {println("false");t().exists(p)}
    case Empty => false

  }
  def zipWith[B,C](s2: Stream[B])(f: (A,B) => C): Stream[C] =unfold((this, s2)){
    case(Cons(h,t), Cons(h2,t2))=> Some(f(h(), h2()), (t(), t2()))
    case _ => None
  }
  def foldRight[B](z: =>B)(f:(A, =>B)=>B):B= this match{
    case Empty => z
    case Cons(h, t)=> f( h(), t().foldRight(z)(f))
  }
  def existsWFoldRight(p:A=>Boolean):Boolean= {
    foldRight(false)((a,b)=> {println("predicate check");p(a)} || {println("zero val");b})
  }
  def forAll(p:A => Boolean):Boolean=this match{
    case Cons(h,t)=> if(!p(h())) {println("ending");false} else {println("going"); t().forAll(p)}
    case _ => true
  }
  def takeWhileWFoldRight(p: A=>Boolean): Stream[A] = foldRight(empty:Stream[A])((a,b)=>if(p(a)) cons(a, b) else b)
  def headOptionWFoldRight: Option[A] = foldRight(None:Option[A])((a,_)=>Some(a))

  def map[B](f:A=>B):Stream[B]=this match{
    case Empty=>Stream.empty
    case Cons(h, t)=> cons(f(h()), t().map(f))
  }
  def mapWithUnfold[B](f:A=>B):Stream[B]={
    unfold(this){
      case Cons(h,t)=>Some(f(h()), t())
      case Empty => None
    }
  }
  def filter(p:A=>Boolean):Stream[A]=this match{
    case Empty=>empty
    case Cons(h,t)=>if(p(h())) cons(h(), t().filter(p)) else t().filter(p)
  }

  def mapWFoldRight[B](f:A=>B):Stream[B]={
    foldRight(empty:Stream[B])((a,b)=>cons(f(a),b))
  }
  def filterWFoldRight(p:A=>Boolean):Stream[A]=foldRight(empty:Stream[A])((a,b)=>if(p(a)) cons(a,b)else b)

  def append[B>:A](that: =>Stream[B]):Stream[B]=that match{
    case Empty=>this
    case Cons(h, t)=>cons(h(), append(t()))
  }
  def appendWFoldRight[B>:A](that: =>Stream[B]):Stream[B]=foldRight(that)((h,t)=>cons(h, t))
  def flatMap[B](f:A=>Stream[B]):Stream[B]=foldRight(empty:Stream[B])((a,b)=>f(a) append b)
}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: ()=>Stream[A] ) extends Stream[A]

object Stream{
  def cons[A](hd: => A, tl: => Stream[A]):Stream[A]={
    lazy val head = hd
    lazy val tail = tl
    Cons(()=>head, ()=>tail)
  }
  def empty[A]: Stream[A] = Empty
  def apply[A](as:  A*):Stream[A]={
    if (as.isEmpty) Empty
    else cons(as.head, apply(as.tail:_*))
  }

  def constant[A](a:A):Stream[A]={
    lazy val tail:Stream[A]=Cons(()=>a, ()=>tail)
    tail
  }

  def from(n: Int): Stream[Int]={
    Stream.cons(n, from(n+1))
  }
  def unfold[A,S](z:S)(f:S=>Option[(A,S)]):Stream[A]=f(z) match{
    case Some((h,s))=>Stream.cons(h, unfold(s)(f))
    case None=>Stream.empty
  }
  val fibs:Stream[Int]=unfold((0,1)) { case (f0,f1) => Some((f0,(f1,f0+f1))) }
  def fromUnfold(n:Int):Stream[Int]={
    unfold(n)(x=>Some(x, x+1))
  }
  def constantWFold[A](a:A):Stream[A]={
    unfold(a)(x=>Some(x,x))
  }



}

