package ua.com.alevel.service.impl;

import ua.com.alevel.dao.FilmDao;
import ua.com.alevel.dao.impl.FilmDaoImpl;
import ua.com.alevel.entity.Film;
import ua.com.alevel.service.FilmService;

import java.util.List;

import static ua.com.alevel.util.CustomUtil.checksFilmTitleInput;
import static ua.com.alevel.util.CustomUtil.checksRightFilmDuration;

public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao = new FilmDaoImpl();

    public void create(Film cinema) {
        if (checksFilmTitleInput(cinema.getTitle())) {
            if (checksRightFilmDuration(cinema.getDurationOfMovie())) {
                filmDao.create(cinema);
            }
        }
    }

    public void update(Film cinema, int id) {
        if (checksFilmTitleInput(cinema.getTitle())) {
            if (checksRightFilmDuration(cinema.getDurationOfMovie())) {
                filmDao.update(cinema, id);
            }
        }
    }

    public void delete(int id) {
        filmDao.delete(id);
    }

    public Film findFilmById(int id) {
        return filmDao.findFilmById(id);
    }

    public List<Film> findAllFilms() {
        return filmDao.findAllFilms();
    }

    public int findIdByTitle(String title) {
        return filmDao.findIdByTitle(title);
    }

    public void updateArrayOfIdActors(int id, Film cinema) {
        filmDao.updateListOfIdActors(id, cinema);
    }
}
