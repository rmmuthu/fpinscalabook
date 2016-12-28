package chap3

import org.scalatest.{FlatSpec, FunSuite, Matchers}
import org.scalatest.junit.{JUnitRunner, JUnitSuite}
import org.junit.runner.{RunWith, Runner}
import org.scalacheck.Arbitrary
import org.scalatest.prop.PropertyChecks



/**
  * Created by bcarlson on 10/24/16.
  */

class ListTest extends FunSuite with Matchers with PropertyChecks{
  test("Sum of List 1,2,3,4 5"){
    List.sum(List.apply(1,2,3,4,5)) should equal (15)
  }

  test("Property Test For Sum"){
    forAll { ls: scala.collection.immutable.List[Int] => {
        List.sum(List.apply(ls: _*)) should equal(ls.sum)
      }
    }

  }

  test("Product of List 1,2,3,4 5"){
    List.product(List.apply(1,2,3,4,5)) should equal (120)

  }
  test("Product of List 1,2,0,4 5"){
    List.product(List.apply(1,2,0,4,5)) should equal (0)

  }
  test("Property Test For Product"){
    forAll { ls: scala.collection.immutable.List[Int] => {
      List.product(List.apply(ls: _*)) should equal(ls.product)
    }
    }

  }
}
