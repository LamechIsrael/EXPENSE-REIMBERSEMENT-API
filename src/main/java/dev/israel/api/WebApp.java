package dev.israel.api;

import com.google.gson.Gson;
import dev.israel.data.EmployeeDAOPostgresImpl;
import dev.israel.entities.Employee;
import dev.israel.services.EmployeeService;
import dev.israel.services.EmployeeServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class WebApp {
    //Get DAO, imps, and gson
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgresImpl());
    public static Gson gson = new Gson();

    // Start writing program
    public static void main(String[] args) {

        // Get Javalin for web functionality
        Javalin app = Javalin.create();

        // CREATE or POST
        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJSON = gson.toJson(employee);
            context.result(employeeJSON);
        });

        // GET or READ all employees
        app.get("/employees", context -> {
            String firstName = context.queryParam("firstName");
            if(firstName==null){
                List<Employee> employees = employeeService.employeeList();
                String employeeJSON = gson.toJson(employees);
                context.result(employeeJSON);
            }else{
                List<Employee> employees = employeeService.getEmployeeByName(firstName);
                String employeeJSON = gson.toJson(employees);
                context.result(employeeJSON);
            }
        });

        //GET Employee by Id
        app.get("/employees/{id}", context -> {
            int id  = Integer.parseInt(context.pathParam("id"));

            try{
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(id));
                context.result(employeeJSON);
            } catch (Exception e) {
                context.status(404);
                context.result("The employee id " + id + " was not found");
            }
        });

        //Replace the employee | PUT Employee
        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employee.setId(id);
            employeeService.exchangeEmployee(employee);
            context.result("Employee Replaced");
        });



        app.start(5001);
    }
}
