package com.ddlab.rnd.service;

import com.ddlab.rnd.entities.Employee;

//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("emp1")
public class EmpServiceImpl implements EmpService {

	@Override
	public List<Employee> getAllEmployees() {
		return Arrays.asList(new Employee(1, "John", "Abraham"), new Employee(2, "Vidya", "Balan"),
				new Employee(3, "Debadatta", "Mishra"));
	}

	@Override
	public Employee getEmployeeById(String empId) {
		Employee emp = null;
		try {
			int id = Integer.parseInt(empId);
			emp = new Employee(id, "Deb", "Mishra");
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Invalid emp Id, unable to create an employee");
		}
		return emp;
	}

	@Override
	public Employee searchEmpByName(String firstName, String lastName) {
		if (firstName == null || lastName == null)
			throw new IllegalArgumentException("Invalid search criteria");
		return new Employee(13, firstName, lastName);
	}

	@Override
	public void createEmployee(Employee emp) {
		System.out.println("emp = " + emp);
		System.out.println("Employee created successfully...");
	}

	@Override
	public void deleteEmployee(int id) {
	}

	@Override
	public Employee updateEmployeeInfo(Employee emp) {
		return emp;
	}

	@Override
	public List<Employee> getAllEmpsException1() {
		throw new NullPointerException("Unable to retrieve the employee details, emp server is down");
	}

	@Override
	public Employee updateEmployeeLogin(Employee emp) {
		return emp;
	}
}
