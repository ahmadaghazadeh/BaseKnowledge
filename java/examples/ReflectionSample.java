
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