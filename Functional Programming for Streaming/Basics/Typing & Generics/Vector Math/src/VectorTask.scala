object VectorTask {

	trait Vector[T <: Vector[T]] {

		/**
		 * Get value of the vector at specific position
		 *
		 * @param position of vector value. Must be less than `size`
		 * @return value at the given position */
		def apply(position: Int): Float

		/**
		 * Create a new Vector instance with the given value and previous values
		 *
		 * @param position of vector value. Must be less than `size`
		 * @param value    the value to set
		 * @return new instance of this vector */
		def modify(position: Int, value: Float): T

		/**
		 * Provides the size of this vector
		 *
		 * @return actual size of the vector */
		def size(): Int
	}

	def sum[T <: Vector[T]](vector1: T, vector2: T): T = {
		opHelper(vector1, vector2, (a, b) => a + b)(0, vector1)
	}

	def sub[T <: Vector[T]](vector1: T, vector2: T): T = {
		opHelper(vector1, vector2, (a, b) => a - b)(0, vector1)
	}

	def opHelper[T <: Vector[T]](vector1: T, vector2: T, operation: (Float, Float) => Float)(position: Int, resultVector:T):T = {
		def f(position: Int, resultVector:T): T = {
			if (resultVector.size()==position) resultVector
			else f(position + 1, resultVector.modify(position, operation(vector1.apply(position), vector2.apply(position))))
		}
		f(position, resultVector)
	}

}