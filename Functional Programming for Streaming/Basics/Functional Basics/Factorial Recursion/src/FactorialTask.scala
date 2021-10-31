object FactorialTask {

  def factorialImpl(): Int => Long = {
    def factorial(n: Int): Long = {
      if (n == 0) 1
      else n*factorial(n-1)
    }
    factorial
  }
}