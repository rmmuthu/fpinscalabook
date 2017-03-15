val r = Map("M"->1000, "D"->500, "C"->100, "L"->50, "X"->10, "V"->5, "I"->1)

def romanToArabic(m:Map[String,Int], s:String) :List[Int] =
  s.toCharArray.flatMap(i=>r.get(i.toString)).foldRight(List():List[Int])((a:Int,b:List[Int])=>
  {
    println(a+"~")
    if(b.isEmpty) a
    else if(a<b.head) -a
    else a
  }::b)

def parseDefinition(a:Array[String] ):Map[String, String]={
  if(a.isEmpty)return Map.empty
  else if(a.length==3)parseDefinitionConversion(a)
  else parseDefinitionProduct(a)

}

def parseDefinitionConversion(a:Array[String] ):Map[String, String]={
  if(a.isEmpty || a.length!=3 || a(1)!="is" )Map.empty
  else Map((a(0).toString->a(2).toString))
}


def parseDefinitionProduct(a:Array[String] ):Map[String, String]={
  def getCredit():String= {
    if (a(a.length - 1) == "Credits")  a(a.length - 2)
    else ""
  }
  def getName():String= {
    if (a(a.length - 1) == "Credits")  a(a.length - 4)
    else ""
  }
  def getQuantity(a:Array[String], q:String):String= {
    if(a.isEmpty || a.head=="is")q
    else getQuantity(a.tail, q+a.head+" ")
  }

  Map(getName() -> getQuantity(a,"").trim)

}

val s = parseDefinition("glob is I".split(" ")) ++
parseDefinition("prok is V".split(" ")) ++
  parseDefinition("pish is X".split(" ")) ++
parseDefinition("tegj is L".split(" "))

val a = ("glob glob Silver is 34 Credits").split(" ")
val s2 = parseDefinitionProduct(a)



val in = "MMVI"
val in2 = "MCMXLIV"
val l = (5::List(1,2,3)).head

val ls1 = romanToArabic(r, in)
ls1.sum

val ls2 = romanToArabic(r,in2)
ls2.sum