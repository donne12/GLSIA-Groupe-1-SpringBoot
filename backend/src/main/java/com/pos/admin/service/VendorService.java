package com.pos.admin.service;

import java.util.List;


import com.pos.admin.entity.Vendor;

public interface VendorService {

	public String deleteVendor(Long id);

	public String updateVendor(Long id, Vendor vendor);

	public String addVendor(Vendor vendor);

	public Vendor getVendorById(Long id);

	public List<Vendor> getAllVendor();
}
