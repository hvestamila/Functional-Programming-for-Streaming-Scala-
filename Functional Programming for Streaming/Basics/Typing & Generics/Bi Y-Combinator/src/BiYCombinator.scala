
object BiYCombinator {

	def fix[T1, T2, R](bifnFactory: ((T1, T2) => R) => (T1, T2) => R): (T1, T2) => R =
		(t1, t2) => bifnFactory(fix(bifnFactory))(t1, t2)
}