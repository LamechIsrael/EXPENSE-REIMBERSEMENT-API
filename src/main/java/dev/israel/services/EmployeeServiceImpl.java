package dev.israel.services;

import dev.israel.data.EmployeeDAO;
import dev.israel.entities.Employee;
import dev.israel.utilities.LogLevel;
import dev.israel.utilities.Logger;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {this.employeeDAO = employeeDAO;}


    // Create New Employee | POST Employee
    @Override
    public Employee registerEmployee(Employee employee) {
        Logger.logInfo("A new employee: " + employee.getFirstName() + " " + employee.getLastName() +
                " with the ID " + employee.getId() + " was added to the system.", LogLevel.INFO);
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
        Logger.logInfo("An employee with the ID " + employee.getId() + " was altered.", LogLevel.INFO);
        return this.employeeDAO.updateEmployee(employee);
    }

    // DELETE Employee
    @Override
    public boolean removeEmployeeById(int id) {
        boolean result = this.employeeDAO.deleteEmployeeById(id);
        Logger.logInfo("An employee with the ID " + id + " was removed from the system.", LogLevel.INFO);
        return result;
    }
}
