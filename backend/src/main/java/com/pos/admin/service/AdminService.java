package com.pos.admin.service;

import java.util.List;

import com.pos.admin.entity.Admin;

public interface AdminService {
	public String deleteAdmin(Long id);

	public String updateAdmin(Long id, Admin admin);

	public String addAdmin(Admin admin);

	public Admin getAdminById(Long id);

	public List<Admin> getAllAdmin();


}
