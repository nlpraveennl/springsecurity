package java8.stramsAPI.stream.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8.Employee;
import java8.Role;

public class Test2
{
	public static void main(String[] args)
	{
		Employee[] arrayOfEmps = {
			    new Employee(1, "Donald Trumph", 100.0, 1 , true), 
			    new Employee(2, "Bill Gates", 135.0, 2, true), 
			    new Employee(3, "Mark Zuckerberg", 300.0, 2, true),
			    new Employee(1, "Nivedita", 145.0, 3, true), 
			    new Employee(2, "Vedantha", 210.0, 3, false), 
			    new Employee(3, "Prvaeen", 245.0, 3, false)
		};
		
		List<Role> roleList = new ArrayList<>();
		roleList.add(new Role(1, "PRESIDENT"));
		roleList.add(new Role(2, "CEO"));
		roleList.add(new Role(3, "EMPLOYEE"));
		

		//Collect all emp name list  in uppercase
		Stream<Employee> empStream = Stream.of(arrayOfEmps);
		List<String> collNames = empStream.map( x -> x.getName().toUpperCase()).collect(Collectors.toList());
		collNames.forEach( name -> System.out.println(name));

		//Collect all Ids
		Stream<Employee> empStream1 = Stream.of(arrayOfEmps);
		List<Integer> collIds = empStream1.map( x -> x.getId()).collect(Collectors.toList());
		collIds.forEach( id -> System.out.println(id));
		
		//Collect all emp name list
		Stream<Employee> empStream2 = Stream.of(arrayOfEmps);
		List<String> collNames2 = empStream2.map( x -> x.getName()).collect(Collectors.toList());
		collNames2.forEach( name -> System.out.println(name));
		
		//Collect all emp name list
		Stream<Employee> empStream3 = Stream.of(arrayOfEmps);
		List<Employee> collEmps = empStream3.filter(x -> x.getSal() > 200).map(x -> {
			x.setName(x.getName().toUpperCase());
			return x;
		}).collect(Collectors.toList());
		collEmps.forEach( emp -> System.out.println(emp));
		
		//Create Employee
		
	}
}
