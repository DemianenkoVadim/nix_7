package ua.com.alevel.service.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.IncomeCategoryDao;
import ua.com.alevel.dao.impl.IncomeCategoryDaoImpl;
import ua.com.alevel.entity.IncomeCategory;
import ua.com.alevel.service.IncomeCategoryService;

import java.sql.SQLException;

@Log4j2
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

    IncomeCategoryDao incomeCategoryDao = new IncomeCategoryDaoImpl();

    @Override
    public void addIncomeCategory(IncomeCategory incomeCategory) throws SQLException {

        log.info("Start adding income category");
        incomeCategoryDao.addIncomeCategory(incomeCategory);
    }

    @Override
    public void printsAllFinancialIncomeInDatabase() {
        log.info("Start printing all financial income categories from database");
        incomeCategoryDao.printsAllFinancialIncomeInDatabase();
    }
}
