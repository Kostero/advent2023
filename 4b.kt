import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.*

fun readInput(name: String) = Path("$name").readLines()

fun main() {
  val fileName = "4.in"
  val input = readInput(fileName)
  var result = 0
  val cards = mutableMapOf<Int, Int>()
  for (i in 0..input.size - 1) {
    cards[i] = cards.getOrDefault(i, 0) + 1
    val (_, data) = input[i].split(": ")
    val (winning, mine) = data.split(" | ")
    val winning_numbers = winning.split("\\s+".toRegex()).toSet()
    val my_numbers = mine.split("\\s+".toRegex())
    var score = 0
    for (my_number in my_numbers) {
      if (winning_numbers.contains(my_number)) {
        score += 1
        cards[i + score] = cards.getOrDefault(i + score, 0) + cards.getOrDefault(i, 0)
      }
    }
    result += cards.getOrDefault(i, 0)
  }
  println(result)
}
