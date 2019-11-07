import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("e1", "l1"),
                new Person("e2", "l1"),
                new Person("e3", "l2"),
                new Person("e4", "l2")
        );

        List<Employee> employees = persons.stream()
                .filter(p -> p.getLastName().equals("l1"))
                .map(p -> new Employee(p.getName(), p.getLastName(), 1000))
                .collect(Collectors.toList());

        System.out.println(employees);
    }

}

class Person {

    private String name;
    private String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}

	
	public String getLastName()
	{
		return lastName;
	}

	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}

class Employee extends Person {

    private double salary;

    public Employee(String name, String lastName, double salary) {
        super(name, lastName);
        this.salary = salary;
    }

	
	public double getSalary()
	{
		return salary;
	}

	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}


	@Override
	public String toString()
	{
		return "Employee [salary=" + salary + ", getName()=" + getName() + ", getLastName()=" + getLastName() + "]";
	}
}