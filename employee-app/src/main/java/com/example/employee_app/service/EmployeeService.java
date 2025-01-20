package com.example.employee_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_app.exception.EmployeeNotFoundException;
import com.example.employee_app.model.Employee;
import com.example.employee_app.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired private EmployeeRepository empRepository;
	
	//insert, fetch, delete, update, partial update
	
	
	public Employee addEmployee(Employee employee) {
		return empRepository.save(employee);
	}
	
	
	public List<Employee> getAllEmployees(){
		return empRepository.findAll();
	}
	
	
	public Employee getEmployeeById(Long id) {
		if(empRepository.existsById(id)) {
			return empRepository.findById(id).get();
		}else {
			throw new EmployeeNotFoundException("Employee with id" + id +" not found");
		}
	}
	
	
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		updatedEmployee.setId(id);
		if(empRepository.existsById(id)) {
			return empRepository.save(updatedEmployee);
		}else {
			throw new EmployeeNotFoundException("Employee with id" + id +" not found");
		}
	}
	
	public Employee partialUpdateEmployee(Long id, Employee partialEmployee) {
		//only update the fields which are not null(that is they are given in partial emp object
		
		partialEmployee.setId(id);
		if(empRepository.existsById(id)) {
			Employee employee = empRepository.findById(id).get();
			if(partialEmployee.getEmployeeName() != null) 
				employee.setEmployeeName(partialEmployee.getEmployeeName());
			
			if(partialEmployee.getDepartment() != null)
				employee.setDepartment(partialEmployee.getDepartment());
			
			if(partialEmployee.getSalary() != null)
				employee.setSalary(partialEmployee.getSalary());
			
			return empRepository.save(employee);
		}
		else {
			throw new EmployeeNotFoundException("Employee with id" + id +" not found");
		}
	}
	
	public boolean deleteEmployee(Long id) {
		if(empRepository.existsById(id)) {
			empRepository.deleteById(id);
			return true;
		}
		else {
			throw new EmployeeNotFoundException("Employee with id" + id +" not found");
		}
	}
}
