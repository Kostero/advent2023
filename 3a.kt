import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun readInput(name: String) = Path("$name").readLines()

fun Any?.println() = println(this)

fun checkNeighbours(x: Int, y: Int, input: List<String>): Boolean {
  for (dx in arrayOf(-1, 0, 1)) {
    for (dy in arrayOf(-1, 0, 1)) {
      val nx = x + dx 
      val ny = y + dy
      if (0 <= nx && nx < input.size && 0 <= ny && ny < input[0].length) {
        if (!input[nx][ny].isDigit() && input[nx][ny] != '.') {
          return true
        }
      }
    }
  }
  return false
}

fun main() {
  val fileName = "3.in"
  val input = readInput(fileName)
  var result = 0
  for (i in 0..input.size-1) {
    var j = 0
    while (j < input[i].length) {
      if (input[i][j].isDigit()) {
        var touches : Boolean = false
        touches = touches || checkNeighbours(i, j, input)
        var k = j+1 
        while (k < input[i].length) {
          if (input[i][k].isDigit()) {
            touches = touches || checkNeighbours(i, k, input)
          } else {
            break
          }
          k += 1
        }
        val number = input[i].slice(j..k-1).toInt()
        if (touches) {
          result += number
        } 
        j = k
      }
      j += 1
    }
  }
  result.println()
}