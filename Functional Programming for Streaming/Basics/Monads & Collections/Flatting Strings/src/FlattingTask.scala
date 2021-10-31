object FlattingTask {

  def flattingStrings(strings: Seq[String]): Seq[Char] = {
    val seqChar = strings.flatten
    seqChar
  }
}