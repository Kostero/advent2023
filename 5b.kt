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
    val fileName = "5.in"
    val input = Path(fileName).readLines()
    val seeds = input[0].split(": ")[1].split(' ').map { it.toLong() }
    var intervals = mutableListOf<Pair<Long, Long>>()
    for (i in 0..seeds.size-1 step 2) {
      intervals += Pair(seeds[i], seeds[i]+seeds[i+1])
    }
    var allMaps = mutableListOf<MutableMap<Pair<Long, Long>, Long>>()
    var currentMap = mutableMapOf<Pair<Long, Long>, Long>()
    for (i in 3..input.size - 1) {
        if (input[i].isBlank()) continue
        if (input[i].contains("map")) {
            allMaps.add(currentMap)
            currentMap = mutableMapOf()
        } else {
            val (b, a, c) = input[i].split(' ').map { it.toLong() }
            currentMap.put(Pair(a, c), b)
        }
    }
    allMaps += currentMap
    for (map in allMaps) {
        val newIntervals = mutableListOf<Pair<Long, Long>>()
        while (intervals.isNotEmpty()) {
            val (x, y) = intervals.removeLast()
            var changed = false
            for ((key, b) in map) {
                var (a, c) = key
                var left = max(x, a)
                var right = min(y, a + c)
                if (left < right) {
                  newIntervals += Pair(left + (b-a), right + (b-a))
                  if (x < left) intervals += Pair(x, left)
                  if (right < y) intervals += Pair(right, y)
                  changed = true
                  break
                }
            }
            if (!changed) newIntervals += Pair(x, y)
        }
        intervals = newIntervals
    }
    intervals.sortWith(compareBy {it.first})
    println(intervals[0].first)
}
