package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "A_Level_Students", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_first_name", nullable = false)
    private String firstName;

    @Column(name = "student_last_name", nullable = false)
    private String lastName;

    @Column(name = "student_email", unique = true, nullable = false)
    private String studentEmail;

    @Column(name = "student_telephone", unique = true, nullable = false)
    private String studentTelephone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group groups;

    @Override
    public String toString() {
        return "ID - " + id + " First name - " + firstName +
                " Last Name - " + lastName +
                " Student`s Email - " + studentEmail +
                " Student`s Telephone - " + studentTelephone + "Group " + groups + "\n";
    }
}
