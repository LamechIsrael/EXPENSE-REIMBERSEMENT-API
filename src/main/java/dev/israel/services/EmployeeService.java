package dev.israel.services;

import dev.israel.entities.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    // Create Employee
    Employee registerEmployee(Employee employee);

    // Get Employee
    Employee retrieveEmployeeById(int id);
    List<Employee> employeeList();

    // Update Employee
    Employee exchangeEmployee(Employee employee);


}
