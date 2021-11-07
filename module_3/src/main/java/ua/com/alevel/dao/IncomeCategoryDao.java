package ua.com.alevel.dao;

import ua.com.alevel.entity.IncomeCategory;

import java.sql.SQLException;

public interface IncomeCategoryDao {

    void addIncomeCategory(IncomeCategory incomeCategory) throws SQLException;

    void printsAllFinancialIncomeInDatabase();
}
