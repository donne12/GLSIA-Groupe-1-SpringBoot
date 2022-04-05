package com.pos.admin.controller;
import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import  com.pos.admin.entity.JWTTokenHelper;
import com.pos.admin.entity.Employee;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
import com.pos.admin.service.EmployeeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class EmployeeController {

	private AuthenticationManager authenticationManager;


	

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		return new ResponseEntity<>(employeeService.getAllEmployee(),new HttpHeaders(),HttpStatus.OK);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		return new ResponseEntity<>(employeeService.getEmployeeById(id),new HttpHeaders(),HttpStatus.OK);
	}


	@PostMapping("/employee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee){

		return new ResponseEntity<>(employeeService.addEmployee(employee),new HttpHeaders(),HttpStatus.CREATED);
	}

	@PostMapping("/employee/login")
	public ResponseEntity<Boolean> employeeLogin(@RequestBody Employee employee){
		return new ResponseEntity<>(employeeService.employeeLogin(employee),new HttpHeaders(),HttpStatus.OK);
	}

	@PostMapping("/employee/loginAdmin")
	public ResponseEntity employeeLoginAdmin(@RequestBody Employee employee) throws InvalidKeySpecException, NoSuchAlgorithmException {
		JWTTokenHelper jWTTokenHelper = new JWTTokenHelper();
		String jwtToken=jWTTokenHelper.generateToken(employee.getPassword());
		HttpHeaders headers = new HttpHeaders();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		System.out.println(jwtToken);
		headers.add("token", jwtToken);
		return new ResponseEntity(headers, headers,HttpStatus.OK);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){

		return new ResponseEntity<>(employeeService.updateEmployee(id,employee),new HttpHeaders(),HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){

		return new ResponseEntity<>(employeeService.deleteEmployee(id),new HttpHeaders(),HttpStatus.OK);
	}


	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateIdException.class)
	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
