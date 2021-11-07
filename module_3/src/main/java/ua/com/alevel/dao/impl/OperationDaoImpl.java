package ua.com.alevel.dao.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import ua.com.alevel.controller.UserController;
import ua.com.alevel.dao.OperationDao;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.util.DBConnection;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

import static ua.com.alevel.util.ConstantModuleApplication.COMMA;
import static ua.com.alevel.util.ConstantModuleApplication.FIRST_COLUMN;
import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class OperationDaoImpl implements OperationDao {

    @Override
    public void addOperation(Operation operation) {
        saveInputInformationSession(operation);
    }

    @SneakyThrows
    @Override
    public void findAllOperationsInGivenPeriodOfTime(Long userBankAccountId, Instant fromDateExport, Instant toDateExport) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select  * from journal_operation jo" +
                     " where jo.bank_account_id = ? and (jo.operation_time >=? and jo.operation_time <= ?)")) {
            preparedStatement.setLong(1, userBankAccountId);
            preparedStatement.setTimestamp(2, Timestamp.from(fromDateExport));
            preparedStatement.setTimestamp(3, Timestamp.from(toDateExport));
            final ResultSet resultSet = preparedStatement.executeQuery();
            createCSVFile(resultSet);
        } catch (Exception e) {
            log.error("Problems with creating csv file" + e.getMessage());
        }
    }

    @SneakyThrows
    private void createCSVFile(ResultSet resultSet) {
        try {
            FileWriter fileWriter = new FileWriter("./User_Operations_In_Given_Period_Of_Time.csv");
            log.info("Starting creating a .csv file named - 'User_Operations_In_Given_Period_Of_Time.csv'");
            int numbersOfColumns = createsHeadersInCSVFile(resultSet, fileWriter);
            while (resultSet.next()) {
                for (int column = FIRST_COLUMN; column <= numbersOfColumns; column++) {
                    fileWriter.append(resultSet.getString(column));
                    if (column < numbersOfColumns) fileWriter.append(COMMA);
                }
                fileWriter.append('\n');
            }
            fileWriter.flush();
            fileWriter.close();
            log.info("CSV file is created successfully");
            new UserController().mainApplicationMenu();
        } catch (Exception e) {
            log.error("Problems with creating csv file" + e.getMessage());
        }
    }

    @SneakyThrows
    private int createsHeadersInCSVFile(ResultSet resultSet, FileWriter fileWriter) {
        int numbersOfColumns = resultSet.getMetaData().getColumnCount();
        for (int objectsColumns = FIRST_COLUMN; objectsColumns <= numbersOfColumns; objectsColumns++) {
            fileWriter.append(resultSet.getMetaData().getColumnLabel(objectsColumns));
            if (objectsColumns < numbersOfColumns) fileWriter.append(COMMA);
            else fileWriter.append('\n');
        }
        return numbersOfColumns;
    }
}
