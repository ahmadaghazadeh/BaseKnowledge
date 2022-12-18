using app.Core;

namespace app.Event
{
    public class EventRun : RunAbstract
    {
        public delegate string MyDel(string str);

         
        public static void ProcessCompleted(object sender, EventArgs e)
        {
            Console.WriteLine("Process Completed!");
        }

        public override Task RunAsync()
        {
            ProcessBusinessLogic bl = new ProcessBusinessLogic();
            bl.ProcessCompleted += ProcessCompleted; // register with an event
            bl.StartProcess();
            return Task.CompletedTask;
        }


            public override int Order()
        {
            return 4;
        }
    }
}
