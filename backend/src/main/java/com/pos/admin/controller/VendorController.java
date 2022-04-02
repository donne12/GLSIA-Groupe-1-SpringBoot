package com.pos.admin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.pos.admin.service.VendorService;

import com.pos.admin.entity.Vendor;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;
	
	@GetMapping("/vendor")
	public ResponseEntity<List<Vendor>> getAllVendor(){
  
		return new ResponseEntity<>(vendorService.getAllVendor(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/vendor/{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable Long id){
		
		return new ResponseEntity<>(vendorService.getVendorById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@PostMapping("/vendor")
	public ResponseEntity<String> addVendor(@RequestBody Vendor vendor){
		
		return new ResponseEntity<>(vendorService.addVendor(vendor),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping("/vendor/{id}")
	public ResponseEntity<String> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor){
		
		return new ResponseEntity<>(vendorService.updateVendor(id,vendor),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/vendor/{id}")
	public ResponseEntity<String> deleteVendor(@PathVariable Long id){
		
		return new ResponseEntity<>(vendorService.deleteVendor(id),new HttpHeaders(),HttpStatus.OK);
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
