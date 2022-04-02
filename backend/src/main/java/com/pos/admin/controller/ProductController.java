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

import com.pos.admin.dto.ProductListDto;
import com.pos.admin.entity.Product;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
import com.pos.admin.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	public ResponseEntity<ProductListDto> getAllProduct(){
		ProductListDto productList=new ProductListDto();
		productList.setProductList(productService.getAllProduct());
		return new ResponseEntity<>(productList,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProduct(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/products/{category-id}")
	public ResponseEntity<ProductListDto> getCategoryProducts(@PathVariable("category-id")Long categoryId){
		ProductListDto productList=new ProductListDto();
		productList.setProductList(productService.getCategoryProducts(categoryId));
		return new ResponseEntity<ProductListDto>(productList,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		return new ResponseEntity<>(productService.getProductById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/product")
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		
		return new ResponseEntity<>(productService.addProduct(product),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("product/{pid}")
	public ResponseEntity<String> updateEmployee(@PathVariable("pid") Long pid,@RequestBody Product product){
		
		return new ResponseEntity<>(productService.updateProduct(pid, product),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("product/{pid}/category/{cid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("pid") Long pid,@PathVariable("cid") Long cid){
		
		return new ResponseEntity<>(productService.deleteProduct(pid, cid),new HttpHeaders(),HttpStatus.OK);
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
