package ua.com.alevel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FilmDto {

    private String filmTitle;
    private String filmGenre;
    private int durationOfTheFilm;
    private boolean filmHasOneActor = true;
}
