fun main() {
    callDay05()
}

fun callDay05(){
    val highScores = listOf(4000, 2000, 10200, 12000, 9030)
    for ((index, value) in highScores.withIndex()) {
        println("the element at $index is $value")
    }
}