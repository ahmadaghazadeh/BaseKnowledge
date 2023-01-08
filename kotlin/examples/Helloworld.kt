 import kotlin.reflect.KProperty

import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new , $prop")
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"
}