package com.pos.admin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pos.admin.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {
	
	@Query("FROM Employee e WHERE e.email=:email OR e.phoneNumber=:phone")
	public Optional<Employee> getEmployeeByEmailPhone(@Param("email") String email,@Param("phone") String phone);
	
	@Query("FROM Employee e WHERE e.email=:email AND e.id!=:id")
	public Optional<Employee> getEmployeeByEmailUpdate(@Param("email") String email,@Param("id") Long id);
	
	@Query("FROM Employee e WHERE  e.phoneNumber=:phone AND e.id!=:id")
	public Optional<Employee> getEmployeeByPhoneUpdate(@Param("phone") String phone,@Param("id") Long id);
	
	@Query("FROM Employee e WHERE e.email=:email")
	public Optional<Employee> getEmployeeByEmail(@Param("email") String email);
      	
}
