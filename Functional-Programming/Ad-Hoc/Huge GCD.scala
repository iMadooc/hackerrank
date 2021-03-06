object Solution {

  def gcf(x: List[Int], y: List[Int]): Long = {
    def primesUpTo(n: Int) = {
      require(n >= 2)
      val isPrime = collection.mutable.BitSet(2 to n: _*) -- (4 to n by 2)
      for (p <- 2 +: (3 to Math.sqrt(n).toInt by 2) if isPrime(p))
        isPrime --= p * p to n by p
      isPrime.toList
    }
    val primes = primesUpTo(10000)

    // extract the whole list to the prime factor list and count by map
    def refined(list: List[Int]): Map[Int, Int] = {
      def extractList(list: List[Int]): List[Int] = {
        def extractMember(n: Int, pl: List[Int] = primes): List[Int] = n match {
          case 1 => Nil
          case _ =>
            val h = pl.head
            if (n % h == 0) h :: extractMember(n / h, pl)
            else extractMember(n, pl.tail)
        }
        if (list.isEmpty) Nil
        else extractMember(list.head) ::: extractList(list.tail)
      }
      extractList(list).groupBy(identity).mapValues(_.size)
    }
    
    // pair two refined list and create new list of gcf
    def getGcf(xs: Map[Int,Int], ys: Map[Int,Int]): List[Int] = {
      def unzip(x: Int, y: Int): List[Int] =
        if (y == 1) List(x)
        else x :: unzip(x, y - 1)
      val zipped = for {
        i <- xs.toList
        j <- ys.toList
        if i._1 == j._1
      } yield (i._1, if (i._2 < j._2) i._2 else j._2)
      if (zipped.isEmpty) List(1)
      else {
        val unzipped = for (i <- zipped) yield unzip(i._1, i._2)
        unzipped.flatten.sortWith(_ > _) // sorted descending
      }
    }

    def modByN(list: List[Int], mod: Int): Long = {
      def recur(l: List[Int]): Long = l match {
        case Nil => 1
        case h :: t => (h % mod) * recur(t)
      }
      recur(list) % mod
    }
    
    val gcf = getGcf(refined(x), refined(y))
    modByN(gcf, 1000000007)
  }
  
///////////////////////////////////////////////////////////////////////

  def main(args: Array[String]) {
    val input = (for (i <- 1 to 4) yield readLine()).map(_.split(" ").toList.map(_.toInt)).toList
    println(gcf(input(1),input(3)))
  }
}
