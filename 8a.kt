import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}

fun main() {
  val fileName = "8.in"
  val input = Path(fileName).readLines()
  val commands = input[0]
  var paths = mutableMapOf<String, Pair<String, String>>()
  for (line in input.drop(2)) {
    val regex = """(\w+) = \((\w+), (\w+)\)""".toRegex()
    val matchResult = regex.find(line)
    if (matchResult != null) {
      val (node, left, right) = matchResult.destructured
      paths.put(node, Pair(left, right))
    }
  }
  var step = 0
  var node = "AAA"
  while (node != "ZZZ") {
    val command = commands[step%commands.length]
    if (command == 'L') node = paths[node]!!.first 
    else node = paths[node]!!.second
    step++
  }
  println(step)
}
