package ua.com.alevel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ua.com.alevel.entity.User;
import ua.com.alevel.factory.ObjectFactory;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Optional;

import static ua.com.alevel.util.ConstantModuleApplication.*;

@Log4j2
@RequiredArgsConstructor
public class UserController {

    private String userChoice;
    private final UserService userService;

    public UserController() {
        this(ObjectFactory.getInstance().getImplClass(UserService.class));
    }

    public void startApplication() {
        System.out.println();
        System.out.println("---  WELCOME TO FINANCE MANAGEMENT ---");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            printsNavigationMenuToRegistrationOrLogin();
            while ((userChoice = reader.readLine()) != null) {
                switch (userChoice) {
                    case USER_CHOOSE_REGISTRATION -> userRegistration(reader);
                    case USER_CHOOSE_LOGIN -> login(reader);
                    case USER_CHOOSE_EXIT_APPLICATION -> System.exit(CORRECT_EXIT_PROGRAM);
                }
            }
        } catch (IOException inputOutputError) {
            log.error("User input mistake " + inputOutputError.getMessage());
        }
    }

    private static void printsNavigationMenuToRegistrationOrLogin() {
        System.out.println();
        System.out.println("If you want to REGISTER in application 'FINANCE MANAGEMENT' --------------------- PRESS 1");
        System.out.println("If you want to LOG IN in application 'FINANCE MANAGEMENT' ----------------------- PRESS 2");
        System.out.println("If you want to EXIT the application 'FINANCE MANAGEMENT' ------------------------ PRESS 0");
        System.out.println();
    }

    private void userRegistration(BufferedReader reader) {
        System.out.println("UserController.register");
        try {
            System.out.println("Please, enter your first name");
            String userFirstName = reader.readLine();
            System.out.println("Please, enter your last name");
            String userLastName = reader.readLine();
            System.out.println("Please, enter your email");
            String userEmail = reader.readLine();
            System.out.println("Please, enter your phone number");
            String userPhoneNumber = reader.readLine();
            System.out.println("Please, enter your password");
            String password = reader.readLine();
            userService.register(userFirstName, userLastName, userEmail, userPhoneNumber, password);
            printsNavigationMenuToRegistrationOrLogin();
        } catch (IOException inputOutputError) {
            log.error("User input mistake " + inputOutputError);
        } catch (SQLException dataBaseError) {
            log.error("Problem with database " + dataBaseError);
        }
    }

    private void login(BufferedReader reader) {
        System.out.println("UserController.login");
        try {
            System.out.println("Please, enter your email");
            String userEmail = reader.readLine();
            System.out.println("Please, enter your phone number");
            String userPhoneNumber = reader.readLine();
            System.out.println("Please, enter your password");
            String password = reader.readLine();
            Optional<User> user = userService.login(userEmail, userPhoneNumber, password);
            if (user.isPresent()) {
                System.out.println();
                System.out.printf("You logged in as %s %s  ID %d%n", user.get().getUserFirstName(), user.get().getUserLastName(), user.get().getId());
                startCreateOperation();
            } else {
                log.warn("User with this input data is not register");
                printsNavigationMenuToRegistrationOrLogin();
            }
        } catch (IOException inputOutputError) {
            System.out.println("User input mistake " + inputOutputError.getMessage());
        }
    }

    public void startCreateOperation() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("------ SELECT OPTION YOU NEED -----");
        try {
            mainApplicationMenu();
            while ((userChoice = reader.readLine()) != null) {
                createOperation(userChoice);
                userChoice = reader.readLine();
                if (userChoice.equals(ZERO_POSITION)) {
                    System.out.println(CORRECT_EXIT_PROGRAM);
                }
                createOperation(userChoice);
            }
        } catch (IOException inputUserError) {
            System.out.println("Input user problem: " + inputUserError.getMessage());
        }
    }
    public void mainApplicationMenu() {
        System.out.println();
        System.out.println("To WORK with Bank Account ------------------------------------------------------- PRESS 1");
        System.out.println("To ADD financial operation (make income or expires finance notes) --------------- PRESS 2");
        System.out.println("To EXPORT of account statements in .CSV format for a certain period ------------- PRESS 3");
        System.out.println("To exit the application --------------------------------------------------------- PRESS 0");
        System.out.println();
    }

    private void createOperation(@NotNull String userChoice) {
        switch (userChoice) {
            case USER_CHOOSE_FIRST_POINT -> new BankAccountController().startBankAccountData();
            case USER_CHOOSE_SECOND_POINT -> new OperationJournalController().startCreateOperation();
            case USER_CHOOSE_THIRD_POINT -> new OperationJournalController().exportAccountStatementsCSVFile();
            case USER_CHOOSE_ZERO_POINT -> System.exit(CORRECT_EXIT_PROGRAM);
        }
    }
}
