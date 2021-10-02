package ua.com.alevel.dao;

import ua.com.alevel.entity.Actor;

import java.util.List;

public interface ActorDao {

    void create(Actor filmActor);

    void update(Actor filmActor);

    void delete(int actorId);

    Actor findActorById(int actorId);

    List<Actor> findAllActor();

    int findIdByFullName(String fullName);

    void delete(int id, Actor filmActor);

    void updateListOfIdFilms(int filmId, Actor actorInDataBase);
}
