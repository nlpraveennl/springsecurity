package java8.stramsAPI.stream.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java8.Employee;
import java8.EmployeeVO;
import java8.Role;

public class StreamMapNewObjectExample
{
	public static void main(String[] args)
	{
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, "Donald Trumph", 100.0, 1 , true));
		empList.add(new Employee(2, "Bill Gates", 135.0, 2, true));
		empList.add(new Employee(3, "Mark Zuckerberg", 300.0, 2, true));
		empList.add(new Employee(4, "Nivedita", 145.0, 3, true));
		empList.add(new Employee(5, "Vedantha", 210.0, 3, false));
		empList.add(new Employee(6, "Prvaeen", 245.0, 3, false));
		
		List<Role> roleList = new ArrayList<>();
		roleList.add(new Role(1, "PRESIDENT"));
		roleList.add(new Role(2, "CEO"));
		roleList.add(new Role(3, "EMPLOYEE"));
		
		Map<Integer, String> roleMap = roleList.stream().collect(Collectors.toMap(Role::getId, Role::getName));
		
		roleMap.forEach((k,v) -> {
			System.out.println("Key:"+ k +", value:"+v);
		});
		

		//Collect all emp name list(name in uppercase) whose salary is greater than 200
		System.out.println("-----Employees with sal>200----------");
		List<EmployeeVO> collEmps1 = empList.stream().map(x -> {
			EmployeeVO empVO = new EmployeeVO(x.getId(), x.getName(), x.getSal(), roleMap.get(x.getRole()), x.isEnabled());
			return empVO;
		}).collect(Collectors.toList());
		collEmps1.forEach( emp -> System.out.println(emp));
		
	}
}
