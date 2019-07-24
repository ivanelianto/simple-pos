package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Product;

public class ProductRepository extends Repository<Product>
{
	public static ArrayList<Product> getAllProducts()
	{
		ResultSet result = Repository.getAll("product");
		return Repository.toModel(Product.class, result);
	}

	public static Integer add(Product product)
	{
		String query = String.format("INSERT INTO Product (name, stock, price) VALUES(?, ?, ?)");

		ResultSet generatedKeys = ProductRepository.executeUpdate(true, query, product.getName(), String.valueOf(product.getStock()),
				String.valueOf(product.getPrice()));
		
		try
		{
			if (generatedKeys.next())
			{
				return generatedKeys.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public static void update(int id, Product product)
	{
		String query = String.format("UPDATE Product SET name=?, stock=?, price=? WHERE id=?");

		ProductRepository.executeUpdate(query, product.getName(), String.valueOf(product.getStock()),
				String.valueOf(product.getPrice()), id + "");
	}

	public static void delete(int id)
	{
		String query = String.format("DELETE FROM Product WHERE ID=?");

		ProductRepository.executeUpdate(query, id + "");
	}
}
