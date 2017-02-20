import errorhandling._

case class Person(name: Name, age: Age)
sealed class Name(val value: String)
sealed class Age(val value: Int)

def mkName(name: String): Either[String, Name] =
  if (name == "" || name == null) Left("Name is empty.")
  else Right(new Name(name))
def mkAge(age: Int): Either[String, Age] =
  if (age < 0) Left("Age is out of range.")
  else Right(new Age(age))


def mkPerson(name: String, age: Int): Either[String, Person] =
  mkName(name).map2(mkAge(age))(Person)


mkPerson(null,-1)
mkPerson("Harry",-1)
mkPerson("Harry",21)
mkPerson(null,-1)


case class A[B,C](b:B, c:C)

sealed trait A1
case class B1() extends A1
case class C1() extends A1

class BB() extends B1
