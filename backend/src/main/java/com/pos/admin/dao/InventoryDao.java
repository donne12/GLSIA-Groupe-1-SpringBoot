package com.pos.admin.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pos.admin.entity.Inventory;

@Repository
public interface InventoryDao extends JpaRepository<Inventory,Long> {

	@Query("select i from Inventory as i where i.product.name like %:productname%")
	public List<Inventory> getInventoryByName(@Param("productname") String name);
	
	@Query("select i from Inventory as i where i.quantity=:quant")
	public List<Inventory> getInventoryByQuantity(@Param("quant") Integer quantity);
	
	@Query("select i from Inventory as i where i.purchasedPrice between :startprice and :endprice")
	public List<Inventory> getInventoryByPrice(@Param("startprice") Double startPrice, @Param("endprice") Double endPrice);
	
	@Query("select i from Inventory as i where i.manufacturedDate between :startdate and :enddate")
	public List<Inventory> getInventoryByDate(@Param("startdate") LocalDate startDate, @Param("enddate") LocalDate endDate);
	
	@Query("select i from Inventory as i order by i.purchasedPrice asc")
	public List<Inventory> getInventoryFromLowPrice();
	
	@Query("select i from Inventory as i order by i.purchasedPrice desc")
	public List<Inventory> getInventoryFromHighPrice();
}
