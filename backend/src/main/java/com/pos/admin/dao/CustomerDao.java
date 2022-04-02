package com.pos.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pos.admin.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>{

	@Query("SELECT c FROM Customer c WHERE c.phoneNumber=:phoneNumber")
	List<Customer> getByMobile(Long phoneNumber);

}
