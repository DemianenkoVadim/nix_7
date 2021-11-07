package ua.com.alevel.service;

import ua.com.alevel.entity.ExpenseCategory;

import java.sql.SQLException;

public interface ExpenseCategoryService {

    void addExpenseCategory(ExpenseCategory expenseCategory) throws SQLException;

    void printsAllFinancialExpenseInDatabase() throws SQLException;
}
