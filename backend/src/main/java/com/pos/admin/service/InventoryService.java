package com.pos.admin.service;

import java.time.LocalDate;
import java.util.List;

import com.pos.admin.entity.Inventory;

public interface InventoryService {

	public String deleteInventory(Long id);

	public String updateInventory(Long id, Inventory inventoryUpdated);

	public String addInventory(Long productId, Long vendorId, Inventory inventory);

	public Inventory getInventoryById(Long id);

	public List<Inventory> getAllInventory();
	
	public List<Inventory> getInventoryByName(String name);
	
	public List<Inventory> getInventoryByQuantity(Integer quantity);
	
	public List<Inventory> getInventoryByPrice(Double startPrice, Double endPrice);
	
	public List<Inventory> getInventoryByDate(LocalDate startDate, LocalDate endDate);
	
	public List<Inventory> getInventoryFromLowPrice();
	
	public List<Inventory> getInventoryFromHighPrice();
}
