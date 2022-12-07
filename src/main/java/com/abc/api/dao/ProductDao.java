package com.abc.api.dao;

import java.util.List;

import com.abc.api.entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);
	
	public Product getProductById(int productId);
	
	public boolean deleteProduct(int productid);
	
	public boolean updateProduct(Product product);
	
	public List<Product> getAllProduct();
	
	public List<Product> sortProductByName();
	
	public List<Product> getMaxPriceProduct();
	
	public int getTotalCountOfProduct();
}
