package ua.com.alevel.entity;

import lombok.*;
import ua.com.alevel.dto.CategoryType;

import javax.persistence.*;

@Entity
@Table(name = "type_category", schema = "module_3")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    public Category(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category", nullable = false, length = 64)
    private String category;

    @Column(name = "category_type", nullable = false, length = 24)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
}

