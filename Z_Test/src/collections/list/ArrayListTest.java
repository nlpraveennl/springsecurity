package collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListTest
{
	public static void main(String[] args)
	{
		List<String> list= new ArrayList<>();
		list.add("zero");
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		list.add("six");
		list.add("seven");
		list.add("eight");
		list.add("nine");
		list.add("ten");
		
		list.add(5, "five-new");
		
		list.forEach(string -> System.out.println(string));
		
		Collections.rotate(list, 2);
		
		System.out.println(list);
	}
}
