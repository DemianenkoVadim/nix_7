package ua.com.alevel.dao.impl;

import jsonparserlibrary.JsonReader;
import jsonparserlibrary.JsonWriter;
import ua.com.alevel.dao.FilmDao;
import ua.com.alevel.entity.Film;


import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.util.Constant.THERE_IS_NO_ID;
import static ua.com.alevel.util.DynamicArray.changeSizeOfArray;
import static ua.com.alevel.util.DynamicArray.getNextIndexOfArray;


public class FilmDaoImpl implements FilmDao {

    private List<Film> films;
    private final String FILE_NAME_JSON = "Films.json";

    @Override
    public void create(Film cinema) {
        films = JsonReader.readSourceInformationFile(FILE_NAME_JSON, cinema);
        if (films == null) {
            films = new ArrayList<>();
        }
        films.add(cinema);
        JsonWriter.writeSourceInformationFile(films, FILE_NAME_JSON);
    }

    @Override
    public void update(Film cinema, int id) {
        films = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Film());
        if (films != null) {
            if (films.stream().anyMatch(movie -> movie.getId() == id)) {
                Film inDbFilm = films.stream().filter(movie -> movie.getId() == id).findFirst().get();
                int filmIndex = films.indexOf(inDbFilm);
                films.set(filmIndex, cinema);
                JsonWriter.writeSourceInformationFile(films, FILE_NAME_JSON);
            }
        }
    }

    @Override
    public void delete(int id) {
        films = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Film());
        if (films != null) {
            films.removeIf(movie -> movie.getId() == id);
            JsonWriter.writeSourceInformationFile(films, FILE_NAME_JSON);
        }
    }

    @Override
    public Film findFilmById(int id) {
        films = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Film());
        if (films != null) {
            if (films.stream().anyMatch(movie -> movie.getId() == id)) {
                return films.stream().filter(movie -> movie.getId() == id).findFirst().get();
            }
        }
        return null;
    }

    @Override
    public List<Film> findAllFilms() {
        Film film = new Film();
        return JsonReader.readSourceInformationFile(FILE_NAME_JSON, film);
    }

    @Override
    public int findIdByTitle(String title) {
        films = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Film());
        if (films != null) {
            if (films.stream().anyMatch(movie -> movie.getTitle().equals(title))) {
                Film film = films.stream().filter(movie -> movie.getTitle().equals(title)).findFirst().get();
                return film.getId();
            }
        }
        return THERE_IS_NO_ID;
    }

    @Override
    public void updateListOfIdActors(int id, Film cinema) {
        int[] film = cinema.getIdActors();
        int filmIndex = getNextIndexOfArray(film);
        if (filmIndex == film.length) {
            film = changeSizeOfArray(film);
        }
        film[filmIndex] = id;
        cinema.setIdActors(film);
        update(cinema, cinema.getId());
    }
}
