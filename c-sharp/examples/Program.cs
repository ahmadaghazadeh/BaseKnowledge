using app.asynchronous;
using app.Attributes;
using System;
using System.Threading.Tasks;

public class Program
{
	public static async Task Main(string[] args)
    {
       // await new AsynchonousRun().RunAsync();

        await new AttributeRun().RunAsync();
    }
   

}