package ua.com.alevel.dao;

import ua.com.alevel.entity.ExpenseCategory;

import java.sql.SQLException;

public interface ExpenseCategoryDao {

    void addExpenseCategory(ExpenseCategory expenseCategory) throws SQLException;

    void printsAllFinancialExpenseInDatabase() throws SQLException;
}
