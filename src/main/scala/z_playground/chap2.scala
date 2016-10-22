package z_playground

import scala.annotation.tailrec

/**
 * Created by Muthu on 9/22/2015.
 */
object chap2 {

  def main(args:Array[String]): Unit ={

    println("Hello Muthu. Welcome to chapter 2");

    printf("factorial of 5 is %d", factorial(5))
    printf("\n")
    printf("factorial of 5000 is %d", factorial(5000))

  }


  def factorial(x:BigInt): BigInt ={
    @tailrec
    def go(inner:BigInt, acc:BigInt):BigInt={
      if(inner<=0) acc
      else go(inner-1, acc*inner)
    }
    go(x, 1)
  }

  def fib(n:Int):Int={
    @tailrec
    def go(x:Int, prevAcc:Int, acc:Int): Int ={
      if(x<=1)acc
      else go(x-1, acc, prevAcc+acc)
    }

    go(n, 0, 1)
  }

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def go[A](n:Int):Boolean ={
      if(n+1>=as.size) true
      else if(ordered(as(n), as(n+1))) go(n+1)
      else false
    }
    go(0)
  }

}
