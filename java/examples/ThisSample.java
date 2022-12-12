
public class ThisSample  {
    public static void main(String[] args) {
       
      Illustration obj = new Illustration();
      obj.Scaler();
      obj.name();

      System.out.println("Object  ......... 100 .........." );
      Illustration obj100 = new Illustration(100);
      obj100.Scaler();
      obj100.name();
      obj100.invoke();
      (new Illustration()).getIllustration().name();
    }

    
}

class Illustration {

  // declaring an instance variable
  int instanceVar = 5;

  // declaring an static variable
  static int staticVar = 10;

  Illustration() {
    // invoking parameterized constructor
    this(10);
  }

  Illustration(int x) {
    System.out.println("Current class parameterized constructor invoked.");
    System.out.println("Number is : " + x);
  }

  void print(Illustration ob) {
    System.out.println("ob.value = " + ob.instanceVar);
  }

  void invoke() {
    // print method is invoked by passing this as an argument
    print(this);
  }


  Illustration getIllustration() {
    // returing the instance of current class
    return this;
  }

  void Scaler() {
    // Method-specific variables
    int instanceVar = 20;
    int staticVar = 40;
      
    // referring to the current class instance and static variables
    this.instanceVar = 50;
    this.staticVar = 100;

    // printing the current class instance and static variable.
    System.out.println("Value of instance variable : " + this.instanceVar);
    System.out.println("Value of static variable : " + this.staticVar);
      
    // printing the method specific variables.
    System.out.println("instanceVar inside method : " + instanceVar);
    System.out.println("staticVar inside method: " + staticVar);
  }

  void name() {
    // invoking current class scaler method.
    System.out.println("Call ......... name method" );
    this.Scaler();
    System.out.println("Ahmad Aghazadeh.");
  }
}

