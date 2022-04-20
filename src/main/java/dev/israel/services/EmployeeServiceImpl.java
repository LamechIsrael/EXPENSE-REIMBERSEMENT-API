package dev.israel.services;

import dev.israel.data.EmployeeDAO;
import dev.israel.entities.Employee;

import java.awt.*;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    // Create New Employee | Post Employee
    @Override
    public Employee registerEmployee(Employee employee) {
        return this.employeeDAO.createEmployee(employee);
    }

    @Override
    public Employee retrieveEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Employee> employeeList() {
        return null;
    }

    @Override
    public Employee exchangeEmployee(Employee employee) {
        return null;
    }
}
