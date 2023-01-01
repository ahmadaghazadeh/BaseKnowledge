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

## Coroutine

* A coroutine is an instance of suspendable computation. It is conceptually similar to a thread, in the sense that it takes a block of code to run that works concurrently with the rest of the code. However, a coroutine is not bound to any particular thread. It may suspend its execution in one thread and resume in another one.
* Coroutines can be thought of as light-weight threads, but there is a number of important differences that make their real-life usage very different from threads.

``` kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}
```
* **launch** is a coroutine builder. It launches a new coroutine concurrently with the rest of the code, which continues to work independently. That's why Hello has been printed first.

* **delay** is a special suspending function. It suspends the coroutine for a specific time. Suspending a coroutine does not block the underlying thread, but allows other coroutines to run and use the underlying thread for their code.

* **runBlocking** is also a coroutine builder that **bridges** the **non-coroutine** world of a regular fun main() and the code with coroutines inside of runBlocking { ... } curly braces. This is highlighted in an IDE by this: CoroutineScope hint right after the runBlocking opening curly brace.

* runBlocking and coroutineScope builders may look similar because they both wait for their body and all its children to complete. The main difference is that the runBlocking method blocks the current thread for waiting, while coroutineScope just suspends, releasing the underlying thread for other usages. Because of that difference, **runBlocking is a regular function and coroutineScope is a suspending function.**

* A launch coroutine builder returns a Job object that is a handle to the launched coroutine and can be used to explicitly wait for its completion. For example, you can wait for completion of the child coroutine and then print "Done" string:

```koltin
val job = launch { // launch a new coroutine and keep a reference to its Job
    delay(1000L)
    println("World!")
}
println("Hello")
job.join() // wait until child coroutine completes
println("Done") 
```

* The pattern of async and await in other languages is based on coroutines. If you're familiar with this pattern, the suspend keyword is similar to async. However in Kotlin, await() is implicit when calling a suspend function.
* Kotlin has a method Deferred.await() that is used to wait for the result from a coroutine started with the async builder.

 * A coroutine started on Dispatchers.Main won't block the main thread while suspended. 

* The library adds a viewModelScope as an extension function of the ViewModel class. This scope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.

* A coroutine dispatcher that is not confined to any specific thread. It executes the initial continuation of a coroutine in the current call-frame and lets the coroutine resume in whatever thread that is used by the corresponding suspending function, without mandating any specific threading policy. Nested coroutines launched in this dispatcher form an event-loop to avoid stack overflows.

![image](images/CoroutineScope.png?raw=png)

![image](images/CoroutineScope1.png?raw=png)

![image](images/Coroutine1.png?raw=png)

![image](images/Coroutine2.png?raw=png)

![image](images/Coroutine3.png?raw=png)

![image](images/Coroutine4.png?raw=png)


![image](images/Coroutine5.png?raw=png)


![image](images/Coroutine6.png?raw=png)

![image](images/Coroutine7.png?raw=png)


![image](images/Coroutine8.png?raw=png)


![image](images/Coroutine9.png?raw=png)


![image](images/Coroutine10.png?raw=png)


![image](images/Coroutine11.png?raw=png)


![image](images/Coroutine12.png?raw=png)


![image](images/Coroutine13.png?raw=png)

![image](images/Coroutine14.png?raw=png)

![image](images/Coroutine15.png?raw=png)

## Numbers & casting
``` kotlin
val pi = 3.14 // Double
val eFloat = 2.7182818284f // Float, actual value is 2.7182817

# You can use underscores to make number constants more readable:

val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

```
## Operators

* Use the **is** operator or its negated form **!is** to perform a runtime check that identifies whether an object conforms to a given type:

``` kotlin
if (obj is String) {
    print(obj.length)
}

if (obj !is String) { // same as !(obj is String)
    print("Not a String")
} else {
    print(obj.length)
}
```
### "Unsafe" cast operator

* Usually, the cast operator throws an exception if the cast isn't possible. And so, it's called unsafe. The unsafe cast in Kotlin is done by the infix operator as.

`val x: String = y as String`
* Note that null cannot be cast to String, as this type is not nullable. If y is null, the code above throws an exception. To make code like this correct for null values, use the nullable type on the right-hand side of the cast:

`val x: String? = y as String?`

### "Safe" (nullable) cast operator
* To avoid exceptions, use the safe cast operator as?, which returns null on failure.

`val x: String? = y as? String`

## Scope functions
* The Kotlin standard library contains several functions whose sole purpose is to execute a block of code within the context of an object. When you call such a function on an object with a lambda expression provided, it forms a temporary scope. In this scope, you can access the object without its name. Such functions are called scope functions. There are five of them: let, run, with, apply, and also.

``` kotlin
Person("Alice", 20, "Amsterdam").let {
    println(it)
    it.moveTo("London")
    it.incrementAge()
    println(it)
}
```
* The scope functions do not introduce any new technical capabilities, but they can make your code more concise and readable.

* **let** can be used to invoke one or more functions on results of call chains. For example, the following code prints the results of two operations on a collection:

```kotlin
val numbers = mutableListOf("one", "two", "three", "four", "five")
val resultList = numbers.map { it.length }.filter { it > 3 }
println(resultList)    

val numbers = mutableListOf("one", "two", "three", "four", "five")
numbers.map { it.length }.filter { it > 3 }.let { 
    println(it)
    // and more function calls if needed
} 

val numbers = mutableListOf("one", "two", "three", "four", "five")
numbers.map { it.length }.filter { it > 3 }.let(::println)

```

### **with**

A non-extension function: the context object is passed as an argument, but inside the lambda, it's available as a receiver (this). The return value is the lambda result.

We recommend with for calling functions on the context object without providing the lambda result. In the code, with can be read as "with this object, do the following."

```kotlin
val numbers = mutableListOf("one", "two", "three")
with(numbers) {
    println("'with' is called with argument $this")
    println("It contains $size elements")
}
```

### **run**

The context object is available as a receiver (this). The return value is the lambda result.

run does the same as with but invokes as let - as an extension function of the context object.

run is useful when your lambda contains both the object initialization and the computation of the return value.

```kotlin
val letResult = service.let {
    it.port = 8080
    it.query(it.prepareRequest() + " to port ${it.port}")
}
```

### apply
The context object is available as a receiver (this). The return value is the object itself.

Use apply for code blocks that don't return a value and mainly operate on the members of the receiver object. The common case for apply is the object configuration. Such calls can be read as "apply the following assignments to the object."

``` kotlin
val adam = Person("Adam").apply {
    age = 32
    city = "London"        
}
println(adam)
```

### also
The context object is available as an argument (it). The return value is the object itself.

also is good for performing some actions that take the context object as an argument. Use also for actions that need a reference to the object rather than its properties and functions, or when you don't want to shadow the this reference from an outer scope.

When you see also in the code, you can read it as "and also do the following with the object."

```kotlin
val numbers = mutableListOf("one", "two", "three")
numbers
    .also { println("The list elements before adding new one: $it") }
    .add("four")
```
## Inline

* An Inline function is a kind of function that is declared with the keyword "inline" just before the function declaration. Once a function is declared inline, the compiler does not **allocate** any memory for this function, instead the compiler **copies** the piece of code virtually at the calling place at runtime.

* Inlining may cause the **generated code to grow**. However, if you do it in a reasonable way **(avoiding inlining large functions)**, it will pay off in performance, especially at "megamorphic" call-sites inside loops.

## Nullable

The only possible causes of an NPE in Kotlin are:

* An explicit call to throw NullPointerException().

* Usage of the !! operator that is described below.

* Data inconsistency with regard to initialization, such as when:

  * An uninitialized this available in a constructor is passed and used somewhere (a "leaking this").

  * A superclass constructor calls an open member whose implementation in the derived class uses an uninitialized state.

**Java interoperation:**

* Attempts to access a member of a null reference of a platform type;

* Nullability issues with generic types being used for Java interoperation. For example, a piece of Java code might add null into a Kotlin MutableList<String>, therefore requiring a MutableList<String?> for working with it.

* Other issues caused by external Java code.

In Kotlin, the type system distinguishes between references that can hold null (nullable references) and those that cannot (non-null references). For example, a regular variable of type String cannot hold null:

``` kotlin
var a: String = "abc" // Regular initialization means non-null by default
a = null // compilation error
```
To allow nulls, you can declare a variable as a nullable string by writing String?:


``` kotlin
var b: String? = "abc" // can be set to null
b = null // ok
print(b)
```

Now, if you call a method or access a property on a, it's guaranteed not to cause an NPE, so you can safely say:

`val l = a.length`

But if you want to access the same property on b, that would not be safe, and the compiler reports an error:

`val l = b.length // error: variable 'b' can be null`

**Checking for null in conditions**

First, you can explicitly check whether b is null, and handle the two options separately:

**val l = if (b != null) b.length else -1**

**Safe calls**
Your second option for accessing a property on a nullable variable is using the safe call operator `?.`:

``` kotlin
val a = "Kotlin"
val b: String? = null
println(b?.length)
println(a?.length) // Unnecessary safe call
```

* This returns b.length if b is not null, and null otherwise. The type of this expression is Int?.

* To perform a certain operation only for non-null values, you can use the safe call operator together with **let**:


``` kotlin
val listWithNulls: List<String?> = listOf("Kotlin", null)
for (item in listWithNulls) {
    item?.let { println(it) } // prints Kotlin and ignores null
}
```

**Nullable receiver**
Extension functions can be defined on a nullable receiver. This way you can specify behaviour for null values without the need to use null-checking logic at each call-site.

* For example, the toString() function is defined on a nullable receiver. It returns the String "null" (as opposed to a null value). This can be helpful in certain situations, for example, logging:
``` kotlin
val person: Person? = null
logger.debug(person.toString()) // Logs "null", does not throw an exception
```
* If you want your toString() invocation to return a nullable string, use the safe-call operator ?.:

``` kotlin
var timestamp: Instant? = null
val isoTimestamp = timestamp?.toString() // Returns a String? object which is `null`
if (isoTimestamp == null) {
   // Handle the case where timestamp was `null`
}
```