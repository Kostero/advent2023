import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun main() {
  val fileName = "12.in"
  val tab = Path(fileName).readLines()
  var result = 0
  for (line in tab) {
    val (puzzle, data) = line.split(" ")
    val numbers = data.split(",").map { it.toInt() }
    var questionMarks = 0
    for (c in puzzle) if (c == '?') questionMarks++
    for (mas in 0 until (1 shl questionMarks)) {
      var current = ""
      var questionNo = 0
      for (c in puzzle) {
        if (c != '?') current += c
        else {
          current += if (mas and (1 shl questionNo) != 0) '#' else '.'
          questionNo++
        }
      }
      val currentNumbers = mutableListOf<Int>()
      for (block in current.split("\\.+".toRegex())) {
        if (block != "") currentNumbers.add(block.length)
      }
      if (currentNumbers == numbers) result++
    }
  }
  println(result)
}
