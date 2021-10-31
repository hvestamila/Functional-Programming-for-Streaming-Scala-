import PrimeCheckerTask.isPrime

import scala.annotation.tailrec
import scala.math.{pow, sqrt}

object PrimeCheckerTask {

	def isPrime(n: Long): Boolean = {
		if (n <= 3) {
			n > 1
		} else if (n % 2 == 0 || n % 3 == 0) {
			false
		}
		else checkPrime(n, 5)
	}

	@tailrec
	def checkPrime(n: Long, i: Int): Boolean = {
		if (pow(i, 2) > n) true
		else if ((n % i == 0) || (n % (i + 2) == 0)) false
		else checkPrime(n, i = i + 6)
	}
}