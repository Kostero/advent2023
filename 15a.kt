import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun main() {
  val fileName = "15.in"
  val input = Path(fileName).readLines()
  val commands = input[0]
  var result = 0
  for (command in commands.split(",")) {
    var hash = 0
    for (char in command) {
      hash += char.code
      hash *= 17
      hash %= 256
    }
    result += hash
  }
  println(result)
}
