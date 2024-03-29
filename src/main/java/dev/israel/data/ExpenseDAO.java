package dev.israel.data;

import dev.israel.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    // Create Expense
    Expense createExpense(Expense expense);

    // Get Expense
    Expense getExpenseById(int id);

    // Get all Expenses
    List<Expense> getAllExpenses();

    // Update (Patch & Put) Expense
    Expense updateExpenseById(Expense expense);

    // Delete Expense
    Boolean deleteExpenseById(int id);
}
