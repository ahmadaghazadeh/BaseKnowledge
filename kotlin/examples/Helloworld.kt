fun main() {
    val stringList = listOf("one", "two", "one")
    println(stringList.lastIndex)
}
 
 val <T> List<T>.lastIndex: Int
    get() = size - 1

 