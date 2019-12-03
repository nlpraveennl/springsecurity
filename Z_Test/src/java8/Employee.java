package java8;

public class Employee
{

	private int		id;
	private String	name;
	private double	sal;
	private int		roleId;
	private boolean	enabled;

	public Employee(int id, String name, double sal, int roleId, boolean enabled)
	{
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.roleId = roleId;
		this.enabled = enabled;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getSal()
	{
		return sal;
	}

	public void setSal(double sal)
	{
		this.sal = sal;
	}

	
	public int getRole()
	{
		return roleId;
	}

	
	public void setRole(int roleId)
	{
		this.roleId = roleId;
	}

	
	public boolean isEnabled()
	{
		return enabled;
	}

	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sal=");
		builder.append(sal);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}
}
