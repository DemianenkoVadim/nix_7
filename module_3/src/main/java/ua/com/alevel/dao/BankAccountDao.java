package ua.com.alevel.dao;

import ua.com.alevel.dto.BankAccountDto;
import ua.com.alevel.entity.BankAccount;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BankAccountDao {

    void addBankAccount(BankAccount account) throws SQLException;

    void updateBankAccount(BankAccount bankAccount) throws SQLException;

    Optional<BankAccount> getBankAccountById(Long id) throws SQLException;

    List<BankAccount> getUsersBankAccounts(Long userId) throws SQLException;

    void deleteBankAccount(Long id) throws SQLException;
}
