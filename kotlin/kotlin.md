# Kotlin

## Sequences

* Along with collections, the Kotlin standard library contains another type – sequences (Sequence<T>). Unlike collections, sequences don't contain elements, they produce them while iterating. Sequences offer the same functions as Iterable but implement another approach to multi-step collection processing.

* **When the processing of an Iterable includes multiple steps, they are executed eagerly: each processing step completes and returns its result – an intermediate collection**. The following step executes on this collection. In turn, multi-step processing of sequences is executed lazily when possible: actual computing happens only when the result of the whole processing chain is requested.

* **So, the sequences let you avoid building results of intermediate steps, therefore improving the performance of the whole collection processing chain.** However, the lazy nature of sequences adds some overhead which may be significant when processing smaller collections or doing simpler computations. Hence, you should consider both Sequence and Iterable and decide which one is better for your case.

```kotlin
val numbersSequence = sequenceOf("four", "three", "two", "one")

val numbers = listOf("one", "two", "three", "four")
val numbersSequence = numbers.asSequence()

```

* One more way to create a sequence is by building it with a function that calculates its elements. To build a sequence based on a function, call generateSequence() with this function as an argument. Optionally, you can specify the first element as an explicit value or a result of a function call.**The sequence generation stops when the provided function returns null. So, the sequence in the example below is infinite.**

```kotlin
val oddNumbers = generateSequence(1) { it + 2 } // `it` is the previous element
println(oddNumbers.take(5).toList()) // [1, 3, 5, 7, 9]
//println(oddNumbers.count())     // error: the sequence is infinite

val oddNumbersLessThan10 = generateSequence(1) { if (it < 8) it + 2 else null }
println(oddNumbersLessThan10.count()) // 5

```

* Finally, there is a function that lets you produce sequence elements one by one or by chunks of arbitrary sizes – the sequence() function. This function takes a lambda expression containing calls of yield() and yieldAll() functions. They return an element to the sequence consumer and suspend the execution of sequence() until the next element is requested by the consumer. yield() takes a single element as an argument; yieldAll() can take an Iterable object, an Iterator, or another Sequence. A Sequence argument of yieldAll() can be infinite. However, such a call must be the last: all subsequent calls will never be executed.

```kotlin
val oddNumbers = sequence {
    yield(1)
    yieldAll(listOf(3, 5))
    yieldAll(generateSequence(7) { it + 2 })
}
println(oddNumbers.take(5).toList()) // [1, 3, 5, 7, 9]

```

### Sequence vs Iterable

```kotlin
val words = "The quick brown fox jumps over the lazy dog".split(" ")
val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
    .map { println("length: ${it.length}"); it.length }
    .take(4)

println("Lengths of first 4 words longer than 3 chars:")
println(lengthsList)

output :

Lengths of first 4 words longer than 3 chars
filter: The
filter: quick
length: 5
filter: brown
length: 5
filter: fox
filter: jumps
length: 5
filter: over
length: 4
[5, 5, 5, 4]

```

![image](images/list-processing.png?raw=png)


```kotlin
val words = "The quick brown fox jumps over the lazy dog".split(" ")
//convert the List to a Sequence
val wordsSequence = words.asSequence()

val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
    .map { println("length: ${it.length}"); it.length }
    .take(4)

println("Lengths of first 4 words longer than 3 chars")
// terminal operation: obtaining the result as a List
println(lengthsSequence.toList())

output :

Lengths of first 4 words longer than 3 chars
filter: The
filter: quick
length: 5
filter: brown
length: 5
filter: fox
filter: jumps
length: 5
filter: over
length: 4
[5, 5, 5, 4]

```

![image](images/sequence-processing.png?raw=png)

In this example, the sequence processing takes 18 steps instead of 23 steps for doing the same with lists.

### Collections 

#### The following collection types are relevant for Kotlin:

**List** is an ordered collection with access to elements by indices – integer numbers that reflect their position. Elements can occur more than once in a list. An example of a list is a telephone number: it's a group of digits, their order is important, and they can repeat.

**Set** is a collection of unique elements. It reflects the mathematical abstraction of set: a group of objects without repetitions. Generally, the order of set elements has no significance. For example, the numbers on lottery tickets form a set: they are unique, and their order is not important.

**Map** (or dictionary) is a set of key-value pairs. Keys are unique, and each of them maps to exactly one value. The values can be duplicates. Maps are useful for storing logical connections between objects, for example, an employee's ID and their position.

* The read-only collection types are covariant. This means that, if a Rectangle class inherits from Shape, you can use a List<Rectangle> anywhere the List<Shape> is required. In other words, the collection types have the same subtyping relationship as the element types. Maps are covariant on the value type, but not on the key type.

* In turn, mutable collections aren't covariant; otherwise, this would lead to runtime failures. If MutableList<Rectangle> was a subtype of MutableList<Shape>, you could insert other Shape inheritors (for example, Circle) into it, thus violating its Rectangle type argument.

* Collection<T> is the root of the collection hierarchy. This interface represents the common behavior of a read-only collection: retrieving size, checking item membership, and so on. Collection inherits from the Iterable<T> interface that defines the operations for iterating elements. You can use Collection as a parameter of a function that applies to different collection types. For more specific cases, use the Collection's inheritors: List and Set.

```kotlin
fun printAll(strings: Collection<String>) {
    for(s in strings) print("$s ")
    println()
}
    
fun main() {
    val stringList = listOf("one", "two", "one")
    printAll(stringList)
    
    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)
}
```

## Extensions 

* Kotlin provides the ability to extend a class or an interface with new functionality without having to inherit from the class or use design patterns such as **Decorator**. This is done via special declarations called extensions.

```kotlin
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

val list = mutableListOf(1, 2, 3)
list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
```
### Nullable receiver
Note that extensions can be defined with a nullable receiver type. These extensions can be called on an object variable even if its value is null, and they can check for this == null inside the body.

This way, you can call toString() in Kotlin without checking for null, as the check happens inside the extension function:

```kotlin
fun Any?.toString(): String {
    if (this == null) return "null"
    // after the null check, 'this' is autocast to a non-null type, so the toString() below
    // resolves to the member function of the Any class
    return toString()
}
```

### Extension properties
Kotlin supports extension properties much like it supports functions:
``` kotlin
val <T> List<T>.lastIndex: Int
    get() = size - 1

```

### Companion object extensions
If a class has a companion object defined, you can also define extension functions and properties for the companion object. Just like regular members of the companion object, they can be called using only the class name as the qualifier:

```kotlin
class MyClass {
    companion object { }  // will be called "Companion"
}

fun MyClass.Companion.printCompanion() { println("companion") }

fun main() {
    MyClass.printCompanion()
}
```