import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Main {
 
private static volatile int MY_INT = 0;

public static void main(String[] args)
	{
		List<String> list1 = new ArrayList<>();
		list1.add("One");
		list1.add("Two");
		list1.add("Three");

		List<String> list2 = new ArrayList<>();
		list2.add("Two");

		list1.remove("Two");
		System.out.println(list1); 
	}
}
 