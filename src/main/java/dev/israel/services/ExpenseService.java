package dev.israel.services;

import dev.israel.entities.Expense;

import java.util.Collection;

public interface ExpenseService {

    Expense registerExpenseItem(Expense expense);

    Expense getExpenseItemById(int id);

    Expense getExpenseStatusById(int id);

    Expense exchangeExpenseItem(Expense expense);

    Expense updateExpenseItem(Expense expense);

    Collection<Expense> expenseLedger();
}
