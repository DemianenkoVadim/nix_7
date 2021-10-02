package ua.com.alevel.service;

import ua.com.alevel.entity.Actor;

import java.util.List;

public interface ActorService {

    void create(Actor filmActor);

    void delete(int id);

    Actor findActorById(int id);

    List<Actor> findAllActors();

    int findIdByFullName(String fullName);

    void delete(int id, Actor actor);

    void updateArrayOfIdFilms(int idFilms, Actor actor);
}
