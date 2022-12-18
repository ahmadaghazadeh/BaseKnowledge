
using app.Core;

namespace app.Indexers
{
    internal class IndexerRun : RunAbstract
    {
        public override Task RunAsync()
        {
            var week = new DayCollection();
            Console.WriteLine(week["Fri"]);

            try
            {
                Console.WriteLine(week["Made-up day"]);
            }
            catch (ArgumentOutOfRangeException e)
            {
                Console.WriteLine($"Not supported input: {e.Message}");
            }
            return Task.CompletedTask;
        }
        public override int Order()
        {
            return 7;
        }
    }
}
