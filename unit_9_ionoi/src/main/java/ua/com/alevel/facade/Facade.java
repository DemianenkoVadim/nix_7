package ua.com.alevel.facade;

import ua.com.alevel.dto.ActorDto;
import ua.com.alevel.dto.FilmDto;

public interface Facade {

    void create(ActorDto actorDto, FilmDto filmDto);

    void createNewActorToFilm(ActorDto actorDto, FilmDto filmDto);

    void update(int idFilm, FilmDto filmDto);

    void delete(FilmDto filmDto);
}
