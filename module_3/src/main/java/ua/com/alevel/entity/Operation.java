package ua.com.alevel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "journal_operation", schema = "module_3")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @Column(name = "operation_time", nullable = false)
    private Instant operationTime;

    @Column(name = "value", scale = 2, precision = 10)
    private BigInteger incomeValue;

    @Column(name = "short_operation_description", nullable = false, length = 64)
    private String operationDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id", nullable = false)
    @ToString.Exclude
    private BankAccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;
}

