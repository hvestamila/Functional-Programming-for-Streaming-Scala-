object FizzBuzzTask {
  def fizzBuzzIt(s: Seq[Int]): Seq[String] = {

    def fb(i: Int): String = i match {
      case i if (i % 3 == 0 && i % 5 == 0) => "FizzBuzz"
      case i if i % 3 == 0 => "Fizz"
      case i if i % 5 == 0 => "Buzz"
      case _ => i.toString
    }

    s.map(fb)
  }
}