package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Product;

public class ProductRepository extends Repository<Product>
{
	public final static int ITEM_PER_PAGE = 25;

	public static ArrayList<Product> getProductsPerPage(int page)
	{
		String query = String.format("SELECT * FROM Product LIMIT %d,%d",
				(page - 1) * ITEM_PER_PAGE, ITEM_PER_PAGE);
		
		ResultSet result = Repository.executeQuery(query);

		return Repository.toModel(Product.class, result);
	}

	public static int getTotalProduct()
	{
		String query = String.format("SELECT COUNT(*) FROM Product");
		
		ResultSet result = Repository.executeQuery(query);
		
		try
		{
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return -1;
	}

	public static Integer add(Product product)
	{
		String query = String.format("INSERT INTO Product (name, stock, price) VALUES(?, ?, ?)");

		ResultSet generatedKeys = ProductRepository.executeUpdate(true, query, product.getName(),
				String.valueOf(product.getStock()), String.valueOf(product.getPrice()));

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
