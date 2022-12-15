

[assembly: CLSCompliant(true)]
public class Attributes2
{
   [Obsolete("method1 is obsolete", true)] 
   public static void method1()
    {
        Console.WriteLine("This is method1");
    }

    public static void method2()
    {
        Console.WriteLine("This is method2");
    }

    public static void Run()
    {
        // method1();
        method2();
    }

}