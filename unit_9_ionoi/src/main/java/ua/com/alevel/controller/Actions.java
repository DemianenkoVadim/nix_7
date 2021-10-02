package ua.com.alevel.controller;

import ua.com.alevel.dto.ActorDto;
import ua.com.alevel.dto.FilmDto;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.entity.Film;
import ua.com.alevel.facade.Facade;
import ua.com.alevel.facade.FacadeImpl;
import ua.com.alevel.service.impl.ActorServiceImpl;
import ua.com.alevel.service.impl.FilmServiceImpl;

import java.util.Arrays;
import java.util.List;

import static ua.com.alevel.util.Constant.*;
import static ua.com.alevel.util.CustomUtil.*;

public class Actions {

    FilmServiceImpl filmServiceImpl = new FilmServiceImpl();
    ActorServiceImpl actorServiceImpl = new ActorServiceImpl();
    Facade facade = new FacadeImpl(actorServiceImpl, filmServiceImpl);

    public void addsNewFilm() {
        ActorDto actorDto = new ActorDto();
        FilmDto filmDto = new FilmDto();
        printsRequestsOrInformationMessages(ADD_TITLE_OF_THE_FILM);
        String title = getString();
        filmDto.setFilmTitle(title);
        String genre = userChooseGenreOfTheFilm();
        filmDto.setFilmGenre(genre);
        printsRequestsOrInformationMessages(ADD_DURATION_OF_THE_FILM);
        int durationOfTheFilm = getInt();
        filmDto.setDurationOfTheFilm(durationOfTheFilm);
        printsRequestsOrInformationMessages(ADD_FULL_NAME_OF_THE_ACTOR_IN_THE_FILM);
        String fullName = getString();
        actorDto.setFullActorName(fullName);
        facade.create(actorDto, filmDto);
        printsRequestsOrInformationMessages(ADD_MORE_ACTORS);
        printsRequestsOrInformationMessages(YES_0R_NO);
        String answer = getString();
        boolean isMoreActors = isMoreActors(answer);
        while (isMoreActors) {
            addsMoreFilmActors(filmDto);
            printsRequestsOrInformationMessages(ADD_MORE_ACTORS);
            printsRequestsOrInformationMessages(YES_0R_NO);
            answer = getString();
            isMoreActors = isMoreActors(answer);
        }
    }

    public void printsRequestToChooseGenreOfTheFilms() {
        printsRequestsOrInformationMessages(CHOOSE_GENRE_OF_THE_FILM);
        for (String requestFilmGenre : Arrays.asList(CHOOSE_COMEDY, CHOOSE_MUSICAL,
                CHOOSE_ACTION, CHOOSE_WESTERN, CHOOSE_THRILLER, CHOOSE_FANTASY, CHOOSE_HORROR)) {
            printsRequestsOrInformationMessages(requestFilmGenre);
        }
    }

    public String userChooseGenreOfTheFilm() {
        printsRequestToChooseGenreOfTheFilms();
        int userChoose = getInt();
        switch (userChoose) {
            case FIRST_USER_CH00SE:
                return COMEDY;
            case SECOND_USER_CH00SE:
                return MUSICAL;
            case THIRD_USER_CH00SE:
                return ACTION;
            case FOURS_USER_CH00SE:
                return WESTERN;
            case FIVES_USER_CH00SE:
                return THRILLER;
            case SIX_USER_CH00SE:
                return FANTASY;
            case SEVENS_USER_CH00SE:
                return HORROR;
            default:
                printsRequestsOrInformationMessages(THERE_IS_NO_FILM_WITH_THIS_GENRE);
                return userChooseGenreOfTheFilm();
        }
    }

    private void addsMoreFilmActors(FilmDto filmDto) {
        printsRequestsOrInformationMessages(ADD_FULL_NAME_OF_THE_ACTOR_IN_THE_FILM);
        String fullName = getString();
        ActorDto actor = new ActorDto();
        actor.setFullActorName(fullName);
        filmDto.setFilmHasOneActor(false);
        facade.createNewActorToFilm(actor, filmDto);
    }

    public void deleteFilm() {
        printsRequestsOrInformationMessages(ADD_TITLE_OF_THE_FILM);
        String title = getString();
        FilmDto filmDto = new FilmDto();
        filmDto.setFilmTitle(title);
        facade.delete(filmDto);
    }

    public void showsListOfAllFilms() {
        printsRequestsOrInformationMessages(ALL_FILMS);
        List<Film> films = filmServiceImpl.findAllFilms();
        for (Film film : films) {
            printsFilms(film);
        }
    }

    public void showsListOfAllActors() {
        printsRequestsOrInformationMessages(ALL_ACTORS);
        List<Actor> actors = actorServiceImpl.findAllActors();
        for (Actor actor : actors) {
            printsActors(actor);
        }
    }

    public void changeFilmInformation() {
        printsRequestsOrInformationMessages(TITLE_FILM_TO_CHANGE);
        String title = getString();
        int id = filmServiceImpl.findIdByTitle(title);
        if (id == THERE_IS_NO_ID) {
            printsRequestsOrInformationMessages(THERE_IS_NO_FILM_WITH_THIS_TITLE);
        } else {
            printsRequestsOrInformationMessages(NEW_FILM_TITLE);
            String newTitle = getString();
            String genre = userChooseGenreOfTheFilm();
            printsRequestsOrInformationMessages(NEW_FILM_DURATION);
            int durationFilm = getInt();
            FilmDto filmDto = new FilmDto();
            filmDto.setFilmTitle(newTitle);
            filmDto.setFilmGenre(genre);
            filmDto.setDurationOfTheFilm(durationFilm);
            facade.update(id, filmDto);
        }
    }

    public void findFilm() {
        printsRequestsOrInformationMessages(ADD_TITLE_OF_THE_FILM);
        String titleOfTheFilm = getString();
        int idFilm = filmServiceImpl.findIdByTitle(titleOfTheFilm);
        if (idFilm == THERE_IS_NO_ID) {
            printsRequestsOrInformationMessages(THERE_IS_NO_FILM_WITH_THIS_TITLE);
        } else
            printsFilms(filmServiceImpl.findFilmById(idFilm));
    }

    public void findFilmGenre() {
        boolean findFilmGenre = false;
        printsRequestsOrInformationMessages(ADD_FILM_GENRE);
        String genre = getString();
        List<Film> films = filmServiceImpl.findAllFilms();
        for (Film film : films) {
            if (film.getGenre().equals(genre)) {
                printsFilms(film);
                findFilmGenre = true;
            }
        }
        if (!findFilmGenre) {
            printsRequestsOrInformationMessages(THERE_IS_NO_FILM_WITH_THIS_TITLE);
        }
    }

    public void findsFilmActors() {
        printsRequestsOrInformationMessages(ADD_FULL_NAME_OF_THE_ACTOR_IN_THE_FILM);
        String fullName = getString();
        int id = actorServiceImpl.findIdByFullName(fullName);
        if (id == THERE_IS_NO_ID) {
            printsRequestsOrInformationMessages(THERE_IS_NO_ACTOR_WITH_THIS_NAME);
        } else
            printsActors(actorServiceImpl.findActorById(id));
    }

    private void printsFilms(Film film) {
        int[] idActors = film.getIdActors();
        printsRequestsOrInformationMessages(film.toString());
        for (int id : idActors) {
            if (id == THERE_IS_NO_ID) continue;
            Actor actor = actorServiceImpl.findActorById(id);
            if (actor != null) {
                printsRequestsOrInformationMessages(ACTOR_FULL_NAME + actor.getFullName());
            }
        }
        printsRequestsOrInformationMessages(EMPTY_STRING);
    }

    private void printsActors(Actor actor) {
        printsRequestsOrInformationMessages(actor.toString());
        int[] idFilms = actor.getIdFilms();
        for (int id : idFilms) {
            if (id == THERE_IS_NO_ID) continue;
            Film film = filmServiceImpl.findFilmById(id);
            if (film != null) {
                printsRequestsOrInformationMessages(FILM_TITLE + film.getTitle());
            }
        }
        printsRequestsOrInformationMessages(EMPTY_STRING);
    }

    private boolean isMoreActors(String answer) {
        if (answer.equals(UPPERCASE_YES) || answer.equals(LOWERCASE_YES)) {
            return true;
        } else if (answer.equals(UPPERCASE_NO) || answer.equals(LOWERCASE_NO)) {
            return false;
        } else {
            printsRequestsOrInformationMessages(YES_0R_NO);
            String newAnswer = getString();
            return isMoreActors(newAnswer);
        }
    }
}
