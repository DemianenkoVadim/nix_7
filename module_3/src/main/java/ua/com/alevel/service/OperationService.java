package ua.com.alevel.service;

import ua.com.alevel.entity.Operation;

import java.time.Instant;

public interface OperationService {

    void addOperation(Operation operation);

    void findAllOperationInGivenPeriodOfTime(Long userBankAccountId, Instant fromDateExport, Instant toDateExport);
}
