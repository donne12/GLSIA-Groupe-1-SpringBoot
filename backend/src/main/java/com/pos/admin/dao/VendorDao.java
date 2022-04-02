package com.pos.admin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.pos.admin.entity.Vendor;



@Repository

public interface VendorDao extends JpaRepository<Vendor, Long> {
	@Query("FROM Employee e WHERE e.email=:email OR e.phoneNumber=:phone")
	public Optional<Vendor> getVendorByEmailPhone(@Param("email") String email,@Param("phone") String phone);
	
	@Query("FROM Employee e WHERE e.email=:email AND e.id!=:id")
	public Optional<Vendor> getVendorByEmailUpdate(@Param("email") String email,@Param("id") Long id);
	
	@Query("FROM Employee e WHERE  e.phoneNumber=:phone AND e.id!=:id")
	public Optional<Vendor> getVendorByPhoneUpdate(@Param("phone") String phone,@Param("id") Long id);

}
