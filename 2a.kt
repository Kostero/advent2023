import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun readInput(name: String) = Path("$name").readLines()

fun Any?.println() = println(this)

fun main() {
  val fileName = "2.in"
  val input = readInput(fileName)
  var result = 0
  for (i in 0..input.size-1) {
    val row = input[i]
    var possible = true
    val (_, desc) = row.split(": ")
    for (round in desc.split("; ")) {
      for (balls in round.split(", ")) {
        val (quantity, colour) = balls.split(" ")
        val q = quantity.toInt()
        if (colour == "red" && q > 12) {
          possible = false
        }
        if (colour == "green" && q > 13) {
          possible = false 
        }
        if (colour == "blue" && q > 14) {
          possible = false
        }
      }
    }
    if (possible) {
      result += i+1
    }
  }
  result.println()
}