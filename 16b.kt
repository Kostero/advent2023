import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

var seen = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

fun go(tab: List<String>, x: Int, y: Int, dx: Int, dy: Int) {
  if (!(0 <= x && x < tab.size && 0 <= y && y < tab[0].length)) return
  if (seen.contains(Pair(Pair(x,y), Pair(dx, dy)))) return 
  seen.add(Pair(Pair(x,y), Pair(dx,dy)))
  if (tab[x][y] == '\\') {
    val nx = dy 
    val ny = dx
    go(tab, x + nx, y + ny, nx, ny)   
  } else if (tab[x][y] == '/') {
    val nx = -dy 
    val ny = -dx
    go(tab, x + nx, y + ny, nx, ny)
  } else if (tab[x][y] == '|' && dx == 0) {
    go(tab, x + 1, y, 1, 0)
    go(tab, x - 1, y, -1, 0)
  } else if (tab[x][y] == '-' && dy == 0) {
    go(tab, x, y + 1, 0, 1)
    go(tab, x, y - 1, 0, -1)
  } else {
    go(tab, x+dx, y+dy, dx, dy)
  }
}

fun helper(tab: List<String>, x: Int, y: Int, dx: Int, dy: Int): Int {
  var result = mutableSetOf<Pair<Int, Int>>()
  go(tab, x, y, dx, dy)
  for (ele in seen) {
    result.add(ele.first)
  }
  seen.clear()
  return result.size
}

fun main() {
  val fileName = "16.in"
  val tab = Path(fileName).readLines()
  var totalResult = 0
  var n = tab.size 
  var m = tab[0].length
  for (i in 0 until n) {
    totalResult = max(totalResult, helper(tab, i, 0, 0, 1))
    totalResult = max(totalResult, helper(tab, i, n-1, 0, -1))
  }
  for (j in 0 until m) {
    totalResult = max(totalResult, helper(tab, 0, j, 1, 0))
    totalResult = max(totalResult, helper(tab, m-1, j, -1, 0))
  }
  println(totalResult)
}
