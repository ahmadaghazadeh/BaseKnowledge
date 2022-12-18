
using app.Core;

namespace app.asynchronous
{
    public class AsynchonousRun : RunAbstract
    {
        public override async Task RunAsync()
        {
            await Asynchronous();
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

        public override int Order()
        {
            return 1;
        }
    }
}
