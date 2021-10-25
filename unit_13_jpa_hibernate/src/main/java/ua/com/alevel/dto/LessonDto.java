package ua.com.alevel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class LessonDto {

    private Integer lessonId;
    private String topicLesson;
    private String groupName;
    private String lector;
    private Instant startLessonDataTime;
    private Instant endLessonDataTime;

    @Override
    public String toString() {
        return "Next lesson - " +
                " TOPIC OR THE LESSON - " + topicLesson + '\'' +
                " GROUP - " + groupName + '\'' +
                " LECTOR - " + lector + '\'' +
                " START LESSON DATA TIME - " + startLessonDataTime +
                " END LESSON DATA TIME - " + endLessonDataTime;
    }
}