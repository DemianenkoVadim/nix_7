package ua.com.alevel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Actor {

    private int id;
    private String fullName;
    private int[] idFilms;
    private boolean hasOneFilm = true;

    @Override
    public String toString() {
        return "Actor{" +
                " fullName='" + fullName + '\'' +
                ", hasOneFilm=" + hasOneFilm +
                '}';
    }
}
