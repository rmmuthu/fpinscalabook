package chap3

import scala.annotation.tailrec

/**
  * Created by bcarlson on 12/21/16.
  */

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head:A, tail: List[A]) extends List[A]

object List {



  def zipWith[A,B,C](ls:List[A], ls2:List[B], f:(A,B)=>C):List[C]= (ls, ls2) match{
    case (_, Nil) => Nil
    case (Nil, _) => Nil
    case (Cons(h1,t1),Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2,f))
  }


  def zip(ls:List[Int], ls2:List[Int]):List[Int]= (ls, ls2) match{
    case (_, Nil) => Nil
    case (Nil, _) => Nil
    case (Cons(h1,t1),Cons(h2,t2)) => Cons(h1+h2, zip(t1,t2))
  }

  def foldRight[A, B](ls: List[A], z: B)(f: (A, B) => B): B = ls match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  @tailrec
  def foldLeft[A, B](ls: List[A], z: B)(f: (B, A) => B): B = ls match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def add1(ls: List[Int]): List[Int] = {
    foldRight(ls, Nil: List[Int])((h, t) => Cons(h + 1, t))
  }


  def doubleToString(ds:List[Double]):List[String]=
    foldRight(ds, Nil:List[String])((h,t)=>Cons(h.toString, t))

  def map[A,B](ls:List[A], f:A=>B):List[B]={
    List.foldRight(ls, Nil:List[B])((x,xs)=>Cons(f(x), xs))
  }
  def filter[A](as: List[A])(f: A => Boolean): List[A]={
    foldRight(as, Nil:List[A])((x,xs)=>if(f(x))Cons(x,xs)else xs)
  }




  def foldRightViaFoldLeft[A, B](ls: List[A], z: B)(f: (A, B) => B): B = {
    List.foldLeft(List.reverse(ls), z)((b, a) => f(a, b))
  }

  def foldRightViaFoldLeft_1[A, B](ls: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(ls, (b: B) => b)((g: B => B, a: A) => b => g(f(a, b)))(z)
  }


  def foldLeftViaFoldRight_1[A, B](ls: List[A], z: B)(f: (B, A) => B): B = {
    foldRight(ls, (b: B) => b)((a: A, g: B => B) => b => g(f(b, a)))(z)
  }


  def foldLeft2[A, B](ls: List[A], z: B)(f: (A, B) => B): B = ls match {
    case Nil => z
    case Cons(x, xs) => foldLeft2(xs, f(x, z))(f)
  }

  def reverse[A](ls: List[A]): List[A] = {
    List.foldLeft(ls, Nil: List[A])((y: List[A], x: A) => Cons(x, y))
  }

  def init[A](ls: List[A]): List[A] = ls match {
    case Nil => throw sys.error("Not enough items")
    case Cons(_, Nil) => Nil
    case Cons(head, tail) => Cons(head, init(tail))


  }


  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(x, xs) => Cons(x, append(xs, a2))
  }

  def appendViaFold[A](ls1: List[A], ls2: List[A]): List[A] = {
    foldRight(ls1, ls2)((x: A, xs: List[A]) => Cons(x, xs))
  }

  def concat[A](ls: List[List[A]]): List[A] = {
    foldLeft(ls, Nil: List[A])(append)
  }

  def setHead[A](ls: List[A], head: A): List[A] = ls match {
    case Nil => Cons(head, Nil)
    case Cons(_, _) => Cons(head, ls)
  }


  def tail[A](ls: List[A]): List[A] = ls match {
    case Nil => throw sys.error("Cannot get the tail of an empty list")
    case Cons(_, tail) => tail
  }

  def drop[A](ls: List[A], n: Int): List[A] = ls match {
    case Nil => throw sys.error("Not as many to drop ")
    case Cons(_, tail) => if (n > 0) drop(tail, n - 1) else ls
  }

  def dropWhile[A](ls: List[A], f: A => Boolean): List[A] = ls match {
    case Nil => Nil
    case Cons(head, tail) => if (f(head)) dropWhile(tail, f) else Cons(head, dropWhile(tail, f))
  }

  def apply[A](ls: A*): List[A] =
    if (ls.isEmpty) Nil
    else Cons(ls.head, apply(ls.tail: _*))


  def combine[A](ls: List[A], zero: A, f: (A, A) => A): A = ls match {
    case Nil => zero
    case Cons(head, tail) => f(head, combine(tail, zero, f))
  }

  def add: (Int, Int) => Int = (x: Int, y: Int) => x + y

  def prod: (Int, Int) => Int = (x: Int, y: Int) => x * y

  def sum(ls: List[Int]): Int = {
    combine(ls, 0, add)
  }

  def product(ls: List[Int]): Int = {
    combine(ls, 0, prod)
  }

  def concatString: (String, String) => String = (x: String, y: String) => x.concat(y)


  def length[A](ls: List[A]): Int = {
    foldRight(ls, 0)((_, acc: Int) => acc + 1)
  }

  def hasSubsequence[A](sup:List[A], sub:List[A]):Boolean = (sup, sub) match {
    case (Nil, Nil)=> true
    case (Nil, _) => false
    case (_, Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) => if (h1 == h2) hasSubsequence(t1, t2) else hasSubsequence(t1, sub)
  }
}