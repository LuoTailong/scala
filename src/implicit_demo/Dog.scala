package implicit_demo


class LearnType{
  def learning(skill:String)=println("学会了"+skill+"技能")
}

class Dog {}

object ItcastPredf{
  implicit def doglearning(x:Dog)=new LearnType
}

object TestDog{
  def main(args: Array[String]): Unit = {
    //使用隐式转化的时候需要手动导入
    import implicit_demo.ItcastPredf._
    val dog = new Dog
    dog.learning("画画")
  }
}

