
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