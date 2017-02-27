package coursera

/**
  * Created by bcarlson on 2/23/17.
  */
trait Generator[+T] {
  self =>
  override def toString: String = ""+generate
  def generate:T
  def map[S](f:T=>S):Generator[S]=new Generator[S] {
    override def generate = f(self.generate)
  }
  def flatMap[S](f:T=>Generator[S]):Generator[S]=new Generator[S] {
    override def generate = f(self.generate).generate
  }

}
object Generator{
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
  def single[T](x:T):Generator[T]=new Generator[T] {
    override def generate = x
  }
//  def choose(lo:Int, hi:Int):Generator[Int]=
//    for(x:Int<-integers) yield lo+x%(hi-lo)

}
