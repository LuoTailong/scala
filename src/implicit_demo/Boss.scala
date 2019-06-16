package implicit_demo

object Company{
  //以隐式数值类型作为区分和名字无关
  implicit val aaa = "zhangsan"
  implicit val bbb = 10000.00
}

class Boss {
  def callName()(implicit name:String):String={
    name+"过来"
  }
  def getMoney()(implicit money:Double):String={
    "取钱"+money
  }
}

object Boss extends App{
  import Company._
  val boss = new Boss
  println(boss.callName()+boss.getMoney())
}
