package ua.com.alevel.service.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.BankAccountDao;
import ua.com.alevel.dao.impl.BankAccountDaoImpl;
import ua.com.alevel.entity.BankAccount;
import ua.com.alevel.service.BankAccountService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class BankAccountServiceImpl implements BankAccountService {

    BankAccountDao bankAccountDao = new BankAccountDaoImpl();

    @Override
    public void addBankAccount(BankAccount bankAccount) throws SQLException {
        log.info("Start adding bank account to data base");
        bankAccountDao.addBankAccount(bankAccount);
    }

    @Override
    public void updateBankAccount(BankAccount account) throws SQLException {
        log.info("Start updating information about bank account");
        bankAccountDao.updateBankAccount(account);
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long id) throws SQLException {
        log.info("Start finding bank account");
        return bankAccountDao.getBankAccountById(id);
    }

    @Override
    public List<BankAccount> getUsersBankAccounts(Long id) throws SQLException {
        log.info("Start scanning all bank accounts");
        return bankAccountDao.getUsersBankAccounts(id);
    }

    @Override
    public void deleteBankAccount(Long id) throws SQLException {
        log.info("Start deleting bank account");
        bankAccountDao.deleteBankAccount(id);
    }
}
