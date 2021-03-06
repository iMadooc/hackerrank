object Solution {

  def pascalTriangle(row: Int, currentRow: List[Int] = List(1)): List[String] = {
    def createNextRow(above: List[Int]): List[Int] = {
      def createColumns(above: List[Int]): List[Int] = {
        val tail = above.tail
        if (tail.isEmpty) List(1)
        else above.head + tail.head :: createColumns(tail)
      }
      1 :: createColumns(above)
    }
    if (row == 0) Nil
    else currentRow.mkString(" ") :: pascalTriangle(row - 1, createNextRow(currentRow))
  }
  
  def main(args: Array[String]) {
    pascalTriangle(readInt()).foreach(println)
  }
}
