object MapTask {
  def mapping(input: Seq[String]): Seq[Int] = {
    def isAllDigits(x: String) = x forall Character.isDigit
    val seqInt = input.filter(isAllDigits).map(_.toInt)
    seqInt
  }
}