## C#

## 1. C# Interface vs Abstract Class Comparison Table

| The Basis of comparison|C# Interface|C# Abstract Class|
|------------------------|------------|-----------------|
| Access Specifier              | In C#, Interface cannot have an access specifier for functions. It is public by default. | In C#, an abstract class can have access to a specifier for functions. |
| Implementation                | In C#, an interface can only have a signature, not the implementation.                   | An abstract class can provide a complete implementation.               |
| Speed                         | The interface is comparatively slow.                                                     | An abstract class is fast.                                             |
| Instantiate                   | The interface is absolutely abstract and cannot be instantiated.                         | An abstract class cannot be instantiated.                              |
| Fields                        | The interface cannot have fields.                                                        | An abstract class can have defined fields and constants.               |
| Methods                       | The interface has only abstract methods.                                                 | An abstract class can have non-abstract methods.                       |

**Conclusion**

* In C#, an Abstract class vs interface C# has been used for data abstraction. An interface is better than an abstract class when multiple classes need to implement the interface. The member of the interface cannot be static. The only complete member of an abstract class can be static.

* C# does not support multiple inheritances; interfaces are mainly used to implement the multiple inheritances. As a class can implement more than one interface and only inherit from one abstract class. An interface is mainly used only when we do not require the implementation of methods or functionalities. An abstract class is used when we do require at least a default implementation.

* These both C# Interface vs Abstract Class are great object-oriented programming concepts that are used highly in developing applications as per the requirement. It is purely selected by the technical leads with which they are more comfortable and the business requirement. Both are easy to use and simple to learn in any programming language.

## 2. Delegates

* What if we want to pass a function as a parameter? How does C# handles the callback functions or event handler? The answer is - delegate.

* The delegate is a reference type data type that defines the method signature. You can define variables of delegate, just like other data type, that can refer to any method with the same signature as the delegate.

* There are three steps involved while working with delegates:

1. Declare a delegate
2. Set a target method
3. Invoke a delegate

* A delegate can be declared using the delegate keyword followed by a function signature, as shown below.

``` c#
[access modifier] delegate [return type] [delegate name]([parameters])


public delegate void MyDelegate(string msg); // declare a delegate

// set target method
MyDelegate del = new MyDelegate(MethodA);
// or 
MyDelegate del = MethodA; 
// or set lambda expression 
MyDelegate del = (string msg) =>  Console.WriteLine(msg);

// target method
static void MethodA(string message)
{
    Console.WriteLine(message);
}

del.Invoke("Hello World!");
// or 
del("Hello World!");

```
![image](images/delegate-mapping.png?raw=png)

* Passing Delegate as a Parameter
A method can have a parameter of the delegate type, as shown below.

``` c#
    static void InvokeDelegate(MyDelegate del) // MyDelegate type parameter
    {
        del("Hello World");
    }
```

* Multicast Delegate
The delegate can point to multiple methods. A delegate that points multiple methods is called a multicast delegate. The "+" or "+=" operator adds a function to the invocation list, and the "-" and "-=" operator removes it.

``` c#
public delegate int MyDelegate(); //declaring a delegate

class Program
{
    static void Main(string[] args)
    {
        MyDelegate del1 = ClassA.MethodA;
        MyDelegate del2 = ClassB.MethodB;

        MyDelegate del = del1 + del2; 
        Console.WriteLine(del());// returns 200

        del = del - del2; // removes del2
        del("Hello World");

        del -= del1 // removes del1
        del("Hello World");
    }
}

class ClassA
{
    static int MethodA()
    {
        return 100;
    }
}

class ClassB
{
    static int MethodB()
    {
        return 200;
    }
}

```

**The addition and subtraction operators always work as part of the assignment: del1 += del2; is exactly equivalent to del1 = del1+del2; and likewise for subtraction.**

If a delegate returns a value, then the last assigned target method's value will be return when a multicast delegate called

* Generic Delegate
A generic delegate can be defined the same way as a delegate but using generic type parameters or return type. The generic type must be specified when you set a target method.

For example, consider the following generic delegate that is used for int and string parameters.

``` c#
public delegate T add<T>(T param1, T param2); // generic delegate

class Program
{
    static void Main(string[] args)
    {
        add<int> sum = Sum;
        Console.WriteLine(sum(10, 20));

        add<string> con = Concat;
        Console.WriteLine(conct("Hello ","World!!"));
    }

    public static int Sum(int val1, int val2)
    {
        return val1 + val2;
    }

    public static string Concat(string str1, string str2)
    {
        return str1 + str2;
    }
}
```
* Points to Remember :

1- Delegate is the reference type data type that defines the signature.
2- Delegate type variable can refer to any method with the same signature as the delegate.
3- Syntax: [access modifier] delegate [return type] [delegate name]([parameters])
4- A target method's signature must match with delegate signature.
5- Delegates can be invoke like a normal function or Invoke() method.
6- Multiple methods can be assigned to the delegate using "+" or "+=" operator and removed using "-" or "-=" operator. It is called multicast delegate.
7- If a multicast delegate returns a value then it returns the value from the last assigned target method.
D8- elegate is used to declare an event and anonymous methods in C#.


## 3. asynchronous

C# and .NET Framework (4.5 & Core) supports asynchronous programming using some native functions, classes, and reserved keywords.

**What is Asynchronous Programming?**
In asynchronous programming, the code gets executed in a thread without having to wait for an I/O-bound or long-running task to finish. For example, in the asynchronous programming model, the LongProcess() method will be executed in a separate thread from the thread pool, and the main application thread will continue to execute the next statement.

Microsoft recommends Task-based Asynchronous Pattern  to implement asynchronous programming in the .NET Framework or .NET Core applications using async , await keywords and Task or Task<TResult> class.

Now let's rewrite the above example in asynchronous pattern using async keyword.

``` c#

using System;
using System.Threading.Tasks;

public class Asynchronous1
{
	public static async Task Run()
	{
		LongProcess();
		ShortProcess();
	}

	public static async void LongProcess()
	{
		Console.WriteLine("LongProcess Started");
		await Task.Delay(4000); // hold execution for 4 seconds
		Console.WriteLine("LongProcess Completed");
	}

	static void ShortProcess()
	{
		Console.WriteLine("ShortProcess Started");
		//do something here
		Console.WriteLine("ShortProcess Completed");
	}
}

```
[Code Sample](examples/asynchronous/asynchronous1.cs)

### async, await, and Task
Use async along with await and Task if the async method returns a value back to the calling code. We used only the async keyword in the above program to demonstrate the simple asynchronous void method.

The await keyword waits for the async method until it returns a value. So the main application thread stops there until it receives a return value.

The Task class represents an asynchronous operation and Task<TResult> generic class represents an operation that can return a value. In the above example, we used await Task.Delay(4000) that started async operation that sleeps for 4 seconds and await holds a thread until 4 seconds.

The following demonstrates the async method that returns a value.

``` c#

public class Asynchronous2
{
	public static async Task Run()
	{
        Task<int> result = LongProcess();
        ShortProcess();
        var val = await result; // wait untile get the return value
        Console.WriteLine("Result: {0}", val);
    }

    static async Task<int> LongProcess()
    {
        Console.WriteLine("LongProcess Started");
        await Task.Delay(4000); // hold execution for 4 seconds
        Console.WriteLine("LongProcess Completed");
        return 10;
    }

    static void ShortProcess()
    {
        Console.WriteLine("ShortProcess Started");
        //do something here
        Console.WriteLine("ShortProcess Completed");
    }
}

```
[Code Sample](examples/asynchronous/asynchronous2.cs)

In the above example, in the static async Task<int> LongProcess() method, Task<int> is used to indicate the return value type int. int val = await result; will stop the main thread there until it gets the return value populated in the result. Once get the value in the result variable, it then automatically assigns an integer to val.

An async method should return void, Task, or Task<TResult>, where TResult is the return type of the async method. Returning void is normally used for event handlers. The async keyword allows us to use the await keyword within the method so that we can wait for the asynchronous method to complete for other methods which are dependent on the return value.

If you have multiple async methods that return the values then you can use await for all methods just before you want to use the return value in further steps.

``` c#


public class Asynchronous3
{
	public static async Task Run()
	{
        Task<int> result1 = LongProcess1();
        Task<int> result2 = LongProcess2();
        //do something here
        Console.WriteLine("After two long processes.");
        int val = await result1; // wait untile get the return value
        DisplayResult(val);
        val = await result2; // wait untile get the return value
        DisplayResult(val);
    }

    static async Task<int> LongProcess1()
    {
        Console.WriteLine("LongProcess 1 Started");
        await Task.Delay(4000); // hold execution for 4 seconds
        Console.WriteLine("LongProcess 1 Completed");
        return 10;
    }

    static async Task<int> LongProcess2()
    {
        Console.WriteLine("LongProcess 2 Started");
        await Task.Delay(4000); // hold execution for 4 seconds
        Console.WriteLine("LongProcess 2 Completed");
        return 20;
    }

    static void DisplayResult(int val)
    {
        Console.WriteLine(val);
    }
}

```

[Code Sample](examples/asynchronous/asynchronous3.cs)

In the above program, we do await result1 and await result2 just before we need to pass the return value to another method.

Thus, you can use async, await, and Task to implement asynchronous programming in .NET Framework or .NET Core using C#.

## 4. Attributes (Annotation In Java)

* Attributes are used in C# to convey declarative information or metadata about various code elements such as methods, assemblies, properties, types, etc. Attributes are added to the code by using a declarative tag that is placed using square brackets ([ ]) on top of the required code element. There are two types of Attributes implementations provided by the .NET Framework are:

1- Predefined Attributes
2- Custom Attributes

**Properties of Attributes:**

Attributes can have arguments just like methods, properties, etc. can have arguments.
Attributes can have zero or more parameters.
Different code elements such as methods, assemblies, properties, types, etc. can have one or multiple attributes.
Reflection can be used to obtain the metadata of the program by accessing the attributes at run-time.
Attributes are generally derived from the System.Attribute Class.

1. **Predefined Attributes**
Predefined attributes are those attributes that are a part of the .NET Framework Class Library and are supported by the C# compiler for a specific purpose. Some of the predefined attributes that are derived from the System.Attribute base class are given as follows:

|Attribute|Description|
|---|---|
|AttributeUsage|This attribute specifies the usage of a different attribute.|
|CLSCompliant|This attribute shows if a particular code element complies with the Common Language Specification.|
|ContextStatic|This attribute indicates if a static field value is unique for the specified context.|
|Flags|This attribute indicates if a static field value is unique for the specified context.|
|LoaderOptimization|This attribute sets the optimization policy for the default loader in the main method.|
|NonSerialized|This attribute signifies that the field of the serializable class should not be serialized.|
|Obsolete|This attribute marks the code elements that are obsolete i.e. not in use anymore.|
|Serializable|This attribute signifies that the field of the serializable class can be serialized.|
|ThreadStatic|This attribute indicates that there is a unique static field value for each thread.|
|DllImport|This attribute indicates that the method is a static entry point as shown by the unmanaged DLL.|

### Custom Attributes
* Custom attributes can be created in C# for attaching declarative information to methods, assemblies, properties, types, etc. in any way required. 