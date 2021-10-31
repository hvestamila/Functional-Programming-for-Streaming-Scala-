object TailFibonacciTask {
  def fibonacciImpl2(): Int => Int = {
    def fibBuilder(curr: Int, prev: Int, step: Int): Int = {
      if (step == 0) curr
      else fibBuilder(curr + prev, curr, step - 1)
    }

    def fib: Int => Int = max => {
      if (max == 0) 0
      else fibBuilder(curr = 1, prev = 0, max - 1)
    }

    def fun: Int => Int = depth => {
      if (depth < 2) depth
      else fib(depth - 1) + fib(depth - 2)
    }

    fun
  }
}