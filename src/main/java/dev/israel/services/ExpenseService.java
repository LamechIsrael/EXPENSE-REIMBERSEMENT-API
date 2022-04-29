package dev.israel.services;

import dev.israel.entities.Expense;

import java.util.List;

public interface ExpenseService {

    // POST
    Expense registerExpenseItem(Expense expense);

    // GET
    Expense getExpenseItemById(int id);
    List<Expense> expenseLedger();

    List<Expense> getExpenseByStatus(String status);

    List<Expense> getExpenseByEmployeeID(int employeeId);

    //PATCH
    Expense updateExpenseItem(Expense expense);

    Expense changeStatus(int id, String status);

    // PUT
    Expense exchangeExpenseItem(Expense expense);

    // DELETE
    boolean removeExpenseById(int id);

    //-----------------------------------------------------------------------------
  //  Expense <Expense> getExpenseAfterEmployee(int employeeid)

}
