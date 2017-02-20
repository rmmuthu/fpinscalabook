package errorhandling

/**
  * Created by bcarlson on 1/31/17.
  */

sealed trait Option[+A] {
  def map[B](f:A=>B):Option[B]=this match{
    case None=>None
    case Some(x)=>Some(f(x))
  }
  def flatMap[B](f:A=>Option[B]):Option[B]=this match{
    case None=>None
    case Some(x)=> f(x)
  }
  def getOrElse[B >: A](default: => B):B = this match{
    case None=>default
    case Some(x)=>x
  }
  def orElse[B>:A](ob: => Option[B]):Option[B]=this match {
    case None=>ob
    case Some(x)=>Some(x)
  }
  def filter(f:A=>Boolean):Option[A]=this match{
    case Some(x) if f(x)=> Some(x)
    case _ => None
  }



}
case object None extends Option[Nothing]
case class Some[A] (value:A)extends Option[A]

object Option{
  def sequence[A](a:List[Option[A]]):Option[List[A]]=a match{
    case Nil =>Some(Nil)
    case x::xs=>x flatMap(hh=>sequence(xs) map(hh::_))
  }
  def sequence_1[A](a: List[Option[A]]): Option[List[A]] =
    a.foldRight[Option[List[A]]](Some(List()))((x:Option[A],y:Option[List[A]])=> x flatMap(xx=>y map(yy=>xx::yy)))

  def map2[A,B,C](a:Option[A], b:Option[B])(f:(A,B)=>C):Option[C]={
    a flatMap((aa:A)=>b map(bb=>f(aa,bb)))
  }

  def traverse[A,B](a:List[A])(f:A=>Option[B]):Option[List[B]]=a match{
    case Nil=>Some(Nil)
    case x::xs=>  traverse(xs)(f) flatMap(yy=> f(x) map ( xx=> xx::yy ) )
  }

  def traverse_1[A,B](a:List[A])(f:A=>Option[B]):Option[List[B]]={
    a.foldRight(Some(Nil):Option[List[B]])((x:A,y:Option[List[B]])=>y flatMap(yy=> {f(x) map(xx=>xx::yy)}))
  }

  def traverse_2[A,B](a:List[A])(f:A=>Option[B]):Option[List[B]]={
    a.foldRight(Some(Nil):Option[List[B]])((x:A,y)=> f(x) flatMap(xx=>y map(yy=>xx::yy)) )
  }

}

