
public class SuperSample  {
    public static void main(String[] args) {
       
      Child obj = new Child();
      obj.print();
    }

    
}
 
class Parent {

  int a = 50;
  String s = "Hello World!";
  void display() {
    System.out.println("Hi I am parent method.");
  }
  Parent() {
    System.out.println("Hi I am Parent class constructor.");
  }

}

// child class extending parent class
class Child extends Parent {

  Child() {
    // invoking parent class constructor
    super();
  }
  int a = 100;
  String s = "Happy Coding!";

  void print() {
    // referencing to the instance variable of parent class
    System.out.println("Number from parent class is : " + super.a);
    System.out.println("String from parent class is : " + super.s);

    // printing a and s of the current/child class
    System.out.println("Number from child class is : " + a);
    System.out.println("String from child class is : " + s);
    display();
    super.display();
  }
  void display() {
    System.out.println("Hi I am child method.");
  }
 
}
