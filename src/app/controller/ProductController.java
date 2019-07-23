package app.controller;

import java.util.ArrayList;

import app.model.Product;
import app.repository.ProductRepository;

public class ProductController
{
	public static ArrayList<Product> getAllProducts()
	{
		return ProductRepository.getAllProducts();
	}
}
