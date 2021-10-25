package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "A_Level_Lecturers", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Lecturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lecture_first_name")
    private String firstName;

    @Column(name = "lecture_last_name")
    private String lastName;

    @Column(name = "lecture_email", unique = true)
    private String lectureEmail;

    @Column(name = "lecture_telephone", unique = true, length = 13)
    private String lectureTelephone;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<Topic> topics;

    @Override
    public String toString() {
        return "First Name Lecture - " + firstName + " " +
                ", Last Name Lecture - " + lastName + " " +
                ", Lecture Email - " + lectureEmail + " " +
                ", Lecture Telephone - " + lectureTelephone;
    }
}
