object Solution {
  
  def report(x: String, y: String): List[String] = {
    val n = (x + 1 zip y + 2).indexWhere(x => x._1 != x._2)
    val a = x.splitAt(n)
    val b = y.splitAt(n)
    a._1.length +" "+ a._1 :: a._2.length +" "+ a._2 :: b._2.length +" "+ b._2 :: Nil
  }

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList.map(_.toString)
    report(input(0), input(1)).foreach(println)
  }
}
