package ua.com.alevel.service;


import ua.com.alevel.entity.BankAccount;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    void addBankAccount(BankAccount bankAccount) throws SQLException;

    void updateBankAccount(BankAccount account) throws SQLException;

    Optional<BankAccount> getBankAccountById(Long id) throws SQLException;

    List<BankAccount> getUsersBankAccounts(Long id) throws SQLException;

    void deleteBankAccount(Long id) throws SQLException;
}
