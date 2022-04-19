package dev.israel.data;

import dev.israel.entities.Expense;

import java.util.Collection;

public interface ExpenseDAO {

    // Create Expense
    Expense createExpense(Expense expense);

    // Get Expense
    Expense getExpenseById(int id);

    // Get all Expenses
    Collection<Expense> getAllExpenses();

    // Update (Patch & Put) Expense
    Expense updateExpenseById(int id);


    // Delete Expense
    Boolean deleteExpenseById(int id);
}
