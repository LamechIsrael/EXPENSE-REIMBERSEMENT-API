package dev.israel.services;

import dev.israel.entities.Employee;

import java.util.List;

public interface EmployeeService {

    // Create Employee
    Employee registerEmployee(Employee employee);

    // Get Employee
    Employee retrieveEmployeeById(int id);
    List<Employee> employeeList();
    List<Employee> getEmployeeByName(String name);

    // Update Employee
    Employee exchangeEmployee(Employee employee);

    // Remove Employee
    boolean removeEmployeeById(int id);


}
