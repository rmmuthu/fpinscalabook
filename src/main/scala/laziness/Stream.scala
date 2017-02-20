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
  def takeWhile(p: A=>Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case _ => empty
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



}

