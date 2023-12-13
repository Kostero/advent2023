import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.math.*

fun main() {
  val fileName = "13.in"
  val blocks = Path(fileName).readText().split("\n\n")
  var result = 0
  for (block in blocks) {
    val tab = block.split("\n")
    val n = tab.size 
    val m = tab[0].length 
    for (k in 0 until n-1) {
      var wrong = 0
      for (i in 1 until n) {
        for (j in 0 until m) {
          if (0 <= k-i+1 && k-i+1 < n && 0 <= k+i && k+i < n) {
            if (tab[k-i+1][j] != tab[k+i][j]) wrong++
          }
        }
      }
      if (wrong == 1) result += 100 * (k+1)
    }
    for (k in 0 until m-1) {
      var wrong = 0
      for (i in 0 until n) {
        for (j in 1 until m) {
          if (0 <= k-j+1 && k-j+1 < m && 0 <= k+j && k+j < m) {
            if (tab[i][k-j+1] != tab[i][k+j]) wrong++
          }
        }
      }
      if (wrong == 1) result += (k+1)
    }
  }
  println(result)
}
