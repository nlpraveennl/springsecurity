package com.pvn.mvctiles.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userdetails")
public class UserDetails implements Serializable
{
	private static final long serialVersionUID = -8203064310467718429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;
	private String	firstName;
	private String	lastName;
	private String	userName;
	private String	password;
	private String	email;
	private String	gender;
	private boolean	enabled;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", updatable = false)
	private List<UserRoleMapping> roleList;
	
	@Transient
	private List<Integer> selectedRoles = new ArrayList<>();
	
	@Transient
	private String previousMappings;
	
	public UserDetails()
	{}
	
	public UserDetails(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
	}


	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public List<UserRoleMapping> getRoleList()
	{
		return roleList;
	}
	
	public void setRoleList(List<UserRoleMapping> roleList)
	{
		this.roleList = roleList;
	}
	
	public List<Integer> getSelectedRoles()
	{
		return selectedRoles;
	}
	
	public void setSelectedRoles(List<Integer> selectedRoles)
	{
		this.selectedRoles = selectedRoles;
	}
	
	public String getPreviousMappings()
	{
		return previousMappings;
	}
	
	public void setPreviousMappings(String previousMappings)
	{
		this.previousMappings = previousMappings;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("UserDetails [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", gender=");
		builder.append(gender);
		builder.append("]");
		return builder.toString();
	}
}
