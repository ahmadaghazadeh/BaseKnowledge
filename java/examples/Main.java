import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Main {
 

public static void main(String[] args)
	{
		Formatter data = new Formatter();
        data.format("course %s", "java ");
        System.out.println(data);
        data.format("tutorial %s", "Merit campus");
        System.out.println(data);
	}
}
 