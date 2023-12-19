import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun main() {
  val fileName = "18.in"
  val lines = Path(fileName).readLines()
  var cx = 0
  var cy = 0
  var border = mutableSetOf<Pair<Int, Int>>()
  border += cx to cy
  for (line in lines) {
    val (dir, length, code) = line.split(" ")
    for (i in 0 until length.toInt()) {
      if (dir == "U") cy++
      if (dir == "D") cy--
      if (dir == "R") cx++
      if (dir == "L") cx--
      border += cx to cy
    }
  }
  val minX = border.minByOrNull { it.first }!!.first
  val maxX = border.maxByOrNull { it.first }!!.first
  val minY = border.minByOrNull { it.second }!!.second
  val maxY = border.maxByOrNull { it.second }!!.second
  var inside = mutableListOf<Pair<Int, Int>>()
  for (i in minX+1..maxX) {
    for (j in minY+1..maxY) {
      if (border.containsAll(listOf(i-1 to j-1, i-1 to j, i to j-1))) {
        inside += i to j
        var k = 0
        while (k < inside.size) {
          for ((dx, dy) in listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)) {
            val nx = inside[k].first + dx 
            val ny = inside[k].second + dy
            if (border.contains(nx to ny) || inside.contains(nx to ny)) continue  
            inside += nx to ny
          }
          k++
        }
        break
      }
    }
    if (inside.isNotEmpty()) break
  }
  // for (x in minX..maxX) {
  //   for (y in minY..maxY) {
  //     if (border.contains(x to y)) print('X')
  //     else if (inside.contains(x to y)) print('x')
  //     else print('.')
  //   }
  //   println()
  // }
  println(border.size + inside.size)
}
