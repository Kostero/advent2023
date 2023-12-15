import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun main() {
  val fileName = "15.in"
  val input = Path(fileName).readLines()
  val commands = input[0]
  var result = 0
  var boxes = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
  var operations = mutableListOf<Triple<Char, String, Int>>()
  for (command in commands.split(",")) {
    val minusRegex = """(\w+)-""".toRegex()
    val minusResult = minusRegex.find(command)
    if (minusResult != null) {
      val (label) = minusResult.destructured
      operations += Triple('-', label, 0)
    }
    val equalRegex = """(\w+)=(\w+)""".toRegex()
    val equalResult = equalRegex.find(command)
    if (equalResult != null) {
      val (label, no) = equalResult.destructured
      operations += Triple('=', label, no.toInt())
    }
  }
  for ((op, label, no) in operations) {
    var hash = 0
    for (char in label) {
      hash += char.code
      hash *= 17
      hash %= 256
    }
    if (op == '=') {
      var found = false
      for ((ix, ele) in boxes[hash]?.withIndex() ?: emptyList()) {
        if (ele.first == label) {
          found = true
          boxes[hash]!![ix] = Pair(label, no)
        }
      }
      if (!found) boxes.getOrPut(hash) { mutableListOf() } += Pair(label, no)
    } else {
      var toRemove = mutableListOf<Int>()
      for ((ix, ele) in boxes[hash]?.withIndex() ?: emptyList()) {
        if (ele.first == label) {
          toRemove += ix
        }
      }
      for (ix in toRemove.reversed()) boxes[hash]!!.removeAt(ix)
    }
  }
  for (i in 0 until 256) {
    for ((ix, ele) in boxes[i]?.withIndex() ?: emptyList()) {
      result += (i + 1) * (ix + 1) * ele.second
    }
  }
  println(result)
}
