package ua.com.alevel.dao;

import ua.com.alevel.entity.Film;

import java.util.List;

public interface FilmDao {

    void create(Film cinema);

    void update(Film cinema, int id);

    void delete(int id);

    Film findFilmById(int id);

    List<Film> findAllFilms();

    int findIdByTitle(String title);

    void updateListOfIdActors(int id, Film cinema);
}
