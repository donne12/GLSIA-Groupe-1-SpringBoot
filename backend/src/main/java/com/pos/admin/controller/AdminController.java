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

import com.pos.admin.entity.Admin;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
import com.pos.admin.service.AdminService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<Admin>> getAllAdmin(){
  //FOR TESTING PURPOSE
		return new ResponseEntity<>(adminService.getAllAdmin(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
		
		return new ResponseEntity<>(adminService.getAdminById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@PostMapping("/admin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin){
		
		return new ResponseEntity<>(adminService.addAdmin(admin),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody Admin admin){
		
		return new ResponseEntity<>(adminService.updateAdmin(id,admin),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Long id){
		
		return new ResponseEntity<>(adminService.deleteAdmin(id),new HttpHeaders(),HttpStatus.OK);
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
