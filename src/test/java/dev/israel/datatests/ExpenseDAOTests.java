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
        Expense ratchet = new Expense(0, "Ratchet", 10.99, 1);
        Expense savedExpense = expenseDAO.createExpense(ratchet);
        ExpenseDAOTests.testExpense = savedExpense;
        Assertions.assertNotEquals(0, savedExpense.getId());
    }
}
