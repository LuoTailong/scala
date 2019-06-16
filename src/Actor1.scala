import scala.actors.{Actor, Future}


case class SyncMessage(id:Int,msg:String)//同步消息
case class ASyncMessage(id:Int,msg:String)//异步消息
case class ReplyMessage(id:Int,msg:String)//返回结果消息

class Actor1 extends Actor {
  override def act(): Unit = {
    loop{
      react{
        //case "start" => println("start......")
        case SyncMessage(id, msg)=>{
          println(s"id:$id,SyncMessage:$msg")
          sender ! ReplyMessage(1,"finished")
        }
        case ASyncMessage(id,msg)=>{
          println(s"id:$id,AsyncMessage:$msg")
          sender ! ReplyMessage(3,"finished")
        }
      }
    }
  }
}

object Actor1{
  def main(args: Array[String]): Unit = {
    // 创建actor实列
    val actor = new Actor1
    // 启动acrot实例
    actor.start()
    // 验证actor是否正常
    //actor ! "start"

    //同步消息有返回值 没有返回值的时候才会有阻塞，有则不会
    val replyMessage1 = actor!?SyncMessage(1,"同步消息")
    println(replyMessage1)
    //异步消息没有返回值
    actor!ASyncMessage(2,"异步没有返回值")
    //异步消息有返回值
    val replyMessage3: Future[Any] = actor!!ASyncMessage(3,"异步有返回值")
    val result: Any = replyMessage3.apply()
    val message = result.asInstanceOf[ReplyMessage]
    println(message)
  }
}
