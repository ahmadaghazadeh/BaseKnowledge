using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace app.threadPool
{
    public class ProduceConsume 
    {
        public int QueueLength;
        public ProduceConsume()
        {
            QueueLength = 0;
        }
        public void Produce(User ware)
        {
            ThreadPool.QueueUserWorkItem(
            new WaitCallback(Consume), ware);
            QueueLength++;
        }
        public void Consume(Object obj)
        {
            Console.WriteLine("Thread {0} consumes {1}",
            Thread.CurrentThread.GetHashCode(), //{0}
            ((User)obj).id); //{1}
            Thread.Sleep(100);
            QueueLength--;
        }
        
    }
}
