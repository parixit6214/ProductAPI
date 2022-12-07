package com.abc.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abc.api.entity.Product;
import com.abc.api.exception.ProductAlreadyExistException;
import com.abc.api.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product/saveproduct")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean isAdded = productService.saveProduct(product);

		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			throw new ProductAlreadyExistException("product alreday exist"+product.getProductId());
		}

	}

	@GetMapping("/product/getproductbyid/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable int productId) {

		Product product = productService.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/product/deleteproduct")
	public ResponseEntity<Boolean> deleteProduct(@RequestParam int productid) {
		boolean isDeleted = productService.deleteProduct(productid);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/product/updateproduct")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean isUpdated = productService.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product/getallproduct")
	public ResponseEntity<List> getAllProduct() {
		List<Product> list = productService.getAllProduct();
		if (!list.isEmpty()) {
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List>(list, HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping("/product/sortproductbyname")
	public ResponseEntity<List> sortProductByName(){
		List<Product> list = productService.sortProductByName();
		if(!list.isEmpty()) {
			return new ResponseEntity<List>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List>(list, HttpStatus.OK);
		}
				
	}
	@GetMapping("/product/getmaxpriceproduct")
	public ResponseEntity<List> getMaxPriceProduct(){
		
		List<Product> maxPriceProduct = productService.getMaxPriceProduct();
		
			return new ResponseEntity<List>(maxPriceProduct, HttpStatus.OK);
		
	}
	@GetMapping("/product/gettotalcountofproduct")
	public ResponseEntity<Integer> getTotalCountOfProduct() {
		int totalCountOfProduct = productService.getTotalCountOfProduct();
		return new ResponseEntity<Integer>(totalCountOfProduct, HttpStatus.OK);
		
	}
	@PostMapping("/product/uploadsheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file){
		
		System.out.println(file.getOriginalFilename());
		return null;
		
	}
}
