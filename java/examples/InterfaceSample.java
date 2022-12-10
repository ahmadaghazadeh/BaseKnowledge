public class InterfaceSample {
     
    interface One {
        default void method() {
            System.out.println("One");
        }
    }
    
    interface Two {
        default void method () {
            System.out.println("One");
        }
    }
    class Three implements One, Two {

        public void method() {
            One.super.method();
            Two.super.method();
        }
    }
}
