## C#

#### 1. C# Interface vs Abstract Class Comparison Table

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