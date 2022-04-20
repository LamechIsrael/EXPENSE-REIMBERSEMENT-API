package dev.israel.datatests;


import dev.israel.data.EmployeeDAO;
import dev.israel.data.EmployeeDAOPostgresImpl;
import dev.israel.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgresImpl();
    static Employee testEmployee = null;

    @Test
    void create_Employee(){
        Employee jimmy = new Employee(0, "Jimmy", "Kudo");
        Employee savedEmployee = employeeDAO.createEmployee(jimmy);
        EmployeeDAOTests.testEmployee = savedEmployee;
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }
}
