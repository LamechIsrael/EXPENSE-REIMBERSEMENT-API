package dev.israel.data;

import dev.israel.entities.Expense;
import dev.israel.exceptions.ResourceNotFound;
import dev.israel.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgresImpl implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {

        // Create connection to expenses table in project1 database

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expenses values (default, ?, ?, default, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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


    // Get Expenses by Id | GET expense
    @Override
    public Expense getExpenseById(int id) {


        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expenses where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Fetches ID from database
            ResultSet rs = ps.executeQuery();
            rs.next();

            if(rs.getRow() !=0){
                Expense expense = new Expense();
                expense.setId(rs.getInt("item_id"));
                expense.setItemName(rs.getString("item_name"));
                expense.setItemCost(rs.getDouble("item_cost"));
                expense.setStatus(rs.getString("item_status"));
                expense.setPurchasingEmployeeId(rs.getInt("purchasing_employee_id"));
                return expense;
            }else throw new ResourceNotFound(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from expenses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();

            while(rs.next()){
                Expense expense = new Expense();
                expense.setItemName(rs.getString("item_name"));
                expense.setItemCost(rs.getDouble("item_cost"));
                expense.setId(rs.getInt("item_id"));
                expense.setPurchasingEmployeeId(rs.getInt("purchasing_employee_id"));
                expense.setStatus(rs.getString("item_status"));
                expenses.add(expense);
            }

            return expenses;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpenseById(Expense expense) {



        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expenses set item_name = ?, item_cost = ?, item_status = ? where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, expense.getItemName());
            ps.setDouble(2, expense.getItemCost());
            ps.setString(3, expense.getStatus());
            ps.setInt(4, expense.getId());

            int rowsUpdated = ps.executeUpdate();
            if(rowsUpdated==0){
                throw new ResourceNotFound(expense.getId());
            }
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Boolean deleteExpenseById(int id) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from expenses where item_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
