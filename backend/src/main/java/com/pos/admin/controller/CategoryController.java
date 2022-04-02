package com.pos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.admin.entity.Category;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
import com.pos.admin.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory(){
		return new ResponseEntity<>(categoryService.getAllCategory(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
		return new ResponseEntity<>(categoryService.getCategorynyId(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/category")
	public ResponseEntity<String> addCategory(@RequestBody Category category){
		return new ResponseEntity<>(categoryService.addCategory(category),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category){
		return new ResponseEntity<>(categoryService.updateCategory(id,category),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		
		return new ResponseEntity<>(categoryService.deleteCategory(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
