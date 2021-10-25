package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "A_Level_IT_Courses", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "it_course_name", unique = true, nullable = false, length = 18)
    private String itCourseName;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private Set<Group> groups;

    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Topic> themes;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "IT Course - " + itCourseName;
    }
}
