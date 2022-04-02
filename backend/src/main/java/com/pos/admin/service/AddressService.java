package com.pos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.pos.admin.entity.Address;
import com.pos.admin.entity.Country;
import com.pos.admin.entity.State;

public interface AddressService {

	public ResponseEntity<String> addAddress(Long stateId,Long customerId,Address address);

	public List<Address> getAddressByCustomerId(Long phoneNumber);

}
