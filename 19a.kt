import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.*

fun main() {
  val fileName = "19.in"
  val blocks = Path(fileName).readText().split("\n\n")
  val rules = blocks[0].split("\n")
  val items = blocks[1].split("\n")
  var result = 0

  var R = mutableMapOf<String, List<String>>()
  for (rule in rules) {
    val parts = rule.split("{")
    val name = parts[0]
    val rest = parts[1].dropLast(1)
    val commands = rest.split(",")
    R[name] = commands
  }

  for (item in items) {
    val values = item.substring(1, item.length - 1).split(",")
    val (x, m, a, s) = values.map { it.split("=")[1].toInt() }
    val valuesMap = mutableMapOf('x' to x, 'm' to m, 'a' to a, 's' to s)

    var currentRule = "in"

    while (currentRule !in listOf("A", "R")) {
      for (command in R[currentRule]!!) {
        if (":" in command) {
          val (cond, nextRule) = command.split(":")
          val variable = cond[0]
          val limit = cond.slice(2..cond.length - 1).toInt()
          if (cond.contains("<")) {
            if (valuesMap[variable]!! < limit) {
              currentRule = nextRule
              break
            }
          }
          if (cond.contains(">")) {
            if (valuesMap[variable]!! > limit) {
              currentRule = nextRule
              break
            }
          }
        } else {
          currentRule = command
        }
      }
    }
    if (currentRule == "A") {
      result += x + m + a + s
    }
  }

  println(result)
}
