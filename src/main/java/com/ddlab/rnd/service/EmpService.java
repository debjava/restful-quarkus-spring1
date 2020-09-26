package com.ddlab.rnd.service;

import com.ddlab.rnd.entities.Employee;

import java.util.List;

public interface EmpService {

  List<Employee> getAllEmployees();

  List<Employee> getAllEmpsException1();

  Employee getEmployeeById(String empId);

  Employee searchEmpByName(String firstName, String lastName);

  void createEmployee(Employee emp);

  void deleteEmployee(int id);

  Employee updateEmployeeInfo(Employee emp);

  Employee updateEmployeeLogin(Employee emp);
}
