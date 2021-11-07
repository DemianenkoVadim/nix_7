package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.IncomeCategoryDao;
import ua.com.alevel.entity.IncomeCategory;
import ua.com.alevel.util.HibernateUtil;

import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class IncomeCategoryDaoImpl implements IncomeCategoryDao {

    @Override
    public void addIncomeCategory(IncomeCategory incomeCategory) {
        log.info("Start save new income category");
        saveInputInformationSession(incomeCategory);
    }

    @Override
    public void printsAllFinancialIncomeInDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var financeIncomeCategoriesQuery = session.createQuery("FROM IncomeCategory", IncomeCategory.class)
                    .list();
            System.out.println(financeIncomeCategoriesQuery);
        } catch (Exception e) {
            log.error("Can`t show financial income categories in database " + e.getMessage());
        }
    }
}
