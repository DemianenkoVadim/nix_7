package ua.com.alevel.service.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.ExpenseCategoryDao;
import ua.com.alevel.dao.impl.ExpenseCategoryDaoImpl;
import ua.com.alevel.entity.ExpenseCategory;
import ua.com.alevel.service.ExpenseCategoryService;

import java.sql.SQLException;

@Log4j2
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDaoImpl();

    @Override
    public void addExpenseCategory(ExpenseCategory expenseCategory) throws SQLException {
        log.info("Start adding expense category");
        expenseCategoryDao.addExpenseCategory(expenseCategory);
    }

    @Override
    public void printsAllFinancialExpenseInDatabase() throws SQLException {
        log.info("Start printing all financial expense categories from database");
        expenseCategoryDao.printsAllFinancialExpenseInDatabase();
    }
}
