package com.pos.admin.service;

import java.util.List;

import com.pos.admin.entity.Employee;

public interface EmployeeService {

	public String deleteEmployee(Long id);

	public String updateEmployee(Long id, Employee employee);

	public String addEmployee(Employee employee);

	public Employee getEmployeeById(Long id);

	public List<Employee> getAllEmployee();

	public Boolean employeeLogin(Employee employee);

}
