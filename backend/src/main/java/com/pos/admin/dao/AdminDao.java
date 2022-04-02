package com.pos.admin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pos.admin.entity.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	@Query("FROM Admin WHERE id =:id AND password=:password")
	public Optional<Admin> getAdminByPasswordUpdate(@Param("password") String password,@Param("id") Long id);
}
