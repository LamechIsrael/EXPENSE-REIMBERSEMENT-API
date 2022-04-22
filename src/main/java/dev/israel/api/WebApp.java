package dev.israel.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.israel.data.EmployeeDAOPostgresImpl;
import dev.israel.data.ExpenseDAOPostgresImpl;
import dev.israel.entities.Employee;
import dev.israel.entities.Expense;
import dev.israel.services.EmployeeService;
import dev.israel.services.EmployeeServiceImpl;
import dev.israel.services.ExpenseService;
import dev.israel.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class WebApp {
    //Get DAO, imps, and gson
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgresImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgresImpl());
    public static Gson gson = new Gson();

    // Start writing program
    public static void main(String[] args) {

        // Get Javalin for web functionality
        Javalin app = Javalin.create();

        // Main Page
        app.get("/", context -> {
            context.status(200);
            context.result("Welcome to Lamech's Employee/Expense page.");
        });

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
            String firstName = context.queryParam("name");
            if(firstName==null){
                List<Employee> employees = employeeService.employeeList();
                context.status(200);
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
                context.status(200);
                context.result(employeeJSON);
            } catch (Exception e) {
                context.status(404);
                context.result("The employee id " + id + " was not found.");
            }
        });

        //Replace the employee | PUT Employee
        app.put("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            try{
                String body = context.body();
                Employee employee = gson.fromJson(body, Employee.class);
                employee.setId(id);
                employeeService.exchangeEmployee(employee);
                context.status(201);
                context.result("Employee Replaced");
            } catch (JsonSyntaxException e) {
                context.status(404);
                context.result("The employee id " + id + " was not found.");
            }

        });

        //Remove the employee by Id | DELETE Employee
        app.delete("/employees/{id}", context -> {
            int id = Integer.parseInt(context.pathParam("id"));
            boolean result = employeeService.removeEmployeeById(id);
            if(result){
                context.status(204);
                context.result("Employee Deleted.");
            }else{
                context.status(404);
                context.result("The employee id " + id + " was not found");
            }
        });



        // EXPENSE FUNCTIONALITY
        //
        //
        // CREATE
        app.post("/expenses", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expenseService.registerExpenseItem(expense);
            context.status(201);
            String expenseJSON = gson.toJson(expense);
            context.result(expenseJSON);
        });

        app.start(5000);
    }
}
