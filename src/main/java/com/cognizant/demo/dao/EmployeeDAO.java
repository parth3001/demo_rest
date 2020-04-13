package com.cognizant.demo.dao;

import java.util.List;

import com.cognizant.demo.model.Employee;

public interface EmployeeDAO {

	List<Employee> get();

	Employee get(int id);

	void save(Employee employee);

	void delete(int id);


}
