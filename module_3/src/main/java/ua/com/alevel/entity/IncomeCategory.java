package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "income_category", schema = "module_3")
@Getter
@Setter
@NoArgsConstructor
public class IncomeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeCategoryId;

    @Column(name = "income_category", nullable = false, length = 64)
    private String operationDescription;
}
