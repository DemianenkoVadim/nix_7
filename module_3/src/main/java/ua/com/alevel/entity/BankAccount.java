package ua.com.alevel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "bank_account_data", schema = "module_3")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BankAccount {

    public BankAccount(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccountId;

    @Column(name = "account_number", nullable = false, unique = true, length = 16)
    private String bankAccountNumber;

    @Column(name = "account_expired_end", nullable = false)
    private Instant bankAccountExpiredEnd;

    @Column(name = "name_finance_institution", nullable = false)
    private String nameOfTheFinanceInstitution;

    @Column(name = "total_amount_value", nullable = false)
    private BigInteger totalAmountValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User users;

    @OneToMany(mappedBy = "account")
    private Set<Operation> operations;

    @Override
    public String toString() {
        return "BankAccount " +
                " ACCOUNT ID  - " + bankAccountId + '\'' +
                " ACCOUNT NUMBER -  " + bankAccountNumber + '\'' +
                " ACCOUNT EXPIRED END - " + bankAccountExpiredEnd + '\'' +
                " THE FINANCE INSTITUTION - " + nameOfTheFinanceInstitution + '\'' +
                " AMOUNT - " + totalAmountValue;
    }
}
