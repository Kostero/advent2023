import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}

val west = "-LF"
val east = "-7J"
val north = "|F7"
val south = "|LJ"

fun plusStart(dir: String): String {
  return dir + "S"
}

fun add(ele: Pair<Int, Int>, Q: MutableList<Pair<Int, Int>>, SQ: MutableSet<Pair<Int, Int>>) {
  Q += ele
  SQ += ele
}

fun main() {
  val fileName = "10.in"
  val tab = Path(fileName).readLines()
  var start = 0 to 0
  val n = tab.size
  val m = tab[0].length
  for (i in 0 until n) {
    for (j in 0 until m) {
      if (tab[i][j] == 'S') start = i to j
    }
  }
  var Q = mutableListOf(start)
  var SQ = mutableSetOf(start)
  var step = 0
  while (step < Q.size) {
    val (cx, cy) = Q[step]
    for ((dx, dy) in arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
      val nx = cx + dx
      val ny = cy + dy
      if (0 <= nx && nx <= n && 0 <= ny && ny < m) {
        if (!SQ.contains(nx to ny)) {
          if (dx to dy == 0 to 1 &&
                  plusStart(west).contains(tab[cx][cy]) &&
                  east.contains(tab[nx][ny])
          ) {
            add(nx to ny, Q, SQ)
          }
          if (dx to dy == 0 to -1 &&
                  plusStart(east).contains(tab[cx][cy]) &&
                  west.contains(tab[nx][ny])
          ) {
            add(nx to ny, Q, SQ)
          }
          if (dx to dy == 1 to 0 &&
                  plusStart(north).contains(tab[cx][cy]) &&
                  south.contains(tab[nx][ny])
          ) {
            add(nx to ny, Q, SQ)
          }
          if (dx to dy == -1 to 0 &&
                  plusStart(south).contains(tab[cx][cy]) &&
                  north.contains(tab[nx][ny])
          ) {
            add(nx to ny, Q, SQ)
          }
        }
      }
    }
    step++
  }
  println(Q.size / 2)
}
