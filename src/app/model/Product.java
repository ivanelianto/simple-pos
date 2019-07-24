package app.model;

import app.annotation.Table;

public class Product
{
	@Table(columnName = "id")
	private int id;

	@Table(columnName = "name")
	private String name;

	@Table(columnName = "stock")
	private int stock;

	@Table(columnName = "price")
	private double price;

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

	public int getStock()
	{
		return stock;
	}

	public boolean setStock(int stock)
	{
		if (stock < 1)
			return false;

		this.stock = stock;

		return true;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

}
