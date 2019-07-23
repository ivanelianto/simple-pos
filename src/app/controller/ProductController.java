package app.controller;

import java.util.ArrayList;

import app.model.Product;
import app.model.User;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import util.Hasher;

public class ProductController
{
	public static ArrayList<Product> getAllProducts()
	{
		return ProductRepository.getAllProducts();
	}
	
	public static void add(String name, int stock, double price)
	{
		Product product = new Product();
		product.setName(name);
		product.setStock(stock);
		product.setPrice(price);
		ProductRepository.add(product);
	}
	
	public static void update(int id, String name, int stock, double price)
	{
		Product product = new Product();
		product.setName(name);
		product.setStock(stock);
		product.setPrice(price);
		ProductRepository.update(id, product);
	}
	
	public static void delete(int id)
	{
		ProductRepository.delete(id);
	}
}
