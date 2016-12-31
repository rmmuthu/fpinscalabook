import chap3._



val v1 = List(1,2,3,4,12,11)
val v12 = List(1,2,3,4,12)
val v13 = List(2,3,4,12)
val v14 = List(2,3,14,12)
val v15=List(12)
val v16=List(1,2)
val v17=List(2,3)
val v18=List(3,4)
val v19=List(4,12)


val v2=Nil:List[Int]
val v3 = List("This is","an example","that is also ","a test")
def flatMap[A,B](as: List[A])(f: A => List[B]): List[B]={
  List.concat(List.map(as, f))
}

flatMap(v1)(i => List(i,i))





List.hasSubsequence(v1, v1)
List.hasSubsequence(v1, v12)
List.hasSubsequence(v1, v13)
List.hasSubsequence(v1, v14)
List.hasSubsequence(v1, v15)
List.hasSubsequence(v1, v16)
List.hasSubsequence(v1, v17)
List.hasSubsequence(v1, v18)
List.hasSubsequence(v1, v19)
List.hasSubsequence(v1, v2)

