package dev.israel.api;

import com.google.gson.Gson;
import dev.israel.data.EmployeeDAOPostgresImpl;
import dev.israel.entities.Employee;
import dev.israel.services.EmployeeService;
import dev.israel.services.EmployeeServiceImpl;
import io.javalin.Javalin;

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

        app.start(5001);
    }
}
