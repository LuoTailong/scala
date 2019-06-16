package wordcount

import scala.actors.{Actor, Future}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

case class SubmitTask(fileName: String)

case class ReplyTask(result: Map[String, Int])

class Task extends Actor {
  override def act(): Unit = {
    loop {
      react {
        case "start" => println("start...")
        case SubmitTask(fileName) => {
          val content: String = Source.fromFile(fileName).mkString
          val lines: Array[String] = content.split("\r\n")
          val words: Array[String] = lines.flatMap(_.split(" "))
          val wordAndOne: Array[(String, Int)] = words.map((_, 1))
          val wordGroup: Map[String, Array[(String, Int)]] = wordAndOne.groupBy(_._1)
          val result: Map[String, Int] = wordGroup.mapValues(_.length)
          // 7、返回消息给发送方
          sender ! ReplyTask(result)
          //println(result.toBuffer)
        }
      }
    }
  }
}

object WordCount {
  def main(args: Array[String]): Unit = {
    //创建一个待处理的文件列表
    val fileList = Array("C:\\hadoop\\test\\input\\scalawordcount\\1.txt", "C:\\hadoop\\test\\input\\scalawordcount\\2.txt", "C:\\hadoop\\test\\input\\scalawordcount\\3.txt")
    //定义一个集合 用于保存每个task返回的future
    val replySet = new mutable.HashSet[Future[Any]]()

    //定义一个集合 用于保存每个future中的最终结果
    val resultList = new ListBuffer[ReplyTask]

    //遍历文件列表 分别启动task进行处理 异步有返回值
    for (file <- fileList) {
      val task = new Task
      task.start()
      val reply: Future[Any] = task !! SubmitTask(file)
      //把返回的future添加至集合
      replySet += reply
    }

    //判断Future中哪些已经有了最终的返回 isSet可用于Future的结果判断 如果返回true 表明有了结果
    while (replySet.size > 0) {
      val completed: mutable.HashSet[Future[Any]] = replySet.filter(_.isSet)
      for (i <- completed) {
        val apply: Any = i.apply()
        val result: ReplyTask = apply.asInstanceOf[ReplyTask]
        //把结果添加至集合
        resultList += result
        //添加完毕 把已经添加到list集合中的future移除
        replySet.remove(i)
      }
    }
    println(resultList.map(_.result).flatten.groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2)))
  }
}