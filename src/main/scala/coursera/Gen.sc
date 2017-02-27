import coursera._

val integers = new Generator[Int] {
  val rand = new java.util.Random

  override def generate = rand.nextInt()
}


val booleans = new Generator[Boolean] {
  override def generate = integers.generate >0
}

val pairs = new Generator[(Int, Int)] {
  override def generate = (integers.generate, integers.generate)
}

//val booleans2 = for(x<-integers) yield x>0
//def pairs2[T,U](t: Generator[T], u:Generator[U])=for{
//  x<-t
//  y<-u
//}yield(x,y)
//booleans.generate
//integers.generate
//pairs.generate
//
//booleans2.generate
//pairs2(integers, integers)

//for(x:Int<-integers) yield 100+x%(200-100)

integers.map(x=>100+x%100).generate