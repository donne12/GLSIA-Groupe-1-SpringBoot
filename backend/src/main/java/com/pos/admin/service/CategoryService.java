package com.pos.admin.service;

import java.util.List;

import com.pos.admin.entity.Category;


public interface CategoryService {
	
	public String deleteCategory(Long id);
	
	public String updateCategory(Long id, Category category);
	
	public String addCategory(Category category);
	
	public Category getCategorynyId(Long id);
	
	public List<Category> getAllCategory();

}
