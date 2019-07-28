package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.IntStream;

import app.model.Product;

public class ProductRepository extends Repository<Product>
{
	public final static int ITEM_PER_PAGE = 25;

	private final static ArrayList<Product> products = new ArrayList<Product>();

	public static ArrayList<Product> getAllProducts()
	{
		if (products.size() < 1)
			products.addAll(getProductsPerPage(1));

		return products;
	}

	public static ArrayList<Product> getProductsPerPage(int page)
	{
		String query = String.format("SELECT * FROM Product LIMIT %d,%d", (page - 1) * ITEM_PER_PAGE, ITEM_PER_PAGE);
		ResultSet result = Repository.executeQuery(query);

		ArrayList<Product> loadedProducts = Repository.toModel(Product.class, result);
		products.addAll(loadedProducts);

		return loadedProducts;
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
				int generatedId = generatedKeys.getInt(1);

				product.setId(generatedId);

				products.add(product);

				return generatedId;
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
		int updatedProductIndex = IntStream.range(0, products.size())
				.filter(x -> Integer.valueOf(x).equals(products.get(x).getId())).findFirst().getAsInt();

		products.set(updatedProductIndex, product);

		String query = String.format("UPDATE Product SET name=?, stock=?, price=? WHERE id=?");
		ProductRepository.executeUpdate(query, product.getName(), String.valueOf(product.getStock()),
				String.valueOf(product.getPrice()), id + "");
	}

	public static void delete(int id)
	{
		Product deletedProduct = products.stream()
				.filter(x -> x.getId() == id)
				.findAny()
				.orElse(null);

		if (deletedProduct != null)
			products.remove(deletedProduct);
		
		String query = String.format("DELETE FROM Product WHERE ID=?");
		ProductRepository.executeUpdate(query, id + "");
	}
}
