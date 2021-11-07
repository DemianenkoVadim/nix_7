package ua.com.alevel.service;

import ua.com.alevel.entity.IncomeCategory;

import java.sql.SQLException;

public interface IncomeCategoryService {

    void addIncomeCategory(IncomeCategory incomeCategory) throws SQLException;

    void printsAllFinancialIncomeInDatabase() throws SQLException;
}

