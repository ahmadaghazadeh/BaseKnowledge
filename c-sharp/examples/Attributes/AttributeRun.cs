using static System.Net.Mime.MediaTypeNames;
using System.Reflection;
using System.ComponentModel.DataAnnotations;

namespace app.Attributes
{
    public class AttributeRun : RunAbstract
    {
        public override Task RunAsync()
        {
            WriteLine("AttributeRun1");
            Attributes1.Run();

            WriteLine("AttributeRun2");
            Attributes2.Run();

            AttributeDisplay(typeof(Student));

            return Task.CompletedTask;
        }
        public static void AttributeDisplay(Type classType)
        {
            Console.WriteLine("Methods of class {0}", classType.Name);

            // Array to store all methods of a class
            // to which the attribute may be applied

            PropertyInfo[] properties = classType.GetProperties();

            // for loop to read through all methods

            for (int i = 0; i < properties.GetLength(0); i++)
            {

                // Creating object array to receive 
                // method attributes returned
                // by the GetCustomAttributes method

                object[] attributesArray = properties[i].GetCustomAttributes(true);

                // foreach loop to read through 
                // all attributes of the method
                foreach (Attribute item in attributesArray)
                {
                    if (item is DisplayAttribute)
                    {

                        // Display the fields of the NewAttribute
                        DisplayAttribute attributeObject = (DisplayAttribute)item;
                        Console.WriteLine("{0} - {1}, {2} ", properties[i].Name,
                         attributeObject.Title, attributeObject.Description);
                    }
                }
            }
        }
    }
}
