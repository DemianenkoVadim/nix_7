package ua.com.alevel.dao;

import ua.com.alevel.entity.Operation;

import java.time.Instant;

public interface OperationDao {

    void addOperation(Operation operation);

    void findAllOperationsInGivenPeriodOfTime(Long userBankAccountId, Instant fromDateExport, Instant toDateExport);

}
