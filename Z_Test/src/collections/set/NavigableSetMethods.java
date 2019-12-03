package collections.set;

import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetMethods 
{
	public static void main(String[] args) 
	{
		NavigableSet<Integer> set = new TreeSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(9);
		set.add(8);
		set.add(15);
		set.add(14);
		set.add(13);
		set.add(7);
		set.add(5);
		set.add(6);
		set.add(4);
		
		
		
		System.out.println(set.first());
		System.out.println(set.last());
		
		System.out.println(set.headSet(5));
		System.out.println(set.tailSet(5));
		
		System.out.println("ceiling of 12: "+set.ceiling(12));
		System.out.println("ceiling of 16: "+set.ceiling(16));
		System.out.println("floor of 12: "+set.floor(12));
		System.out.println("floor of 0: "+set.floor(0));
		
		System.out.println("ceiling of 13: "+set.ceiling(13));
		System.out.println("floor of 9: "+set.floor(9));
		
		System.out.println("heigher of 13: "+set.higher(13));
		System.out.println("lower of 9: "+set.lower(9));
		
		System.out.println(set);
		System.out.println(set.pollFirst());
		System.out.println(set);
		System.out.println(set.pollLast());
		System.out.println(set);
		
		System.out.print("Polling: "+set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		System.out.print(", " + set.pollFirst());
		
		
//		Iterator itr = ((NavigableSet)set).descendingIterator();
//		while(itr.hasNext())
//		{
//			System.out.println(itr.next());
//		}
	}
}
