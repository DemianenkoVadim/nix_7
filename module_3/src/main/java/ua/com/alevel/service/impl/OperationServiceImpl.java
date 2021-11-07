package ua.com.alevel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.BankAccountDao;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.entity.BankAccount;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.factory.ObjectFactory;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.HibernateUtil;

import java.time.Instant;

@RequiredArgsConstructor
@Log4j2
public class OperationServiceImpl implements OperationService {

    private final OperationDao operationDao;
    private final BankAccountDao bankAccountDao;
    private final CategoryDao categoryDao;

    public OperationServiceImpl() {
        this(ObjectFactory.getClass(OperationDao.class),
                ObjectFactory.getClass(BankAccountDao.class),
                ObjectFactory.getClass(CategoryDao.class));
    }

    @SneakyThrows
    @Override
    public void addOperation(Operation operation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            BankAccount account = bankAccountDao.getBankAccountById(operation.getAccount().getBankAccountId()).orElseThrow();
            account.setTotalAmountValue(account.getTotalAmountValue().add(operation.getIncomeValue()));
            bankAccountDao.updateBankAccount(account);
            operation.setAccount(account);
            operation.setCategory(categoryDao.findById(operation.getCategory().getCategoryId()));
            operationDao.addOperation(operation);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Can not perform operation", e);
        }
    }

    @Override
    public void findAllOperationInGivenPeriodOfTime(Long bankAccount, Instant fromDateExport, Instant toDateExport) {
        log.info("Starting finding all operations in given period");
        operationDao.findAllOperationsInGivenPeriodOfTime(bankAccount, fromDateExport, toDateExport);
    }


}
