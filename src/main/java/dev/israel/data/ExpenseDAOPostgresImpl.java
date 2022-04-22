package dev.israel.data;

import dev.israel.entities.Expense;
import dev.israel.utilities.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class ExpenseDAOPostgresImpl implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {

        // Create connection to expenses table in project1 database
        Connection conn = ConnectionUtil.createConnection();
        String sql = "insert into expenses values (default, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, expense.getItemName());
            ps.setDouble(2, expense.getItemCost());
            ps.setInt(3, expense.getPurchasingEmployeeId());

            // Execute Query for Table
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generateExpenseID = rs.getInt("item_id");
            expense.setId(generateExpenseID);
            return expense;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpenseById(int id) {
        return null;
    }

    @Override
    public Boolean deleteExpenseById(int id) {
        return null;
    }
}
