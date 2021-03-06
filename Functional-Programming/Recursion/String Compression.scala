object Solution {

  def compress(s: String) = {
    def f(arr: List[Char], prev: String = "", count: Int = 1): List[(String, Int)] = {
      if (arr.isEmpty) List((prev, count))
      else {
        val curr = arr.head.toString
        if (curr != prev) (prev, count) :: f(arr.tail, curr, 1)
        else f(arr.tail, curr, count + 1)
      }
    }
    f(s.toList).drop(1).map(x => if (x._2 == 1) x._1 else x._1 + x._2).mkString
  }
  
  def main(args: Array[String]) {
    println(compress(readLine()))
  }
}
