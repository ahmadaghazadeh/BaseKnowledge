import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

class Main {
 
private static volatile int MY_INT = 0;

public static void main(String[] args)
{
	TreeSet<Integer> tree=new TreeMap<>();
	tree.add(10);
	tree.add(8);
	tree.add(11);
	tree.add(18);
	for (Integer integer : tree) {
		System.out.println(integer);	
	}
 }
}
 