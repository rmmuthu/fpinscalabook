import coursera.queue._




class Fruit{

}
class Apple extends Fruit
class Orange extends Fruit

val a = Queue(new Apple)
val a1 = a.enqueue(new Apple)
val b = a.enqueue(new Orange)




val t :List[AnyRef] = List(2)::List("a")::Nil

val q = Queue(1,2,3)
q
q.head

q.head

q.tail.head
q.tail.tail.head

val q2:Queue[Any]= q.enqueue(5).enqueue(6)
val q3:Queue[Any]=q2
q2.head
q2.tail.head
q2.tail.tail.head
q2.tail.tail.tail.head
q2.tail.tail.tail.tail.head

class StrangeIntQueue extends Queue[Int]{
  override def head: Int = 1

  override def tail: Queue[Int] = Queue.apply(1)

  override def enqueue[U >: Int](x: U): Queue[U] = {println((x));Queue.apply(x)}
}


val x:Queue[Any]=new StrangeIntQueue
x.enqueue("a")