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

Instead of writing the complete if expression, you can also express this with the Elvis operator **?:**:

`val l = b?.length ?: -1`

**The !! operator**

The third option is for NPE-lovers: the not-null assertion operator (!!) converts any value to a non-null type and throws an exception if the value is null. You can write b!!, and this will return a non-null value of b (for example, a String in our example) or throw an NPE if b is null:

`val l = b!!.length` 

**Thus, if you want an NPE, you can have it**, but you have to ask for it explicitly and it won't appear out of the blue.

## Reflection

### Class references

``` kotlin
val c = MyClass::class

val widget: Widget = ...
assert(widget is GoodWidget) { "Bad widget: ${widget::class.qualifiedName}" }

```

### Function references

Alternatively, you can use the function as a function type value, that is, pass it to another function. To do so, use the `::` operator:

```kotlin
val numbers = listOf(1, 2, 3)
println(numbers.filter(::isOdd))

fun isOdd(x: Int) = x % 2 != 0
fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

val numbers = listOf(1, 2, 3)
println(numbers.filter(::isOdd)) // refers to isOdd(x: Int)

output:
[1, 3]

val predicate: (String) -> Boolean = ::isOdd   // refers to isOdd(x: String)


```
* If you need to use a member of a class or an extension function, it needs to be qualified: `String::toCharArray.`

### Example: function composition

``` kotlin
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}


fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length

val oddLength = compose(::isOdd, ::length)
val strings = listOf("a", "ab", "abc")

println(strings.filter(oddLength))

```

### Property references
To access properties as first-class objects in Kotlin, use the :: operator:
```kotlin
val x = 1

fun main() {
    println(::x.get())
    println(::x.name)
}
```

## Object expressions
* Sometimes you need to create an object that is a slight modification of some class, without explicitly declaring a new subclass for it. Kotlin can handle this with object expressions and object declarations.

* Object expressions create objects of anonymous classes, that is, classes that aren't explicitly declared with the class declaration. Such classes are useful for one-time use. You can define them from scratch, inherit from existing classes, or implement interfaces. Instances of anonymous classes are also called anonymous objects because they are defined by an expression, not a name.

``` kotlin
val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    // object expressions extend Any, so `override` is required on `toString()`
    override fun toString() = "$hello $world"
}

```

### Object declarations

* This is called an object declaration, and it always has a name following the object keyword. Just like a variable declaration, an object declaration is not an expression, and it cannot be used on the right-hand side of an assignment statement.

*The initialization of an object declaration is **thread-safe and done on first access**.

``` kotlin 
 
object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {
        // ...
    }

    val allDataProviders: Collection<DataProvider>
        get() = // ...
}
 

object MyObject

fun main() {
    println(MyObject) // MyObject@1f32e575
}

data object MyObject

fun main() {
    println(MyObject) // MyObject
}

```

* **Sealed class hierarchies** are a particularly good fit for data object declarations, since they allow you to maintain symmetry with any data classes you might have defined alongside the object:

``` kotlin
sealed class ReadResult {
    data class Number(val value: Int): ReadResult()
    data class Text(val value: String): ReadResult()
    data object EndOfFile: ReadResult()
}

fun main() {
    println(ReadResult.Number(1)) // Number(value=1)
    println(ReadResult.Text("Foo")) // Text(value=Foo)
    println(ReadResult.EndOfFile) // EndOfFile
}
```

### Companion objects

* There is one important semantic difference between object expressions and object declarations:

Object expressions are executed (and initialized) immediately, where they are used.

Object declarations are **initialized lazily**, when accessed for the first time.

A companion object is initialized when the **corresponding class is loaded (resolved)** that matches the semantics of a Java static initializer.

## Data classes

* It is not unusual to create classes whose main purpose is to hold data. In such classes, some standard functionality and some utility functions are often mechanically derivable from the data. In Kotlin, these are called data classes and are marked with data:

`data class User(val name: String, val age: Int)`

### The compiler automatically derives the following members from all properties declared in the primary constructor:

* equals()/hashCode() pair

* toString() of the form "User(name=John, age=42)"

* componentN() functions corresponding to the properties in their order of declaration.

* copy() function (see below).

### To ensure consistency and meaningful behavior of the generated code, data classes have to fulfill the following requirements:

* The primary constructor needs to have at least one parameter.

* All primary constructor parameters need to be marked as val or var.

* Data classes cannot be abstract, open, sealed, or inner.

### Properties declared in the class body

The compiler only uses the properties defined inside the primary constructor for the automatically generated functions. To exclude a property from the generated implementations, declare it inside the class body:

``` kotlin
data class Person(val name: String) {
    var age: Int = 0
}
```

Only the property name will be used inside the toString(), equals(), hashCode(), and copy() implementations, and there will only be one component function component1(). While two Person objects can have different ages, they will be treated as equal.

``` kotlin
data class Person(val name: String) {
    var age: Int = 0
}
fun main() {

    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20

    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: ${person1}")
    println("person2 with age ${person2.age}: ${person2}")
}

output

person1 == person2: true
person1 with age 10: Person(name=John)
person2 with age 20: Person(name=John)
```

### Copying

Use the copy() function to copy an object, allowing you to alter some of its properties while keeping the rest unchanged. The implementation of this function for the User class above would be as follows:

``` kotlin
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)

val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)
```

### Data classes and destructuring declarations

``` kotlin
val jane = User("Jane", 35)
val (name, age) = jane
println("$name, $age years of age") // prints "Jane, 35 years of age"

val name = person.component1()
val age = person.component2()

map.mapValues { entry -> "${entry.value}!" }
map.mapValues { (key, value) -> "$value!" }

```

## Pair
Kotlin language provides a simple datatype to store two values in a single instance. This can be done using a data class known as Pair. It is a simple generic class that can store two values of same or different data types, and there can or can not be a relationship between the two values. The comparison between two Pair objects is done on the basis of values, i.e. two Pair objects are equal if their values are equal.

``` kotlin
data class Pair<out A, out B> : Serializable
Pair(first: A, second: B)


fun main() {
    var pair = Pair("Hello Geeks", "This is Kotlin tutorial")
    val (x, y) = pair
    println(x)
    println(y)
    println(pair.first)
    println(pair.second)
}

```

## Triple
Kotlin language provides a simple datatype to store three values in a single instance. This can be done using a data class known as Triple. It is a simple generic class that stores any three values, there is no valuable meaning of the relationship between the three values. The comparison between two Triple objects is done on the basis of values, i.e. two Triples are equal only if all three components are equal. 

``` kotlin
data class Triple<out A, out B, out C> : Serializable
Triple(first: A, second: B, third: C)

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
```

## Sealed
* Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance. All direct subclasses of a sealed class are known at compile time. No other subclasses may appear outside a module within which the sealed class is defined. For example, third-party clients can't extend your sealed class in their code. Thus, each instance of a sealed class has a type from a limited set that is known when this class is compiled.

* The same works for sealed interfaces and their implementations: once a module with a sealed interface is compiled, no new implementations can appear.

* In some sense, sealed classes are similar to enum classes: the set of values for an enum type is also restricted, but each enum constant exists only as a single instance, whereas a subclass of a sealed class can have multiple instances, each with its own state.

``` kotlin
sealed interface Error

sealed class IOError(): Error

class FileReadError(val file: File): IOError()
class DatabaseError(val source: DataSource): IOError()

object RuntimeError : Error

```

* **A sealed class is abstract by itself**, it cannot be instantiated **directly** and can have abstract members.

* Constructors of sealed classes can have one of two visibilities: protected (by default) or private:

* **Direct subclasses of sealed classes and interfaces must be declared in the same package.**
* Subclasses of sealed classes must have a proper qualified name. They can't be local nor anonymous objects.
* enum classes can't extend a sealed class (as well as any other class), but they can implement sealed interfaces.
  
* These restrictions don't apply to indirect subclasses. If a direct subclass of a sealed class is not marked as sealed, it can be extended in any way that its modifiers allow:
  
```kotlin
sealed interface Error // has implementations only in same package and module

sealed class IOError(): Error // extended only in same package and module
open class CustomError(): Error // can be extended wherever it's visible
```

* The key benefit of using sealed classes comes into play when you use them in a when expression. If it's possible to verify that the statement covers all cases, you don't need to add an else clause to the statement. However, this works only if you use when as an expression (using the result) and not as a statement:

``` kotlin
fun log(e: Error) = when(e) {
    is FileReadError -> { println("Error while reading file ${e.file}") }
    is DatabaseError -> { println("Error while reading from database ${e.source}") }
    is RuntimeError ->  { println("Runtime error") }
    // the `else` clause is not required because all the cases are covered
}
```

## If there is a name clash, you can disambiguate by using as keyword to locally rename the clashing entity:

``` kotlin
import org.example.Message // Message is accessible
import org.test.Message as testMessage // testMessage stands for 'org.test.Message'
```

## Visibility modifiers

* Classes, objects, interfaces, constructors, and functions, as well as properties and their setters, can have visibility modifiers. Getters always have the same visibility as their properties.

* There are four visibility modifiers in Kotlin: `private`, `protected`, `internal`, and `public`. The `default` visibility is `public`.

* If you don't use a visibility modifier, public is used by default, which means that your declarations will be visible everywhere.

* If you mark a declaration as private, it will only be visible inside the file that contains the declaration.

* If you mark it as internal, it will be visible everywhere in the same **module**.

* The protected modifier is not available for top-level declarations.

* To use a visible top-level declaration from another package, you should import it.

## Structural equality
* Structural equality is checked by the == operation and its negated counterpart !=. By convention, an expression like a == b is translated to:

`a?.equals(b) ?: (b === null)`
* If a is not null, it calls the equals(Any?) function, otherwise (a is null) it checks that b is referentially equal to null.

* Note that there's no point in optimizing your code when comparing to null explicitly: a == null will be automatically translated to a === null.

* To provide a custom equals check implementation, override the equals(other: Any?): Boolean function. Functions with the same name and other signatures, like equals(other: Foo), don't affect equality checks with the operators == and !=.

* Structural equality has nothing to do with comparison defined by the Comparable<...> interface, so only a custom equals(Any?) implementation may affect the behavior of the operator.

# Referential equality
* Referential equality is checked by the === operation and its negated counterpart !==. a === b evaluates to true if and only if a and b point to the same object. For values represented by primitive types at runtime (for example, Int), the === equality check is equivalent to the == check.

## Enum
``` kotlin

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}
```

* An enum class can implement an interface (but it cannot derive from a class), providing either a common implementation of interface members for all the entries, or separate implementations for each entry within its anonymous class. This is done by adding the interfaces you want to implement to the enum class declaration as follows:

``` kotlin
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

// The valueOf() method throws an IllegalArgumentException if the specified name does not match any of the enum constants defined in the class.
EnumClass.valueOf(value: String): EnumClass


EnumClass.values(): Array<EnumClass>

```

* You can access the constants in an enum class in a generic way using the enumValues<T>() and enumValueOf<T>() functions:
```kotlin
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

printAllValues<RGB>() // prints RED, GREEN, BLUE
```

* Every enum constant has properties for obtaining its name and position (starting with 0) in the enum class declaration:
``` kotlin
val name: String
val ordinal: Int
```

## Calling Kotlin from Java

A Kotlin property is compiled to the following Java elements:

a getter method, with the name calculated by prepending the get prefix

a setter method, with the name calculated by prepending the set prefix (only for var properties)

a private field, with the same name as the property name (only for properties with backing fields)

* For example, var firstName: String compiles to the following Java declarations:
  
``` kotlin
private String firstName;

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}
```
* If the name of the property starts with is, a different name mapping rule is used: the name of the getter will be the same as the property name, and the name of the setter will be obtained by replacing is with set. For example, for a property isOpen, the getter will be called isOpen() and the setter will be called setOpen(). **This rule applies for properties of any type, not just Boolean.**

## Instance fields
If you need to expose a Kotlin property as a field in Java, annotate it with the @JvmField annotation. The field will have the same visibility as the underlying property. You can annotate a property with @JvmField if it:

has a backing field

is not private

does not have open, override or const modifiers

is not a delegated property

```kotlin
class User(id: String) {
    @JvmField val ID = id
}

// Java
class JavaClient {
    public String getID(User user) {
        return user.ID;
    }
}

```

* Usually these fields are private but they can be exposed in one of the following ways:

    * @JvmField annotation

    * lateinit modifier

    * const modifier

Annotating such a property with @JvmField makes it a static field with the same visibility as the property itself.

## Ranges and progressions
``` kotlin
if (i in 1..4) { // equivalent of i >= 1 && i <= 4 
    print(i)
}

for (i in 4 downTo 1) print(i)

for (i in 1..8 step 2) print(i)

for (i in 1 until 10) {       // i in 1 until 10, excluding 10
    print(i)
}

val versionRange = Version(1, 11)..Version(1, 30)

a.rangeTo(b)

if (i in a..b) {  // a.rangeTo(b)
    print(i)
}

for (i in (1..4).reversed()) print(i)

println((1..10).filter { it % 2 == 0 })
```

## Variable number of arguments 
``` kotlin
fun foo(vararg strings: String) { /*...*/ }

foo(strings = *arrayOf("a", "b", "c"))


fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = asList(1, 2, 3)

```
## Infix notation

''' kotlin
infix fun Int.shl(x: Int): Int { ... }

// calling the function using the infix notation
1 shl 2

// is the same as
1.shl(2)
```

## Grouping

``` kotlin
 val numbers = listOf("one", "two", "three", "four", "five")

 println(numbers.groupBy { it.first().uppercase() }) // {o=[one], t=[two, three], f=[four, five]}
```

Namely, Grouping supports the following operations:

eachCount() counts the elements in each group.

fold() and reduce() perform fold and reduce operations on each group as a separate collection and return the results.

aggregate() applies a given operation subsequently to all the elements in each group and returns the result. This is the generic way to perform any operations on a Grouping. Use it to implement custom operations when fold or reduce are not enough.

``` kotlin
val numbers = listOf("one", "two", "three", "four", "five", "six")
println(numbers.groupingBy { it.first() }.eachCount()) //{o=1, t=2, f=2, s=1}
```

## Delegated properties

* Lazy properties: the value is computed only on first access.

* Observable properties: listeners are notified about changes to this property.

* Storing properties in a map instead of a separate field for each property.

```kotlin

fun main() {
val e = Example()
println(e.p)
e.p="hello"
}
 
 class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

output: 

Example@20fa23c1, thank you for delegating 'p' to me!
hello has been assigned to 'p' in Example@20fa23c1.

```
* The syntax is: val/var <property name>: <Type> by <expression>. The expression after **by is a delegate**, because the get() (and set()) that correspond to the property will be delegated to its getValue() and setValue() methods. Property delegates don't have to implement an interface, but they have to provide a getValue() function (and setValue() for vars).

### Standard delegates

#### Lazy properties

* lazy() is a function that takes a lambda and returns an instance of Lazy<T>, which can serve as a delegate for implementing a lazy property. The first call to get() executes the lambda passed to lazy() and remembers the result. Subsequent calls to get() simply return the remembered result.

``` kotlin
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main() {
    println(lazyValue)
    println(lazyValue)
}
```
* By default, the evaluation of lazy properties is **synchronized**: the value is computed only in one thread, but all threads will see the same value. If the synchronization of the initialization delegate is not required to allow multiple threads to execute it simultaneously, pass **LazyThreadSafetyMode.PUBLICATION** as a parameter to lazy().

If you're sure that the initialization will always happen in the same thread as the one where you use the property, you can use **LazyThreadSafetyMode.NONE**. It doesn't incur any **thread-safety guarantees and related overhead**.

#### Observable properties
* Delegates.observable() takes two arguments: the initial value and a handler for modifications.

* The handler is called every time you assign to the property (after the assignment has been performed). It has three parameters: the property being assigned to, **the old value, and the new value**:

* If you want to intercept assignments and veto them, use vetoable() instead of observable(). The handler passed to vetoable will be called before the assignment of a new property value.

```kotlin
import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"
}

var max: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
    newValue > oldValue
}

println(max) // 0

max = 10
println(max) // 10

max = 5
println(max) // 10

```

#### Delegating to another property

* A property can delegate its getter and setter to another property. Such delegation is available for both top-level and class properties (member and extension). The delegate property can be:

* A top-level property

* A member or an extension property of the same class

* A member or an extension property of another class

* To delegate a property to another property, use the :: qualifier in the delegate name, for example, this::delegate or MyClass::delegate.

``` kotlin
class MyClass {
   var newName: Int = 0
   @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
   var oldName: Int by this::newName
}
fun main() {
   val myClass = MyClass()
   // Notification: 'oldName: Int' is deprecated.
   // Use 'newName' instead
   myClass.oldName = 42
   println(myClass.newName) // 42
}

```
#### Storing properties in a map
```kotlin

val user = User(mapOf(
    "name" to "John Doe",
    "age"  to 25
))

println(user.name) // Prints "John Doe"
println(user.age)  // Prints 25

```

## Dorp, Take, sortedDescending
```kotlin

   val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.take(3)) [one, two, three]
    println(numbers.takeLast(3)) [four, five, six]
    println(numbers.drop(1)) [two, three, four, five, six]
    println(numbers.dropLast(5)) [one]
    println(numbers.drop(3).take(2).sortedDescending().toList()) [four, five]

```