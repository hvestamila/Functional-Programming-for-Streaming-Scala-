object GroupWordsTask {
  def groupIt(wordsSeq: Seq[String]): Map[Char, Int] = {
    wordsSeq.groupBy(_.head).map { case (key, value) => key -> value.mkString.count(_ == key)}
  }
}