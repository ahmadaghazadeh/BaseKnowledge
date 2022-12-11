public class Sample extends MainClass {
    public static void main(String[] args) {
        System.out.println(message());
    }

      String message(){
            return "World!";
       }
}
class MainClass {
      final String message(){
          return "Hello!";
       }
     }
