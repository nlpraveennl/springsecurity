package collections.set;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest 
{
	public static void main(String[] args) 
	{
		TreeSet<Student> set2 = new TreeSet<>();
		set2.add(new Student("Amith1", 10));
		set2.add(new Student("Amith3", 20));
		set2.add(new Student("Amith2", 30));
		
		Comparator<Student> ageComp = (o1, o2) ->  {
			return o1.getAge() - o2.getAge();
		};

//		Set<Student> set3 = new TreeSet<Student>(ageComp);
		Set<Student> set3 = new HashSet<Student>();
		set3.add(new Student("Amith1", 10));
		set3.add(new Student("Amith2", 20));
		set3.add(new Student("Amith3", 30));
		set3.add(new Student("Amith4", 10));
		set3.add(new Student("Amith5", 20));
		set3.add(new Student("Amith6", 30));
		set3.add(new Student("Amith7", 10));
		set3.add(new Student("Amith8", 20));
		set3.add(new Student("Amith9", 30));
		
		
		set3.forEach(stud -> {
			System.out.println(stud.getName());
		});
		
		SortedSet<String> set = new TreeSet<>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("9");
		set.add("8");
		set.add("6");
		set.add("7");
		set.add("5");
		set.add("4");
		
		
		
		System.out.println(set.first());
		System.out.println(set.last());
		
		System.out.println(set.headSet("5"));
		System.out.println(set.tailSet("5"));
		
		NavigableSet navigableSet = ((NavigableSet)set);
		System.out.println(navigableSet.ceiling("10"));
		System.out.println(navigableSet);
		
//		Iterator itr = ((NavigableSet)set).descendingIterator();
//		while(itr.hasNext())
//		{
//			System.out.println(itr.next());
//		}
	}
}
