package ua.com.alevel.dao.impl;

import jsonparserlibrary.JsonReader;
import jsonparserlibrary.JsonWriter;
import ua.com.alevel.dao.ActorDao;
import ua.com.alevel.entity.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.com.alevel.util.Constant.*;
import static ua.com.alevel.util.DynamicArray.changeSizeOfArray;
import static ua.com.alevel.util.DynamicArray.getNextIndexOfArray;

public class ActorDaoImpl implements ActorDao {

    private List<Actor> filmActor;
    private final String FILE_NAME_JSON = "Actors.json";

    @Override
    public void create(Actor filmActor) {
        this.filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, filmActor);
        if (this.filmActor == null) {
            this.filmActor = new ArrayList<>();
        }
        this.filmActor.add(filmActor);
        JsonWriter.writeSourceInformationFile(this.filmActor.toArray(), FILE_NAME_JSON);
    }

    @Override
    public void update(Actor filmActor) {
        this.filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, filmActor);
        if (this.filmActor != null) {
            if (this.filmActor.stream().anyMatch(player -> player.getId() == filmActor.getId())) {
                Actor idBaseActor = this.filmActor.stream().filter(player -> player.getId() == filmActor.getId()).findFirst().get();
                int actorIndex = this.filmActor.indexOf(idBaseActor);
                this.filmActor.set(actorIndex, filmActor);
                JsonWriter.writeSourceInformationFile(this.filmActor, FILE_NAME_JSON);
            }
        }
    }

    @Override
    public void delete(int actorId) {
        filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Actor());
        if (filmActor != null) {
            filmActor.removeIf(author -> author.getId() == actorId);
            JsonWriter.writeSourceInformationFile(filmActor, FILE_NAME_JSON);
        }
    }

    @Override
    public Actor findActorById(int actorId) {
        filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Actor());
        if (filmActor != null) {
            if (filmActor.stream().anyMatch(actor -> actor.getId() == actorId)) {
                return filmActor.stream().filter(actor -> actor.getId() == actorId).findFirst().get();
            } else return null;
        } else return null;
    }

    @Override
    public List<Actor> findAllActor() {
        Actor actor = new Actor();
        return filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, actor);
    }

    @Override
    public int findIdByFullName(String fullName) {
        filmActor = JsonReader.readSourceInformationFile(FILE_NAME_JSON, new Actor());
        if (filmActor != null) {
            if (filmActor.stream().anyMatch(actor -> actor.getFullName().equals(fullName))) {
                Actor actor = filmActor.stream().filter(player -> player.getFullName().equals(fullName)).findFirst().get();
                return actor.getId();
            } else return THERE_IS_NO_ID;
        } else return THERE_IS_NO_ID;
    }

    @Override
    public void delete(int idFilm, Actor filmActor) {
        int[] idFilms = filmActor.getIdFilms();
        for (int movie = FIRST_INDEX; movie < idFilms.length; movie++) {
            if (idFilms[movie] == idFilm) {
                idFilms[movie] = FIRST_INDEX;
                break;
            }
        }
        idFilms = getNotNullIdFilms(idFilms);
        if (idFilms.length == ONE_FILM)
            filmActor.setHasOneFilm(true);
        update(filmActor);
    }

    @Override
    public void updateListOfIdFilms(int filmId, Actor actorInDataBase) {
        int[] array = actorInDataBase.getIdFilms();
        int index = getNextIndexOfArray(array);
        if (index == array.length) {
            array = changeSizeOfArray(array);
        }
        array[index] = filmId;
        if (array.length > ONE_FILM) {
            actorInDataBase.setHasOneFilm(false);
        }
        actorInDataBase.setIdFilms(array);
        update(actorInDataBase);
    }

    private int[] getNotNullIdFilms(int[] idFilms) {
        return Arrays.stream(idFilms).filter(id -> id != THERE_IS_NO_ID).toArray();
    }
}
