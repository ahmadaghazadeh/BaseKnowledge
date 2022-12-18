namespace app.Operator
{
    public class OperatorRun : RunAbstract
    {
        public override Task RunAsync()
        {
            var a = new Fraction(5, 4);
            var b = new Fraction(1, 2);
            Console.WriteLine(-a);   // output: -5 / 4
            Console.WriteLine(a + b);  // output: 14 / 8
            Console.WriteLine(a - b);  // output: 6 / 8
            Console.WriteLine(a * b);  // output: 5 / 8
            Console.WriteLine(a / b);  // output: 10 / 4
            return Task.CompletedTask;
        }
    }
}
