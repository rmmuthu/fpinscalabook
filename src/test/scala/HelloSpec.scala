import org.scalatest.FlatSpec
import z_playground.Hello

/**
 * Created by Muthu on 9/23/2015.
 */
class HelloSpec extends FlatSpec{

  "Calling forTest method"  should " return forTest" in {
    assert(Hello.forTest=="forTestRes")
  }

  "Calling forTest method 2"  should " return forTest" in {
    assert(Hello.forTest=="forTestRes")
  }



  it should "return forTest 3" in {
    assert(Hello.forTest == "forTestRes")
  }


}
