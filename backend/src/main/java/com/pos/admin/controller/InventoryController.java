package com.pos.admin.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.pos.admin.entity.Inventory;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
import com.pos.admin.service.InventoryService;
import com.pos.admin.util.pdfreport.PDFGenerator;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/inventory")
	public ResponseEntity<List<Inventory>> getAllInventory(){
		return new ResponseEntity<>(inventoryService.getAllInventory(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/{id}")
	public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id){
		return new ResponseEntity<>(inventoryService.getInventoryById(id),new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping("/product/{id}/vendor/{vid}/inventory")
	public ResponseEntity<String> addInventory(@PathVariable("id") Long productId, @PathVariable("vid") Long vendorId,@RequestBody Inventory inventory){
		
		inventory.setAddedDate();
		return new ResponseEntity<>(inventoryService.addInventory(productId, vendorId, inventory),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/name/{name}")
	public ResponseEntity<List<Inventory>> getInventoryByName(@PathVariable("name") String name) {
		
		return new ResponseEntity<>(inventoryService.getInventoryByName(name),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/quantity/{quant}")
	public ResponseEntity<List<Inventory>> getInventoryByQuantity(@PathVariable("quant") Integer quantity) {
		
		return new ResponseEntity<>(inventoryService.getInventoryByQuantity(quantity),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/price/{start}/{end}")
	public ResponseEntity<List<Inventory>> getInventoryByPrice(@PathVariable("start") Double start, @PathVariable("end") Double end) {
		
		return new ResponseEntity<>(inventoryService.getInventoryByPrice(start, end),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/date/{start}/{end}")
	public ResponseEntity<List<Inventory>> getInventoryByDate(@PathVariable("start") String start, @PathVariable("end") String end) {
		
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return new ResponseEntity<>(inventoryService.getInventoryByDate(startDate, endDate),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/lowprice")
	public ResponseEntity<List<Inventory>> getInventoryByLowPrice() {
		
		return new ResponseEntity<>(inventoryService.getInventoryFromLowPrice(),new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/inventory/highprice")
	public ResponseEntity<List<Inventory>> getInventoryByHighPrice() {
		
		return new ResponseEntity<>(inventoryService.getInventoryFromHighPrice(),new HttpHeaders(),HttpStatus.OK);
	}
	
	 @GetMapping(value = "/inventory/pdf",
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> inventoryReport()  {
	        List<Inventory> inventories =  inventoryService.getAllInventory();

	        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(inventories);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=inventory.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }
	
	@PutMapping("/inventory/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id,@RequestBody Inventory inventory){
		
		return new ResponseEntity<>(inventoryService.updateInventory(id, inventory),new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
		
		return new ResponseEntity<>(inventoryService.deleteInventory(id),new HttpHeaders(),HttpStatus.OK);
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
