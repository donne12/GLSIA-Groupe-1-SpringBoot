package com.pos.admin.service;

import java.util.List;

import com.pos.admin.entity.Product;

public interface ProductService {

	public String deleteProduct(Long id,Long categoryId);

	public String updateProduct(Long id, Product productUpdated);

	public String addProduct(Product product);

	public Product getProductById(Long id);

	public List<Product> getAllProduct();
	
	public List<Product> getCategoryProducts(Long categoryId);
	
}
