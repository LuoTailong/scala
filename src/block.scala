import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable._

object block {
  def main(args: Array[String]): Unit = {
    val a = 10
    val b = 20
    val result = {
      val c = b - a
      val d = b - c
      d
    }
    println(result)
  }

}

object test {
  def main(args: Array[String]): Unit = {
    val arr = Array("a", "b", "c")
    for (i <- 1 to 3; j <- 1 to 3 if i != j) {
      println(10 * i + j + "")
    }
    val v = for (i <- 1 to 10) yield i * 10
    println(v)
  }
}

object test1 {
  def main(args: Array[String]): Unit = {
    var a: Int = 10
    while (a > 5) {
      println(a)
      a = a - 1
    }
  }
}

object test2 {
  def main(args: Array[String]): Unit = {
    var a: Int = 10
    do {
      println(a)
      a = a - 1
    } while (a > 5)
  }
}

object test3 {
  def m1(f: (Int, Int) => Int) = f(2, 3)

  val f1 = (x: Int, y: Int) => x + y

  def tt(x: Int, y: Int) = x + y

  def m2(x: Int, y: Int): Int = {
    if (x <= 1) 1
    else m2(x - 1, y - 1) * x + y
  }

  def main(args: Array[String]): Unit = {
    println(m1(f1))
    println(m1(tt _))
    println(m2(1, 3)) //1
    println(m2(2, 3)) //5
  }
}

object test4 {
  def main(args: Array[String]): Unit = {
    val arr = new Array[Int](5)
    println(arr)
    println(arr.toBuffer)
    val arr2 = Array[Int](10)
    println(arr2.toBuffer)
    val arr3 = Array("hadoop", "storm", "spark")
    println(arr3(2))
    val ab = ArrayBuffer[Int]()
    // 向 数组缓冲的尾部追加一个元素
    // += 尾部追加元素
    ab += 1
    // 追加多个元素
    ab += (2, 3, 4, 5)
    // 追加一个数组 ++=
    ab ++= Array(6, 7)
    // 追加一个数组缓冲
    ab ++= ArrayBuffer(8, 9)
    // 打印数组缓冲 ab
    // 在数组某个位置插入元素用 insert ，从某下标插入
    ab.insert(0, -1, 0)
    // 删除数组某个位置的元素用 remove  按照下标删除
    ab.remove(0)
    println(ab)
  }
}

object test5 {
  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8)
    for (i <- arr) {
      print(i)
    }
    println()
    for (i <- (0 until arr.length).reverse) {
      print(arr(i))
    }
    println()
    val res = for (e <- arr if e % 2 == 0) yield e * 10
    print(res.toBuffer)
    println()
    val r = arr.filter(_ % 2 == 0).map(_ * 10)
    print(r.toBuffer)
    println()
    println(arr.max)
    println(arr.min)
    println(arr.sorted.toBuffer)
  }
}

object map {
  // 1
  val map1 = Map("zhangsan" -> 10, "lisi" -> 20)
  // 2
  val map2 = Map(("wangwu", 18), ("zhaoliu", 30))
  // 3
  val user = Map("zhangsan" -> 80, "list" -> 100)
  user += ("wangwu" -> 30)
  user += ("wangwu1" -> 30, "sdf" -> 23)

  def main(args: Array[String]): Unit = {
    println(user)
    println(user.keys)
    println(user.keySet)
    println(user("zhangsan"))
    println(user.getOrElse("zhangsan", 0))
    println(user.getOrElse("zhangsan1", 0))

    //更新
    user("zhangsan") = 34
    println(user)
    user += ("zhangsan" -> 32, "lisi" -> 32)
    println(user)
    user -= ("zhangsan")
    println(user)
    user.remove("lisi")
    println(user)
    for (x <- user.keys) println(x + "--" + user(x))
    for ((x, y) <- user) println(x + "--" + y)
    user.foreach { case (x, y) => println(x + "--" + y) }
  }
}

object tuple {
  def main(args: Array[String]): Unit = {
    val t1 = new Tuple3("hello", 2, 3.123)
    println(t1._1)
    println(t1._2)
    println(t1._3)
    t1.productIterator.foreach(println(_))
  }
}

object tuple2 {
  def main(args: Array[String]): Unit = {
    val arr = Array(("zhangsan", 1), ("lisi", 2))
    print(arr.toMap)
    println()

    val scores = Array(120, 324, 555)
    val names = Array("asd", "sdfds", "fdsf")
    print(names.zip(scores).toBuffer)
    print(scores.zip(names).toBuffer)
  }
}

object list {
  def main(args: Array[String]): Unit = {
    val site: List[String] = List("hello", "world", "scala")
    val site1 = "hello" :: ("world" :: ("scala" :: Nil))

    val nums: List[Int] = List(1, 2, 3, 4)
    val nums1 = 1 :: (2 :: (3 :: (4 :: Nil)))

    val empty: List[Nothing] = List()
    val empty1 = Nil

    def main(args: Array[String]): Unit = {
      var nums2 = 5 +: nums
      var nums3 = nums.+:(5)
      var nums4 = 5 :: nums
      var nums6 = nums.::(5)

      var nums7 = nums :+ 6
      var nums8 = List(7, 8, 9)
      var nums9 = nums ++ nums8
      var nums11 = nums ++: nums8
      var nums10 = nums ::: nums8
    }
  }
}

object list1 extends App {
  val list0 = ListBuffer[Int](1, 2, 3)
  val list1 = new ListBuffer[Int]
  list1 += 4
  list1.append(6)
  list0 ++= list1
  val list2 = list0 ++ list1
  val list3 = list0 :+ 5
  val list4 = ListBuffer[Int](1, 2, 3, 4, 5)
  list4 -= 5
  val list5 = list4 -- List(1, 2)
  val list6 = list5.toList //可变list转不可变list
  val list7 = list5.toArray
}

object set {
  val set = Set(1, 2, 3, 4, 5, 6, 7)

  def main(args: Array[String]): Unit = {
    println(set + 8)
    val set1 = Set(7, 8, 9)
    println(set & set1)
    println(set ++ set1)
    println(set -- set1)
    println(set &~ set1) //返回第一个不同于第二个set的元素集合
    println(set.diff(set1)) //返回第一个不同于第二个set的元素集合
    println(set.count(_ > 5))
    println(set.slice(2, 5)) //包左不包右
    set1.subsets(2).foreach(x => println(x))
    set1.subsets(2).foreach(println(_))
  }
}

object set1 {
  val set = new HashSet[Int]()

  def main(args: Array[String]): Unit = {
    println(set += 8)
    println(set.add(9))

    set ++= Set(1, 4, 5)

    set -= 5
    set.remove(1)
    println(set)

    val set1 = Set(1, 2, 3, 4, 5)
    val set2 = set ++ set1
    println(set2)
  }
}

object set2 {
  val set = new HashSet[Int]()

  def main(args: Array[String]): Unit = {
    println(set += 8)
    println(set.add(9))

    set ++= Set(1, 4, 5)

    set -= 5
    set.remove(1)
    println(set)

    val set1 = Set(1, 2, 3, 4, 5)
    val set2 = set ++ set1
    println(set2)
  }
}

object test222 {
   val value: set2.type = set2
  set2.set
}




