import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Main {
 
private static volatile int MY_INT = 0;

public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
		for(String value :list) {
			if(value.equals("a")) {
				list.remove(value);
			}
		}
		System.out.println(list); // outputs [b,c]	 
	}
}
 