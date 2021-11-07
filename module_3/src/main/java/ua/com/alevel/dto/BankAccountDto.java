package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {

    private Long id;
    private String bankAccountNumber;
    private Instant bankAccountExpiredEnd;
    private String nameOfTheFinanceInstitution;
    private BigInteger totalAmountValue;

    @Override
    public String toString() {
        return "BankAccount " +
                " Account id " + id + '\'' +
                " Account Number " + bankAccountNumber + '\'' +
                " Account Expired End " + bankAccountExpiredEnd +
                " The Finance Institution " + nameOfTheFinanceInstitution + '\'' +
                " Amount " + totalAmountValue;
    }
}
