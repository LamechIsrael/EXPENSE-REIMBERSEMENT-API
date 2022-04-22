package dev.israel.datatests;


import dev.israel.data.EmployeeDAO;
import dev.israel.data.EmployeeDAOPostgresImpl;
import dev.israel.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgresImpl();
    static Employee testEmployee = null;

    //POST TEST
    @Test
    @Order(1)
    void create_Employee(){
        Employee jimmy = new Employee(0, "Jimmy", "Kudo");
        Employee savedEmployee = employeeDAO.createEmployee(jimmy);
        EmployeeDAOTests.testEmployee = savedEmployee;
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    //GET TEST
    @Test
    @Order(2)
    void get_Employee_By_Id(){
        Employee retrievedEmployee = employeeDAO.getEmployeeById(5);
        Assertions.assertEquals("Jimmy", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(3)
    void get_all_employees(){
        int totalEmployees = employeeDAO.getAllEmployees().size();
        Assertions.assertTrue(totalEmployees >= 2);
    }

    //PUT TEST
    @Test
    @Order(4)
    void update_employee(){
        EmployeeDAOTests.testEmployee.setFirstName("Shudowudo");
        employeeDAO.updateEmployee(testEmployee);
        Employee retrievedEmployee = employeeDAO.getEmployeeById(testEmployee.getId());

        Assertions.assertEquals("Shudowudo", retrievedEmployee.getFirstName());
    }

    //DELETE TEST
    @Test
    @Order(5)
    void delete_employee(){
        boolean result = employeeDAO.deleteEmployeeById(5);
        Assertions.assertTrue(result);
    }
}
