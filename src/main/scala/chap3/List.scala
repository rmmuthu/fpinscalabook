package chap3

/**
  * Created by bcarlson on 10/24/16.
  */
sealed trait List[+A]

case object Nil extends List[Nothing]
case class Cons[+A](head:A, tail:List[A]) extends List[A]


object List{
  def sum(ints:List[Int]):Int=ints match {
    case Nil => 0
    case Cons(head, tail) => head + sum(tail)
  }
  def product(ints:List[Int]):Int=ints match {
    case Nil => 0
    case Cons(head, tail) => head * product(tail)
  }

  def apply[A](args:A*):List[A] = {
    if(args.isEmpty) Nil
    else Cons(args.head, apply(args.tail:_*))
  }

  def tail[A](ls:List[A]):List[A] = ls match{
    case Nil =>  sys.error("List does not have enough elements to get tail")
    case Cons(_, tail) => tail
  }
  def setHead[A](head:A, ls:List[A]):List[A] = ls match{
    case Nil =>  sys.error("Empty list and cannot replace Head")
    case Cons(_, tail) => Cons(head, tail)
  }
  def drop[A](ls:List[A], n:Int):List[A] = ls match{
    case Nil =>  throw sys.error("List is smaller than n and cannot drop n elements")
    case Cons(_, tail) => drop(tail, n-1)
  }
  def dropWhile[A](ls:List[A], p:A=>Boolean):List[A] = ls match{
    case Nil =>  Nil
    case Cons(head, tail) => if(p(head)) dropWhile(tail, p) else Cons(head, dropWhile(tail, p))
  }
  def init[A](ls:List[A]):List[A] = ls match{
    case Nil=>throw sys.error("Cannot do init on an empty list")
    case Cons(head, Nil) => Nil
    case Cons(head, tail) => Cons(head, init(tail))
  }


  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B= as match
  {
    case Nil=> z
    case Cons(head, tail) => f(head, foldRight(tail, z)(f))
  }

}


