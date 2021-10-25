package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "A_Level_Grades", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "grade_value", unique = true, nullable = false)
    private int gradeValue;
}
