package ua.com.alevel.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import ua.com.alevel.entity.BankAccount;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.impl.CategoryServiceImpl;
import ua.com.alevel.service.impl.OperationServiceImpl;
import ua.com.alevel.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.time.Instant;

import static ua.com.alevel.service.requests.RequestsValidInputInCLI.*;
import static ua.com.alevel.util.ConstantModuleApplication.*;

@Log4j2
public class OperationJournalController {

    String position;
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final OperationService operationService = new OperationServiceImpl();
    BankAccountController bankAccountController = new BankAccountController();

    public void startCreateOperation() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("------ SELECT OPTION YOU NEED -----");
        try {
            runMenuCategories();
            while ((position = reader.readLine()) != null || !reader.readLine().equals("")) {
                createCategorise(position, reader);
                position = reader.readLine();
                if (position.equals(ZERO_POSITION)) {
                    System.out.println(CORRECT_EXIT_PROGRAM);
                }
                createCategorise(position, reader);
            }
        } catch (IOException inputUserError) {
            System.out.println("Input user problem: " + inputUserError.getMessage());
        }
    }

    private void runMenuCategories() {
        System.out.println();
        System.out.println("To MAKE FINANCIAL INCOME operation ------------------------ PRESS 1");
        System.out.println("To MAKE FINANCIAL EXPENSE operation ----------------------- PRESS 2");
        System.out.println("To GET BACK to previous menu, please enter ---------------- PRESS 6");
        System.out.println("To EXIT the application ----------------------------------- PRESS 0");
    }

    private void createCategorise(String position, BufferedReader reader) {
        switch (position) {
            case USER_CHOOSE_FIRST_POINT -> addIncomeOperation(reader);
            case USER_CHOOSE_SECOND_POINT -> addExpenseOperation(reader);
            case USER_CHOOSE_SIX_POINT -> new UserController().mainApplicationMenu();
            case USER_CHOOSE_ZERO_POINT -> System.exit(CORRECT_EXIT_PROGRAM);
        }
        runMenuCategories();
    }

    @SneakyThrows
    private void addIncomeOperation(BufferedReader reader) {
        System.out.println();
        System.out.println("OperationJournalController.addNewFinancialReceipt");
        categoryService.printsAllIncomeCategory();
        try {
            System.out.println("Please, enter category id");
            Long idCategory = Long.valueOf(reader.readLine());
            System.out.println("Please, choose your bank account id");
            bankAccountController.findAllBankAccounts();
            Long bankAccountId = Long.valueOf(reader.readLine());
            BigInteger incomeValue = requestAddingIncomeOperation(reader);
            System.out.println("Please, enter short description of your financial receipt");
            String shortDescription = reader.readLine();
            Operation operation = Operation.builder()
                    .operationTime(Instant.now())
                    .account(BankAccount.builder().build())
                    .account(new BankAccount(bankAccountId))
                    .category(Category.builder().build())
                    .category(new Category(idCategory))
                    .incomeValue(incomeValue)
                    .operationDescription(shortDescription)
                    .build();
            operationService.addOperation(operation);
        } catch (Exception userInputError) {
            log.error("User input mistake " + userInputError.getMessage());
        }
    }

    @SneakyThrows
    private void addExpenseOperation(BufferedReader reader) {
        System.out.println();
        System.out.println("OperationJournalController.addNewFinancialContribution");
        categoryService.printsAllExpenseCategory();
        EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
        try {
            System.out.println("Please, enter category id");
            Long idCategory = Long.valueOf(reader.readLine());
            System.out.println("Please, enter your bank account id");
            Long bankAccountId = Long.valueOf(reader.readLine());
            BigInteger expenseValue = requestAddingExpenseOperation(reader);
            System.out.println("Please, enter short description of your financial contribution");
            String shortDescription = reader.readLine();
            operationService.addOperation(Operation.builder()
                    .operationTime(Instant.now())
                    .account(em.getReference(BankAccount.class, bankAccountId))
                    .category(em.getReference(Category.class, idCategory))
                    .incomeValue(expenseValue)
                    .operationDescription(shortDescription)
                    .build());
        } catch (Exception userInputError) {
            log.error("User input mistake " + userInputError.getMessage());
        }
    }

    public void exportAccountStatementsCSVFile() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("OperationJournalController.exportAccountsStatementsToCSVFile");
            while ((position = reader.readLine()) != null) {
                System.out.println("Please, enter your bank account id");
                Long userBankAccountId = Long.valueOf(reader.readLine());
                Instant fromDateExport = requestAddingDateFrom(reader);
                Instant toDateExport = requestAddingDateTo(reader);
                operationService.findAllOperationInGivenPeriodOfTime(userBankAccountId, fromDateExport, toDateExport);
            }
        } catch (IOException inputUserError) {
            System.out.println("Input user problem: " + inputUserError.getMessage());
        }
    }
}
