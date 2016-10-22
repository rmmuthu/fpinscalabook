import org.scalatest.FlatSpec
import z_playground.chap2

/**
 * Created by Muthu on 9/23/2015.
 */
class chap2spec extends FlatSpec{

  "Factorial of 5 " should " be " in {
    assert(chap2.factorial(5)==120)
  }

  "Fibonacci of 1 " should " be " in {
    assert(chap2.fib(1)==1)
  }
  "Fibonacci of 2 " should " be " in {
    assert(chap2.fib(2)==1)
  }
  "Fibonacci of 3 " should " be " in {
    assert(chap2.fib(3)==2)
  }
  "Fibonacci of 4 " should " be " in {
    assert(chap2.fib(4)==3)
  }
  "Fibonacci of 5 " should " be " in {
    assert(chap2.fib(5)==5)
  }
  "Fibonacci of 6 " should " be " in {
    assert(chap2.fib(6)==8)
  }
  "Fibonacci of 7 " should " be " in {
    assert(chap2.fib(7)==13)
  }
  "Fibonacci of 8 " should " be " in {
    assert(chap2.fib(8)==21)
  }
  "Fibonacci of 9 " should " be " in {
    assert(chap2.fib(9)==34)
  }
  "Fibonacci of 10 " should " be " in {
    assert(chap2.fib(10)==55)
  }

}
