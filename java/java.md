# Git

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
    