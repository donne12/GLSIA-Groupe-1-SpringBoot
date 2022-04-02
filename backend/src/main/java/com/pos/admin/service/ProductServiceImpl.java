package com.pos.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.admin.dao.CategoryDao;
import com.pos.admin.dao.ProductDao;
import com.pos.admin.entity.Category;
import com.pos.admin.entity.Product;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	static final String ID_NOT_FOUND="Product not found with id ";
	static final String CATEGORY_NOT_FOUND="Category not found with id";
	static final String COULDNT_UPDATE="Couldn't update Product...";
	
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@Override
	public String deleteProduct(Long id,Long categoryId) {
		
		if(!categoryDao.existsById(categoryId)) {
    		throw new IdNotFoundException(CATEGORY_NOT_FOUND);
    	}
	
		return productDao.findById(id)
                .map(product -> {
                	productDao.delete(product);
                    return "Product with id: "+id+" deleted Successfully!";
                }).orElseThrow(() -> new IdNotFoundException("Couldn't delete Product..."+ID_NOT_FOUND+ id));

	}

	@Override
	public String updateProduct(Long id, Product productUpdated) {
		
		if(!categoryDao.existsById(productUpdated.getCategory().getId())) {
    		throw new IdNotFoundException(CATEGORY_NOT_FOUND);
    	}
	
		return productDao.findById(id)
				.map(product -> {
					product.setName(productUpdated.getName());
					product.setImage(productUpdated.getImage());
					product.setDescription(productUpdated.getDescription());
					product.setBrand(productUpdated.getBrand());
					product.setCategory(productUpdated.getCategory());
					productDao.save(product);
					return "Product updated successfully!";
				}).orElseThrow(()-> new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+ id));
					
					
		
		
		
	}

	@Override
	public String addProduct(Product product) {
		
		if(!categoryDao.existsById(product.getCategory().getId())) {
    		throw new IdNotFoundException(CATEGORY_NOT_FOUND);
    	}
		
		
		return categoryDao.findById(product.getCategory().getId()).map(category->{
			product.setCategory(product.getCategory());
			productDao.save(product);
			return "Product added successfully!";
		}).orElseThrow(()->new DuplicateIdException("Couldn't add Product.."));
				
		 
	}

	@Override
	public Product getProductById(Long id) {
		Optional<Product> optProduct = productDao.findById(id);
		  if(optProduct.isPresent()) {
	    		return optProduct.get();
	    	}else {
	    		throw new IdNotFoundException(ID_NOT_FOUND+ id);
	    	}
		
		
	}

	@Override
	public List<Product> getAllProduct() {
		return productDao.findAll();
	}

	@Override
	public List<Product> getCategoryProducts(Long categoryId) {
		Optional<Category> category=categoryDao.findById(categoryId);
		if(category.isPresent())
			return new ArrayList<>(category.get().getProduct());
		else
			throw new IdNotFoundException(ID_NOT_FOUND+ categoryId);
	}
	
}
