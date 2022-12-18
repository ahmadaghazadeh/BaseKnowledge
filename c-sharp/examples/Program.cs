using app.asynchronous;
using app.Attributes;
using app.Event;
using app.Operator;
using app.threadPool;
using System;
using System.Threading.Tasks;

public class Program
{
	public static async Task Main(string[] args)
    {
        // await new AsynchonousRun().RunAsync();

        //await new AttributeRun().RunAsync();

        //await new ThreadPoolRun().RunAsync();

       // await new OperatorRun().RunAsync();

        await new EventRun().RunAsync();
    }


}