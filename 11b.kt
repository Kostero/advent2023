import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}


fun main() {
  val fileName = "11.in"
  val tab = Path(fileName).readLines()
  var result = 0L
  val n = tab.size
  val m = tab[0].length
  var galaxies = mutableListOf<Pair<Int, Int>>()
  for (i in 0 until n) {
    for (j in 0 until m) {
      if (tab[i][j] == '#') galaxies += i to j
    }
  }
  var rowCost = mutableListOf<Int>()
  var columnCost = mutableListOf<Int>()
  for (i in 0 until n) {
    rowCost += if (tab[i].contains('#')) 1 else 1000000
  }
  for (j in 0 until m) {
    var hasGalaxy = false 
    for (i in 0 until n) {
      if (tab[i][j] == '#') hasGalaxy = true 
    }
    columnCost += if (hasGalaxy) 1 else 1000000
  }
  for ((x1, y1) in galaxies) {
    for ((x2, y2) in galaxies) {
      var (xa, xb) = min(x1, x2) to max(x1, x2)
      var (ya, yb) = min(y1, y2) to max(y1, y2)
      while (xa < xb) {
        result += rowCost[xa]
        xa++
      }
      while (ya < yb) {
        result += columnCost[ya]
        ya++
      }
    }
  }
  println(result / 2)
}
