package datastructures

/**
  * Created by bcarlson on 1/3/17.
  */
sealed trait Tree[+A]
case class Leaf[A](value:A) extends Tree[A]
case class Branch[A](left:Tree[A], right:Tree[A]) extends Tree[A]


object Tree{
  def size[A](t:Tree[A]):Int=t match{
    case Leaf(v)=> 1
    case Branch(t1,t2)=>1+size(t1)+size(t2)
  }

  def max(t:Tree[Int]):Int=t match{
    case Leaf (x) => x
    case Branch (t1, t2) => max(t1) max max(t2)
  }


  def maxDepth[A](t:Tree[A]):Int=t match{
    case Leaf(_)=>0
    case Branch(t1,t2)=>1+(maxDepth(t1) max maxDepth(t2))
  }

  def map[A,B](t:Tree[A])(f:A=>B):Tree[B]=t match{
    case Leaf(x)=>Leaf(f(x))
    case Branch(t1, t2)=>Branch(map(t1)(f), map(t2)(f))
  }


  def fold[A,B](t:Tree[A])(f:A=>B)(g:(B,B)=>B):B=t match{
    case Leaf(x)=>f(x)
    case Branch(t1,t2)=>g(fold(t1)(f)(g), fold(t2)(f)(g))
  }
  def sizeWFold[A](t:Tree[A]):Int=
    fold(t)(_=>1)(1+_+_)

  def maxWFold(t:Tree[Int]):Int={
    fold(t)(x=>x)((x:Int,y:Int)=>x.max(y))
  }
  def maxDepthWFold[A](t:Tree[A]):Int={
    fold(t)(_=>1)((x,y)=>1+(x max y))
  }


  def mapWithFold[A,B](t:Tree[A])(f:A=>B):Tree[B]={
    fold(t)(x=>Leaf(f(x)):Tree[B])((x:Tree[B],y:Tree[B])=>Branch(x,y))
  }


}