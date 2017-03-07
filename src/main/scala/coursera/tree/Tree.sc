import coursera.tree.Tree

(Tree("B","C") combine Tree("X")).thisValue
Tree("B","C","A")
Tree("B","C","A","D")
Tree("C","A","Z","B","D")


(Tree("C","A","Z","M","D") combine Tree("X","U","B"))

(Tree("C","A","Z","M","D") combine Tree("X","U","B")).thisValue

Tree("B","D","H", "P", "Y", "C","M", "G","Q","K").thisValue
case class Num(val x:Int, val y:Int){
  def add(a:Int)(b:Int):Num = Num(a+b+x+y,0)
}
