package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ua.com.alevel.entity.BankAccount;
import ua.com.alevel.factory.ObjectFactory;
import ua.com.alevel.service.BankAccountService;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

import static ua.com.alevel.util.ConstantModuleApplication.*;

@Log4j2
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankAccountController() {
        this(ObjectFactory.getInstance().getImplClass(BankAccountService.class),
                ObjectFactory.getInstance().getImplClass(UserService.class));
    }

    public void startBankAccountData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------ SELECT OPTION YOU NEED -----");
        try {
            runMenuBankAccount();
            String position;
            while ((position = reader.readLine()) != null) {
                crudBankAccount(position, reader);
                position = reader.readLine();
                if (position.equals(ZERO_POSITION)) {
                    System.out.println(CORRECT_EXIT_PROGRAM);
                }
                crudBankAccount(position, reader);
            }
        } catch (IOException inputUserError) {
            System.out.println("Input user problem: " + inputUserError.getMessage());
        }
    }

    public void runMenuBankAccount() {
        System.out.println();
        System.out.println("To ADD a new bank account please enter ------------------- PRESS 1");
        System.out.println("To UPDATE bank account, please enter --------------------- PRESS 2");
        System.out.println("To DELETE delete bank account, please enter -------------- PRESS 3");
        System.out.println("To FIND BY ID bank account, please enter ----------------- PRESS 4");
        System.out.println("To FIND ALL bank account, please enter ------------------- PRESS 5");
        System.out.println("To GET BACK to previous menu, please enter --------------- PRESS 6");
        System.out.println("To EXIT the application ---------------------------------- PRESS 0");
    }

    private void crudBankAccount(@NotNull String position, BufferedReader reader) {
        switch (position) {
            case USER_CHOOSE_FIRST_POINT -> addANewBankAccount(reader);
            case USER_CHOOSE_SECOND_POINT -> updateBankAccount(reader);
            case USER_CHOOSE_THIRD_POINT -> deleteBankAccount(reader);
            case USER_CHOOSE_FOURTH_POINT -> findBankAccountById(reader);
            case USER_CHOOSE_FIVES_POINT -> findAllBankAccounts();
            case USER_CHOOSE_SIX_POINT -> new UserController().mainApplicationMenu();
            case USER_CHOOSE_ZERO_POINT -> System.exit(CORRECT_EXIT_PROGRAM);
        }
        runMenuBankAccount();
    }

    private void addANewBankAccount(@NotNull BufferedReader reader) {
        System.out.println("BankAccountController.addNewBankAccount");
        try {
            System.out.println("Please, enter bank account number");
            String bankAccountNumber = reader.readLine();
            System.out.println("Please, enter bank account expired end in next format - yyyy-mm-ddThh:ss:mmZ");
            Instant bankAccountExpiredEnd = Instant.parse(reader.readLine());
            System.out.println("Please, enter title of finance institution");
            String nameOfTheFinanceInstitution = reader.readLine();
            System.out.println("Please, enter start amount value on your bank account");
            BigInteger startAmountValue = BigInteger.valueOf(Integer.parseInt(reader.readLine()));
            BankAccount bankAccount = BankAccount.builder()
                    .bankAccountNumber(bankAccountNumber)
                    .bankAccountExpiredEnd(bankAccountExpiredEnd)
                    .nameOfTheFinanceInstitution(nameOfTheFinanceInstitution)
                    .totalAmountValue(startAmountValue)
                    .users(userService.getCurrentUser())
                    .build();
            bankAccountService.addBankAccount(bankAccount);
        } catch (IOException inputOutputError) {
            log.error("User input mistake " + inputOutputError.getMessage());
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError.getMessage());
        }
    }

    private void updateBankAccount(@NotNull BufferedReader reader) {
        System.out.println("BankAccountController.update");
        try {
            System.out.println("Please, enter bank account id");
            Long bankAccountId = Long.valueOf(reader.readLine());
            System.out.println("Please, enter bank account number");
            String bankAccountNumber = reader.readLine();
            System.out.println("Please, enter data end expired of account");
            Instant bankAccountExpiredEnd = Instant.parse(reader.readLine());
            System.out.println("Please, enter title of finance institution");
            String nameOfTheFinanceInstitution = reader.readLine();
            System.out.println("Please, enter start amount value on your bank account");
            BigInteger startAmountValue = BigInteger.valueOf(Integer.parseInt(reader.readLine()));
            BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId).get();
            bankAccount.setBankAccountNumber(bankAccountNumber);
            bankAccount.setBankAccountExpiredEnd(bankAccountExpiredEnd);
            bankAccount.setNameOfTheFinanceInstitution(nameOfTheFinanceInstitution);
            bankAccount.setTotalAmountValue(startAmountValue);
            bankAccountService.updateBankAccount(bankAccount);
        } catch (IOException inputOutputError) {
            log.error("User input mistake " + inputOutputError.getMessage());
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError.getMessage());
        }
    }

    private void deleteBankAccount(@NotNull BufferedReader reader) {
        System.out.println("BankAccountController.delete");
        try {
            System.out.println("Please, enter the bank account id");
            Long bankAccountId = Long.valueOf(reader.readLine());
            bankAccountService.deleteBankAccount(bankAccountId);
        } catch (IOException inputError) {
            log.error("User input mistake " + inputError.getMessage());
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError.getMessage());
        }
    }

    private void findBankAccountById(@NotNull BufferedReader reader) {
        System.out.println("BankAccountController.findById");
        try {
            System.out.println("Please, enter you bank account id");
            Long bankAccountId = Long.valueOf(reader.readLine());
            bankAccountService.getBankAccountById(bankAccountId)
                    .ifPresent(System.out::println);
        } catch (IOException inputError) {
            log.error("User input mistake " + inputError.getMessage());
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError.getMessage());
        }
    }

    void findAllBankAccounts() {
        System.out.println("BankAccountController.findAll");
        try {
            List<BankAccount> accounts = bankAccountService.getUsersBankAccounts(userService.getCurrentUser().getId());
            for (BankAccount bankAccount : accounts) {
                System.out.println("Bank Account : " + bankAccount.toString());
            }
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError.getMessage());
        }
    }
}
