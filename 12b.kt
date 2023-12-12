import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun foo(
    puzzle: String,
    numbers: List<Int>,
    i: Int,
    j: Int,
    dp: MutableMap<Pair<Int, Int>, Long>
): Long {
  if (i >= puzzle.length) return if (j == numbers.size) 1L else 0L
  dp[i to j]?.let { return it }
  var res = 0L
  if (".?".contains(puzzle[i])) res += foo(puzzle, numbers, i + 1, j, dp)
  if ("#?".contains(puzzle[i])) {
    if (j < numbers.size) {
      val c = numbers[j]
      if ((i + c < puzzle.length && puzzle[i + c] != '#') || (i + c == puzzle.length)) {
        if (!puzzle.slice(i..i + c - 1).contains('.')) {
          res += foo(puzzle, numbers, i + c + 1, j + 1, dp)
        }
      }
    }
  }
  dp[i to j] = res
  return res
}

fun main() {
  val fileName = "12.in"
  val tab = Path(fileName).readLines()
  var result = 0L
  for (line in tab) {
    val (puzzle, data) = line.split(" ")
    val numbers = data.split(",").map { it.toInt() }
    var newPuzzle = puzzle
    var newNumbers = numbers
    for (i in 1 until 5) {
      newPuzzle += "?$puzzle"
      newNumbers += numbers
    }
    var dp = mutableMapOf<Pair<Int, Int>, Long>()
    result += foo(newPuzzle, newNumbers, 0, 0, dp)
  }
  println(result)
}
