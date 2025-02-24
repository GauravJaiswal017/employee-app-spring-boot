package com.example.employee_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee_app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
