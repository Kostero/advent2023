import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun readInput(name: String) = Path("$name").readLines()

fun Any?.println() = println(this)

fun checkNeighbours(x: Int, y: Int, input: List<String>): Pair<Int, Int>? {
  for (dx in arrayOf(-1, 0, 1)) {
    for (dy in arrayOf(-1, 0, 1)) {
      val nx = x + dx
      val ny = y + dy
      if (0 <= nx && nx < input.size && 0 <= ny && ny < input[0].length) {
        if (!input[nx][ny].isDigit() && input[nx][ny] == '*') {
          return Pair(nx, ny)
        }
      }
    }
  }
  return null
}

fun main() {
  val fileName = "3.in"
  val input = readInput(fileName)
  var result = 0
  var numbersTouchingStar = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
  for (i in 0..input.size - 1) {
    var j = 0
    while (j < input[i].length) {
      if (input[i][j].isDigit()) {
        var touching_stars = mutableSetOf<Pair<Int, Int>>()
        var star = checkNeighbours(i, j, input)
        if (star != null) {
          touching_stars += star
        }
        var k = j + 1
        while (k < input[i].length) {
          if (input[i][k].isDigit()) {
            var star = checkNeighbours(i, k, input)
            if (star != null) {
              touching_stars += star
            }
          } else {
            break
          }
          k += 1
        }
        val number = input[i].slice(j..k - 1).toInt()
        for (touching_star in touching_stars) {
          val list = numbersTouchingStar.getOrPut(touching_star) { mutableListOf() }
          list.add(number)
        }
        j = k
      }
      j += 1
    }
  }
  for ((_, numbers) in numbersTouchingStar) {
    if (numbers.size == 2) {
      result += numbers[0] * numbers[1]
    }
  }
  result.println()
}
