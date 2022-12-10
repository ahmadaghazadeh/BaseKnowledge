public class ExceptionSample {
    public static void main(String[] args) {
        System.out.println(print(1));
        Math.abs(0);
        //System.out.println(print1(1));
    }

    static Exception print(int i){
        if (i>0) {
            return new Exception();
        } else {
            throw new RuntimeException();
        }
    }

    static  void print1(int i) throws Exception {
        if (i>0) {
            throw new Exception();
        } else {
            throw new RuntimeException();
        }
    }
}
