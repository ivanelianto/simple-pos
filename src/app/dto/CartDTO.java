package app.dto;

public class CartDTO
{
	public static final int ID_INDEX = 0;
	public static final int NAME_INDEX = 1;
	public static final int QUANTITY_INDEX = 2;
	public static final int PRICE_INDEX = 3;
	public static final int SUBTOTAL_INDEX = 4;
	
	private int id;

	private String name;

	private int quantity;

	private double price;
	
	public CartDTO(){}

	public CartDTO(int id, String name, int quantity, double price)
	{
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
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

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
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
