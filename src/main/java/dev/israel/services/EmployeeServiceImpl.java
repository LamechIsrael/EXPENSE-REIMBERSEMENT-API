package dev.israel.services;

import dev.israel.data.EmployeeDAO;
import dev.israel.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    // Create New Employee | POST Employee
    @Override
    public Employee registerEmployee(Employee employee) {
        return this.employeeDAO.createEmployee(employee);
    }


    // Retrieve Employees | GET Employee
    @Override
    public Employee retrieveEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Employee> employeeList() {
        return this.employeeDAO.getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeeByName(String firstName) {
        List<Employee> allEmployees = this.employeeDAO.getAllEmployees();

        List<Employee> filteredEmployees = new ArrayList();
        for(int i = 0; i<allEmployees.size(); i++){
            if(allEmployees.get(i).getFirstName().equalsIgnoreCase(firstName)){
                filteredEmployees.add(allEmployees.get(i));
            }
        }
        return filteredEmployees;
    }


    // PUT Employee
    @Override
    public Employee exchangeEmployee(Employee employee) {
        return null;
    }
}
