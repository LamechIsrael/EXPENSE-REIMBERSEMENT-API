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

    //PATCH
    Expense updateExpenseItem(Expense expense);

    Expense setStatus(String status);

    // PUT
    Expense exchangeExpenseItem(Expense expense);



    // DELETE

}
