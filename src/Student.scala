class Person {}

class Student extends Person {}

object test1232131 {
  def main(args: Array[String]): Unit = {
    val p: Person = new Student
    p match {
      case per: Person => println("this is person")
      case _ => println("Unknown type!")
    }
  }
}

class Person7(val name:String, val age:Int){
  var score:Double=0.2
  var address:String = "beijing"

  def this(name:String, score:Double)={
    this(name,90)
    this.score=score
  }

  def this(name:String, age: Int,address:String){
    this(name,23.0)
    this.address = address
  }
}

object testOption{
  def main(args: Array[String]): Unit = {
    val m1 = Map("a"->1,"b"->2)
    val maybeInt: Option[Int] = m1.get("c")
    maybeInt match {
      case Some(i)=>println(i)
      case None=>println(0)
    }
  }
}