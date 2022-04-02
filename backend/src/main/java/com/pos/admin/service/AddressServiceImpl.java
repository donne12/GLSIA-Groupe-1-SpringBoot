package com.pos.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pos.admin.dao.AddressDao;
import com.pos.admin.dao.CountryDao;
import com.pos.admin.dao.CustomerDao;
import com.pos.admin.dao.StateDao;
import com.pos.admin.entity.Address;
import com.pos.admin.entity.Country;
import com.pos.admin.entity.Customer;
import com.pos.admin.entity.State;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressDao addressDao;
		
	@Autowired
	private StateDao stateDao;
	
	@Autowired
	private CustomerDao customerDao;
	

	@Override
	public ResponseEntity<String> addAddress(Long stateId,Long customerId, Address address) {
		// TODO Auto-generated method stub
		State stateModel = stateDao.getById(stateId);
		address.setState(stateModel);
		
		Customer customer = customerDao.getById(customerId);
		address.setCustomer(customer);
		
		addressDao.save(address);
		return new ResponseEntity<String>("Address Details Added Successfully!",new HttpHeaders(),HttpStatus.OK);
	}

	@Override
	public List<Address> getAddressByCustomerId(Long phoneNumber) {
		// TODO Auto-generated method stub
		List<Address> addressList = new ArrayList<>();
		addressList = addressDao.getByMobileNumber(phoneNumber);
		return addressList;
	}

}
