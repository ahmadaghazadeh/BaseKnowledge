using app.asynchronous;
using app.Attributes;
using System;
using System.Threading.Tasks;

public class Program
{
	public static async Task Main(string[] args)
    {
       // await new AsynchonousRun().RunAsync();

        //await new AttributeRun().RunAsync();

        string a = new string(new char[] { 'h', 'e', 'l', 'l', 'o' });
        string b = new string(new char[] { 'h', 'e', 'l', 'l', 'o' });

        Console.WriteLine(a == b);
        Console.WriteLine(a.Equals(b));

        // Now let's see what happens with the same tests but
        // with variables of type object
        object c = a;
        object d = b;

        Console.WriteLine(c == d);
        Console.WriteLine(c.Equals(d));
    }
   

}