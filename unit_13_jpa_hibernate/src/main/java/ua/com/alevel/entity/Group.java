package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "A_Level_IT_Groups", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "it_group_name", unique = true, nullable = false, length = 18)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course courses;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private Set<Student> students;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @Override
    public String toString() {
        return "group name - " + groupName + " " +
                 courses  + "\n";
    }
}
