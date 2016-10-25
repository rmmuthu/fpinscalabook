package chap3

import org.scalatest.{FlatSpec, FunSuite, Matchers}
import org.scalatest.junit.{JUnitRunner, JUnitSuite}
import org.junit.runner.{RunWith, Runner}
import org.scalatest.prop.PropertyChecks


/**
  * Created by bcarlson on 10/24/16.
  */

class ListTest extends FunSuite with Matchers with PropertyChecks{
  test("Sum of List 1,2,3,4 5"){
    List(1,2,3,4,5) equals(25)
  }



}
