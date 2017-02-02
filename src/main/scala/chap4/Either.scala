package chap4

/**
  * Created by bcarlson on 1/31/17.
  */
sealed trait Either[+E, +A] {
  def map[B](f:A=>B):Either[E, B]=this match{
    case Left(e) => Left(e)
    case Right(a) => Right(f(a))
  }

  def flatMap[EE >:E, B](f:A=>Either[EE,B]):Either[EE,B]=this match{
    case Left(e)=> Left(e)
    case Right(a)=> f(a)
  }
  def orElse[EE >: E, B >: A](b: => Either[EE,B]):Either[EE,B]=this match{
    case Left(e) => b
    case Right(a) => Right(a)
  }

  def map2[EE >: E, B, C](b:Either[EE,B])(f:(A,B)=>C):Either[EE,C]=this match{
    case Left(e)=> Left(e)
    case Right(a) => b map((bb:B)=> f(a,bb)  )
  }


}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

object Either{
  def traverse[E,A,B](as:List[A])(f:A=>Either[E,B]):Either[E,List[B]]={
    as.foldRight[Either[E,List[B]]](Right(Nil:List[B]))((a:A,b:Either[E,List[B]]) => f(a).map2(b)(_::_))
  }


  def sequence[E,A](es:List[Either[E,A]]):Either[E,List[A]]=
    traverse(es)(x=>x)
}