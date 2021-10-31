import scala.annotation.tailrec

object FibonacciTask {

  def fibonacciImpl(): Int => Int = {
    @tailrec
    def fibBuilder(curr: Int, prev: Int, step: Int): Int = {
      if (step == 0) curr
      else fibBuilder(curr + prev, curr, step - 1)
    }

    def fib(max: Int): Int = {
      if (max == 0) 0
      else fibBuilder(curr = 1, prev = 0, max - 1)
    }

    fib
  }
}