package com.pos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.admin.dao.CategoryDao;
import com.pos.admin.entity.Category;
import com.pos.admin.exception.IdNotFoundException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	static final String ID_NOT_FOUND="Category not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Category...";

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public String deleteCategory(Long id) {
		
		return categoryDao.findById(id).map(category -> {
                	categoryDao.delete(category);
                    return "Category with id: "+id+" deleted Successfully!";
                }).orElseThrow(() -> new IdNotFoundException("Sorry,  Couldn't delete Category..."+ID_NOT_FOUND+ id));
		
	}
	
	@Override
	public String updateCategory(Long id, Category categoryUpdated) {
		
		return categoryDao.findById(id).map(category ->
		                                      {category.setName(categoryUpdated.getName());
		                                      categoryDao.save(category);
		                                      return "Category updated successfully";
		                                      
		                                      }).orElseThrow(() -> new IdNotFoundException("Sorry,  Couldn't update Category..."+ID_NOT_FOUND+ id));
		                                      
		
		
	}

	
	
	
	@Override
	public String addCategory(Category categoryAdd) {
		categoryDao.save(categoryAdd);
		return "Category added successfully.....";
		
	}
	
	@Override
	public Category getCategorynyId(Long id) {
		Optional<Category> optCategory = categoryDao.findById(id);
		
		if(optCategory.isPresent()) {
			return optCategory.get();
		}else {
    		throw new IdNotFoundException("Sorry, Category ould not be retrived" + ID_NOT_FOUND+ id);
    	}
	}
	
	@Override
	public List<Category> getAllCategory(){
		return categoryDao.findAll();
	}
	
}

