package ua.com.alevel.service;

import ua.com.alevel.entity.Film;

import java.util.List;

public interface FilmService {

    void create(Film cinema);

    void update(Film cinema, int id);

    void delete(int id);

    Film findFilmById(int id);

    List<Film> findAllFilms();

    int findIdByTitle(String title);

    void updateArrayOfIdActors(int id, Film cinema);
}
