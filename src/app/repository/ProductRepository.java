package app.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.model.Product;
import app.model.User;

public class ProductRepository extends Repository<Product>
{
	public static ArrayList<Product> getAllProducts()
	{
		ResultSet result = Repository.getAll("product");
		return Repository.toModel(Product.class, result);
	}

	public static void add(Product product) {
		String query = String.format("INSERT INTO Product (name, stock, price) VALUES(?, ?, ?)");
		ProductRepository.executeUpdate(query, 
				product.getName(), 
				String.valueOf(product.getStock()),
				String.valueOf(product.getPrice()));
	}

	public static void update(int id, Product product) {
		String query = String.format("UPDATE Product SET name=?, stock=?, price=? WHERE id=?");

		ProductRepository.executeUpdate(query, 
				product.getName(),  
				String.valueOf(product.getStock()),
				String.valueOf(product.getPrice()), 
				id + "");
	}
	
	public static void delete(int id)
	{
		String query = String.format("DELETE FROM Product WHERE ID=?");
		
		ProductRepository.executeUpdate(query, id + "");
	}
}
