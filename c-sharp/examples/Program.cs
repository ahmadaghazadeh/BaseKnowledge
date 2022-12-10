using System;
using System.Threading.Tasks;

public class Program
{
	public static async Task Main(string[] args)
    {
        //await Asynchronous();
        Attributes();
    }

    private static async Task Asynchronous()
    {
        WriteLine("Asynchronous1");
        await Asynchronous1.Run();
        WriteLine("Asynchronous1");

        WriteLine("Asynchronous2");
        await Asynchronous2.Run();
        WriteLine("Asynchronous2");


        WriteLine("Asynchronous3");
        await Asynchronous3.Run();
        WriteLine("Asynchronous3");
    }

    private static void Attributes()
    {
        WriteLine("Asynchronous1");
        Attributes1.Run();
        WriteLine("Asynchronous1");

    }

    private static void WriteLine(string value){
		Console.WriteLine($"---- {value} ----");
	}

}