import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.function.Function;

class Main {
 
private static volatile int MY_INT = 0;

public static void main(String[] args)
	{
		Function<Integer, Double> half = a -> a / 2.0;
 
        // However treble the value given to half function
        half = half.compose(a -> 3 * a);

		half = half.andThen(a -> a+1);
 
        // Applying the function to get the result
        System.out.println(half.apply(5));
	}
}
 