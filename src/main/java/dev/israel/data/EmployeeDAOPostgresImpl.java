package dev.israel.data;

import dev.israel.entities.Employee;
import dev.israel.utilities.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class EmployeeDAOPostgresImpl implements EmployeeDAO{

    // Creates new Employee | POST Employee
    @Override
    public Employee createEmployee(Employee employee) {


        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into employees values (default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generateID = rs.getInt("employee_id");
            employee.setId(generateID);
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get employees by Id | GET Employee
    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean deleteEmployeeById(int id) {
        return null;
    }
}
