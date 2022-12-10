public class Hello {
    public static void main(String[] args) {
        Interface1.print(); Interface2.print();
    }

    interface Interface1 {
        static void print() {
            System.out.print("Hello");
        }
    }
    
    interface Interface2 {
        static void print() {
            System.out.print("World!");
        }
    }
}
