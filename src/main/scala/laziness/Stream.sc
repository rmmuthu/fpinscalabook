import laziness._

val z = Stream({println("1+1");1+1},
  {println("2+2");2+2},
  {println("3+3");3+3})




//
//def take[A](n: Int, s:Stream[A]): Stream[A] = s match {
//  case Cons(h, t) if n>0 => cons[A](h(), take(n-1, t()))
//  case _ => empty
//}


//Stream(1,2,3,4,5,6,7).take(4).toList()
//Stream(1,2,3,4,5,6,7).drop(2).toList()
//
//Stream(1,2,3,4,5,6,7).takeWhile(i=>i<3).toList()
//Stream(1,2,3,4,5,6,7).takeWhileWFoldRight(i=>i<3).toList()
//Stream(1,2,3,4,5,6,7).exists(x=>x==1)
//Stream(1,2,3,4,5,6,7).exists(x=>x==7)
//Stream(1,2,3,4,5,6,7).exists(x=>x==5)
//
//Stream(1,2,3,4,5,6,7).foldRight(1)((a,b)=>a*b)
//Stream(1,2,3,4, 5).foldRight(1)((a,b)=>a*b)
//Stream(1,2,3,4,5,6,7).existsWFoldRight(x=>x==1)
//Stream(1,2,3,4,5,6,7).existsWFoldRight(x=>x==5)
//Stream(1,2,3,4,5,6,7).existsWFoldRight(x=>x==9)
//Stream(1,2,3,4,5,6,7).forAll(x=>x<5)
//Stream.cons({println("1");1}, Stream.cons({println("2");2}, Stream.cons({println("3");3}, Stream.empty))).forAll(x=>x<5)


Stream.cons({println("1");1}, Stream.cons({println("2");2}, Stream.cons({println("3");3}, Stream.empty))).headOption
Stream.cons({println("1");1}, Stream.cons({println("2");2}, Stream.cons({println("3");3}, Stream.empty))).headOptionWFoldRight





//
//Stream(1,2,3,4,5,6,7).take(7).toList()
//
//Stream(1,2,3,4,5,6,7).take(77).toList()
