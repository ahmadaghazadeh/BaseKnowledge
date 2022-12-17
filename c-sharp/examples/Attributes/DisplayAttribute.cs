
namespace app.Attributes
{

    // InformationAttribute is a custom attribute class
    // that is derived from Attribute class
    [AttributeUsage(AttributeTargets.All)]
    public class DisplayAttribute : Attribute
    {

        public DisplayAttribute(string Title,string Description="")
        {
            this.Title = Title; 
            this.Description = Description;
        }

        public string Title { get;  }
        public string Description { get;  }

    }

    public class Student
    {
        [Display("Id","Student Id")]
        public int Id { get; set; }
        [Display("Name", "Student Name")]
        public string Name { get; set; }
        [Display("Title", "Student Title")]
        public string Title { get; set; }   
        public Student(int Id,string Name, string Title) { 
            this.Id = Id;
            this.Name = Name;
            this.Title = Title;
        }
    }

     
}
