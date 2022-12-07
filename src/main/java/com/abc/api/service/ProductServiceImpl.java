package com.abc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.api.dao.ProductDao;
import com.abc.api.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded = productDao.saveProduct(product);
		
		return isAdded;
	}

	@Override
	public Product getProductById(int productId) {
		Product product = productDao.getProductById(productId);
		return product;
	}

	@Override
	public boolean deleteProduct(int productId) {
		boolean isDeleted = productDao.deleteProduct(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated = productDao.updateProduct(product);
		return isUpdated;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productDao.getAllProduct();
		return list;
	}

	@Override
	public List<Product> sortProductByName() {
		List<Product> list = productDao.sortProductByName();
		return list;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		List<Product> list = productDao.getMaxPriceProduct();
		return list;
	}

	@Override
	public int getTotalCountOfProduct() {
		int totalCountOfProduct = productDao.getTotalCountOfProduct();
		return totalCountOfProduct;
	}

}
