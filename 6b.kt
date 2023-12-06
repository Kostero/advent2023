import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
    container.forEach { element ->
        print("$element ")
    }
    println()
}

fun main() {
    val fileName = "6.in"
    val input = Path(fileName).readLines()
    val times = listOf(input[0].split(": ")[1].split(' ').filter { it.isNotBlank() }.joinToString("").toLong())
    val distances = listOf(input[1].split(": ")[1].split(' ').filter{ it.isNotBlank() }.joinToString("").toLong())
    var result = 1
    for (i in 0..times.size-1) {
      val time = times[i]
      val dist = distances[i]
      var options = 0
      for (t in 0..time) {
        val rem_time = time - t
        val total_dist = 1L * rem_time * t 
        if (total_dist >= dist) options++
      }
      result *= options
    }
    println(result)
}
