import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Sample  {
    public static void main(String[] args) {
      CopyOnWriteArrayList<String> arr = new CopyOnWriteArrayList<String>();

      // Adding elements to synchronized ArrayList
      arr.add("Hello");
      arr.add("World");
      arr.add("in");
      arr.add("Java");
  
      System.out.println("Elements of synchronized ArrayList :");
  
      // Iterating on the synchronized ArrayList using an iterator.
      Iterator<String> it = arr.iterator();
  
      while (it.hasNext()) System.out.println(it.next());
    }

      
}

