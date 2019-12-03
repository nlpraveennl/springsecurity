package java8.stramsAPI.stream.map;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8.Employee;

public class StreamMapExample
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

		//Collect all Ids
		Stream<Employee> empStream1 = Stream.of(arrayOfEmps);
		List<Integer> collIds1 = empStream1.map( x -> x.getId()).collect(Collectors.toList());
		collIds1.forEach( id -> System.out.print("id: "+ id + ", "));
		
		System.out.println("\n-----DoubleColon Operator(::)--------");
		//Collect all Ids
		Stream<Employee> empStream2 = Stream.of(arrayOfEmps);
		List<Integer> collIds2 = empStream2.map(Employee::getId).collect(Collectors.toList());
		collIds2.forEach(System.out::print);

		System.out.println("\n------ Map with lambda expression -------");
		//Collect all emp name list  in uppercase
		Stream<Employee> empStream3 = Stream.of(arrayOfEmps);
		List<String> collNames1 = empStream3.map( x -> x.getName().toUpperCase()).collect(Collectors.toList());
		collNames1.forEach(System.out::println);

		System.out.println("-------Map with method referance operator------");
		//Collect all emp name list
		Stream<Employee> empStream4 = Stream.of(arrayOfEmps);
		List<String> collNames2 = empStream4.map(Employee::getName).collect(Collectors.toList());
		collNames2.forEach(System.out::println);
		
	}
}
