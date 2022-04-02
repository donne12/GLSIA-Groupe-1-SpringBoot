package com.pos.admin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.admin.dao.AdminDao;
import com.pos.admin.entity.Admin;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;
@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	static final String ID_NOT_FOUND="Admin not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Admin.";
	
	@Autowired
	private AdminDao adminDao;
	//@Override
	public String deleteAdmin(Long id) {
		return adminDao.findById(id)
	.map(admin -> {
    	adminDao.delete(admin);
        return "Admin with id: "+id+" deleted Successfully!";
    }).orElseThrow(() -> new IdNotFoundException("Couldn't delete Admin..."+ID_NOT_FOUND+ id));
}
   public String updateAdmin(Long id, Admin adminUpdated) {
		
		
		return adminDao.findById(id)
				.map(admin -> {
					admin.setPassword(adminUpdated.getPassword());				
					
					
				adminDao.save(admin);
				return "Admin updated successfully!";
				}).orElseThrow(() -> new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+ id)); 
	}
       public String addAdmin(Admin adminAdd) {
	Optional<Admin> passwordOpt=adminDao.getAdminByPasswordUpdate(adminAdd.getPassword(),adminAdd.getId());
	if(passwordOpt.isPresent()) {
		throw new DuplicateIdException("Couldn't add Admin...Entered  id or password is already exists in another record ");
	}
		
	adminDao.save(adminAdd);
	return "Admin added successfully!";
}
       public Admin getAdminById(Long id) {
   		Optional<Admin> optAdmin = adminDao.findById(id);
   		  if(optAdmin.isPresent()) {
   	    		return optAdmin.get();
   	    	}else {
   	    		throw new IdNotFoundException(ID_NOT_FOUND+ id);
   	    	}
   	  }
   	

   	public List<Admin> getAllAdmin() {
   		
   		return adminDao.findAll();
}
}