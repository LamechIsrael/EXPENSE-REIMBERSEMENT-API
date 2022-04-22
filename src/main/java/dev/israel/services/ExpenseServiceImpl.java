package dev.israel.services;

import dev.israel.data.ExpenseDAO;
import dev.israel.entities.Expense;
import dev.israel.utilities.LogLevel;
import dev.israel.utilities.Logger;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO){this.expenseDAO = expenseDAO;}

    //Create new Expense Item | POST
    @Override
    public Expense registerExpenseItem(Expense expense)
    {
        Logger.logInfo("A" + expense.getItemName() + " was added to the system as an expense.", LogLevel.INFO);
        return this.expenseDAO.createExpense(expense);
    }

    @Override
    public Expense getExpenseItemById(int id) {
        return null;
    }

    @Override
    public Expense getExpenseStatusById(int id) {
        return null;
    }

    @Override
    public Expense exchangeExpenseItem(Expense expense) {
        return null;
    }

    @Override
    public Expense updateExpenseItem(Expense expense) {
        return null;
    }

    @Override
    public Expense setStatus(String status) {
        return null;
    }

    @Override
    public List<Expense> expenseLedger() {
        return null;
    }
}
