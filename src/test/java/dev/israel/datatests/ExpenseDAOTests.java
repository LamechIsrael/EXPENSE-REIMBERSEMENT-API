package dev.israel.datatests;

import dev.israel.data.ExpenseDAO;
import dev.israel.data.ExpenseDAOPostgresImpl;
import dev.israel.entities.Expense;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgresImpl();
    static Expense testExpense = null;

    //POST TEST
    @Test
    @Order(1)
    void create_Expense() {
        Expense ratchet = new Expense(0, "Ratchet", -11.99, "", 1);
        Expense savedExpense = expenseDAO.createExpense(ratchet);
        ExpenseDAOTests.testExpense = savedExpense;
        Assertions.assertNotEquals(0, savedExpense.getId());
    }

    //GET Tests
    @Test
    @Order(2)
    void get_Expense_by_Id(){
        Expense retrievedExpense = expenseDAO.getExpenseById(2);
        Assertions.assertEquals("Ratchet", retrievedExpense.getItemName());
    }

    @Test
    @Order(3)
    void get_All_Expenses(){
        int totalExpenses = expenseDAO.getAllExpenses().size();
        Assertions.assertTrue(totalExpenses>=1);
    }

    @Test
    @Order(4)
    void update_Expenses(){
        ExpenseDAOTests.testExpense.setItemCost(24.99);
        expenseDAO.updateExpenseById(testExpense);
        Expense retrievedExpense = expenseDAO.getExpenseById(testExpense.getId());

        System.out.println(expenseDAO.getExpenseById(testExpense.getId()));
        Assertions.assertEquals(24.99, retrievedExpense.getItemCost());

    }
}
