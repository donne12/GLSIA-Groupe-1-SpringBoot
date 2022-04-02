package com.pos.admin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.admin.dao.VendorDao;
import com.pos.admin.dao.InventoryDao;
import com.pos.admin.dao.ProductDao;
import com.pos.admin.entity.Inventory;
import com.pos.admin.entity.Product;
import com.pos.admin.entity.Vendor;
import com.pos.admin.exception.IdNotFoundException;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	static final String ID_NOT_FOUND="Inventory not found with id";
	static final String PRODUCT_NOT_FOUND="Product not found with id";
	static final String VENDOR_NOT_FOUND="Vendor not found with id";
	static final String COULDNT_UPDATE="Couldn't update Inventory...";
	
	
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public String deleteInventory(Long id) {

		return inventoryDao.findById(id)
                .map(inventory -> {
                	inventoryDao.delete(inventory);
                    return "Inventory with id: "+id+" deleted Successfully!";
                }).orElseThrow(() -> new IdNotFoundException("Couldn't delete Inventory..."+ID_NOT_FOUND+ id));

	}

	@Override
	public String updateInventory(Long id, Inventory inventoryUpdated) {
		
		return inventoryDao.findById(id)
				.map(inventory -> {
				inventory.setManufacturedDate(inventoryUpdated.getManufacturedDate());
				inventory.setExpiryDate(inventoryUpdated.getExpiryDate());
				inventoryDao.save(inventory);
				return "Inventory updated successfully!";
				}).orElseThrow(() -> new IdNotFoundException(COULDNT_UPDATE+ID_NOT_FOUND+ id)); 
	}

	@Override
	public String addInventory(Long productId, Long vendorId, Inventory inventory) {
		
		Integer stat=Integer.MIN_VALUE;
		Integer stati=Integer.MIN_VALUE;
		
		
		if(!vendorDao.existsById(vendorId)) {
    		throw new IdNotFoundException(VENDOR_NOT_FOUND);
    	} else {
    		Vendor vendor = vendorDao.getById(vendorId);
    		inventory.setVendor(vendor);
    	}
		
		
		if(!productDao.existsById(productId)) {
    		throw new IdNotFoundException(PRODUCT_NOT_FOUND);
    	} else {
    		Product product = productDao.getById(productId);
    		inventory.setProduct(product);
    		if(product.getMrp()<inventory.getPurchasedPrice()) {
    				stat=productDao.updateMrpAndTax(inventory.getPurchasedPrice(), inventory.getTax(), productId);
    		}
    		int quant=inventory.getQuantity()+product.getStock();
    		stati=productDao.updateQuantity(quant, productId);
    	}
		
		if(stat!=0 && stati!=0) {
		inventoryDao.save(inventory);
		return "Inventory added Successfully...:)";
		}
		return "Error in updating MRP price...";
	}

	@Override
	public Inventory getInventoryById(Long id) {
		
		Optional<Inventory> list = inventoryDao.findById(id);
		  if(list.isPresent()) {
	    		return list.get();
	    	}else {
	    		throw new IdNotFoundException(ID_NOT_FOUND+ id);
	    	}
	}

	@Override
	public List<Inventory> getAllInventory() {
		
		return inventoryDao.findAll();
	}

	@Override
	public List<Inventory> getInventoryByName(String name) {
		
		return inventoryDao.getInventoryByName(name);
	}

	@Override
	public List<Inventory> getInventoryByQuantity(Integer quantity) {
		
		return inventoryDao.getInventoryByQuantity(quantity);
	}

	@Override
	public List<Inventory> getInventoryByPrice(Double startPrice, Double endPrice) {
		
		return inventoryDao.getInventoryByPrice(startPrice, endPrice);
	}

	@Override
	public List<Inventory> getInventoryFromLowPrice() {
		
		return inventoryDao.getInventoryFromLowPrice();
	}

	@Override
	public List<Inventory> getInventoryFromHighPrice() {
		
		return inventoryDao.getInventoryFromHighPrice();
	}

	@Override
	public List<Inventory> getInventoryByDate(LocalDate startDate, LocalDate endDate) {
		
		return inventoryDao.getInventoryByDate(startDate, endDate);
	}

}
