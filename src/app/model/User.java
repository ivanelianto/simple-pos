package app.model;

import app.annotation.Table;

public class User
{
	@Table(columnName = "id")
	private int id;

	@Table(columnName = "name")
	private String name;

	@Table(columnName = "username")
	private String username;

	@Table(columnName = "password")
	private String password;

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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
