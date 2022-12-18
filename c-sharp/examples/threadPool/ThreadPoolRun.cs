

namespace app.threadPool
{
    public class ThreadPoolRun : RunAbstract
    {
        public override Task RunAsync()
        {
            ProduceConsume obj = new ProduceConsume();
            for (int i = 0; i < 100; i++)
            {
                obj.Produce(new User(i));
            }
            Console.WriteLine("Thread {0}",
            Thread.CurrentThread.GetHashCode()); //{0}
            while (obj.QueueLength != 0)
            {
                Thread.Sleep(1000);
            }
            Console.Read();
            return Task.CompletedTask;
        }
    }
}
