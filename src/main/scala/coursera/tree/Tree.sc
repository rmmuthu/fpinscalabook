import coursera.tree.Tree

val t1 = Tree("B","C") combine Tree("X")
t1.thisValue
val t2 = Tree("B","C","A")
t2.thisValue

val t3 = Tree("B","C","A","D")
t3.thisValue
val t4 = Tree("C","A","Z","B","D")
t4.thisValue

val t5 = Tree("C","A","Z","M","D") combine Tree("X","U","B")
t5.thisValue
val t6 = Tree("C","A","Z","M","D") combine Tree("X","U","B")
t6.thisValue
val t7 = Tree("B","D","H", "P", "Y", "C","M", "G","Q","K")
t7.thisValue

val t8 = Tree( "K", "G","Q","C","M", "B","D","H", "P", "Y")
t8.thisValue
t8.out
