import z_playground.chap2


chap2.fib(1)
chap2.fib(2)
chap2.fib(3)
chap2.fib(4)
chap2.fib(5)
chap2.fib(6)
chap2.fib(7)
chap2.fib(8)
chap2.fib(9)
chap2.fib(10)



chap2.isSorted(Array(1,2,3),((a:Int,b:Int)=>a<=b))
chap2.isSorted(Array(33,22,11),((a:Int,b:Int)=>a>=b))

chap2.isSorted(Array("Ajay", "Muthu", "Subha"),((a:String,b:String)=>a.compareTo(b)<=0))
chap2.isSorted(Array("xAjay", "xMuthu", "xSubha"),((a:String,b:String)=>a.compareTo(b)<=0))

chap2.isSorted(Array("xAjay" ),((a:String,b:String)=>a.compareTo(b)<=0))
chap2.isSorted(Array(),((a:String,b:String)=>a.compareTo(b)<=0))