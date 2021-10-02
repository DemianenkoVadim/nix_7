package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Film {

    private int id;
    private String title;
    private String genre;
    private int durationOfMovie;
    private int[] idActors;
    private boolean hasOneActor = true;

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", durationMovie=" + durationOfMovie +
                ", Actors: ";
    }
}
