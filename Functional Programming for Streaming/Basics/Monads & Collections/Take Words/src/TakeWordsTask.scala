import scala.annotation.tailrec

object TakeWordsTask {
  val lineSeparatorString = "\n"

  def takeWords(seq: Seq[String]): Seq[String] = {
    if (seq.nonEmpty && seq != List("\n")) {
      val strRes = seq.mkString(" ").split('\n').head.trim.split(' ').toList
      strRes
    }
    else List()
  }
}
