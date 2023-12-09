import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}

fun main() {
  val fileName = "9.in"
  val input = Path(fileName).readLines()
  var result = 0
  for (line in input) {
    val firstRow = line.split(" ").map { it.toInt() }.toMutableList()
    var levels = mutableListOf(firstRow)
    while (true) {
      var newRow = mutableListOf<Int>()
      for (i in 1 until levels.last().size) {
        newRow += levels.last()[i] - levels.last()[i-1]
      }
      levels += newRow
      if (newRow.all { it == 0 }) break
    }
    levels.last() += 0
    for (i in levels.size-2 downTo 0) {
      levels[i] += levels[i+1].last() + levels[i].last()
    }
    result += levels.first().last()
  }
  println(result)
}
