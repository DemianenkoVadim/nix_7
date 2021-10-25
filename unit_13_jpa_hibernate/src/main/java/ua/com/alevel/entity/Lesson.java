package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "A_Level_Lessons", schema = "unit_13_hibernate")
@Getter
@Setter
@NoArgsConstructor
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_data_time")
    private Instant startDateTime;

    @Column(name = "end_data_time")
    private Instant endDataTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group groups;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topics;

    @Override
    public String toString() {
        return "Lesson start Date Time - " + startDateTime +
                ", Lesson end Data Time - " + endDataTime;
    }
}
