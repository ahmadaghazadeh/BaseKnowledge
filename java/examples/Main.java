import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Main {
 
private static volatile int MY_INT = 0;

public static void main(String[] args)
{
	PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(4);
		queue.add(3);
		queue.add(2);
		queue.add(1);

		while (queue.isEmpty() == false) {
			System.out.println( queue.remove());
		}
 }
}
 