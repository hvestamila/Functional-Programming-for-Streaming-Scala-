object ZipWordTask {
  def zipIt(prefixSeq: Seq[String], wordSeq: Seq[String], suffixSeq: Seq[String]): Seq[String] = {
    val newSq = prefixSeq.zip(wordSeq).map { tuple =>
      tuple.productIterator.mkString
    }
    newSq.zip(suffixSeq).map { tuple =>
      tuple.productIterator.mkString
    }
  }
}