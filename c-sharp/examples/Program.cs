using app.Core;
using Microsoft.Extensions.DependencyInjection;
public class Program
{
    private static ServiceProvider serviceProvider;

    public static async Task Main(string[] args)
    {
        RegisterService();
        
        await RunLatestSampleAsync();
        //RunAllSamples();
        Console.Read();
    }

    private static async void RunAllSamples()
    {
        var services = serviceProvider.GetServices<IRunnable>().ToList();
        services.Sort();
        foreach (var service in services)
        {
           await service.RunAsync();
        }
    }

    private static async Task RunLatestSampleAsync()
    {
        var services = serviceProvider.GetServices<IRunnable>().ToList();
        services.Sort();
        await services.Last().RunAsync();
    }

    private static void RegisterService()
    {
        var collection = new ServiceCollection();
        serviceProvider = collection.Scan(scan => {
            scan.FromAssemblyOf<IRunnable>().
            AddClasses(classes => classes.AssignableTo<IRunnable>()).
            AsImplementedInterfaces().
            WithTransientLifetime();
        }).BuildServiceProvider();
    }
}