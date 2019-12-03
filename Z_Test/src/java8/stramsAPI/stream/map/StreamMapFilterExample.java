package java8.stramsAPI.stream.map;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8.Employee;

public class StreamMapFilterExample
{
	public static void main(String[] args)
	{
		Employee[] arrayOfEmps = {
			    new Employee(1, "Donald Trumph", 100.0, 1 , true), 
			    new Employee(2, "Bill Gates", 135.0, 2, true), 
			    new Employee(3, "Mark Zuckerberg", 300.0, 2, true),
			    new Employee(4, "Nivedita", 145.0, 3, true), 
			    new Employee(5, "Vedantha", 210.0, 3, false), 
			    new Employee(6, "Prvaeen", 245.0, 3, false)
		};

		//Collect all emp name list(name in uppercase) whose salary is greater than 200
		System.out.println("-----Employees with sal>200----------");
		Stream<Employee> empStream1 = Stream.of(arrayOfEmps);
		List<Employee> collEmps1 = empStream1.map(x -> {
			x.setName(x.getName().toUpperCase());
			return x;
		}).filter(x -> x.getSal() > 200).collect(Collectors.toList());
		collEmps1.forEach( emp -> System.out.println(emp));
		
		//collect all enabled employees
		System.out.println("-----Enabled Employees----------");
		Stream<Employee> empStream2 = Stream.of(arrayOfEmps);
		List<Employee> collEmps2 = empStream2
			.filter(x -> x.isEnabled()).collect(Collectors.toList());
		collEmps2.forEach( emp -> System.out.println(emp));
		
	}
}
