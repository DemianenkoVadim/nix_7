package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "A_Level_IT_Topics", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "it_topic_name", nullable = false, unique = true, length = 25)
    private String topicName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course courses;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    @Override
    public String toString() {
        return "Topic Name - " + topicName;
    }
}
