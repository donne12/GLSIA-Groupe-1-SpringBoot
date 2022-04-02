package com.pos.admin.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pos.admin.dao.VendorDao;

import com.pos.admin.entity.Vendor;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
@Transactional
@Service
@Component
public class VendorServiceImpl implements VendorService{

	static final String ID_NOT_FOUND="Vendor not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Vendor...";
	

	@Autowired
	private VendorDao vendorDao;
	
	@Override
	public String deleteVendor(Long id) {
		
		return vendorDao.findById(id)
                .map(vendor -> {
                	vendorDao.delete(vendor);
                    return "Vendor with id: "+id+" deleted Successfully!";
                }).orElseThrow(() -> new IdNotFoundException("Couldn't delete Vendor..."+ID_NOT_FOUND+ id));
	}

	@Override
	public String updateVendor(Long id, Vendor vendorUpdated) {
		
		
		return vendorDao.findById(id)
				.map(vendor -> {
					vendor.setName(vendorUpdated.getName());
					vendor.setPhoneNumber(vendorUpdated.getPhoneNumber());
					vendor.setEmail(vendorUpdated.getEmail());
					vendor.setCompany(vendorUpdated.getCompany());

					Optional<Vendor> emailOpt=vendorDao.getVendorByEmailUpdate(vendorUpdated.getEmail(),id);
					if(emailOpt.isPresent()) {
						throw new DuplicateIdException(COULDNT_UPDATE+"Entered Email already exists in another record ");
					}
					Optional<Vendor> phoneOpt=vendorDao.getVendorByPhoneUpdate(vendorUpdated.getPhoneNumber(),id);
					if(phoneOpt.isPresent()) {
						throw new DuplicateIdException(COULDNT_UPDATE+"Entered Phone number already exists in another record ");
					}
					
					vendorDao.save(vendor);
				return "Vendor updated successfully!";
				}).orElseThrow(() -> new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+ id)); 
	}

	@Override
	public String addVendor(Vendor vendorAdd) {
		Optional<Vendor> emailOpt=vendorDao.getVendorByEmailPhone(vendorAdd.getEmail(),vendorAdd.getPhoneNumber());
		if(emailOpt.isPresent()) {
			throw new DuplicateIdException("Couldn't add vendor...Entered Email id or phone number already exists in another record ");
		}
			
		vendorDao.save(vendorAdd);
		return "vendor added successfully!";
	}



	@Override
	public Vendor getVendorById(Long id) {
		Optional<Vendor> optVendor = vendorDao.findById(id);
		  if(optVendor.isPresent()) {
	    		return optVendor.get();
	    	}else {
	    		throw new IdNotFoundException(ID_NOT_FOUND+ id);
	    	}
	  }
	

	@Override
	public List<Vendor> getAllVendor() {
		
		return vendorDao.findAll();
	}

}


