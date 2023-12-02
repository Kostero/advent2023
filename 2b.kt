import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*
import kotlin.collections.*

fun readInput(name: String) = Path("$name").readLines()

fun Any?.println() = println(this)

fun main() {
  val fileName = "2.in"
  val input = readInput(fileName)
  var result = 0
  for (i in 0..input.size-1) {
    val row = input[i]
    val needed = mutableMapOf<String, Int>().withDefault { 0 }
    val (_, desc) = row.split(": ")
    for (round in desc.split("; ")) {
      for (balls in round.split(", ")) {
        val (quantity, colour) = balls.split(" ")
        needed[colour] = max(needed.getValue(colour), quantity.toInt())
      }
    }
    result += needed.getValue("red") * needed.getValue("green") * needed.getValue("blue")
  }
  result.println()
}