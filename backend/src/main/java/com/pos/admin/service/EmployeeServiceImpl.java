package com.pos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.admin.dao.EmployeeDao;
import com.pos.admin.entity.Employee;
import com.pos.admin.exception.DuplicateIdException;
import com.pos.admin.exception.IdNotFoundException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	static final String ID_NOT_FOUND="Employee not found with id ";
	static final String COULDNT_UPDATE="Couldn't update Employee...";
	

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public String deleteEmployee(Long id) {
		
		return employeeDao.findById(id)
                .map(employee -> {
                	employeeDao.delete(employee);
                    return "Employee with id: "+id+" deleted Successfully!";
                }).orElseThrow(() -> new IdNotFoundException("Couldn't delete Employee..."+ID_NOT_FOUND+ id));
	}

	@Override
	public String updateEmployee(Long id, Employee employeeUpdated) {
		
		
		return employeeDao.findById(id)
				.map(employee -> {
					employee.setName(employeeUpdated.getName());
					employee.setRole(employeeUpdated.getRole());
					employee.setEmail(employeeUpdated.getEmail());
					employee.setPassword(employeeUpdated.getPassword());
					employee.setAddress(employeeUpdated.getAddress());
					employee.setPhoneNumber(employeeUpdated.getPhoneNumber());

					Optional<Employee> emailOpt=employeeDao.getEmployeeByEmailUpdate(employeeUpdated.getEmail(),id);
					if(emailOpt.isPresent()) {
						throw new DuplicateIdException(COULDNT_UPDATE+"Entered Email already exists in another record ");
					}
					Optional<Employee> phoneOpt=employeeDao.getEmployeeByPhoneUpdate(employeeUpdated.getPhoneNumber(),id);
					if(phoneOpt.isPresent()) {
						throw new DuplicateIdException(COULDNT_UPDATE+"Entered Phone number already exists in another record ");
					}
					
				employeeDao.save(employee);
				return "Employee updated successfully!";
				}).orElseThrow(() -> new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+ id)); 
	}

	@Override
	public String addEmployee(Employee employeeAdd) {
		Optional<Employee> emailOpt=employeeDao.getEmployeeByEmailPhone(employeeAdd.getEmail(),employeeAdd.getPhoneNumber());
		if(emailOpt.isPresent()) {
			throw new DuplicateIdException("Couldn't add Employee...Entered Email id or phone number already exists in another record ");
		}
			
		employeeDao.save(employeeAdd);
		return "Employee added successfully!";
	}



	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> optEmployee = employeeDao.findById(id);
		  if(optEmployee.isPresent()) {
	    		return optEmployee.get();
	    	}else {
	    		throw new IdNotFoundException(ID_NOT_FOUND+ id);
	    	}
	  }
	

	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeDao.findAll();
	}

	@Override
	public Boolean employeeLogin(Employee employee) {
		Boolean stat=false;
		Optional<Employee> optEmployee = employeeDao.getEmployeeByEmail(employee.getEmail());
		  if(optEmployee.isPresent()) {
	    		stat=optEmployee.get().getPassword().equals(employee.getPassword());
	    	}else {
	    		stat=false;
	    	}
		
		return stat;
	}

}
