import java.util.concurrent.RecursiveTask

object ImageFilteringTask {
  def filterImage(filter: Array[Array[Int]] => (Int, Int) => Int, windowSize: Int)(imageData: Array[Array[Int]]): Array[Array[Int]] = {
    FilteringTask(filter(imageData), windowSize, 0, imageData(0).length, 0, imageData.length)()
  }


  case class FilteringTask(filter: (Int, Int) => Int, windowSize: Int, collStart: Int, collEnd: Int, rowStart: Int, rowEnd: Int) extends RecursiveTask[Array[Array[Int]]] {

    def apply(): Array[Array[Int]] = {
      val rowCnt = rowEnd - rowStart
      val collCnt = collEnd - collStart

      if (collCnt <= windowSize && rowCnt <= windowSize) {
        Array.tabulate[Int](rowCnt, collCnt)((row, coll) => filter(rowStart + row, collStart + coll))
      } else if (rowCnt > windowSize) {
        val partSize = rowCnt / 2

        val asyncSubtask = FilteringTask(filter, windowSize, collStart, collEnd, rowStart + partSize, rowEnd).fork()
        Array.concat(
          FilteringTask(filter, windowSize, collStart, collEnd, rowStart, rowStart + partSize)(),
          asyncSubtask.join()
        )
      } else {
        val partSize = collCnt / 2

        val asyncSubtask = FilteringTask(filter, windowSize, collStart + partSize, collEnd, rowStart, rowEnd).fork()

        val part1Result = FilteringTask(filter, windowSize, collStart, collStart + partSize, rowStart, rowEnd)()
        val part2Result = asyncSubtask.join()

        for (i <- 0 until rowCnt)
          part1Result.update(i, Array.concat(part1Result(i), part2Result(i)))

        part1Result
      }
    }

    override def compute(): Array[Array[Int]] = apply()
  }
}