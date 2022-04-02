package com.pos.admin.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pos.admin.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Long>{

	@Transactional
	@Modifying
	@Query("update Product P set P.mrp=:updatedMrp,P.tax=:updatedTax where P.id=:id")
	public Integer updateMrpAndTax(@Param("updatedMrp") Double mrp,@Param("updatedTax") Double tax,@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("update Product P set P.stock=:updatedQuantity where P.id=:id")
	public Integer updateQuantity(@Param("updatedQuantity") Integer quantity,@Param("id") Long id);	
	
	
}
