package dev.israel.data;

import dev.israel.entities.Employee;
import dev.israel.exceptions.ResourceNotFound;
import dev.israel.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgresImpl implements EmployeeDAO{

    // Creates new Employee | POST Employee
    @Override
    public Employee createEmployee(Employee employee) {


        try (Connection conn = ConnectionUtil.createConnection()) {
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


        try (Connection conn = ConnectionUtil.createConnection()) {
            // Connects to database
            String sql = "select * from employees where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Fetches ID
            ResultSet rs = ps.executeQuery();
            rs.next();

            // Check to see if person under id exists
            if(rs.getRow() == 0){
                throw new ResourceNotFound(id);
            }

            // Return Employee
            Employee employee = new Employee();
            employee.setId(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            System.out.println(employee);
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {


        try (Connection conn = ConnectionUtil.createConnection()) {

            String sql = "select * from employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employees = new ArrayList();

            while(rs.next()){
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employees.add(employee);
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update Employee | PUT or PATCH Employee
    @Override
    public Employee updateEmployee(Employee employee) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update employees set first_name = ?, last_name = ? where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getId());

            int rowsUpdated = ps.executeUpdate();
            if(rowsUpdated==0){
                throw new ResourceNotFound(employee.getId());
            }
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteEmployeeById(int id) {

        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from employees where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
