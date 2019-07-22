package app.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.model.Product;

public class ProductRepository extends Repository<Product>
{
	public static ArrayList<Product> getAllProducts()
	{
		ResultSet result = Repository.getAll("product");
		return Repository.toModel(Product.class, result);
	}
}
