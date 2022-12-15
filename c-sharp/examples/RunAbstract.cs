
using System.Runtime.CompilerServices;

namespace app
{
    public abstract class RunAbstract
    {
        public abstract Task RunAsync();
        protected static void WriteLine(string value)
        {
            Console.WriteLine($"---- {value} ----");
        }
    }
}
