package com.pos.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pos.admin.dao.CustomerDao;
import com.pos.admin.entity.Customer;
import com.pos.admin.exception.IdNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public ResponseEntity<String> addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.save(customer);
		return new ResponseEntity<String>("Customer Details Added Successfully!",new HttpHeaders(),HttpStatus.OK);
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		List<Customer> customerList = new ArrayList<>();
		customerList = customerDao.findAll();
		return customerList;
	}

	@Override
	public ResponseEntity<Customer> getCustomerById(Long customerId) {
		Customer customerDetails=null;
		boolean stat=customerDao.existsById(customerId);
		if(stat) {
		customerDetails = customerDao.findById(customerId).get();}
		else {
			throw new IdNotFoundException("No record found for "+customerId);
		}
		return new ResponseEntity<Customer>(customerDetails,new HttpHeaders(),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> updateCustomer(Long phoneNo, Customer customerDetails) {
		// TODO Auto-generated method stub
		return customerDao.findById(phoneNo).map(customer -> {
			customer.setName(customerDetails.getName());
			customer.setEmail(customerDetails.getEmail());
			return new ResponseEntity<String>("Customer Details Updated Successfully!", new HttpHeaders(),
					HttpStatus.OK);
		}).orElse(new ResponseEntity<String>("Customer Not Found With" + " " + phoneNo + "!", new HttpHeaders(),
				HttpStatus.OK));
	}
	
	
	/*@Override
	public ResponseEntity<String> activateProfile(Long phoneNo, String status) {
		// TODO Auto-generated method stub
		ResponseEntity<String> response = null;
		if (!customerDao.existsById(phoneNo)) {
			response = new ResponseEntity<String>("Customer Not Found With" + " " + phoneNo + "!", new HttpHeaders(),
					HttpStatus.OK);
		}
		customerDao.activateProfile(phoneNo, status);
		response = new ResponseEntity<String>("Profile Activated Successfully!", new HttpHeaders(), HttpStatus.OK);
		return response;
	}

	@Override
	public ResponseEntity<String> deActivateProfile(Long phoneNo, String status) {
		// TODO Auto-generated method stub
		ResponseEntity<String> response = null;
		if (!customerDao.existsById(phoneNo)) {
			response = new ResponseEntity<String>("Customer Not Found With" + " " + phoneNo + "!", new HttpHeaders(),
					HttpStatus.OK);
		}
		customerDao.deActivateProfile(phoneNo, status);
		response = new ResponseEntity<String>("Profile Deactivated Successfully!", new HttpHeaders(), HttpStatus.OK);
		return response;
	}*/

}
