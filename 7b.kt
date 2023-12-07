import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun <T> debug(container: Iterable<T>) {
  container.forEach { element -> print("$element ") }
  println()
}

var CARDS = mutableMapOf<Char, Int>(
  'A' to 14, 
  'K' to 13, 
  'Q' to 12, 
  'T' to 10,
  'J' to 0
)

fun power(cards: String): List<Int> {
  var cards2freq = mutableMapOf<Char, Int>()
  var jokers = 0
  for (card in cards) {
    if (card == 'J') jokers++
    else cards2freq[card] = cards2freq.getOrPut(card) { 0 } + 1
  }
  var values = cards2freq.values.sortedDescending().toMutableList()
  while (values.size != 5) values += 0
  values[0] += jokers
  for (card in cards) {
    values += CARDS.getOrDefault(card, -1)
  }
  return values
}

fun main() {
  val fileName = "7.in"
  val input = Path(fileName).readLines()
  var result = 0L
  var hands = mutableListOf<Pair<String, Int>>()
  for (i in 2..9) {
    CARDS += i.toString()[0] to i
  }
  for (line in input) {
    val (cards, no) = line.split(" ")
    hands += cards to no.toInt()
  }
  hands.sortWith(
      Comparator { hand1, hand2 ->
        val powerList1 = power(hand1.first)
        val powerList2 = power(hand2.first)
        powerList1.zip(powerList2).find { it.first != it.second }?.let {
          it.first.compareTo(it.second)
        } ?: 0
      }
  )
  for (i in 0 until hands.size) {
    result += 1L * (i + 1) * hands[i].second
  }
  println(result)
}
