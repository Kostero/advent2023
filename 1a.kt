import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun readInput(name: String) = Path("$name").readLines()

fun Any?.println() = println(this)

fun main() {
  val fileName = "1.in"
  val input = readInput(fileName)
  var sum = 0
  for (i in 0..input.size-1) {
    val row = input[i]
    var digits = ""
    for (j in 0..row.length-1) {
      if ('0' <= row[j] && row[j] <= '9') {
        digits += row[j]
      }
    }
    val res = digits[0].toString().plus(digits[digits.length-1].toString())
    sum += res.toInt()
  }
  sum.println()
}