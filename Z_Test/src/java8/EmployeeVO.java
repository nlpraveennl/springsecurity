package java8;

public class EmployeeVO
{

	private int		id;
	private String	name;
	private double	sal;
	private String	role;
	private boolean	enabled;

	public EmployeeVO(int id, String name, double sal, String role, boolean enabled)
	{
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.role = role;
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

	
	public String getRole()
	{
		return role;
	}

	
	public void setRole(String role)
	{
		this.role = role;
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
		builder.append(", role=");
		builder.append(role);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}
}
