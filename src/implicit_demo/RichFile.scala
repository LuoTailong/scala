package implicit_demo

import java.io.File

import scala.io.Source

class RichFile{
  def read(filepath:String)=Source.fromFile(filepath).mkString
}

object  MyPreDef{
  implicit def fileToRichFile(f:File)= new RichFile()
}

object RichFile {
  def main(args: Array[String]): Unit = {
    val richFile = new RichFile
    val result = richFile.read("C:\\Users\\Administrator\\Desktop\\1.txt")
    println(result)

    import implicit_demo.MyPreDef.fileToRichFile
    val file = new File("C:\\Users\\Administrator\\Desktop\\1.txt")
    val read: String = file.read("C:\\Users\\Administrator\\Desktop\\1.txt")
    println(read)
  }
}
