using app.Core;

namespace app.Enum
{
    public class EnumRun:RunAbstract
    {
        public override int Order()
        {
            return 6;
        }

        public override Task RunAsync()
        {
            Days meetingDays = Days.Monday | Days.Wednesday | Days.Friday;
            Console.WriteLine(meetingDays);
            // Output:
            // Monday, Wednesday, Friday // if not flag 21

            Days workingFromHomeDays = Days.Thursday | Days.Friday;
            Console.WriteLine($"Join a meeting by phone on {meetingDays & workingFromHomeDays}");
            // Output:
            // Join a meeting by phone on Friday

            bool isMeetingOnTuesday = (meetingDays & Days.Tuesday) == Days.Tuesday;
            Console.WriteLine($"Is there a meeting on Tuesday: {isMeetingOnTuesday}");
            // Output:
            // Is there a meeting on Tuesday: False

            var a = (Days)37;
            Console.WriteLine(a);
            // Output:
            // Monday, Wednesday, Saturday // if not flag 37
            return Task.CompletedTask;
        }
    }
}
