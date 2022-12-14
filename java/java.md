# Java

## 1. Runtime Polymorphism in Java

* Polymorphism is a technique wherein a single action can be performed in two different ways.

* Polymorphism in Java can be done in two ways, method **overloading** and method **overriding**. There are two types of polymorphism in Java.
  
  1. Compile-time polymorphism
    Compile-time polymorphism is a process in which a call to an **overridden** method is resolved at compile time. 

  2. Runtime polymorphism.
    Runtime polymorphism, also known as the **Dynamic Method Dispatch**, is a process that resolves a call to an **overridden method at runtime**. The process involves the use of the reference variable of a superclass to call for an overridden method.
* Upcasting
If the reference variable of Parent class refers to the object of Child class, it is known as upcasting.
``` java
class Bike{  
  void run(){System.out.println("running");}  
}  
class Splendor extends Bike{  
  void run(){System.out.println("running safely with 60km");}  
  
  public static void main(String args[]){  
    Bike b = new Splendor();//upcasting  
    b.run();  
  }  
}  
```

## 2. Exception
* In Java, an exception is an event that disrupts the normal flow of the program. It is an object which is thrown at runtime.

![image](images/hierarchy-of-exception-handling.png?raw=png)

There are mainly two types of exceptions: checked and unchecked. An error is considered as the unchecked exception. However, according to Oracle, there are three types of exceptions namely:

 1. Checked Exception
  The classes that directly inherit the Throwable **class except RuntimeException** and Error are known as checked exceptions. For example, IOException, SQLException, etc. Checked exceptions are checked **at compile-time**.

 2. Unchecked Exception
   The classes that inherit the RuntimeException are known as unchecked exceptions. For example, ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException, etc. Unchecked exceptions are not **checked at compile-time, but they are checked at runtime.**
 
 3. Error
  Error is irrecoverable. Some example of errors are OutOfMemoryError, VirtualMachineError, AssertionError etc.

![image](images/exception-checked-unchecked.jpg)

|Keyword|Description|
|:----|:----|
|try|The "try" keyword is used to specify a block where we should place an exception code. It means we can't use try block alone. The try block must be followed by either catch or finally.|
|catch|The "catch" block is used to handle the exception. It must be preceded by try block which means we can't use catch block alone. It can be followed by finally block later.|
|finally|The "finally" block is used to execute the necessary code of the program. It is executed whether an exception is handled or not.|
|throw|The "throw" keyword is used to throw an exception.|
|throws|The "throws" keyword is used to declare exceptions. It specifies that there may occur an exception in the method. It doesn't throw an exception. It is always used with method signature.|

 [Code Sample](examples/ExceptionSample.java)

 ``` java

 public class ExceptionSample {
    public static void main(String[] args) {
        System.out.println(print(1));
        System.out.println(print1(1)); <== has an error because print1 has throws in signature
    }

    static Exception print(int i){
        if (i>0) {
            return new Exception();
        } else {
            throw new RuntimeException();
        }
    }

    static  void print1(int i) throws Exception {
        if (i>0) {
            throw new Exception();
        } else {
            throw new RuntimeException();
        }
    }
}

 ```   

 ## 3. interface
 * An **interface in Java** is a blueprint of a class. It has static constants and abstract methods.

* In other words, you can say that interfaces can have abstract methods and **variables**. It cannot have a method body.

* It cannot be **instantiated** just like the abstract class.

* Since Java 8, we can have **default and static methods** in an interface.

* Since Java 9, we can have **private methods** in an interface.

* Why use Java interface?
  1. It is used to achieve abstraction.
  2. By interface, we can support the functionality of **multiple inheritance**.
  3. It can be used to achieve **loose coupling**.

![image](images/interfacerelation.jpg)

[Code Sample](examples/InterfaceSample.java)

 ``` java

public class InterfaceSample {
     
    interface One {
        default void method() {
            System.out.println("One");
        }
    }
    
    interface Two {
        default void method () {
            System.out.println("One");
        }
    }
    class Three implements One, Two {

        public void method() {
            One.super.method();
            Two.super.method();
        }
    }
}
 ``` 

## 4. instanceof

* The java instanceof operator is used to test whether the object is an instance of the specified type (class or subclass or interface).

* The instanceof in java is also known as type comparison operator because it compares the instance with type. It returns either **true or false**. If we apply the instanceof operator with any variable that has null value, it returns false.

* If we apply instanceof operator with a variable that have null value, it returns **false**. Let's see the example given below where we apply instanceof operator with the variable that have null value.

``` java
  Dog2 d=null;  
  System.out.println(d instanceof Dog2);//false  
```
* Possibility of downcasting with instanceof
Let's see the example, where downcasting is possible by instanceof operator.

``` java
class Dog3 extends Animal {  
  static void method(Animal a) {  
    if(a instanceof Dog3){  
       Dog3 d=(Dog3)a;//downcasting  
       System.out.println("ok downcasting performed");  
    }  
  }  
```

* Downcasting without the use of java instanceof

``` java
class Animal { }  
class Dog4 extends Animal {  
  static void method(Animal a) {  
       Dog4 d=(Dog4)a;//downcasting  
       System.out.println("ok downcasting performed");  
  }  
   public static void main (String [] args) {  
    Animal a=new Dog4();  
    Dog4.method(a);  
  }  
}  

```

## 5. access modifiers 

![image](images/access-modifiers.jpg)

## 6. final
* final keyword is used in different contexts. First of all, final is a **non-access modifier** applicable only to a variable, a method, or a class. The following are different contexts where final is used.

Non Access Modifiers are the keywords introduced in Java 7 to notify JVM about a class’s behaviour, methods or variables, etc. That helps introduce additional functionalities, such as the final keyword used to indicate that the variable **cannot be initialized twice**. There are a total of 7 non-access modifiers introduced.

1. Static
2. Final
3. Abstract
4. Synchronized
5. transient
6. strictfp
7. native

![image](images/FinalkeywordinJava.jpg)

1. Final Variables
* When a variable is declared with the final keyword, its value can’t be **modified**, essentially, a constant. This also means that you must initialize a final variable. If the final variable is a reference, this means that the variable **cannot be re-bound to reference another object**, but the internal state of the object pointed by that reference variable can be changed i.e. **you can add or remove elements** from the final array or final collection.

There are three ways to initialize a final variable: 
1. You can initialize a final variable when it is declared. This approach is the most common. A final variable is called a blank final variable if it is not initialized while declaration. Below are the two ways to initialize a blank final variable.
2. A blank final variable can be initialized inside an instance-initializer block or inside the constructor. If you have more than one constructor in your class then it must be initialized in all of them, otherwise, a compile-time error will be thrown.
3. A blank final static variable can be initialized inside a static block.

``` java

class GFG {
    
    // a final variable
    // direct initialize
    final int THRESHOLD = 5;
      
    // a blank final variable
    final int CAPACITY;
      
    // another blank final variable
    final int  MINIMUM;
      
    // a final static variable PI
    // direct initialize
    static final double PI = 3.141592653589793;
      
    // a  blank final static  variable
    static final double EULERCONSTANT;
      
    // instance initializer block for 
    // initializing CAPACITY
    {
        CAPACITY = 25;
    }
      
    // static initializer block for 
    // initializing EULERCONSTANT
    static{
        EULERCONSTANT = 2.3;
    }
      
    // constructor for initializing MINIMUM
    // Note that if there are more than one
    // constructor, you must initialize MINIMUM
    // in them also
    public GFG() 
    {
        MINIMUM = -1;
    }
          
}

```

*Remember the below key points as perceived before moving forward as listed below as follows:

1. Note the difference between C++ const variables and Java final variables. const variables in **C++ must be assigned a value when declared**. For final variables in Java, it is not necessary as we see in the above examples. A final variable can be assigned **value later, but only once.**
2 . final with foreach loop: final with for-each statement **is a legal statement**.

### Final classes
* When a class is declared with final keyword, it is called a final class. A final class **cannot be extended(inherited)**. 

**Usage 1:** One is definitely to prevent inheritance, as final classes cannot be extended. For example, all Wrapper Classes like Integer, Float, etc. are final classes. We can not extend them.

``` java

final class A
{
     // methods and fields
}
// The following class is illegal
class B extends A 
{ 
    // COMPILE-ERROR! Can't subclass A
}

```

**Usage 2:** The other use of final with classes is to create an immutable class like the predefined String class. One can not make a class immutable without making it final.

* Immutable class in java means that once an object is created, we cannot change its content. In Java, all the wrapper classes (like Integer, Boolean, Byte, Short) and String class is immutable. Following are the requirements: 

1. The class must be declared as final so that child classes can’t be created.
2. Data members in the class must be declared **private** so that direct access is not allowed.
3. Data members in the class must be declared as **final so that we can’t change the value of it after object creation**.
4. A parameterized constructor should initialize all the fields performing a **deep copy** so that data members can’t be modified with an object reference.
5. Deep Copy of objects should be performed in the getter methods to return a copy rather than returning the actual object reference)

### Final Methods
* When a method is declared with final keyword, it is called a final method. A final method **cannot be overridden**. 


### finally keyword
* Just as final is a reserved keyword, so in the same way finally is also a reserved keyword in java i.e, we can’t use it as an identifier. The finally keyword is used in association with a try/catch block and guarantees that a section of code will be executed, even if an exception is thrown. 

``` java
class E{
  public static void main(String[] args){
    try{
      System.out.println("In try block");
      System.exit(0);
    }
    catch(ArithmeticException e){
      System.out.println("In catch block");
    }
    finally{
      System.out.println("finally block");
    }
  }
}
```
Here in the above program, finally, the block doesn’t execute. There is only one situation where finally block won’t be executed when we are using System.exit(0) method. When we are using System.exit(0) then JVM itself shutdown, hence in this case finally block won’t be executed. Here, the number within the parenthesis is known as the status code. **Instead of zero, we can take any integer value where zero means normal termination, and non-zero means abnormal termination.** Whether it is zero or non-zero, there is no change in the result and the effect is the same with respect to the program. 

### Finalize method

* It is a method that the Garbage Collector always calls just before the deletion/destroying of the object which is eligible for Garbage Collection, so as to perform clean-up activity. Clean-up activity means closing the resources associated with that object like Database Connection, Network Connection, or we can say resource de-allocation. Remember, it is not a reserved keyword. Once the finalized method completes immediately Garbage Collector destroys that object. finalize method is present in the Object class and its syntax is:

``` java
protected void finalize throws Throwable{}
```

* Since the Object class contains the finalize method, hence finalize method is **available for every Java class** since Object is the superclass of all Java classes. Since it is available for every java class hence Garbage Collector can call finalize method on any Java object Now, the finalize method which is present in the Object class, **has an empty implementation**, in our class clean-up activities are there, then we have to override this method to define **our own clean-up activities**. Cases related to finalizing method:

``` java
class Hi {
    public static void main(String[] args)
    {
        Hi j = new Hi();
 
        // Calling finalize method Explicitly.
        j.finalize();
 
        j = null;
 
        // Requesting JVM to call Garbage Collector method
        System.gc();
        System.out.println("Main Completes");
    }
 
    // Here overriding finalize method
    public void finalize()
    {
        System.out.println("finalize method overridden");
        System.out.println(10 / 0);
    }
}

Output:
exception in thread "main" java.lang.ArithmeticException:
/ by zero followed by stack trace.

```

* So the key point is: If the programmer calls finalize method while executing finalize method some unchecked exception rises, then JVM terminates the program abnormally by rising an exception. So in this case, the program **termination is Abnormal**.


 **If the garbage Collector calls finalize method while executing finalize method, some unchecked exception rises.**

 ``` java

 class RR {
    public static void main(String[] args)
    {
        RR q = new RR();
        q = null;
 
        // Requesting JVM to call Garbage Collector method
        System.gc();
        System.out.println("Main Completes");
    }
 
    // Here overriding finalize method
    public void finalize()
    {
        System.out.println("finalize method overridden");
        System.out.println(10 / 0);
    }
}

Output:

finalize method overridden
Main Completes
 ```
* So the key point is if Garbage Collector calls finalize method while executing finalize method some unchecked exception rises then JVM ignores that exception and the rest of the program will be continued normally. **So in this case the program termination is Normal and not abnormal.**

### Important points:

1. There is no guarantee about the time when finalize is called. It may be called any time after the object is not being referred anywhere (can be garbage collected).
2. JVM does not ignore all exceptions while executing finalize method, **but it ignores only unchecked exceptions**. If the corresponding catch block is there, then JVM won’t ignore any corresponding catch block and will be executed.
3. System.gc() is just a request to JVM to execute the Garbage Collector. It’s up to JVM to call Garbage Collector or not. Usually, JVM calls Garbage Collector when there **is not enough space available in the Heap area or when the memory is low.**

### compareTo

"a".compareTo("b") is -1

"a".compareTo("a") is 0

"b".compareTo("a") is 1

## ArrayList 
* ArrayList is a class in java.util package which implements dynamic-sized arrays.** ArrayList dynamically grows and shrinks in size on the addition and removal of elements respectively.** ArrayList inherits the AbstractList class and implements the List, RandomAccess and java.io.Serializable interface. Addition of elements to an ArrayList takes amortized constant time - O(1).

* When an ArrayList is created, its default **capacity or size is 10** if not provided by the user. The size of the ArrayList grows based on load factor and current capacity.

1. The Load Factor is a measure to decide when to increase its capacity. The default value of load factor of an ArrayList is 0.75f
2. ArrayList in Java expands its capacity after each threshold which is calculated as the product of current capacity and load factor of the ArrayList instance.

```
Threshold = (Load Factor) * (Current Capacity)
```

For example, if the user creates an ArrayList of size 10,

**Threshold = Load Factor * Current Capacity = 0.75 * 10 ≅ 7**

![image](images/how-java-arraylist-works.webp)

In Java 8 and later, the new capacity of the ArrayList is calculated to be 50% more than its old capacity.

``` 
new_capacity = old_capacity + (old_capacity >> 1)
```

For example, if the array size is 10 and it has reached the threshold value, we have to increase its capacity to add new elements. The new capacity will be 10 + (10 >> 1) => 10 + 5 => 15. Hence, the size is increased from 10 to 15.


``` java
import java.util.*;

class SortMethod {

  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<String>();
    names.add("Raj");
    names.add("Priya");
    names.add("Shashank");
    names.add("Ansh");
    System.out.println("Before sorting, names : " + names);

    //Sorting ArrayList in ascending order
    Collections.sort(names); //1
    names.sort(Comparator.comparing(String::toString))//2
    names.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList())// 3
    System.out.println("After sorting, names : " + names);
  }
}

The implementation is iterative merge sort and takes O(n * log(n)).

```

### How to synchronize ArrayList in Java?

* Collections.synchronizedList
* CopyOnWriteArrayList

``` java
import java.util.*;

class SynchronizeExample {

  public static void main(String[] args) {
    List<String> arr = new ArrayList<String>();
    // adding elements to the list
    arr.add("Hello");
    arr.add("World");
    arr.add("in");
    arr.add("Java");

    // Synchronizing the ArrayList externally using
    // synchronizedList() method
    arr = Collections.synchronizedList(arr);

    synchronized (arr) {
      // It should be in synchronized block
      Iterator it = arr.iterator();

      // Iterating through the elements
      while (it.hasNext()) System.out.println(it.next());
    }
  }
}


import java.io.*;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class SynchronizeUsingCopyOnWriteArrayList {

  public static void main(String[] args) {
    // creating a thread-safe ArrayList using
    // CopyOnWriteArrayist.
    CopyOnWriteArrayList<String> arr = new CopyOnWriteArrayList<String>();

    // Adding elements to synchronized ArrayList
    arr.add("Hello");
    arr.add("World");
    arr.add("in");
    arr.add("Java");

    System.out.println("Elements of synchronized ArrayList :");

    // Iterating on the synchronized ArrayList using an iterator.
    Iterator<String> it = arr.iterator();

    while (it.hasNext()) System.out.println(it.next());
  }
}

```
### Difference Between Array and Arraylist

#### Array
* Length of Array is static that means one cannot change its length that has been already defined by the developer to that particular element. This Array needs to specify the size of the elements.  
* In other words, the length of the elements in the array is static or  requires more memory to store the elements and less time to iterate the elements. 
* Array does not allow generics, though multidimensional in nature. 
* In the array, giving references to objects or elements  depends upon the type of array such as primitive type or object type.
* Functions such as indexOf() and remove() are not supported by Arrays in Java.
#### ArrayList
* ArrayList uses the size() method to compute the size of the elements. Also, it is dynamic, which means one can change the size of the arraylist if the elements are modified in it, which means the length of the arraylist is variable. 
* ArrayList requires more memory to store the elements as well as more time to iterate. 
* ArraList enables the use of generic and single dimensional in nature. 
* In arrayList, we can convert the primitive int data type into an Integer object with the help of commands such as “arraylist.add(1)” as shown in example. 
* Since primitive data types can be created in ArrayList, the members of ArrayList are always given references to the  objects at every different memory locations . Thus, in ArrayList, the actual objects or elements are never stored at contiguous locations whereas their References can be stored at contiguous locations. 
* In ArrayList, primitive types have actual values with contiguous locations, however object type allocation is similar to ArrayList. 
Operations such as indexOf(), remove() are supported by ArrayList in Java.

## this vs super

* The **super** keyword is used to represent an instance of the **parent class** which is created implicitly for each object of the child class. The super keyword can be used to invoke the parent class methods and constructors. It can also be used to access the fields of the parent class.

* The **this** keyword is used to represent the **current instance of a class**. It is used to access the instance variables and invoke current class methods and constructors. The this keyword can be passed as an argument to a method call representing the current class instance.

[Refrence]([images/how-java-arraylist-works.webp](https://www.scaler.com/topics/java/this-and-super-keyword-in-java/))

### this

 [Code Sample](examples/ThisSample.java)

``` java 
 
public class ThisSample  {
    public static void main(String[] args) {
       
      Illustration obj = new Illustration();
      obj.Scaler();
      obj.name();

      System.out.println("Object  ......... 100 .........." );
      Illustration obj100 = new Illustration(100);
      obj100.Scaler();
      obj100.name();
      obj100.invoke();
      (new Illustration()).getIllustration().name();
    }

    
}

class Illustration {

  // declaring an instance variable
  int instanceVar = 5;

  // declaring an static variable
  static int staticVar = 10;

  Illustration() {
    // invoking parameterized constructor
    this(10);
  }

  Illustration(int x) {
    System.out.println("Current class parameterized constructor invoked.");
    System.out.println("Number is : " + x);
  }

  void print(Illustration ob) {
    System.out.println("ob.value = " + ob.instanceVar);
  }

  void invoke() {
    // print method is invoked by passing this as an argument
    print(this);
  }


  Illustration getIllustration() {
    // returing the instance of current class
    return this;
  }

  void Scaler() {
    // Method-specific variables
    int instanceVar = 20;
    int staticVar = 40;
      
    // referring to the current class instance and static variables
    this.instanceVar = 50;
    this.staticVar = 100;

    // printing the current class instance and static variable.
    System.out.println("Value of instance variable : " + this.instanceVar);
    System.out.println("Value of static variable : " + this.staticVar);
      
    // printing the method specific variables.
    System.out.println("instanceVar inside method : " + instanceVar);
    System.out.println("staticVar inside method: " + staticVar);
  }

  void name() {
    // invoking current class scaler method.
    System.out.println("Call ......... name method" );
    this.Scaler();
    System.out.println("Ahmad Aghazadeh.");
  }
}

``` 
### super



``` java
class A{
    void methodP(){
        // method
    }
}
class B extends A{
    void methodC(){
        // method
    }
}
class C extends B{
    void methodGC(){
        // method
    }
}
```
* Let us understand the concept of immediate parent. In the above code snippet of multi-level inheritance class B extends class A, it implies class A is immediate parent of class B. Similarly class C extends class B and now class C has two parents i.e., class A and class B, where class B is immediate parent of class C.

* The **super** is a special keyword in Java that is used to refer to the instance of the **immediate parent class**.

 [Code Sample](examples/ThisSample.java.java)

 ``` java
 
public class SuperSample  {
    public static void main(String[] args) {
       
      Child obj = new Child();
      obj.print();
    }

    
}
 
class Parent {

  int a = 50;
  String s = "Hello World!";
  void display() {
    System.out.println("Hi I am parent method.");
  }
  Parent() {
    System.out.println("Hi I am Parent class constructor.");
  }

}

// child class extending parent class
class Child extends Parent {

  Child() {
    // invoking parent class constructor
    super();
  }
  int a = 100;
  String s = "Happy Coding!";

  void print() {
    // referencing to the instance variable of parent class
    System.out.println("Number from parent class is : " + super.a);
    System.out.println("String from parent class is : " + super.s);

    // printing a and s of the current/child class
    System.out.println("Number from child class is : " + a);
    System.out.println("String from child class is : " + s);
    display();
    super.display();
  }
  void display() {
    System.out.println("Hi I am child method.");
  }
 
}

 ```

### Difference Between this and super Keyword in Java

|                                  **<span class="highlight--red">this</span>** keyword in Java|**<span class="highlight--red">super</span>** keyword in Java|
|:----|:----|
|    <span class="highlight--red">this</span> is an implicit reference variable keyword used to represent the current class.|<span class="highlight--red">super</span> is an implicit reference variable keyword used to represent the immediate parent class.|
|                      <span class="highlight--red">this</span> is to invoke methods of the current class.|<span class="highlight--red">super</span> is used to invoke methods of the immediate parent class.|
|                 <span class="highlight--red">this</span> is used to invoke a constructor of the current class.|<span class="highlight--red">super</span> is used to invoke a constructor of the immediate parent class.|
|           <span class="highlight--red">this</span> refers to the instance and static variables of the current class.|<span class="highlight--red">super</span> refers to the instance and static variables of the immediate parent class.|
|<span class="highlight--red">this</span> can be used to return and pass as an argument in the context of a current class object.|<span class="highlight--red">super</span> can be used to return and pass as an argument in the context of an immediate parent class object.|

## Anonymous Inner Class in Java
 * It is an inner class without a name and for which only a single object is created. An anonymous inner class can be useful when making an instance of an object with certain “extras” such as overriding methods of a class or interface, without having to actually subclass a class.
 * Anonymous inner classes are useful in writing implementation classes for listener interfaces in graphics programming. 

### Now let us do discuss the difference between regular class(normal classes) and Anonymous Inner class

1. A normal class can implement any number of interfaces but the anonymous inner class can implement **only one interface at a time**.
2. A regular class can extend a class and implement any number of interfaces simultaneously. But anonymous Inner class **can extend a class or can implement an interface but not both at a time**.
3. For regular/normal class, we can write any number of constructors but we can’t write any constructor for anonymous Inner class because the anonymous class does not have any name and while defining constructor class name and constructor name must be same.

### Based on declaration and behavior, there are 3 types of anonymous Inner classes: 

1. Anonymous Inner class that extends a class
``` java
  Thread t = new Thread() {
      
      // run() method for the thread
      public void run()
      {
          // Print statement for child thread
          // execution
          System.out.println("Child Thread");
      }
  };

  // Starting the thread
  t.start();

  // Displaying main thread only for readability
  System.out.println("Main Thread");
```
2. Anonymous Inner class that implements an interface
``` java
Runnable r = new Runnable() {
    
    // run() method for the thread
    public void run()
    {
        // Print statement when run() is invoked
        System.out.println("Child Thread");
    }
};

// Creating thread in main() using Thread class
Thread t = new Thread(r);

// Starting the thread using start() method
// which invokes run() method automatically
t.start();
```

3. Anonymous Inner class that defines inside method/constructor argument
``` java
Thread t = new Thread(new Runnable() {
    
    public void run()
    {
        System.out.println("Child Thread");
    }
});

t.start();
```

## volatile
* Using volatile is yet another way (like synchronized, atomic wrapper) of making class thread-safe. Thread-safe means that a method or class instance can be used by multiple threads at the same time without any problem. The volatile keyword can be used either with **primitive type or objects**.
* The volatile keyword **does not cache** the value of the variable and **always read the variable from the main memory**. The volatile keyword cannot be used with classes or methods.

``` java
class Test  
{  
  static int var=5;  
}  
```
*In the above example, assume that two threads are working on the same class. Both threads run on different processors where each thread has its local copy of var. If any thread modifies its value, the change will not reflect in the original one in the main memory. It leads to data inconsistency because the other thread is not aware of the modified value.

``` java
class Test  
{  
  static volatile int var =5;  
}  
```
* In the above example, static variables are class members that are shared among all objects. There is only one copy in the main memory. The value of a volatile variable will never be stored in the cache. All read and write will be done from and to the main memory.

### Difference between synchronization and volatile keyword

**Mutual Exclusion:** It means that only one thread or process can execute a block of code (**critical section**) at a time.
**Visibility:** It means that changes made by one thread to shared data are visible to other threads.
**Java’s synchronized keyword guarantees both mutual exclusion and visibility.** If we make the blocks of threads that modify the value of the shared variable synchronized only one thread can enter the block and changes made by it will be reflected in the main memory. All other threads trying to enter the block at the same time will be blocked and put to sleep. 

In some cases, we may only desire visibility and not atomicity. The use of synchronized in such a situation is overkill and may cause scalability problems. Here volatile comes to the rescue. **Volatile variables have the visibility features of synchronized but not the atomicity features**. The values of the volatile variable will never be cached and all writes and reads will be done to and from the main memory. However, the use of volatile is limited to a very restricted set of cases as most of the times atomicity is desired. For example, a simple increment statement such as x = x + 1; or x++ seems to be a single operation but is really a compound read-modify-write sequence of operations that **must execute atomically**. 


|Volatile Keyword|Synchronization Keyword|
|:----|:----|
|Volatile keyword is a field modifier.|Synchronized keyword modifies code blocks and methods.|
|The thread cannot be blocked for waiting in case of volatile.|Threads can be blocked for waiting in case of synchronized.|
|It improves thread performance.|Synchronized methods degrade the thread performance.|
|It synchronizes the value of one variable at a time between thread memory and main memory.|It synchronizes the value of all variables between thread memory and main memory.|
|Volatile fields are not subject to compiler optimization.|Synchronize is subject to compiler optimization.|
## java.lang.UnsupportedClassVersionError
* The UnsupportedClassVersionError is a sub-class of the LinkageError and thrown by the Java Virtual Machine (JVM). When a class file is read and when major and minor version numbers are not supported, this error is thrown, a**nd especially during the linking phase, this error is thrown**

**When we tried to compile a program using a higher version of Java and execute it using a JVM of a lower version, this error is thrown**

## Java is Pass by Value, Not Pass by Reference
Technically, Java is always pass by value, because even though a variable might hold a reference to an object, that object reference is a value that represents the object’s location in memory. Object references are therefore passed by value.

**Both reference data types and primitive data types are passed by value.**

## native

* The native keyword in Java is applied to a method to indicate that the method is implemented in native code using JNI (Java Native Interface). The native keyword is a modifier that is applicable only for methods, and we can’t apply it anywhere else. The methods which are implemented in C, C++ are called native methods or foreign methods.

* The native modifier indicates that a method is implemented in platform-dependent code, often seen in C language. Native modifier indicates that a method is implemented in platform-dependent code, often in C.

### Main objectives of the native keyword
1. To improve the performance of the system.
2. To achieve machine level/memory level communication.
3. To use already existing legacy non-java code.

## advantages & disadvantage of inheritance

### Advantages of Inheritance in OOPS
1. The main advantage of the inheritance is that it helps in reusability of the code. The codes are defined only once and can be used multiple times. In java we define the super class or base class in which we define the functionalities and any number of child classes can use the functionalities at any time.
2. Through inheritance a lot of time and efforts are being saved.
3. It improves the program structure which can be readable.
4. The program structure is short and concise which is more reliable.
5. The codes are easy to debug. Inheritance allows the program to capture the bugs easily
6. Inheritance makes the application code more flexible to change.
7. Inheritance results in better organisation of codes into smaller, simpler and simpler compilation units.
### Disadvantages of Inheritance in OOPS
1. The main disadvantage of the inheritance is that the two classes(base class and super class) are tightly coupled that is the classes are dependent on each other.
2. If the functionality of the base class is changed then the changes have to be done on the child classes also.
3. If the methods in the super class are deleted then it is very difficult to maintain the functionality of the child class which has implemented the super class’s method.
4. It increases the time and efforts take to jump through different levels of the inheritance.

## lambda 
Lambda Expressions were added in Java 8.

A lambda expression is a short block of code which takes in parameters and returns a value. Lambda expressions are similar to methods, but they do not need a name and they can be implemented right in the body of a method.

![image](images/lambda-expression.jpg)
``` java

parameter -> expression

(parameter1, parameter2) -> expression


(parameter1, parameter2) -> { code block }

```

## hashcode
* Simply put, hashCode() returns an integer value, generated by a hashing algorithm.

* Objects that are equal (according to their equals()) must return the same hash code. Different objects do not need to return different hash codes.

## Is-A(inheritance) and Has-A(composition) Relationship in Java

* In Java, we can reuse our code using an Is-A relationship or using a Has-A relationship. An **Is-A relationship is also known as inheritance** and a **Has-A relationship is also known as composition** in Java.
 
 ![image](images/IS-A-and-HAS-A-relationship.jpg)

 ### Garbage Collection in Java

 * The main objective of Garbage Collector is to free heap memory by destroying unreachable objects. The garbage collector is the best example of the **Daemon thread** as it is always running in the background. 

Two types of garbage collection activity usually happen in Java.

1. **Minor or incremental Garbage Collection:** It is said to have occurred when unreachable objects in the young generation heap memory are removed.
2. **Major or Full Garbage Collection:** It is said to have occurred when the objects that survived the minor garbage collection are copied into the old generation or permanent generation heap memory are removed. When compared to the young generation, garbage collection happens less frequently in the old generation.

**Unreachable objects:** An object is said to be unreachable if it doesn’t contain any reference to it. Also, note that objects which are part of the island of isolation are also unreachable. 

 ![image](images/garbagecollection.jpeg)

**Eligibility for garbage collection:** An object is said to be eligible for GC(garbage collection) if it is unreachable. After i = null, integer object 4 in the heap area is suitable for garbage collection in the above image.

### Ways to make an object eligible for Garbage Collector
1. Nullifying the reference variable
2. Re-assigning the reference variable
3. An object created inside the method
4. Island of Isolation

* Once we make an object eligible for garbage collection, **it may not destroy immediately by the garbage collector**. Whenever JVM runs the Garbage Collector program, then only the object will be destroyed. But when JVM runs Garbage Collector, we can not expect.

1. Using **System.gc()** method: System class contain static method gc() for requesting JVM to run Garbage Collector.
2. Using **Runtime.getRuntime().gc()** method: Runtime class allows the application to interface with the JVM in which the application is running. Hence by using its gc() method, we can request JVM to run Garbage Collector.
3. There is no guarantee that any of the above two methods will run Garbage Collector.
4. The call System.gc() is effectively equivalent to the call : Runtime.getRuntime().gc(). **The only difference is System.gc() is a class method where as Runtime.gc() is an instance method. So, System.gc() is more convenient.**


Set references to null(i.e X = Y = null;)
Call, System.gc();
Call, System.runFinalization();

 [Code Sample](examples/GarbageCollectorSample.java)

 ### Daemon Thread in Java
* Daemon thread in Java is a low-priority thread that runs in the background to perform tasks such as garbage collection. Daemon thread in Java is also a service provider thread that provides services to the user thread. Its life depends on the mercy of user threads i.e. when all the user threads die, JVM terminates this thread automatically.

#### Default Nature of Daemon Thread
* By default, the main thread is always non-daemon but for all the remaining threads, daemon nature will be inherited from parent to child. That is, if the parent is Daemon, the child is also a Daemon and if the parent is a non-daemon, then the child is also a non-daemon.
* Note: Whenever the last non-daemon thread terminates, all the daemon threads will be terminated automatically.

#### Methods of Daemon Thread
1. void setDaemon(boolean status): 
This method marks the current thread as a daemon thread or user thread. For example, if I have a user thread tU then tU.setDaemon(true) would make it a Daemon thread. On the other hand, if I have a Daemon thread tD then calling tD.setDaemon(false) would make it a user thread. 
2. boolean isDaemon(): 
This method is used to check that the current thread is a daemon. It returns true if the thread is Daemon. Else, it returns false. 

``` java
public class DaemonThread extends Thread
{
    public DaemonThread(String name){
        super(name);
    }
  
    public void run()
    {
        // Checking whether the thread is Daemon or not
        if(Thread.currentThread().isDaemon())
        {
            System.out.println(getName() + " is Daemon thread");
        }
          
        else
        {
            System.out.println(getName() + " is User thread");
        }
    }
      
    public static void main(String[] args)
    {
      
        DaemonThread t1 = new DaemonThread("t1");
        DaemonThread t2 = new DaemonThread("t2");
        DaemonThread t3 = new DaemonThread("t3");
      
        // Setting user thread t1 to Daemon
        t1.setDaemon(true);
              
        // starting first 2 threads
        t1.start();
        t2.start();
  
        // Setting user thread t3 to Daemon
        t3.setDaemon(true);
        t3.start();        
    }
}

Output: 

t1 is Daemon thread
t3 is Daemon thread
t2 is User thread

```
#### Exceptions in a Daemon thread 
If you call the setDaemon() method after starting the thread, it would throw **IllegalThreadStateException**.

## Reflection
Reflection is an API that is used to **examine or modify the behavior of methods, classes, and interfaces at runtime**. The required classes for reflection are provided under java.lang.reflect package which is essential in order to understand reflection.

 ![image](images/reflection.jpg)

  Reflection can be used to get information about class, constructors, and methods as depicted below in tabular format as shown:
**Class:** The **getClass() or Class.forName("Test")** method is used to get the name of the class to which an object belongs.
**Constructors:** The getConstructors() method is used to get the public constructors of the class to which an object belongs.

**Methods:** The getMethods() method is used to get the public methods of the class to which an object belongs.

* We can invoke a method through reflection if we know its name and parameter types. We use two methods for this purpose as described below before moving ahead as follows

``` java
Class.getDeclaredMethod(name, parametertype)
Method.invoke(Object, parameter)
```
**Tip:** If the method of the class doesn’t accept any parameter then null is passed as an argument.
**Note:** Through reflection, we can access the private variables and methods of a class with the help of its class object and invoke the method by using the object as discussed above. We use below two methods for this purpose.

 **Field.setAccessible(true):** Allows to access the field irrespective of the access modifier used with the field.

 [Code Sample](examples/ReflectionSample.java)

 ``` java

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
// Class 1
// Of Whose object is to be created
class Test {
    // creating a private field
    private String s;
  
    // Constructor of this class
  
    // Constructor 1
    // Public constructor
    public Test() { s = "Ahmad Aghazadeh Test Class"; }
  
    // Constructor 2
    // no arguments
    public void method1()
    {
        System.out.println("The string is " + s);
    }
  
    // Constructor 3
    // int as argument
    public void method2(int n)
    {
        System.out.println("The number is " + n);
    }
  
    // Constructor 4
    // Private method
    private void method3()
    {
        System.out.println("Private method invoked");
    }
}
  
// Class 2
// Main class
class ReflectionSample {
  
    // Main driver method
    public static void main(String args[]) throws Exception
    {
        // Creating object whose property is to be checked
  
        // Creating an object of class 1 inside main()
        // method
        Test obj = new Test();
  
        // Creating class object from the object using
        // getClass() method
        Class cls = obj.getClass();

        // OR
        Class<?> clazz = Class.forName("Test");
  
        // Printing the name of class
        // using getName() method
        System.out.println("The name of class is "
                           + cls.getName());

        System.out.println("The name of class is "
                           + clazz.getName()+" Class.forName");
  
        // Getting the constructor of the class through the
        // object of the class
        Constructor constructor = cls.getConstructor();
  
        // Printing the name of constructor
        // using getName() method
        System.out.println("The name of constructor is "
                           + constructor.getName());
  
        // Display message only
        System.out.println(
            "The public methods of class are : ");
  
        // Getting methods of the class through the object
        // of the class by using getMethods
        Method[] methods = cls.getMethods();
  
        // Printing method names
        for (Method method : methods)
            System.out.println(method.getName());
  
        // Creates object of desired method by
        // providing the method name and parameter class as
        //  arguments to the getDeclaredMethod() method
        Method methodcall1
            = cls.getDeclaredMethod("method2", int.class);
  
        // Invoking the method at runtime
        methodcall1.invoke(obj, 19);
  
        // Creates object of the desired field by
        // providing the name of field as argument to the
        // getDeclaredField() method
        Field field = cls.getDeclaredField("s");
  
        // Allows the object to access the field
        // irrespective of the access specifier used with
        // the field
        field.setAccessible(true);
  
        // Takes object and the new value to be assigned
        // to the field as arguments
        field.set(obj, "JAVA");
  
        // Creates object of desired method by providing the
        // method name as argument to the
        // getDeclaredMethod()
        Method methodcall2
            = cls.getDeclaredMethod("method1");
  
        // Invokes the method at runtime
        methodcall2.invoke(obj);
  
        // Creates object of the desired method by providing
        // the name of method as argument to the
        // getDeclaredMethod() method
        Method methodcall3
            = cls.getDeclaredMethod("method3");
  
        // Allows the object to access the method
        // irrespective of the access specifier used with
        // the method
        methodcall3.setAccessible(true);
  
        // Invoking the method at runtime
        methodcall3.invoke(obj);

        final var test = new ArrayList(10);
        Class<?> goatClass = test.getClass();
        Package pkg = goatClass.getPackage();

        System.out.println("Package name is "+
            pkg.getName());
    }
}
 ```
* get Packename
 ``` java
    Test test = new Goat("goat");
    Class<?> goatClass = test.getClass();
    Package pkg = test.getPackage();
 ```






