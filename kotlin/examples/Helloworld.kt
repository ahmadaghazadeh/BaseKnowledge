 
fun main() {
   val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3))
    println(numbers.takeLast(3))
    println(numbers.drop(1))
    println(numbers.dropLast(5))
    println(numbers.drop(3).take(2).sortedDescending().toList())
}