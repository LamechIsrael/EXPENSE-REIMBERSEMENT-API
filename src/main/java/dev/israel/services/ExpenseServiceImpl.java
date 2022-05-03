package dev.israel.services;

import dev.israel.data.ExpenseDAO;
import dev.israel.entities.Expense;
import dev.israel.utilities.LogLevel;
import dev.israel.utilities.Logger;

import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO){this.expenseDAO = expenseDAO;}

    //Create new Expense Item | POST
    @Override
    public Expense registerExpenseItem(Expense expense)
    {
        Logger.logInfo("A " + expense.getItemName() + " was added to the system as an expense.", LogLevel.INFO);
        return this.expenseDAO.createExpense(expense);
    }

    // Get Expenses | GET
    @Override
    public Expense getExpenseItemById(int id) {
        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> expenseLedger() {

        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        List<Expense> allExpenses = this.expenseDAO.getAllExpenses();
        List<Expense> filteredExpenses = new ArrayList<>();
        for(int i=0; i< allExpenses.size(); i++){
            if(allExpenses.get(i).getStatus().equalsIgnoreCase(status)){
                filteredExpenses.add(allExpenses.get(i));
            }
        }
        return filteredExpenses;
    }

    @Override
    public List<Expense> getExpenseByEmployeeID(int employeeId) {
        List<Expense> allExpenses = this.expenseDAO.getAllExpenses();
        List<Expense> filteredExpenses = new ArrayList<>();
        for(int i=0; i< allExpenses.size(); i++){
            if(allExpenses.get(i).getPurchasingEmployeeId() == employeeId){
                filteredExpenses.add(allExpenses.get(i));
            }
        }
        return filteredExpenses;
    }

    // PUT
    @Override
    public Expense exchangeExpenseItem(Expense expense) {
        Logger.logInfo("An item with the ID " + expense.getId() + " was altered.", LogLevel.INFO);
        return this.expenseDAO.updateExpenseById(expense);
    }


    // DELETE
    @Override
    public boolean removeExpenseById(int id) {
        boolean result = this.expenseDAO.deleteExpenseById(id);
        Logger.logInfo("An item with the ID " + id + " was removed from the system.", LogLevel.INFO);
        return result;
    }

    // PATCH
    @Override
    public Expense updateExpenseItem(Expense expense) {
        return this.expenseDAO.updateExpenseById(expense);
    }


    @Override
    public Expense changeStatus(int id, String status) {
        Expense expense = this.expenseDAO.getExpenseById(id);
        expense.setStatus(status);
        this.expenseDAO.updateExpenseById(expense);
        Logger.logInfo("An item with the ID " + expense.getId() + " was " + status + ".", LogLevel.INFO);
        return expense;
    }


}
