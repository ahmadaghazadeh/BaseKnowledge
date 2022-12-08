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