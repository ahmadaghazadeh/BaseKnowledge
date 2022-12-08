using System;
using System.Threading.Tasks;

public class Program
{
	public static async Task Main(string[] args)
	{
		WriteLine("Asynchronous1");
		Asynchronous1.Run();
		WriteLine("Asynchronous1");
	}

	private static void WriteLine(string value){
		Console.WriteLine($"---- {value} ----");
	}

}