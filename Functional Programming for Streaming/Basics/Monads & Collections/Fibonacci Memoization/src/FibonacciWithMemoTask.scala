object FibonacciWithMemoTask {
  def memoFibonacci(): Int => Long = {
    def wrapFib(n: Int): Long = {

      val store = new Array[Long](n + 1)

      def fib(n: Int): Long = {
        if (n == 0) 0
        else if (n == 1) 1
        else if (store(n) > 0) store(n)
        else {
          val res:Long = fib(n - 1) + fib(n - 2)
          store(n) = res
          res
        }
      }

      fib(n)
    }
    wrapFib
  }
}