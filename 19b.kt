import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.*

var R = mutableMapOf<String, List<String>>()

fun go(currentRule: String, variables: MutableMap<String, Pair<Int, Int>>): Long {
  if (currentRule == "R") return 0
  if (currentRule == "A") {
    // println(variables)
    var res = 1L
    for (c in listOf("x", "m", "a", "s")) {
      res *= variables[c]!!.second - variables[c]!!.first
    }
    return res
  }
  var res = 0L
  for (command in R[currentRule]!!) {
    if (":" in command) {
      val (cond, newCommand) = command.split(":")
      if (">" in cond) {
        val (variable, no) = cond.split(">")
        res +=
            go(
                newCommand,
                variables.toMutableMap().apply {
                  this[variable] = Pair(no.toInt() + 1, variables[variable]!!.second)
                }
            )
        variables[variable] = Pair(variables[variable]!!.first, no.toInt() + 1)
      }
      if ("<" in cond) {
        val (variable, no) = cond.split("<")
        res +=
            go(
                newCommand,
                variables.toMutableMap().apply {
                  this[variable] = Pair(variables[variable]!!.first, no.toInt())
                }
            )
        variables[variable] = Pair(no.toInt(), variables[variable]!!.second)
      }
    } else {
      res += go(command, variables)
    }
  }
  return res
}

fun main() {
  val fileName = "19.in"
  val blocks = Path(fileName).readText().split("\n\n")
  val rules = blocks[0].split("\n")

  for (rule in rules) {
    val parts = rule.split("{")
    val name = parts[0]
    val rest = parts[1].dropLast(1)
    val commands = rest.split(",")
    R[name] = commands
  }

  val result =
      go(
          "in",
          mutableMapOf(
              "x" to Pair(1, 4001),
              "m" to Pair(1, 4001),
              "a" to Pair(1, 4001),
              "s" to Pair(1, 4001),
          )
      )

  println(result)
}
