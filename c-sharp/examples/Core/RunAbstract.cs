
namespace app.Core
{
    public abstract class RunAbstract : IRunnable
    {

        protected static void WriteLine(string value)
        {
            Console.WriteLine($"---- {value} ----");
        }

        public abstract Task RunAsync();

        public abstract int Order();

        public int CompareTo(IRunnable? other)
        {
            return Order() - other!.Order()  ;
        }
    }
}
