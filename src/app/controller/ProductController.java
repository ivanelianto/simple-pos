package app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import app.model.Product;
import app.repository.ProductRepository;
import util.FileHelper;

public class ProductController
{
	private static ArrayList<Product> products;

	private static boolean isChanged = false;

	public static ArrayList<Product> getAllProducts()
	{
		if (products == null || isChanged)
		{
			products = ProductRepository.getAllProducts();
			isChanged = false;
		}

		return products;
	}

	public static Product getProductByID(int id)
	{
		for (Product product : getAllProducts())
		{
			if (product.getId() == id)
				return product;
		}

		return null;
	}

	public static void add(String name, int stock, double price)
	{
		Product product = new Product();
		product.setName(name);
		product.setStock(stock);
		product.setPrice(price);
		int generatedId = ProductRepository.add(product);
		
		String data = String.format("%d#%s#%d#%f", generatedId, name, 1, price);
		
		FileHelper.writeFile(FileHelper.getProductsPath(), data, true);
		
		isChanged = true;
	}

	public static void update(int id, String name, int stock, double price)
	{
		Product product = new Product();
		product.setName(name);
		product.setStock(stock);
		product.setPrice(price);
		ProductRepository.update(id, product);
		isChanged = true;
	}

	public static void delete(int id)
	{
		String fileLocation = String.format("%s/%d.iv", FileHelper.getProductsPath(), id);
		
		try
		{
			Files.deleteIfExists(Paths.get(fileLocation));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		ProductRepository.delete(id);
		isChanged = true;
	}
}
