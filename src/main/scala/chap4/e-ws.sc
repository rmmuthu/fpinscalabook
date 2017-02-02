import chap4._
def Try[A](a: => A )={
  try Right(a)
  catch{ case e:Exception => Left(e) }
}

def traverse[E,A,B](as:List[A])(f:A=>Either[E,B]):Either[E,List[B]]={
  as.foldRight[Either[E,List[B]]](Right(Nil:List[B]))((a:A,b:Either[E,List[B]]) => f(a).map2(b)(_::_))
}


def sequence[E,A](es:List[Either[E,A]]):Either[E,List[A]]=
  traverse(es)(x=>x)

def safeDiv(i:Int, j:Int):Either[Exception, Int]=
  Try (i/j)

//
//
//safeDiv(1,0).map(_+1)
//
//orElse(safeDiv(1,3), Left(22))
//orElse(safeDiv(1,0), Left(22))

sequence(List(safeDiv(1,0),safeDiv(200,2), safeDiv(4,2)))