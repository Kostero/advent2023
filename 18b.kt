import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun main() {
  val fileName = "18.in"
  val lines = Path(fileName).readLines()
  var cx = 0
  var cy = 0
  var points = mutableListOf<Pair<Int, Int>>()
  points += cx to cy
  for (line in lines) {
    val (_, _, code) = line.split(" ")
    val dirCode = code[code.length - 2]
    var dir = ""
    val length = code.slice(2..6).toInt(16)
    if (dirCode == '0') dir = "R"
    if (dirCode == '1') dir = "D"
    if (dirCode == '2') dir = "L"
    if (dirCode == '3') dir = "U"
    if (dir == "U") cy += length
    if (dir == "D") cy -= length
    if (dir == "R") cx += length
    if (dir == "L") cx -= length
    points += cx to cy
  }
  var perimeter = 0L
  var area = 0L
  for (i in points.indices) {
    val (x1, y1) = points[i]
    val (x2, y2) = points[(i + 1) % points.size]
    perimeter += abs(x2 - x1) + abs(y2 - y1)
    area += 1L * x1 * y2 - 1L * x2 * y1
  }
  area = abs(area) / 2
  println(area + perimeter / 2 + 1)
}
