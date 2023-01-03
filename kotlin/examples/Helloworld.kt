 

fun main() {
       var triple = Triple("Hello Geeks",
                        "This is Kotlin tutorial",
                        listOf(10, 20, 30))

    val (x, y, z) = triple
    println(x)
    println(y)
    println(z)

    println(triple.first)
    println(triple.second)
    println(triple.third)
}
 