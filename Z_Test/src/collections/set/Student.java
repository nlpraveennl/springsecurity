package collections.set;


public class Student implements Comparable<Student>
{
	private String name;
	private Integer age;
	
	public Student(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public int hashCode()
	{
		return this.age.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return this.age.equals(((Student)obj).getAge());
	}

	@Override
	public int compareTo(Student o)
	{
		return this.name.hashCode() - o.name.hashCode();
	}

	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getAge()
	{
		return age;
	}
	
	public void setAge(Integer age)
	{
		this.age = age;
	}
}
