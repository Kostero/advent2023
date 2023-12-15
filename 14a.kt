import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}


fun main() {
  val fileName = "14.in"
  val tab = Path(fileName).readLines().map { it.toMutableList() }
  var result = 0
  val n = tab.size
  val m = tab[0].size
  for (k in 1 until n) {
    for (i in 1 until n) {
      for (j in 0 until m) {
        if (tab[i][j] == 'O' && tab[i-1][j] == '.') {
          tab[i][j] = tab[i-1][j].also { tab[i-1][j] = tab[i][j] }
        }
      }
    }
  }
  for (i in 0 until n) {
    for (j in 0 until m) {
      if (tab[i][j] == 'O') result += n-i
    }
  }
  println(result)
}
