package com.pos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.admin.entity.Address;
import com.pos.admin.service.AddressService;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AddressController {

	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add-address/{stateId}/{customerId}")
	public ResponseEntity<String> addAddress(@PathVariable("stateId") Long stateId,@PathVariable("customerId") Long customerId,@RequestBody Address address)
	{
		return addressService.addAddress(stateId,customerId,address);
	}
	
	@GetMapping("/get-address/{phoneNumber}")
	public ResponseEntity<List<Address>> getAddressByCustomerId(@PathVariable("phoneNumber")Long phoneNumber)
	{
		List<Address> addressList=addressService.getAddressByCustomerId(phoneNumber);
		return new ResponseEntity<List<Address>>(addressList,new HttpHeaders(),HttpStatus.OK);
	}
	
}
