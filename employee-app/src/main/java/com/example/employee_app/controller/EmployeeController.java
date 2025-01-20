package com.example.employee_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_app.exception.EmployeeNotFoundException;
import com.example.employee_app.model.Employee;
import com.example.employee_app.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired private EmployeeService employeeService;
	
	//insert, fetch, delete, update, partial update
	
	@PostMapping
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
	}
	
	@GetMapping
	public ResponseEntity<?> getAllEmployee(){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
		}
		catch(EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(id, employee));
		}
		catch(EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdateEmployee(@PathVariable Long id, @RequestBody Employee partialEmployee){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.partialUpdateEmployee(id, partialEmployee));
		}
		catch(EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(id));
		}
		catch(EmployeeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
