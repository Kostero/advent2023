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
    val seeds = input[0].split(": ")[1].split(' ').map { it.toLong() }.toMutableList()
    var allMaps = mutableListOf<MutableMap<Pair<Long, Long>, Long>>()
    var currentMap = mutableMapOf<Pair<Long, Long>, Long>()
    for (i in 2..input.size - 1) {
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
    // debug(allMaps[0].entries)
    for (map in allMaps) {
        for (i in 0..seeds.size-1) {
            for ((key, b) in map) {
                var (a, c) = key
                if (a <= seeds[i] && seeds[i] < a+c) {
                    seeds[i] = b+(seeds[i]-a)
                    break
                }
            }
        }
    }
    println(seeds.min())
}
