import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

typealias Tab = MutableList<MutableList<Char>>


fun tilt(tab: Tab) {
  val n = tab.size
  val m = tab[0].size
  for (j in 0 until m) {
    var countStones = 0
    var previousEnd = 0
    for (i in 0 until n+1) {
      if (i == n || tab[i][j] == '#') {
        for (k in previousEnd until i) {
          tab[k][j] = if (k - previousEnd < countStones) 'O' else '.'
        }
        previousEnd = i+1
        countStones = 0
      }
      else if (tab[i][j] == 'O') countStones++ 
    }
  }
}

fun rotate(tab: Tab) : Tab {
  val n = tab.size
  val m = tab[0].size
  val rotated = MutableList(m) { MutableList(n) { ' ' } }
  for (i in 0 until n) {
      for (j in 0 until m) {
        rotated[j][n - 1 - i] = tab[i][j]
      }
  }
  return rotated
}

fun main() {
  val fileName = "14.in"
  var tab = Path(fileName).readLines().map { it.toMutableList() }.toMutableList()
  var result = 0
  val n = tab.size
  val m = tab[0].size
  var steps = 1000000000
  var mem = HashMap<List<List<Char>>, Int>()
  while (steps != 0) {
    val immutableTab = tab.map { it.toList() }.toList()
    if (mem.containsKey(immutableTab)) {
      val cycle = mem[immutableTab]!! - steps
      steps %= cycle
    }
    mem[immutableTab] = steps
    for (d in 0 until 4) {
      tilt(tab)
      tab = rotate(tab)
    }
    steps--
  }
  // tilt(tab)
  for (i in 0 until n) {
    for (j in 0 until m) {
      if (tab[i][j] == 'O') result += n - i
    }
  }
  println(result)
}
