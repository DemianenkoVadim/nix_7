package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.ExpenseCategoryDao;
import ua.com.alevel.entity.ExpenseCategory;
import ua.com.alevel.util.HibernateUtil;

import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {

    @Override
    public void addExpenseCategory(ExpenseCategory expenseCategory) {
        log.info("Start adding expense category");
        saveInputInformationSession(expenseCategory);
    }

    @Override
    public void printsAllFinancialExpenseInDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var financeExpenseCategoriesQuery = session.createQuery("FROM ExpenseCategory", ExpenseCategory.class)
                    .list();
            System.out.println(financeExpenseCategoriesQuery);
        } catch (Exception e) {
            log.error("Can`t show financial expense categories in database " + e.getMessage());
        }
    }
}
