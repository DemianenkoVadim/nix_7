package ua.com.alevel.facade;

import ua.com.alevel.dto.ActorDto;
import ua.com.alevel.dto.FilmDto;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Film;
import ua.com.alevel.service.impl.ActorServiceImpl;
import ua.com.alevel.service.impl.FilmServiceImpl;
import ua.com.alevel.util.Entity;

import java.util.List;
import java.util.Random;

import static ua.com.alevel.util.Constant.THERE_IS_NO_ID;

public class FacadeImpl implements Facade {

    private final ActorServiceImpl actorServiceImpl;
    private final FilmServiceImpl filmServiceImpl;

    public FacadeImpl(ActorServiceImpl actorServiceImpl, FilmServiceImpl filmServiceImpl) {
        this.actorServiceImpl = actorServiceImpl;
        this.filmServiceImpl = filmServiceImpl;
    }

    @Override
    public void create(ActorDto actorDto, FilmDto filmDto) {
        Actor actor = new Actor();
        actor.setFullName(actorDto.getFullActorName());
        Film film = new Film();
        film.setTitle(filmDto.getFilmTitle());
        int idFilm = generateId(Entity.FILM);
        film.setId(idFilm);
        film.setGenre(filmDto.getFilmGenre());
        film.setDurationOfMovie(filmDto.getDurationOfTheFilm());
        film.setHasOneActor(filmDto.isFilmHasOneActor());
        actor.setIdFilms(new int[]{idFilm});
        int idActor = actorServiceImpl.findIdByFullName(actor.getFullName());
        if (idActor == THERE_IS_NO_ID) {
            idActor = generateId(Entity.ACTOR);
            actor.setId(idActor);
            actorServiceImpl.create(actor);
        } else {
            actor = actorServiceImpl.findActorById(idActor);
            actorServiceImpl.updateArrayOfIdFilms(idFilm, actor);
        }
        film.setIdActors(new int[]{idActor});
        filmServiceImpl.create(film);
    }

    @Override
    public void createNewActorToFilm(ActorDto actorDto, FilmDto filmDto) {
        Actor actor = new Actor();
        actor.setFullName(actorDto.getFullActorName());
        int idFilm = filmServiceImpl.findIdByTitle(filmDto.getFilmTitle());
        Film film = filmServiceImpl.findFilmById(idFilm);
        film.setHasOneActor(filmDto.isFilmHasOneActor());
        int idActor = actorServiceImpl.findIdByFullName(actor.getFullName());
        if (idActor == THERE_IS_NO_ID) {
            idActor = generateId(Entity.ACTOR);
            actor.setId(idActor);
            actor.setIdFilms(new int[]{idFilm});
            actorServiceImpl.create(actor);
        } else {
            actor = actorServiceImpl.findActorById(idActor);
            actorServiceImpl.updateArrayOfIdFilms(idFilm, actor);
        }
        filmServiceImpl.updateArrayOfIdActors(idActor, film);
    }

    @Override
    public void update(int idFilm, FilmDto filmDto) {
        Film film = filmServiceImpl.findFilmById(idFilm);
        film.setTitle(filmDto.getFilmTitle());
        film.setGenre(filmDto.getFilmGenre());
        film.setDurationOfMovie(filmDto.getDurationOfTheFilm());
        filmServiceImpl.update(film, idFilm);
    }

    @Override
    public void delete(FilmDto filmDto) {
        int idFilm = filmServiceImpl.findIdByTitle(filmDto.getFilmTitle());
        Film film = filmServiceImpl.findFilmById(idFilm);
        int[] idActors = film.getIdActors();
        filmServiceImpl.delete(idFilm);
        for (int id : idActors) {
            if (id != THERE_IS_NO_ID) {
                Actor actor = actorServiceImpl.findActorById(id);
                if (actor.isHasOneFilm()) {
                    actorServiceImpl.delete(id);
                } else {
                    actorServiceImpl.delete(idFilm, actor);
                }
            }
        }
    }

    private int generateId(Entity entity) {
        int id = new Random().nextInt();
        switch (entity) {
            case FILM:
                List<Film> films = filmServiceImpl.findAllFilms();
                if (films == null) return id;
                for (Film film : films) {
                    if (film.getId() == id) {
                        return generateId(Entity.FILM);
                    }
                }
                break;
            case ACTOR:
                List<Actor> actor = actorServiceImpl.findAllActors();
                if (actor == null) return id;
                for (Actor authors : actor) {
                    if (authors.getId() == id) {
                        return generateId(Entity.ACTOR);
                    }
                }
                break;
        }
        return id;
    }
}
